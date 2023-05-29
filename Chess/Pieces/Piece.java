package Pieces;

import Boards.*;

public abstract class Piece {
    public static final int WHITE=0;    
    public static final int BLACK=1;
    
    protected int Piece_ID;
    private int color;
    // private boolean killed;

    public Piece(int color){ //initialize piece
        if(color<0 || color>1)
            throw new IllegalArgumentException("Incorrect color number!");
        
        this.color = color;
        this.Piece_ID = -1;
        // this.killed = false;
    }

    public int getColor(){
        return this.color;
    }

    public int getPieceID(){
        return this.Piece_ID;
    }
    // will be overwritten in specific piece class
    public abstract boolean canMove(Board board, Spot start, Spot end);

}
