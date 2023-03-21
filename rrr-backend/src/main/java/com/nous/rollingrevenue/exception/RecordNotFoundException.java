package com.nous.rollingrevenue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nous Infosystems
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException() {
		super();
	}

	public RecordNotFoundException(String message) {
		super(message);
	}

	public RecordNotFoundException(Throwable cause) {
		super(cause);
	}

	public RecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
