package com.capgemini.chess.algorithms.data.generated;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;

/**
 * Board representation. Board objects are generated based on move history.
 * 
 * @author Michal Bejm
 *
 */
public class Board {

	public static final int SIZE = 8;

	private Piece[][] pieces = new Piece[SIZE][SIZE];
	private List<Move> moveHistory = new ArrayList<>();
	private BoardState state;

	public Board() {
	}

	public List<Move> getMoveHistory() {
		return moveHistory;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public BoardState getState() {
		return state;
	}

	public void setState(BoardState state) {
		this.state = state;
	}

	/**
	 * Sets chess piece on board based on given coordinates
	 * 
	 * @param piece
	 *            chess piece
	 * @param board
	 *            chess board
	 * @param coordinate
	 *            given coordinates
	 */
	public void setPieceAt(Piece piece, Coordinate coordinate) {
		pieces[coordinate.getX()][coordinate.getY()] = piece;
	}

	/**
	 * Gets chess piece from board based on given coordinates
	 * 
	 * @param coordinate
	 *            given coordinates
	 * @return chess piece
	 */
	public Piece getPieceAt(Coordinate coordinate) {
		return pieces[coordinate.getX()][coordinate.getY()];
	}

	public List<Coordinate> getCoordinateOfEnemyPieces() {
		List<Coordinate> listPositionsOfEnemyPieces = new ArrayList<>();
		Color enemyColor = Color.BLACK;

		if (getMoveHistory().size() % 2 != 0) {
			enemyColor = Color.WHITE;
		}

		for (int coordinateX = 0; coordinateX < 8; coordinateX++) {
			for (int coordinateY = 0; coordinateY < 8; coordinateY++) {
				Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
				if (getPieceAt(coordinate) != null && getPieceAt(coordinate).getColor() == enemyColor) {
					listPositionsOfEnemyPieces.add(coordinate);
				}
			}
		}

		return listPositionsOfEnemyPieces;
	}

	public List<Coordinate> getCoordinateOfOwnPieces() {
		List<Coordinate> listPositionsOfEnemyPieces = new ArrayList<>();
		Color ownColor = Color.BLACK;

		if (getMoveHistory().size() % 2 == 0) {
			ownColor = Color.WHITE;
		}

		for (int coordinateX = 0; coordinateX < 8; coordinateX++) {
			for (int coordinateY = 0; coordinateY < 8; coordinateY++) {
				Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
				if (getPieceAt(coordinate) != null && getPieceAt(coordinate).getColor() == ownColor) {
					listPositionsOfEnemyPieces.add(coordinate);
				}
			}
		}

		return listPositionsOfEnemyPieces;
	}

	public Coordinate getOwnKingPosition() {

		Color color = Color.BLACK;

		if (getMoveHistory().size() % 2 == 0) {
			color = Color.WHITE;
		}
		Coordinate kingPosition = new Coordinate(0, 0);

		for (int coordinateX = 0; coordinateX < 8; coordinateX++) {
			for (int coordinateY = 0; coordinateY < 8; coordinateY++) {
				Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
				if (getPieceAt(coordinate) != null && getPieceAt(coordinate).getColor() == color
						&& getPieceAt(coordinate).getType() == PieceType.KING) {
					kingPosition = coordinate;
				}
			}
		}
		return kingPosition;
	}

	public Coordinate getEnemyKingPosition() {

		Color color = Color.BLACK;

		if (getMoveHistory().size() % 2 != 0) {
			color = Color.WHITE;
		}
		Coordinate kingPosition = new Coordinate(0, 0);

		for (int coordinateX = 0; coordinateX < 8; coordinateX++) {
			for (int coordinateY = 0; coordinateY < 8; coordinateY++) {
				Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
				if (getPieceAt(coordinate) != null && getPieceAt(coordinate).getColor() == color
						&& getPieceAt(coordinate).getType() == PieceType.KING) {
					kingPosition = coordinate;
				}
			}
		}
		return kingPosition;
	}

	public Coordinate getKingPosition(Color color) {

		Coordinate kingPosition = new Coordinate(0, 0);

		for (int coordinateX = 0; coordinateX < 8; coordinateX++) {
			for (int coordinateY = 0; coordinateY < 8; coordinateY++) {
				Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
				if (getPieceAt(coordinate) != null && getPieceAt(coordinate).getColor() == color
						&& getPieceAt(coordinate).getType() == PieceType.KING) {
					kingPosition = coordinate;
				}
			}
		}
		return kingPosition;
	}

	public boolean isCoordinateOnBoard(Coordinate coordinate) {

		if (coordinate.getX() > SIZE - 1 || coordinate.getY() < 0 || coordinate.getX() < 0
				|| coordinate.getY() > SIZE - 1) {
			return false;
		}

		return true;
	}

}
