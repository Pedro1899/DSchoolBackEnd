package com.Turq.DigitalSchool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class globalExceptionHandler {

	// handle specific exceptions 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException
	(ResourceNotFoundException exception, WebRequest request ){
		
	ErrorDetail errorDetails = new ErrorDetail(exception.getMessage(), request.getDescription(false), 404);
		return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	// global exceptions
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException
	(Exception exception, WebRequest request ){
		
	ErrorDetail errorDetails = new ErrorDetail(exception.getMessage(), request.getDescription(false), 404);
		return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
