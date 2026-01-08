package com.br.UniVagas.exception;


public class RazaoSocialAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RazaoSocialAlreadyExistsException() {
		super("This Razão Social already exists!");
	}
	
	

}
