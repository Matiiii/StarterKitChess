package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class FullGameTest {

	@Test
	public void testIsGameCorrectlyPlayed() throws InvalidMoveException {

		BoardManager boardManager = new BoardManager();
		Board board = boardManager.getBoard();
		List<Move> moveList = board.getMoveHistory();

		boardManager.performMove(new Coordinate(4, 1), new Coordinate(4, 3));
		Move move1 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move1.getType());
		assertEquals(Piece.WHITE_PAWN, move1.getMovedPiece());

		boardManager.performMove(new Coordinate(3, 6), new Coordinate(3, 4));
		Move move2 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move2.getType());
		assertEquals(Piece.BLACK_PAWN, move2.getMovedPiece());

		boardManager.performMove(new Coordinate(4, 3), new Coordinate(3, 4));
		Move move3 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move3.getType());
		assertEquals(Piece.WHITE_PAWN, move3.getMovedPiece());

		boardManager.performMove(new Coordinate(4, 6), new Coordinate(4, 4));
		Move move4 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move4.getType());
		assertEquals(Piece.BLACK_PAWN, move4.getMovedPiece());

		boardManager.performMove(new Coordinate(3, 4), new Coordinate(4, 5));
		Move move5 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.EN_PASSANT, move5.getType());
		assertEquals(Piece.WHITE_PAWN, move5.getMovedPiece());

		boardManager.performMove(new Coordinate(5, 6), new Coordinate(5, 4));
		Move move6 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move6.getType());
		assertEquals(Piece.BLACK_PAWN, move6.getMovedPiece());

		boardManager.performMove(new Coordinate(3, 0), new Coordinate(7, 4));
		Move move7 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move7.getType());
		assertEquals(Piece.WHITE_QUEEN, move7.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.CHECK, board.getState());

		boardManager.performMove(new Coordinate(6, 6), new Coordinate(6, 5));
		Move move8 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move8.getType());
		assertEquals(Piece.BLACK_PAWN, move8.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.REGULAR, board.getState());

		boardManager.performMove(new Coordinate(7, 4), new Coordinate(3, 0));
		Move move9 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move9.getType());
		assertEquals(Piece.WHITE_QUEEN, move9.getMovedPiece());

		boardManager.performMove(new Coordinate(2, 7), new Coordinate(4, 5));
		Move move10 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move10.getType());
		assertEquals(Piece.BLACK_BISHOP, move10.getMovedPiece());

		boardManager.performMove(new Coordinate(3, 1), new Coordinate(3, 3));
		Move move11 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move11.getType());
		assertEquals(Piece.WHITE_PAWN, move11.getMovedPiece());

		boardManager.performMove(new Coordinate(3, 7), new Coordinate(3, 4));
		Move move12 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move12.getType());
		assertEquals(Piece.BLACK_QUEEN, move12.getMovedPiece());

		boardManager.performMove(new Coordinate(2, 1), new Coordinate(2, 2));
		Move move13 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move13.getType());
		assertEquals(Piece.WHITE_PAWN, move13.getMovedPiece());

		boardManager.performMove(new Coordinate(5, 7), new Coordinate(4, 6));
		Move move14 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move14.getType());
		assertEquals(Piece.BLACK_BISHOP, move14.getMovedPiece());

		boardManager.performMove(new Coordinate(2, 0), new Coordinate(5, 3));
		Move move15 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move15.getType());
		assertEquals(Piece.WHITE_BISHOP, move15.getMovedPiece());

		boardManager.performMove(new Coordinate(6, 5), new Coordinate(6, 4));
		Move move16 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move16.getType());
		assertEquals(Piece.BLACK_PAWN, move16.getMovedPiece());

		boardManager.performMove(new Coordinate(5, 3), new Coordinate(2, 6));
		Move move17 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move17.getType());
		assertEquals(Piece.WHITE_BISHOP, move17.getMovedPiece());

		boardManager.performMove(new Coordinate(1, 6), new Coordinate(1, 4));
		Move move18 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move18.getType());
		assertEquals(Piece.BLACK_PAWN, move18.getMovedPiece());

		boardManager.performMove(new Coordinate(1, 0), new Coordinate(0, 2));
		Move move19 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move19.getType());
		assertEquals(Piece.WHITE_KNIGHT, move19.getMovedPiece());

		boardManager.performMove(new Coordinate(6, 7), new Coordinate(5, 5));
		Move move20 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move20.getType());
		assertEquals(Piece.BLACK_KNIGHT, move20.getMovedPiece());

		boardManager.performMove(new Coordinate(0, 2), new Coordinate(1, 4));
		Move move21 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move21.getType());
		assertEquals(Piece.WHITE_KNIGHT, move21.getMovedPiece());

		boardManager.performMove(new Coordinate(4, 6), new Coordinate(3, 7));
		Move move22 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move22.getType());
		assertEquals(Piece.BLACK_BISHOP, move22.getMovedPiece());

		boardManager.performMove(new Coordinate(2, 6), new Coordinate(3, 7));
		Move move23 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move23.getType());
		assertEquals(Piece.WHITE_BISHOP, move23.getMovedPiece());

		boardManager.performMove(new Coordinate(3, 4), new Coordinate(2, 5));
		Move move24 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move24.getType());
		assertEquals(Piece.BLACK_QUEEN, move24.getMovedPiece());

		boardManager.performMove(new Coordinate(3, 7), new Coordinate(5, 5));
		Move move25 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move25.getType());
		assertEquals(Piece.WHITE_BISHOP, move25.getMovedPiece());

		boardManager.performMove(new Coordinate(7, 7), new Coordinate(5, 7));
		Move move26 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move26.getType());
		assertEquals(Piece.BLACK_ROOK, move26.getMovedPiece());

		boardManager.performMove(new Coordinate(5, 5), new Coordinate(6, 4));
		Move move27 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move27.getType());
		assertEquals(Piece.WHITE_BISHOP, move27.getMovedPiece());

		boardManager.performMove(new Coordinate(4, 5), new Coordinate(2, 3));
		Move move28 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move28.getType());
		assertEquals(Piece.BLACK_BISHOP, move28.getMovedPiece());

		boardManager.performMove(new Coordinate(5, 0), new Coordinate(2, 3));
		Move move29 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move29.getType());
		assertEquals(Piece.WHITE_BISHOP, move29.getMovedPiece());

		boardManager.performMove(new Coordinate(2, 5), new Coordinate(6, 5));
		Move move30 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move30.getType());
		assertEquals(Piece.BLACK_QUEEN, move30.getMovedPiece());

		boardManager.performMove(new Coordinate(6, 0), new Coordinate(5, 2));
		Move move31 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move31.getType());
		assertEquals(Piece.WHITE_KNIGHT, move31.getMovedPiece());

		boardManager.performMove(new Coordinate(0, 6), new Coordinate(0, 5));
		Move move32 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move32.getType());
		assertEquals(Piece.BLACK_PAWN, move32.getMovedPiece());

		boardManager.performMove(new Coordinate(1, 4), new Coordinate(2, 6));
		Move move33 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move33.getType());
		assertEquals(Piece.WHITE_KNIGHT, move33.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.CHECK, board.getState());

		boardManager.performMove(new Coordinate(4, 7), new Coordinate(3, 6));
		Move move34 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move34.getType());
		assertEquals(Piece.BLACK_KING, move34.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.REGULAR, board.getState());

		boardManager.performMove(new Coordinate(2, 6), new Coordinate(0, 7));
		Move move35 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move35.getType());
		assertEquals(Piece.WHITE_KNIGHT, move35.getMovedPiece());

		boardManager.performMove(new Coordinate(5, 4), new Coordinate(5, 3));
		Move move36 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move36.getType());
		assertEquals(Piece.BLACK_PAWN, move36.getMovedPiece());

		boardManager.performMove(new Coordinate(3, 0), new Coordinate(1, 2));
		Move move37 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move37.getType());
		assertEquals(Piece.WHITE_QUEEN, move37.getMovedPiece());

		boardManager.performMove(new Coordinate(5, 7), new Coordinate(5, 5));
		Move move38 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move38.getType());
		assertEquals(Piece.BLACK_ROOK, move38.getMovedPiece());

		boardManager.performMove(new Coordinate(1, 2), new Coordinate(1, 7));
		Move move39 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move39.getType());
		assertEquals(Piece.WHITE_QUEEN, move39.getMovedPiece());

		boardManager.performMove(new Coordinate(5, 5), new Coordinate(3, 5));
		Move move40 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move40.getType());
		assertEquals(Piece.BLACK_ROOK, move40.getMovedPiece());

		boardManager.performMove(new Coordinate(4, 0), new Coordinate(2, 0));
		Move move41 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CASTLING, move41.getType());
		assertEquals(Piece.WHITE_KING, move41.getMovedPiece());

		boardManager.performMove(new Coordinate(6, 5), new Coordinate(5, 4));
		Move move42 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move42.getType());
		assertEquals(Piece.BLACK_QUEEN, move42.getMovedPiece());

		boardManager.performMove(new Coordinate(5, 2), new Coordinate(4, 4));
		Move move43 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move43.getType());
		assertEquals(Piece.WHITE_KNIGHT, move43.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.CHECK, board.getState());

		boardManager.performMove(new Coordinate(5, 4), new Coordinate(4, 4));
		Move move44 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move44.getType());
		assertEquals(Piece.BLACK_QUEEN, move44.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.REGULAR, board.getState());

		boardManager.performMove(new Coordinate(3, 3), new Coordinate(4, 4));
		Move move45 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move45.getType());
		assertEquals(Piece.WHITE_PAWN, move45.getMovedPiece());

		boardManager.performMove(new Coordinate(3, 5), new Coordinate(3, 0));
		Move move46 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move46.getType());
		assertEquals(Piece.BLACK_ROOK, move46.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.CHECK, board.getState());

		boardManager.performMove(new Coordinate(2, 0), new Coordinate(3, 0));
		Move move47 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.CAPTURE, move47.getType());
		assertEquals(Piece.WHITE_KING, move47.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.REGULAR, board.getState());

		boardManager.performMove(new Coordinate(7, 6), new Coordinate(7, 4));
		Move move48 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move48.getType());
		assertEquals(Piece.BLACK_PAWN, move48.getMovedPiece());

		boardManager.performMove(new Coordinate(4, 4), new Coordinate(4, 5));
		Move move49 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move49.getType());
		assertEquals(Piece.WHITE_PAWN, move49.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.CHECK, board.getState());

		boardManager.performMove(new Coordinate(3, 6), new Coordinate(2, 5));
		Move move50 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move50.getType());
		assertEquals(Piece.BLACK_KING, move50.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.REGULAR, board.getState());

		boardManager.performMove(new Coordinate(1, 7), new Coordinate(2, 7));
		Move move51 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move51.getType());
		assertEquals(Piece.WHITE_QUEEN, move51.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.CHECK, board.getState());

		boardManager.performMove(new Coordinate(2, 5), new Coordinate(3, 5));
		Move move52 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move52.getType());
		assertEquals(Piece.BLACK_KING, move52.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.REGULAR, board.getState());

		boardManager.performMove(new Coordinate(7, 0), new Coordinate(4, 0));
		Move move53 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move53.getType());
		assertEquals(Piece.WHITE_ROOK, move53.getMovedPiece());

		boardManager.performMove(new Coordinate(0, 5), new Coordinate(0, 4));
		Move move54 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move54.getType());
		assertEquals(Piece.BLACK_PAWN, move54.getMovedPiece());

		boardManager.performMove(new Coordinate(4, 5), new Coordinate(4, 6));
		Move move55 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move55.getType());
		assertEquals(Piece.WHITE_PAWN, move55.getMovedPiece());

		boardManager.performMove(new Coordinate(7, 4), new Coordinate(7, 3));
		Move move56 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move56.getType());
		assertEquals(Piece.BLACK_PAWN, move56.getMovedPiece());

		boardManager.performMove(new Coordinate(4, 0), new Coordinate(4, 5));
		Move move57 = moveList.get(moveList.size() - 1);
		assertEquals(MoveType.ATTACK, move57.getType());
		assertEquals(Piece.WHITE_ROOK, move57.getMovedPiece());
		boardManager.updateBoardState();
		assertEquals(BoardState.CHECK_MATE, board.getState());

	}
}