package com.hedlundkaua.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hedlundkaua.course.services.exceptions.DatabaseException;
import com.hedlundkaua.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //isso vai interceptas as exceções para que esse objeto possa executar um ossivel tratamento
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)// to falando que o metodo vai interceptar qualquer exceção do tipo que esta no parametro e vai fazer o tratamento que estiver no metodo.
	public ResponseEntity<StandardError> resourcenotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}	

	@ExceptionHandler(DatabaseException.class)// to falando que o metodo vai interceptar qualquer exceção do tipo que esta no parametro e vai fazer o tratamento que estiver no metodo.
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
