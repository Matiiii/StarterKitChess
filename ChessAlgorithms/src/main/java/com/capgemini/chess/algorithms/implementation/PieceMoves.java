package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public abstract class PieceMoves {

	protected List<Coordinate> correctMovesInOneDirection(Coordinate from, Board board, int diffX, int diffY) {

		boolean canMoveNextPosition = true;

		Coordinate incrasedCoordinate = new Coordinate(from.getX() + diffX, from.getY() + diffY);
		List<Coordinate> lista = new ArrayList<>();

		while (canMoveNextPosition && board.isCoordinateOnBoard(incrasedCoordinate)) {
			if (board.getPieceAt(incrasedCoordinate) == null) {
				lista.add(incrasedCoordinate);

			} else if (board.getPieceAt(incrasedCoordinate).getColor() != board.getPieceAt(from).getColor()) {
				lista.add(incrasedCoordinate);
				canMoveNextPosition = false;
			} else {
				canMoveNextPosition = false;
			}
			incrasedCoordinate = new Coordinate(incrasedCoordinate.getX() + diffX, incrasedCoordinate.getY() + diffY);
		}

		return lista;
	}

	protected Coordinate correctMoveInThisPosition(Coordinate from, Board board, int diffX, int diffY) {

		Coordinate incrasedCoordinate = new Coordinate(from.getX() + diffX, from.getY() + diffY);

		if (board.isCoordinateOnBoard(incrasedCoordinate)) {

			if (board.getPieceAt(incrasedCoordinate) == null
					|| board.getPieceAt(incrasedCoordinate).getColor() != board.getPieceAt(from).getColor()) {
				return incrasedCoordinate;
			}

		}
		return null;

	}

}
