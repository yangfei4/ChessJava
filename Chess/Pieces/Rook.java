package Pieces;

import Game.Constants;
import Boards.Board;
import java.util.*;

public class Rook extends Piece{
    public int ROOK_ID = Constants.ROOK_ID;

    public Rook(int color){
        super(color);
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♖":"♜";
    }

    @Override
    public boolean isLegitMove(int[][] moveCoordinates, Board board) {
        int startRow = moveCoordinates[0][0];
        int startCol = moveCoordinates[0][1];
        int endRow = moveCoordinates[1][0];
        int endCol = moveCoordinates[1][1];
    
        // Check if the move is a valid straight move
        if (startRow == endRow || startCol == endCol) {
            int rowDirection = 0;
            int colDirection = 0;
    
            if (startRow != endRow) {
                rowDirection = (endRow - startRow) / Math.abs(endRow - startRow);
            }
            if (startCol != endCol) {
                colDirection = (endCol - startCol) / Math.abs(endCol - startCol);
            }
    
            int row = startRow + rowDirection;
            int col = startCol + colDirection;
    
            while (row != endRow || col != endCol) {
                if (board.hasPiece(row, col)) {
                    return false; // Path is blocked
                }
                row += rowDirection;
                col += colDirection;
            }
    
            return true;
        }
    
        return false;
    }
    

    public void setMoved(){

    }

    @Override
    public List<int[][]> getValidMoves(int startRow, int startCol, Board board) {
        List<int[][]> validMoves = new ArrayList<>();
        // Possible movement directions for a rook (straight lines)
        int[][] directions = {
                {-1, 0},    // Up
                {0, -1},    // Left
                {0, 1},     // Right
                {1, 0}      // Down
        };

        for (int[] direction : directions) {
            int row = startRow + direction[0];
            int col = startCol + direction[1];

            while (row >= 0 && row < 8 && col >= 0 && col < 8) {
                int[][] moveCoordinates = {{startRow, startCol}, {row, col}};
                if (isLegitMove(moveCoordinates, board)) {
                    validMoves.add(moveCoordinates);
                } else {
                    break;
                }

                row += direction[0];
                col += direction[1];
            }
        }

        return validMoves;
    }
}