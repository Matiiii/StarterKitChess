package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;

public class PawnMoveProvider implements MoveProvider {

	@Override
	public List<Coordinate> moves(Coordinate from, Board board) {
		List<Coordinate> listOfCorrectMoves = new ArrayList<>();

		if (board.getPieceAt(from).getColor().equals(Color.WHITE)) {
			listOfCorrectMoves.add(correctMoveInThisPositionForPawn(from, board, 0, 1));

			if (from.getX() == 2 && correctMoveInThisPositionForPawn(from, board, 0, 1) != null) {
				listOfCorrectMoves.add(correctMoveInThisPositionForPawn(from, board, 0, 2));
			}
			listOfCorrectMoves.add(correctMoveInCrossPositionForPawn(from, board, 1, 1));
			listOfCorrectMoves.add(correctMoveInCrossPositionForPawn(from, board, -1, 1));
			listOfCorrectMoves.add(correctEnPassantForPawn(from, board, 1, 1));
			listOfCorrectMoves.add(correctEnPassantForPawn(from, board, -1, 1));
		} else {

			listOfCorrectMoves.add(correctMoveInThisPositionForPawn(from, board, 0, -1));

			if (from.getY() == 6 && correctMoveInThisPositionForPawn(from, board, 0, -1) != null) {
				listOfCorrectMoves.add(correctMoveInThisPositionForPawn(from, board, 0, -2));
			}
			listOfCorrectMoves.add(correctMoveInCrossPositionForPawn(from, board, -1, -1));
			listOfCorrectMoves.add(correctMoveInCrossPositionForPawn(from, board, 1, -1));
			listOfCorrectMoves.add(correctEnPassantForPawn(from, board, 1, -1));
			listOfCorrectMoves.add(correctEnPassantForPawn(from, board, -1, -1));

		}
		while (listOfCorrectMoves.contains(null)) {
			listOfCorrectMoves.remove(null);
		}

		return listOfCorrectMoves;

	}

	private Coordinate correctEnPassantForPawn(Coordinate from, Board board, int diffX, int diffY) {

		Coordinate incrasedCoordinate = new Coordinate(from.getX() + diffX, from.getY() + diffY);
		if (!board.getMoveHistory().isEmpty()) {
			Move lastMove = board.getMoveHistory().get(board.getMoveHistory().size() - 1);
			Piece lastMovedPiecie = lastMove.getMovedPiece();
			boolean islastMovedDistance2 = Math.abs(lastMove.getFrom().getY() - lastMove.getTo().getY()) == 2;
			boolean isLastMovedPiecePawn = lastMovedPiecie.getType().equals(PieceType.PAWN);
			boolean isCoordinateXCorect = lastMove.getTo().getX() == from.getX() + diffX;
			boolean isCoordinateYCorect = lastMove.getTo().getY() == from.getY();

			if (isLastMovedPiecePawn && islastMovedDistance2 && isCoordinateXCorect && isCoordinateYCorect) {
				return incrasedCoordinate;
			}
		}
		return null;
	}

	private Coordinate correctMoveInThisPositionForPawn(Coordinate from, Board board, int diffX, int diffY) {

		Coordinate incrasedCoordinate = new Coordinate(from.getX() + diffX, from.getY() + diffY);

		if (isCoordinateOnBoard(incrasedCoordinate)) {

			if (board.getPieceAt(incrasedCoordinate) == null) {
				return incrasedCoordinate;
			}

		}
		return null;

	}

	private Coordinate correctMoveInCrossPositionForPawn(Coordinate from, Board board, int diffX, int diffY) {

		Coordinate incrasedCoordinate = new Coordinate(from.getX() + diffX, from.getY() + diffY);

		if ((isCoordinateOnBoard(incrasedCoordinate) && board.getPieceAt(incrasedCoordinate) != null
				&& board.getPieceAt(incrasedCoordinate).getColor() != board.getPieceAt(from).getColor())) {
			return incrasedCoordinate;

		}
		return null;

	}

	private boolean isCoordinateOnBoard(Coordinate coordinate) {

		if (coordinate.getX() > 7 || coordinate.getY() < 0 || coordinate.getX() < 0 || coordinate.getY() > 7) {
			return false;
		}

		return true;
	}

}
