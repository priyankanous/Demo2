package com.nous.rollingrevenue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidFileTypeException extends RuntimeException {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFileTypeException() {
		super();
	}

	public InvalidFileTypeException(String message) {
		super(message);
	}

	public InvalidFileTypeException(Throwable cause) {
		super(cause);
	}

	public InvalidFileTypeException(String message, Throwable cause) {
		super(message, cause);
	}

}
