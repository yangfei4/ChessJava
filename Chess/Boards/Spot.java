package Boards;

import Pieces.Piece;

public class Spot {
    private Piece piece; // piece type

    public Spot(Piece piece){
        // Question: someone wrap these as three functions setX(), setY(), setPiece()
        this.piece = piece;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public boolean hasPiece(){
        return this.piece==null?false:true;
    }
    
    public void setPiece(Piece p){
        this.piece = p;
    }

    public void removePiece(){
        this.piece = null;
    }

}
