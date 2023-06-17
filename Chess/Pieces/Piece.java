package Pieces;

import Game.Constants;
import Boards.Board;
import java.util.List;

public abstract class Piece {
    public int WHITE = Constants.WHITE;
    private int color;

    public Piece(int color){ //initialize piece
        if(color<0 || color>1)
            throw new IllegalArgumentException("Incorrect color number!");
        
        this.color = color;
    }

    public int getColor(){
        return this.color;
    }

    // will be overwritten in each piece class
    public abstract String getSymbol();
    public abstract boolean isLegitMove(int[][] moveCoordinates, Board board);
    public abstract void setMoved();
    public abstract List<int[][]> getValidMoves(int startRow, int startCol, Board board);
}