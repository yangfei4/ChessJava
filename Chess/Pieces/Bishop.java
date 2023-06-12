package Pieces;

import Game.*;

public class Bishop extends Piece{
    // TODOs: add special attribute for different pieces
    public int BISHOP_ID = Constants.BISHOP_ID;

    public Bishop(int color){
        super(color);
        this.pieceID = BISHOP_ID;
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♗":"♝";
    }

    @Override
    public boolean isLegitMove(int startx, int starty, int endx, int endy){
        
        return true;
    }
}