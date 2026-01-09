package com.br.UniVagas.exception;


public class AlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(String attribute) {
		super(String.format("This %s already exists!", attribute));
	}
	
	

}
