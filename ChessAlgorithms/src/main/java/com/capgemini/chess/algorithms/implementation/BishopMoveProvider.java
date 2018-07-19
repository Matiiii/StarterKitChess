package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class BishopMoveProvider implements MoveProvider {

	@Override
	public List<Coordinate> moves(Coordinate from, Board board) {
		List<Coordinate> listOfCorrectMoves = new ArrayList<>();
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, 1, 1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, -1, 1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, -1, -1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, 1, -1));

		while (listOfCorrectMoves.contains(null)) {
			listOfCorrectMoves.remove(null);
		}

		return listOfCorrectMoves;
	}

	private List<Coordinate> correctMovesInOneDirection(Coordinate from, Board board, int diffX, int diffY) {

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

}
