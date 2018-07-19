package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class QeenMoveProvider implements MoveProvider {
	private MoveProvider moveProviderRook = new BishopMoveProvider();
	private MoveProvider moveProviderBishop = new RookMoveProvider();

	@Override
	public List<Coordinate> moves(Coordinate from, Board board) {

		List<Coordinate> listOfCorrectMoves = new ArrayList<>();

		listOfCorrectMoves.addAll(moveProviderBishop.moves(from, board));
		listOfCorrectMoves.addAll(moveProviderRook.moves(from, board));

		while (listOfCorrectMoves.contains(null)) {
			listOfCorrectMoves.remove(null);
		}

		return listOfCorrectMoves;
	}

}
