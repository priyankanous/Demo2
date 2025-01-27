//
// Copyright (c) 2016 Intel Corporation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package ssntp

import (
	"encoding/gob"
	"github.com/docker/distribution/uuid"
	"net"
	"time"
)

func setReadTimeout(conn net.Conn) {
	conn.SetReadDeadline(time.Now().Add(readTimeout * time.Second))
}

func clearReadTimeout(conn net.Conn) {
	conn.SetReadDeadline(time.Time{})
}

func setWriteTimeout(conn net.Conn) {
	conn.SetWriteDeadline(time.Now().Add(readTimeout * time.Second))
}

func clearWriteTimeout(conn net.Conn) {
	conn.SetWriteDeadline(time.Time{})
}

type session struct {
	src      uuid.UUID
	dest     uuid.UUID
	srcRole  uint32
	destRole uint32
	conn     net.Conn

	encoder *gob.Encoder
	decoder *gob.Decoder
}

/*
 * session methods
 */
func newSession(src *uuid.UUID, srcRole uint32, destRole uint32, netConn net.Conn) *session {
	var session session

	if src != nil {
		copy(session.src[:], src[:16])
	}

	session.srcRole = srcRole
	session.destRole = destRole

	session.conn = netConn
	session.encoder = gob.NewEncoder(netConn)
	session.decoder = gob.NewDecoder(netConn)

	return &session
}

func (session *session) setDest(uuid []byte) {
	copy(session.dest[:], uuid[:16])
}

func (session *session) connectedFrame(serverRole uint32) (f *ConnectFrame) {
	f = &ConnectFrame{
		Major:       major,
		Minor:       minor,
		Type:        STATUS,
		Operand:     byte(CONNECTED),
		Role:        serverRole,
		Source:      session.src[:],
		Destination: session.dest[:],
	}

	return f
}

func (session *session) connectFrame() (f *ConnectFrame) {
	f = &ConnectFrame{
		Major:       major,
		Minor:       minor,
		Type:        COMMAND,
		Operand:     byte(CONNECT),
		Role:        session.srcRole,
		Source:      session.src[:],
		Destination: session.dest[:],
	}

	return
}

func (session *session) commandFrame(cmd Command, payload []byte, trace *TraceConfig) (f *Frame) {
	f = &Frame{
		Major:         major,
		Minor:         minor,
		Type:          COMMAND,
		Operand:       byte(cmd),
		PayloadLength: (uint32)(len(payload)),
		Payload:       payload,
	}

	f.setTrace(trace)
	f.addPathNode(session)

	return
}

func (session *session) statusFrame(status Status, payload []byte, trace *TraceConfig) (f *Frame) {
	f = &Frame{
		Major:         major,
		Minor:         minor,
		Type:          STATUS,
		Operand:       byte(status),
		PayloadLength: (uint32)(len(payload)),
		Payload:       payload,
	}

	f.setTrace(trace)
	f.addPathNode(session)

	return
}

func (session *session) eventFrame(event Event, payload []byte, trace *TraceConfig) (f *Frame) {
	f = &Frame{
		Major:         major,
		Minor:         minor,
		Type:          EVENT,
		Operand:       byte(event),
		PayloadLength: (uint32)(len(payload)),
		Payload:       payload,
	}

	f.setTrace(trace)
	f.addPathNode(session)

	return
}

func (session *session) errorFrame(error Error, payload []byte, trace *TraceConfig) (f *Frame) {
	f = &Frame{
		Major:         major,
		Minor:         minor,
		Type:          ERROR,
		Operand:       byte(error),
		PayloadLength: (uint32)(len(payload)),
		Payload:       payload,
	}

	f.setTrace(trace)
	f.addPathNode(session)

	return
}

func (session *session) Write(frame interface{}) (int, error) {
	switch f := frame.(type) {
	case *Frame:
		if f.PathTrace() == false {
			break
		}

		f.Trace.Path[f.Trace.PathLength-1].TxTimestamp = time.Now()
	}

	setWriteTimeout(session.conn)
	err := session.encoder.Encode(frame)
	clearWriteTimeout(session.conn)

	return 0, err
}

func (session *session) Read(frame interface{}) error {
	err := session.decoder.Decode(frame)

	switch f := frame.(type) {
	case *Frame:
		if f.PathTrace() == false {
			break
		}

		node := Node{
			UUID:        session.src[:],
			Role:        session.srcRole,
			RxTimestamp: time.Now(),
		}

		f.Trace.Path = append(f.Trace.Path, node)
		f.Trace.PathLength++
	}

	return err

}
