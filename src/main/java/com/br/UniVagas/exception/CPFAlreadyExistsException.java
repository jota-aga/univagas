package com.br.UniVagas.exception;


public class CPFAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CPFAlreadyExistsException() {
		super("This CPF already exists!");
	}
	
	

}
