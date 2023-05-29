package Pieces;

import java.util.*;

import Boards.*;
import Game.*;


public class King extends Piece{
    // TODOs: add special attribute for different pieces
    public int KING_ID = Constants.KING_ID;

    public King(int color){
        super(color);
        this.Piece_ID = KING_ID;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end){
        int boardSize = Constants.BOARD_SIZE;
        
        // Case1: out of bound
        if(end.getX()<0 || end.getX()>=boardSize||
            end.getY()<0 || end.getY()>=boardSize)
            return false;
        // Case2: end position is same color
        else if(end.getPiece().isPresent() && 
                start.getPiece().get().getColor()==end.getPiece().get().getColor())
            return false;
        
        return true;
    }

    public List<Move> getPossibleMove(Position startPosition){
        List<Move> possibleMove = new ArrayList<>();

        // TODOs: modify this for each type of piece
    
        return possibleMove;
    };
}