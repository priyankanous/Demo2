package com.nous.rollingrevenue.exception.advice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.common.rest.ErrorResponse;
import com.nous.rollingrevenue.exception.ExcelParserException;
import com.nous.rollingrevenue.exception.InvalidFileTypeException;
import com.nous.rollingrevenue.exception.RecordNotFoundException;

@RestControllerAdvice
public class RollingRevenueExceptionAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(RollingRevenueExceptionAdvice.class);

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(ErrorConstants.RECORD_NOT_FOUND, details);
		LOGGER.warn(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidFileTypeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<Object> handleInvalidFileTypeException(InvalidFileTypeException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(ErrorConstants.INVALID_FILE_TYPE, details);
		LOGGER.warn(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ExcelParserException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<Object> handleInvalidFileTypeException(ExcelParserException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(ErrorConstants.EXCEL_PARSING_FAIL, details);
		LOGGER.warn(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().stream().forEach(error -> details.add(error.getDefaultMessage()));
		ErrorResponse error = new ErrorResponse(ErrorConstants.VALIDATION_FAIL, details);
		LOGGER.error(error.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		LOGGER.error(ex.getMessage());
		ErrorResponse error = new ErrorResponse(ErrorConstants.INTERNAL_SERVER_ERROR, details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MissingPathVariableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<Object> handle(MissingPathVariableException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		LOGGER.error(ex.getMessage());
		ErrorResponse error = new ErrorResponse(ErrorConstants.MISSING_PATH_VARIABLE, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
