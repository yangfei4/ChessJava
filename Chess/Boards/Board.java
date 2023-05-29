package Boards;

import java.util.*;

import Game.*;
import Pieces.*;

public class Board {
    public int BOARD_SIZE = Constants.BOARD_SIZE;
    public int WHITE = Constants.WHITE;
    public int BLACK = Constants.BLACK;

    private Spot[][] board;

    public Board(){ // Initialize 8x8 Board
        this.resetBoard();
    }

    public void resetBoard(){
        this.board = new Spot[BOARD_SIZE][BOARD_SIZE];

        /* Assign some grids with a Spot(x,y,piece) for playes */ 
        for(int color=0; color<=1; color++){ // WHITE=0 BLACK=1
            int row1 = (color==WHITE)?7:0;
            int row2 = (color==WHITE)?6:1;
            // Rook
            this.board[row1][0] = new Spot(new Position(row1, 0), Optional.of(new Rook(color)));
            this.board[row1][7] = new Spot(new Position(row1, 7), Optional.of(new Rook(color)));
            // Knight
            this.board[row1][1] = new Spot(new Position(row1, 1), Optional.of(new Knight(color)));
            this.board[row1][6] = new Spot(new Position(row1, 6), Optional.of(new Knight(color)));
            // Bishop
            this.board[row1][2] = new Spot(new Position(row1, 2), Optional.of(new Bishop(color)));
            this.board[row1][5] = new Spot(new Position(row1, 5), Optional.of(new Bishop(color)));
            // Queen
            this.board[row1][3] = new Spot(new Position(row1, 3), Optional.of(new Queen(color)));
            // King
            this.board[row1][4] = new Spot(new Position(row1, 4), Optional.of(new King(color)));
            // Pawn
            for(int y=0; y<8; y++)
                this.board[row2][y] = new Spot(new Position(row2, y), Optional.of(new Pawn(color)));
        }

        for(int x=2; x<=5; x++){
            for(int y=0; y<8; y++){
                this.board[x][y] = new Spot(new Position(x, y), Optional.empty());
            }
        }

    }

    public Spot getSpot(int x, int y){ 
        if(x<0 || y<0 || x>=BOARD_SIZE || y>=BOARD_SIZE){
            throw new IndexOutOfBoundsException("Index out of bound");
        }

        return this.board[x][y];
    }
}