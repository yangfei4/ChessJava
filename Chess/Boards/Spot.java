package Boards;

import java.util.*;
import Pieces.Piece;
import Game.*;

public class Spot {
    private Position positon;       // piece position
    private Optional<Piece> piece; // piece type

    public Spot(Position position, Optional<Piece> piece){
        // Question: someone wrap these as three functions setX(), setY(), setPiece()
        this.positon = position;
        this.piece = piece; 
    }

    public Optional<Piece> getPiece(){
        return this.piece;
    }

    public int getX(){
        return this.positon.getX();
    }

    public int getY(){
        return this.positon.getY();
    }
}
