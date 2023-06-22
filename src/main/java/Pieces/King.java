package Pieces;

import Boards.Board;
import java.util.*;

public class King extends Piece{
    public King(int color){
        super(color);
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♔":"♚";
    }

    @Override
    public boolean isLegitMove(int[][] moveCoordinates, Board board){
        int startRow = moveCoordinates[0][0];
        int startCol = moveCoordinates[0][1];
        int endRow = moveCoordinates[1][0];
        int endCol = moveCoordinates[1][1];
    
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
    
        // Check if the king is moving to an adjacent square
        if (rowDiff <= 1 && colDiff <= 1) {
            // Check if the destination square is either empty or occupied by an opponent's piece
            if (!board.hasPiece(endRow, endCol) || board.getPiece(endRow, endCol).getColor() != this.getColor()) {
                // Check if the destination square is not under attack by an opponent's piece
                if (!board.isSquareUnderAttack(endRow, endCol, this.getColor())) {
                    return true;
                }
            }
        }
    
        return false;
    }

    @Override
    public void setMoved(){

    }

    @Override
    public List<int[][]> getValidMoves(int startRow, int startCol, Board board) {
        List<int[][]> validMoves = new ArrayList<>();
        // Possible movement directions for a king (horizontal, vertical, and diagonal)
        int[][] directions = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

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