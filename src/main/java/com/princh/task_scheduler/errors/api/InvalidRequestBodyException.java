package com.princh.task_scheduler.errors.api;

public class InvalidRequestBodyException extends RuntimeException {

	public InvalidRequestBodyException(String message) {
		super(message);
	}
}
