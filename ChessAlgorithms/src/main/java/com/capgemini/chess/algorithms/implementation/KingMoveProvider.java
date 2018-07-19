package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidColorException;

public class KingMoveProvider extends PieceMoves implements MoveProvider {

	@Override
	public List<Coordinate> moves(Coordinate from, Board board) {

		List<Coordinate> listOfCorrectMoves = new ArrayList<>();
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 0, 1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 0, -1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, 1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, -1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 1, 0));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, 0));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 1, -1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, -1));

		try {
			listOfCorrectMoves.add(correctCastlingForKing(from, board, -2, 0));
		} catch (InvalidColorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			listOfCorrectMoves.add(correctCastlingForKing(from, board, 2, 0));
		} catch (InvalidColorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (listOfCorrectMoves.contains(null)) {
			listOfCorrectMoves.remove(null);
		}

		return listOfCorrectMoves;
	}

	private Coordinate correctCastlingForKing(Coordinate from, Board board, int diffX, int diffY)
			throws InvalidColorException {

		Coordinate incrasedCoordinate = new Coordinate(from.getX() + diffX, from.getY() + diffY);

		if (!board.getMoveHistory().isEmpty()) {

			boolean isKingsFirstMove = board.getMoveHistory().stream().noneMatch(x -> x.getFrom().equals(from))
					&& (from.equals(new Coordinate(4, 0)) || (from.equals(new Coordinate(3, 7))));
			if (!isKingsFirstMove) {
				return null;
			}
			Coordinate rook;

			List<Coordinate> coordinateToAttackCheck = new ArrayList<>();

			if (diffX < 0) {
				rook = new Coordinate(0, from.getY());
				coordinateToAttackCheck.add(from);
				coordinateToAttackCheck.add(new Coordinate(from.getX() + diffX, from.getY()));
				coordinateToAttackCheck.add(new Coordinate(from.getX() + diffX + 1, from.getY()));

			} else {
				rook = new Coordinate(7, from.getY());
				coordinateToAttackCheck.add(from);
				coordinateToAttackCheck.add(new Coordinate(from.getX() + diffX, from.getY()));
				coordinateToAttackCheck.add(new Coordinate(from.getX() + diffX - 1, from.getY()));
			}
			if (board.getPieceAt(rook) == null) {
				return null;
			}
			boolean isRookFirstMove = board.getMoveHistory().stream()
					.noneMatch(x -> x.getFrom().equals(rook) || x.getTo().equals(rook));

			MoveValidator moveValidator = new MoveValidator();
			Piece king = board.getPieceAt(from);
			board.setPieceAt(null, from);
			boolean isFreeSpaceForRook = false;
			try {
				if (board.getPieceAt(rook) != null) {
					isFreeSpaceForRook = moveValidator.getCorrectMovesFromPiece(rook, board).stream()
							.anyMatch(x -> x.equals(from));
				}

			} catch (InvalidColorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean isCoordinateUnderAttack = false;

			isCoordinateUnderAttack = moveValidator.isCoordinatesUnderAttack(board, coordinateToAttackCheck);

			board.setPieceAt(king, from);

			if (isKingsFirstMove && isRookFirstMove && isFreeSpaceForRook && !isCoordinateUnderAttack) {
				return incrasedCoordinate;
			}
		}
		return null;
	}

}
