package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.BoardManager;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidColorException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidCoordinateException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidKingNumberException;

public class MoveValidator {
	
	
	
	
	private boolean isCoordinateExist(Coordinate coordinate) throws InvalidCoordinateException{
		
		if(coordinate.getX()>7||coordinate.getY()<0||coordinate.getX()<0||coordinate.getY()>7){
			throw new InvalidCoordinateException(coordinate.toString());
		}
		
		return true;
	}
	
private boolean isCoordinateOnBoard(Coordinate coordinate){
		
		if(coordinate.getX()>7||coordinate.getY()<0||coordinate.getX()<0||coordinate.getY()>7){
			return false;
		}
		
		return true;
	}
	
	private boolean isInCoordinateRightColorPiece(Coordinate coordinate,Board board, Color expectedColor  ) throws InvalidColorException{
		if(board.getPieceAt(coordinate).getColor().equals(expectedColor)){
			return true;
		}
		else {
			throw new InvalidColorException(); 
		}
		
	}
		
	private boolean areKingsOnBoard(Board board) throws InvalidKingNumberException{
		
		int countWhiteKing=0;
		int countBlackKing=0;
		
		for(int coordinateX=0; coordinateX<8; coordinateX++){
			for( int coordinateY=0; coordinateY<8; coordinateY++){
				Coordinate coordinate = new Coordinate(coordinateX,coordinateY); 
				if(board.getPieceAt(coordinate).getType().equals(Piece.WHITE_KING)){
					countWhiteKing++;
				}
				if(board.getPieceAt(coordinate).getType().equals(Piece.BLACK_KING)){
					countBlackKing++;
				}
			}
		}
		
		if(countBlackKing!=1||countWhiteKing!=1){
			throw new InvalidKingNumberException();
		}
		
		return true;
		
	}
	
	private Coordinate correctMoveInThisPosition(Coordinate from,Board board, int diffX, int diffY){
		
		Coordinate incrasedCoordinate= new Coordinate(from.getX()+diffX,from.getY()+diffY);
		
		if((isCoordinateOnBoard(incrasedCoordinate)&&board.getPieceAt(incrasedCoordinate).getColor()==board.getPieceAt(from).getColor())&&!isKingInCheckAfterMove(from, incrasedCoordinate)){
			return incrasedCoordinate;
			
			}
		return null;
		
	}
	

	private List<Coordinate> correctMovesInOneDirection(Coordinate from, Board board, int diffX, int diffY){
		
		boolean canMoveNextPosition=true;
		
		Coordinate incrasedCoordinate= new Coordinate(from.getX()+diffX,from.getY()+diffY);
		List<Coordinate>lista = new ArrayList<>();
		
		while(canMoveNextPosition&&isCoordinateOnBoard(incrasedCoordinate)){
			if(board.getPieceAt(incrasedCoordinate)==null&&!isKingInCheckAfterMove(from,incrasedCoordinate)){
				lista.add(incrasedCoordinate);
				
				}
			else if(board.getPieceAt(incrasedCoordinate).getColor()!=board.getPieceAt(from).getColor()&&!isKingInCheckAfterMove(from,incrasedCoordinate)){
				lista.add(incrasedCoordinate);
				canMoveNextPosition=false;
				}
			else{
				canMoveNextPosition=false;
			}
			incrasedCoordinate= new Coordinate(incrasedCoordinate.getX()+diffX,incrasedCoordinate.getY()+diffY);
		}
		
		return lista;
	}
	
	private List<Coordinate> correctMovesRook(Coordinate from, Board board){
		
		List<Coordinate> listOfCorrectMoves = new ArrayList<>();
		
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board,1 , 0));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board,0 , 1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board,-1 , 0));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board,0, -1));
		return listOfCorrectMoves;
		
	}
	
private List<Coordinate> correctMovesKnight(Coordinate from, Board board){
		
		List<Coordinate> listOfCorrectMoves = new ArrayList<>();
		
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board,1 , 1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board,-1, 1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board,-1 ,-1));
		listOfCorrectMoves.addAll(correctMovesInOneDirection(from, board,1, -1));
		return listOfCorrectMoves;
		
	}
	
	private boolean isKingInCheckAfterMove(Coordinate from, Coordinate to){
		return true;
	}
	
	private List<Coordinate> correctMovesBishop(Coordinate from, Board board){
		
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
	
private List<Coordinate> correctMovesQueen(Coordinate from, Board board){
		
		List<Coordinate> listOfCorrectMoves = new ArrayList<>();
		
		listOfCorrectMoves.addAll(correctMovesRook(from, board));
		listOfCorrectMoves.addAll(correctMovesKnight(from, board));
		
		return listOfCorrectMoves;
		
	}
	
	
		
	
	
	
	

}
