package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidColorException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidCoordinateException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidKingNumberException;

public class MoveValidator {
	
	
	private boolean isCoordinateExist(Coordinate coordinate) throws InvalidCoordinateException{
		
		if(coordinate.getX()>7||coordinate.getY()<0){
			throw new InvalidCoordinateException(coordinate.toString());
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
		
		if(countBlackKing!=1||countBlackKing!=1){
			throw new InvalidKingNumberException();
		}
		
		return true;
		
	}
		
	
	
	
	

}
