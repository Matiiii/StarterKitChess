package com.capgemini.chess.algorithms.implementation;

import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public interface MoveProvider {
	List<Coordinate> moves(Coordinate from, Board board);

}
