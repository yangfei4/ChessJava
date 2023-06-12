package Pieces;

import Game.*;


public class Knight extends Piece{
    // TODOs: add special attribute for different pieces
    public int KNIGHT_ID = Constants.KNIGHT_ID;

    public Knight(int color){
        super(color);
        this.pieceID = KNIGHT_ID;
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♘":"♞";
    }

    @Override
    public boolean isLegitMove(int startx, int starty, int endx, int endy){
        
        return true;
    }
}