package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class KnightMoveProvider implements MoveProvider {

	@Override
	public List<Coordinate> moves(Coordinate from, Board board) {

		List<Coordinate> listOfCorrectMoves = new ArrayList<>();
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 2, 1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 2, -1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -2, 1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -2, -1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 1, 2));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, 2));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 1, -2));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, -2));

		while (listOfCorrectMoves.contains(null)) {
			listOfCorrectMoves.remove(null);
		}

		return listOfCorrectMoves;
	}

	private Coordinate correctMoveInThisPosition(Coordinate from, Board board, int diffX, int diffY) {

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
