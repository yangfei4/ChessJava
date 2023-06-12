package Pieces;

import Game.*;

public abstract class Piece {
    public int WHITE = Constants.WHITE;
    protected int pieceID;
    private int color;
    private boolean killed;

    public Piece(int color){ //initialize piece
        if(color<0 || color>1)
            throw new IllegalArgumentException("Incorrect color number!");
        
        this.color = color;
        this.pieceID = -1;
        this.killed = false;
    }

    public int getColor(){
        return this.color;
    }

    public int getPieceID(){
        return this.pieceID;
    }

    public void setKilled(){
        this.killed = true;
    }

    // will be overwritten in each piece class
    public abstract String getSymbol();
    public abstract boolean isLegitMove(int startx, int starty, int endx, int endy);

}
