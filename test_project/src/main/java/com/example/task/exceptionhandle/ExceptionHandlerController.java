package com.example.task.exceptionhandle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;



public class ExceptionHandlerController {

	@ExceptionHandler(ResourceNotFoundException.class)
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public ExceptionEntity resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ExceptionEntity message = new ExceptionEntity(
	        HttpStatus.NOT_FOUND.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return message;
	  }
	  
	  @ExceptionHandler(Exception.class)
	  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	  public ExceptionEntity globalExceptionHandler(Exception ex, WebRequest request) {
		  ExceptionEntity message = new ExceptionEntity(
	        HttpStatus.INTERNAL_SERVER_ERROR.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return message;
	  }
}
