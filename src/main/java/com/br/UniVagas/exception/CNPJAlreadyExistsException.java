package com.br.UniVagas.exception;


public class CNPJAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CNPJAlreadyExistsException() {
		super("This CNPJ already exists!");
	}
	
	

}
