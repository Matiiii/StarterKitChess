package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class BishopMoveProvider extends PieceMoves implements MoveProvider {

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

}
