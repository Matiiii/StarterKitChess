package com.capgemini.chess.algorithms.implementation.exceptions;

import org.omg.CORBA.DynAnyPackage.Invalid;

public class InvalidCoordinateException extends InvalidMoveException {

	private static final long serialVersionUID = -6768364307151019766L;

	public InvalidCoordinateException() {
		super("Coordinate is out of board");
		// TODO Auto-generated constructor stub
	}

	public InvalidCoordinateException(String message) {
		super("Coordinate is out of board:" + message);
		// TODO Auto-generated constructor stub
	}

}
