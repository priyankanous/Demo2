package com.nous.rollingrevenue.exception.advice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.nous.rollingrevenue.common.rest.ErrorResponse;
import com.nous.rollingrevenue.exception.RecordNotFoundException;

/**
 * Custom Exception handler advice
 * 
 * @author Nous Infosystems
 *
 */

@RestControllerAdvice
public class RollingRevenueExceptionAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(RollingRevenueExceptionAdvice.class);

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Record Not Found", details);
		LOGGER.warn(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();
//		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//			details.add(error.getDefaultMessage());
//		}
		ex.getBindingResult().getFieldErrors().stream().forEach(error -> details.add(error.getDefaultMessage()));
		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		LOGGER.error(error.toString());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		LOGGER.error(ex.getMessage());
		ErrorResponse error = new ErrorResponse("Internal Server Error", details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MissingPathVariableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<Object> handle(MissingPathVariableException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		LOGGER.error(ex.getMessage());
		ErrorResponse error = new ErrorResponse("Path variable expected", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
