package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class KnightMoveProvider extends PieceMoves implements MoveProvider {

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

}
