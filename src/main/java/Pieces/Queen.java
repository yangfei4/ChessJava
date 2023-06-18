package Pieces;

import Boards.Board;
import java.util.*;

public class Queen extends Piece{

    public Queen(int color){
        super(color);
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♕":"♛";
    }


    @Override
    public boolean isLegitMove(int[][] moveCoordinates, Board board){
        int startRow = moveCoordinates[0][0];
        int startCol = moveCoordinates[0][1];
        int endRow = moveCoordinates[1][0];
        int endCol = moveCoordinates[1][1];
    
        // Check if the move is a valid diagonal move
        if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol)) {
            int rowDirection = (endRow - startRow) / Math.abs(endRow - startRow);
            int colDirection = (endCol - startCol) / Math.abs(endCol - startCol);
    
            int row = startRow + rowDirection;
            int col = startCol + colDirection;
    
            while (row != endRow && col != endCol) {
                if (board.hasPiece(row, col)) {
                    return false; // Path is blocked
                }
                row += rowDirection;
                col += colDirection;
            }
    
            // Check if the end position has the same color
            if (board.hasPiece(endRow, endCol) && board.getPiece(endRow, endCol).getColor() == getColor()) {
                return false; // End position has a piece with the same color
            }

            return true;
        }
    
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
    
            // Check if the end position has the same color
            if (board.hasPiece(endRow, endCol) && board.getPiece(endRow, endCol).getColor() == getColor()) {
                return false; // End position has a piece with the same color
            }

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
        // Possible movement directions for a queen (diagonal and straight)
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
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