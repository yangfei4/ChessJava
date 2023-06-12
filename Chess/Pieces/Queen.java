package Pieces;

import Game.*;


public class Queen extends Piece{
    // TODOs: add special attribute for different pieces
    public int QUEEN_ID = Constants.QUEEN_ID;

    public Queen(int color){
        super(color);
        this.pieceID = QUEEN_ID;
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♕":"♛";
    }


    @Override
    public boolean isLegitMove(int startx, int starty, int endx, int endy){
        
        return true;
    }

}