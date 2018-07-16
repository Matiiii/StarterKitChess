package com.capgemini.chess.algorithms.implementation.exceptions;

public class InvalidColorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4921882562195976256L;

	public InvalidColorException() {
		super("You take or capture wrong color! Stupid!");
		// TODO Auto-generated constructor stub
	}

	public InvalidColorException(String message) {
		super("You take or capture wrong color! Stupid!" + message);
		// TODO Auto-generated constructor stub
	}
	
	

}
