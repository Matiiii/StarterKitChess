package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidColorException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidCoordinateException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidKingNumberException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

public class MoveValidator {

	private boolean isCoordinateExist(Coordinate coordinate) throws InvalidCoordinateException {

		if (coordinate.getX() > 7 || coordinate.getY() < 0 || coordinate.getX() < 0 || coordinate.getY() > 7) {
			throw new InvalidCoordinateException(coordinate.toString());
		}

		return true;
	}

	private boolean isCoordinateNotEmpty(Coordinate coordinate, Board board) throws InvalidCoordinateException {
		if (board.getPieceAt(coordinate) == null) {
			throw new InvalidCoordinateException();
		}
		return true;

	}

	private boolean isInCoordinateRightColorPiece(Coordinate coordinate, Board board, Color expectedColor)
			throws InvalidColorException {
		if (board.getPieceAt(coordinate).getColor().equals(expectedColor)) {
			return true;
		} else {
			throw new InvalidColorException();
		}

	}

	private void isTryCaptureOwnColor(Coordinate coordinateToCapture, Board board, Color ownColor)
			throws InvalidColorException {
		if (board.getPieceAt(coordinateToCapture) != null
				&& board.getPieceAt(coordinateToCapture).getColor().equals(ownColor)) {
			throw new InvalidColorException();
		}

	}

	private boolean areKingsOnBoard(Board board) throws InvalidKingNumberException {

		int countWhiteKing = 0;
		int countBlackKing = 0;

		for (int coordinateX = 0; coordinateX < 8; coordinateX++) {
			for (int coordinateY = 0; coordinateY < 8; coordinateY++) {
				Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
				if (board.getPieceAt(coordinate) != null && board.getPieceAt(coordinate).getColor().equals(Color.WHITE)
						&& board.getPieceAt(coordinate).getType().equals(PieceType.KING)) {
					countWhiteKing++;
				}
				if (board.getPieceAt(coordinate) != null && board.getPieceAt(coordinate).getType() == (PieceType.KING)
						&& board.getPieceAt(coordinate).getColor().equals(Color.BLACK)) {
					countBlackKing++;
				}
			}
		}

		if (countBlackKing != 1 || countWhiteKing != 1) {
			throw new InvalidKingNumberException();
		}

		return true;

	}

	private List<Coordinate> removeCoordinateIfKingInCheckAfterMove(Coordinate from, Board board,
			List<Coordinate> listOfCorrectMoves) throws InvalidColorException {
		List<Coordinate> listCoordinatesWhenKingIsChecked = new ArrayList<>();
		if (!listOfCorrectMoves.isEmpty()) {
			for (Coordinate coordinate : listOfCorrectMoves) {
				Piece pieceInHand = board.getPieceAt(from);
				Piece pieceInCoordinateTo = null;
				if (board.getPieceAt(coordinate) != null) {
					pieceInCoordinateTo = board.getPieceAt(coordinate);
				}

				board.setPieceAt(null, from);
				board.setPieceAt(pieceInHand, coordinate);

				if (isKingInCheck(board, pieceInHand.getColor())) {
					listCoordinatesWhenKingIsChecked.add(coordinate);
				}
				board.setPieceAt(pieceInHand, from);
				board.setPieceAt(pieceInCoordinateTo, coordinate);

			}
			if (!listCoordinatesWhenKingIsChecked.isEmpty()) {
				listOfCorrectMoves.removeAll(listCoordinatesWhenKingIsChecked);
			}
		}

		return listOfCorrectMoves;
	}

	public boolean isKingInCheck(Board board, Color color) throws InvalidColorException {

		Coordinate kingPosition = board.getKingPosition(color);
		List<Coordinate> enemyPiecesPositions = board.getCoordinateOfEnemyPieces();
		List<Coordinate> enemyAvaliblePositions = new ArrayList<>();

		for (Coordinate coordinate : enemyPiecesPositions) {

			enemyAvaliblePositions.addAll(getCorrectMovesFromPiece(coordinate, board));

		}

		if (enemyAvaliblePositions.contains(kingPosition)) {
			return true;
		}

		return false;
	}

	public boolean isCoordinatesUnderAttack(Board board, List<Coordinate> coordinates) throws InvalidColorException {

		List<Coordinate> enemyPiecesPositions = board.getCoordinateOfEnemyPieces();
		List<Coordinate> enemyAvaliblePositions = new ArrayList<>();

		for (Coordinate coordinate : enemyPiecesPositions) {

			enemyAvaliblePositions.addAll(getCorrectMovesFromPiece(coordinate, board));

		}

		for (Coordinate coordinate : coordinates) {
			if (enemyAvaliblePositions.contains(coordinate)) {
				return true;
			}
		}

		return false;
	}

	public boolean isAnyMoveValid(Board board) throws InvalidColorException {

		List<Coordinate> ownPiecesPositions = board.getCoordinateOfOwnPieces();
		List<Coordinate> ownAvaliblePositions = new ArrayList<>();

		for (Coordinate coordinate : ownPiecesPositions) {

			ownAvaliblePositions.addAll(removeCoordinateIfKingInCheckAfterMove(coordinate, board,
					getCorrectMovesFromPiece(coordinate, board)));

		}

		if (!ownAvaliblePositions.isEmpty()) {
			return true;
		}

		return false;
	}

	public List<Coordinate> getCorrectMovesFromPiece(Coordinate from, Board board) throws InvalidColorException {

		Piece piece = board.getPieceAt(from);
		switch (piece.getType()) {

		case BISHOP:
			return new BishopMoveProvider().moves(from, board);

		case KING:
			return new KingMoveProvider().moves(from, board);

		case KNIGHT:
			return new KnightMoveProvider().moves(from, board);

		case PAWN:
			return new PawnMoveProvider().moves(from, board);

		case QUEEN:
			return new QeenMoveProvider().moves(from, board);

		case ROOK:
			return new RookMoveProvider().moves(from, board);

		default:
			break;

		}
		return null;

	}

	public boolean validateMoveWitchChecks(Coordinate from, Coordinate to, Board board, Color expectedColor)
			throws InvalidColorException, InvalidCoordinateException, InvalidKingNumberException, KingInCheckException {
		isCoordinateExist(from);
		isCoordinateExist(to);
		isCoordinateNotEmpty(from, board);
		isInCoordinateRightColorPiece(from, board, expectedColor);
		isTryCaptureOwnColor(to, board, expectedColor);
		// areKingsOnBoard(board);

		List<Coordinate> avalibleMoves = getCorrectMovesFromPiece(from, board);

		if (!avalibleMoves.contains(to)) {

			throw new InvalidCoordinateException();
		}

		avalibleMoves = removeCoordinateIfKingInCheckAfterMove(from, board, avalibleMoves);

		if (avalibleMoves.contains(to)) {
			return true;
		} else {
			throw new KingInCheckException();
		}
	}

}
