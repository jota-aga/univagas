package com.br.UniVagas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RazaoSocialAlreadyExistsException.class)
	public ResponseEntity<String> handlerRazaoSocialAlreadyExists(RazaoSocialAlreadyExistsException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(CNPJAlreadyExistsException.class)
	public ResponseEntity<String> handlerCNPJAlreadyExistsException(CNPJAlreadyExistsException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(CPFAlreadyExistsException.class)
	public ResponseEntity<String> handlerCPFAlreadyExistsException(CPFAlreadyExistsException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> handlerIdNotFoundException(IdNotFoundException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
}
