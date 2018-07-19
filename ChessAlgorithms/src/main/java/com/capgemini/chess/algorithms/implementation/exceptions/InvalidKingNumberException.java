package com.capgemini.chess.algorithms.implementation.exceptions;

public class InvalidKingNumberException extends InvalidMoveException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4929816444729294477L;

	public InvalidKingNumberException() {
		super("Fatal Error! Wrong Number Of Kings!");
		// TODO Auto-generated constructor stub
	}

	public InvalidKingNumberException(String message) {
		super("Fatal Error! Wrong Number Of Kings!" + message);
		// TODO Auto-generated constructor stub
	}

}
