package com.princh.task_scheduler.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import com.princh.task_scheduler.errors.api.ExceptionResponse;
import com.princh.task_scheduler.errors.api.InvalidDateFormatException;
import com.princh.task_scheduler.errors.api.InvalidRequestBodyException;
import com.princh.task_scheduler.errors.api.ResourceNotFoundException;

// sets up api exceptions to return DTO with fields of ExceptionResponse
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("NOT_FOUND");
		response.setErrorMessage(ex.getMessage());
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidRequestBodyException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(InvalidRequestBodyException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("INVALID_BODY");
		response.setErrorMessage(ex.getMessage());
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(InvalidDateFormatException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("INVALID_DATE");
		response.setErrorMessage(ex.getMessage());
		response.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
}