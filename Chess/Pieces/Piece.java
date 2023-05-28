package Pieces;

import Boards.*;

public abstract class Piece {
    public static final int WHITE=0;    
    public static final int BLACK=1;

    private int color;
    // private boolean killed;

    public Piece(int color){ //initialize piece
        if(color<0 || color>1)
            throw new IllegalArgumentException("Incorrect color number!");
        
        this.color = color;
        // this.killed = false;
    }

    public int getColor(){
        return this.color;
    }

    // will be overwritten in specific piece class
    public abstract boolean canMove(Board board, Spot start, Spot end);

}
