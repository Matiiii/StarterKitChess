package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.BoardManager;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidColorException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidCoordinateException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidKingNumberException;

public class MoveValidator {

	private boolean isCoordinateExist(Coordinate coordinate) throws InvalidCoordinateException {

		if (coordinate.getX() > 7 || coordinate.getY() < 0 || coordinate.getX() < 0 || coordinate.getY() > 7) {
			throw new InvalidCoordinateException(coordinate.toString());
		}

		return true;
	}

	private boolean isCoordinateOnBoard(Coordinate coordinate) {

		if (coordinate.getX() > 7 || coordinate.getY() < 0 || coordinate.getX() < 0 || coordinate.getY() > 7) {
			return false;
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

	private boolean areKingsOnBoard(Board board) throws InvalidKingNumberException {

		int countWhiteKing = 0;
		int countBlackKing = 0;

		for (int coordinateX = 0; coordinateX < 8; coordinateX++) {
			for (int coordinateY = 0; coordinateY < 8; coordinateY++) {
				Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
				if (board.getPieceAt(coordinate).getType().equals(Piece.WHITE_KING)) {
					countWhiteKing++;
				}
				if (board.getPieceAt(coordinate).getType().equals(Piece.BLACK_KING)) {
					countBlackKing++;
				}
			}
		}

		if (countBlackKing != 1 || countWhiteKing != 1) {
			throw new InvalidKingNumberException();
		}

		return true;

	}

	private Coordinate correctMoveInThisPosition(Coordinate from, Board board, int diffX, int diffY) {

		Coordinate incrasedCoordinate = new Coordinate(from.getX() + diffX, from.getY() + diffY);

		if ((isCoordinateOnBoard(incrasedCoordinate)
				&& (board.getPieceAt(incrasedCoordinate).getColor() != board.getPieceAt(from).getColor())
				|| board.getPieceAt(incrasedCoordinate) == null)) {
			return incrasedCoordinate;

		}
		return null;

	}

	private Coordinate correctMoveInCrossPositionForPawn(Coordinate from, Board board, int diffX, int diffY) {

		Coordinate incrasedCoordinate = new Coordinate(from.getX() + diffX, from.getY() + diffY);

		if ((isCoordinateOnBoard(incrasedCoordinate)
				&& board.getPieceAt(incrasedCoordinate).getColor() != board.getPieceAt(from).getColor())) {
			return incrasedCoordinate;

		}
		return null;

	}

	private List<Coordinate> correctMovesInOneDirection(Coordinate from, Board board, int diffX, int diffY) {

		boolean canMoveNextPosition = true;

		Coordinate incrasedCoordinate = new Coordinate(from.getX() + diffX, from.getY() + diffY);
		List<Coordinate> lista = new ArrayList<>();

		while (canMoveNextPosition && isCoordinateOnBoard(incrasedCoordinate)) {
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

	private List<Coordinate> correctMovesRook(Coordinate from, Board board) {

		List<Coordinate> listOfCorrectMoves = new ArrayList<>();

		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, 1, 0));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, 0, 1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, -1, 0));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, 0, -1));
		return listOfCorrectMoves;

	}

	private List<Coordinate> correctMovesKnight(Coordinate from, Board board) {

		List<Coordinate> listOfCorrectMoves = new ArrayList<>();

		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, 1, 1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, -1, 1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, -1, -1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board, 1, -1));
		return listOfCorrectMoves;

	}

	private List<Coordinate> removeCoordinateIfKingInCheckAfterMove(Coordinate from, Board board,
			List<Coordinate> listOfCorrectMoves) {
		for (Coordinate coordinate : listOfCorrectMoves) {
			Piece pieceInHand = board.getPieceAt(from);
			Piece pieceInCoordinateTo = board.getPieceAt(coordinate);

			board.setPieceAt(null, from);
			board.setPieceAt(pieceInHand, coordinate);

			if (isKingInCheck(board, pieceInHand.getColor())) {
				listOfCorrectMoves.remove(coordinate);
			}
			board.setPieceAt(pieceInHand, from);
			board.setPieceAt(pieceInCoordinateTo, coordinate);

		}
		return listOfCorrectMoves;
	}

	public boolean isKingInCheck(Board board, Color color) {

		Coordinate kingPosition = getKingPosition(board, color);
		List<Coordinate> enemyPiecesPositions = listOfEnemyPieces(board, color);
		List<Coordinate> enemyAvaliblePositions = new ArrayList<>();

		for (Coordinate coordinate : enemyPiecesPositions) {
			
			enemyAvaliblePositions.addAll(getCorrectMovesFromPiece(coordinate, board));
			

		}

		if (enemyAvaliblePositions.contains(kingPosition)) {
			return true;
		}

		return false;
	}

	private Coordinate getKingPosition(Board board, Color color) {

		Coordinate kingPosition = new Coordinate(0, 0);

		for (int coordinateX = 0; coordinateX < 8; coordinateX++) {
			for (int coordinateY = 0; coordinateY < 8; coordinateY++) {
				Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
				if (board.getPieceAt(coordinate).getColor() == color
						&& board.getPieceAt(coordinate).getType() == PieceType.KING) {
					kingPosition = coordinate;
				}
			}
		}
		return kingPosition;
	}

	private List<Coordinate> listOfEnemyPieces(Board board, Color color) {
		List<Coordinate> listPositionsOfEnemyPieces = new ArrayList<>();
		Color enemyColor = Color.BLACK;

		if (color == Color.BLACK) {
			enemyColor = Color.WHITE;
		}

		for (int coordinateX = 0; coordinateX < 8; coordinateX++) {
			for (int coordinateY = 0; coordinateY < 8; coordinateY++) {
				Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
				if (board.getPieceAt(coordinate).getColor() == enemyColor) {
					listPositionsOfEnemyPieces.add(coordinate);
				}
			}
		}

		return listPositionsOfEnemyPieces;
	}

	private List<Coordinate> correctMovesBishop(Coordinate from, Board board) {

		List<Coordinate> listOfCorrectMoves = new ArrayList<>();
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 2, 1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 2, -1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -2, 1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -2, -1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 1, 2));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, 2));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 1, -2));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, -2));

		return listOfCorrectMoves;
	}

	private List<Coordinate> correctMovesKing(Coordinate from, Board board) {

		List<Coordinate> listOfCorrectMoves = new ArrayList<>();
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 0, 1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 0, -1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, 1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, -1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 1, 0));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, 0));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 1, -1));
		listOfCorrectMoves.add(correctMoveInThisPosition(from, board, -1, -1));

		return listOfCorrectMoves;
	}

	private List<Coordinate> correctMovesQueen(Coordinate from, Board board) {

		List<Coordinate> listOfCorrectMoves = new ArrayList<>();

		listOfCorrectMoves.addAll(correctMovesRook(from, board));
		listOfCorrectMoves.addAll(correctMovesKnight(from, board));

		return listOfCorrectMoves;

	}

	private List<Coordinate> correctMovesPawn(Coordinate from, Board board) {
		List<Coordinate> listOfCorrectMoves = new ArrayList<>();

		if (board.getPieceAt(from).getColor().equals(Color.WHITE)) {
			listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 0, 1));

			if (from.getX() == 2 && correctMoveInThisPosition(from, board, 0, 1) != null) {
				listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 0, 2));
			}
			listOfCorrectMoves.add(correctMoveInCrossPositionForPawn(from, board, 1, 1));
			listOfCorrectMoves.add(correctMoveInCrossPositionForPawn(from, board, -1, 1));
			listOfCorrectMoves.add(correctEnPassantForPawn(from, board, 1, 1));
			listOfCorrectMoves.add(correctEnPassantForPawn(from, board, -1, 1));
		} else {

			listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 0, -1));

			if (from.getX() == 2 && correctMoveInThisPosition(from, board, 0, -1) != null) {
				listOfCorrectMoves.add(correctMoveInThisPosition(from, board, 0, -2));
			}
			listOfCorrectMoves.add(correctMoveInCrossPositionForPawn(from, board, -1, -1));
			listOfCorrectMoves.add(correctMoveInCrossPositionForPawn(from, board, 1, -1));
			listOfCorrectMoves.add(correctEnPassantForPawn(from, board, 1, -1));
			listOfCorrectMoves.add(correctEnPassantForPawn(from, board, -1, -1));

		}

		return listOfCorrectMoves;
	}

	private Coordinate correctEnPassantForPawn(Coordinate from, Board board, int diffX, int diffY) {

		Coordinate incrasedCoordinate = new Coordinate(from.getX() + diffX, from.getY() + diffY);
		Move lastMove = board.getMoveHistory().get(board.getMoveHistory().size() - 1);
		Piece lastMovedPiecie = lastMove.getMovedPiece();
		int lastMovedDistance = Math.abs(lastMove.getFrom().getY() - lastMove.getTo().getY());
		boolean isLastMovedPiecePawn = lastMovedPiecie.getType().equals(PieceType.PAWN);
		boolean isCoordinateXCorect = lastMove.getTo().getX() == from.getX() + diffX;
		boolean isCoordinateYCorect = lastMove.getTo().getY() == from.getY();

		if (isLastMovedPiecePawn && lastMovedDistance == 2 && isCoordinateXCorect && isCoordinateYCorect) {
			return incrasedCoordinate;
		}
		return null;
	}

private List<Coordinate> getCorrectMovesFromPiece(Coordinate from, Board board){
	
	Piece piece =board.getPieceAt(from);
	switch(piece.getType()){
	
	case BISHOP:
		return correctMovesBishop(from, board);
		
		
	case KING:
		return correctMovesKing(from, board);
		
		
	case KNIGHT:
		return correctMovesKnight(from, board);
		
		
	case PAWN:
		return correctMovesPawn(from, board);
		
		
	case QUEEN:
		return correctMovesQueen(from, board);
		
		
	case ROOK:
		return correctMovesRook(from, board);
		
		
	default:
		break;
	
	}
	return null;
	
}

public boolean validateMoveWitchChecks(Coordinate from, Coordinate to, Board board, Color expectedColor) throws InvalidColorException, InvalidCoordinateException, InvalidKingNumberException{
	isCoordinateExist(from);
	isCoordinateExist(to);
	isInCoordinateRightColorPiece(from, board, expectedColor);
	areKingsOnBoard(board);
	
	List<Coordinate> avalibleMoves = removeCoordinateIfKingInCheckAfterMove(from, board, getCorrectMovesFromPiece(from, board));
	
	
	
	return avalibleMoves.contains(to);
}

}
