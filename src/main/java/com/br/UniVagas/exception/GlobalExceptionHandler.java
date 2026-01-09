package com.br.UniVagas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<String> handlerRazaoSocialAlreadyExists(AlreadyExistsException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> handlerIdNotFoundException(IdNotFoundException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
}
