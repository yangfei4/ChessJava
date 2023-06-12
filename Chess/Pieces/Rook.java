package Pieces;

import Game.*;

public class Rook extends Piece{
    // TODOs: add special attribute for different pieces
    public int ROOK_ID = Constants.ROOK_ID;

    public Rook(int color){
        super(color);
        this.pieceID = ROOK_ID;
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♖":"♜";
    }

    @Override
    public boolean isLegitMove(int startx, int starty, int endx, int endy){
        
        return true;
    }
}