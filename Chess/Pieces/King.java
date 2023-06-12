package Pieces;

import Game.*;


public class King extends Piece{
    // TODOs: add special attribute for different pieces
    public int KING_ID = Constants.KING_ID;

    public King(int color){
        super(color);
        this.pieceID = KING_ID;
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♔":"♚";
    }

    @Override
    public boolean isLegitMove(int startx, int starty, int endx, int endy){
        
        return true;
    }
}