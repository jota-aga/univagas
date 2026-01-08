package com.br.UniVagas.exception;


public class IdNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdNotFoundException() {
		super("Id not found!");
	}
	
	

}
