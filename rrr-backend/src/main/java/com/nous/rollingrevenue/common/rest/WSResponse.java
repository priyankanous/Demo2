package com.nous.rollingrevenue.common.rest;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author Nous Infosystems
 */
public class WSResponse<T> implements Serializable {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	private int responseCode;

	private String message;

	private List<Exception> errors;

	private T data;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	private WSResponse() {

	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public void setErrors(List<Exception> errors) {
		this.errors = errors;
	}

	public List<Exception> getErrors() {
		return this.errors;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public LocalDateTime getTimestamp() {
		if (timestamp == null)
			timestamp = LocalDateTime.now();
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public static <T> WSResponse<T> buildWSResponse(HttpStatus httpStatus, String message, List<Exception> errors) {
		WSResponse<T> response = new WSResponse<>();
		response.message = message;
		response.errors = errors;
		response.responseCode = httpStatus.value();
		return response;
	}

	public static <T> WSResponse<T> buildWSResponse(String message, List<Exception> errors) {

		WSResponse<T> response = new WSResponse<>();
		response.message = message;
		response.errors = errors;
		response.responseCode = 200;

		return response;
	}

	public static <T> WSResponse<T> buildWSResponse(HttpStatus httpStatus, String message, T data) {

		WSResponse<T> response = new WSResponse<>();
		response.data = data;
		response.message = message;
		response.responseCode = httpStatus.value();

		return response;
	}

	public static <T> WSResponse<T> buildWSResponse(HttpStatus httpStatus, String message) {
		WSResponse<T> response = new WSResponse<>();
		response.message = message;
		response.responseCode = httpStatus.value();
		return response;
	}

	public static <T> WSResponse<T> buildWSResponse(String message, T data) {
		WSResponse<T> response = new WSResponse<>();
		response.data = data;
		response.message = message;
		response.responseCode = 200;
		return response;
	}

}
