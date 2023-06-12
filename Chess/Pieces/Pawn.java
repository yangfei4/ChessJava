package Pieces;

import Game.*;


public class Pawn extends Piece{
    // TODOs: add special attribute for different pieces
    public int PAWN_ID = Constants.PAWN_ID;

    public Pawn(int color){
        super(color);
        this.pieceID = PAWN_ID;
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♙":"♟";
    }


    @Override
    public boolean isLegitMove(int startx, int starty, int endx, int endy){
        
        return true;
    }

}