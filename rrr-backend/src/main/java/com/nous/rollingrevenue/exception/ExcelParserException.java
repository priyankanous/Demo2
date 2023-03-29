package com.nous.rollingrevenue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExcelParserException extends RuntimeException {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public ExcelParserException() {
		super();
	}

	public ExcelParserException(String message) {
		super(message);
	}

	public ExcelParserException(Throwable cause) {
		super(cause);
	}

	public ExcelParserException(String message, Throwable cause) {
		super(message, cause);
	}

}
