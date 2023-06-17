package Pieces;

import Game.Constants;
import Boards.Board;
import java.util.*;

public class Knight extends Piece{

    public Knight(int color){
        super(color);
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♘":"♞";
    }

    @Override
    public boolean isLegitMove(int[][] moveCoordinates, Board board){
        int startRow = moveCoordinates[0][0];
        int startCol = moveCoordinates[0][1];
        int endRow = moveCoordinates[1][0];
        int endCol = moveCoordinates[1][1];
    
        // Check if the move is a valid knight move
        if (Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 1) {
            return true;
        }
        if (Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 2) {
            return true;
        }
    
        return false;
    }

    @Override
    public void setMoved(){
        
    }

    @Override
    public List<int[][]> getValidMoves(int startRow, int startCol, Board board) {
        List<int[][]> validMoves = new ArrayList<>();
        // Possible movement directions for a knight
        int[][] directions = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };

        for (int[] direction : directions) {
            int row = startRow + direction[0];
            int col = startCol + direction[1];

            if (row >= 0 && row < 8 && col >= 0 && col < 8) {
                int[][] moveCoordinates = { { startRow, startCol }, { row, col } };
                if (isLegitMove(moveCoordinates, board)) {
                    validMoves.add(moveCoordinates);
                }
            }
        }

        return validMoves;
    }

}