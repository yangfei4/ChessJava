package Boards;

import Pieces.Piece;

public class Spot {
    private Piece piece; // piece type
    private boolean hasPiece;

    public Spot(Piece piece){
        // Question: someone wrap these as three functions setX(), setY(), setPiece()
        this.piece = piece;
        this.hasPiece = piece==null?false:true;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public boolean hasPiece(){
        return this.hasPiece;
    }
    
    public void setPiece(Piece p){
        this.piece = p;
        this.hasPiece = true;
    }

    public void removePiece(){
        this.piece = null;
        this.hasPiece = false;
    }

}
