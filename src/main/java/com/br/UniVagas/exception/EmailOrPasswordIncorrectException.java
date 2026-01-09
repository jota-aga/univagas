package com.br.UniVagas.exception;


public class EmailOrPasswordIncorrectException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailOrPasswordIncorrectException() {
		super(String.format("Email or Password is incorrect!"));
	}
	
	

}
