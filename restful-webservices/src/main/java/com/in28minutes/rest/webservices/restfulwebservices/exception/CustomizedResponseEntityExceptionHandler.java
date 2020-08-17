package com.in28minutes.rest.webservices.restfulwebservices.exception;


import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.exception.ExceptionResponse;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler 
extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
	
		ExceptionResponse exResponse = 
				new ExceptionResponse(new Date(),"From CustomizedResponseEntityExceptionHandler"+ex.getMessage(),
				request.getDescription(false));
		
				System.out.println("In handleAllException()");
				return new ResponseEntity(exResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserException(UserNotFoundException ex, WebRequest request) {
	
		ExceptionResponse exResponse = 
				new ExceptionResponse(new Date(),"From CustomizedResponseEntityExceptionHandler"+ex.getMessage(),
				request.getDescription(false));

				System.out.println("In handleUserException()");
				
				return new ResponseEntity(exResponse,HttpStatus.NOT_FOUND);
	}

}
