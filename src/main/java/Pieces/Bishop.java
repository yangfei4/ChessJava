package Pieces;

import Boards.Board;
import java.util.*;

public class Bishop extends Piece{

    public Bishop(int color){
        super(color);
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♗":"♝";
    }

    @Override
    public boolean isLegitMove(int[][] moveCoordinates, Board board){
        int startRow = moveCoordinates[0][0];
        int startCol = moveCoordinates[0][1];
        int endRow = moveCoordinates[1][0];
        int endCol = moveCoordinates[1][1];
    
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
    
        // Check if the bishop is moving diagonally
        if (rowDiff == colDiff) {
            int rowDirection = (endRow > startRow) ? 1 : -1;
            int colDirection = (endCol > startCol) ? 1 : -1;
    
            // Check if the path is clear (no obstructions between start and end positions)
            int currentRow = startRow + rowDirection;
            int currentCol = startCol + colDirection;
            while (currentRow != endRow && currentCol != endCol) {
                if (board.hasPiece(currentRow, currentCol)) {
                    // There is an obstruction in the path
                    return false;
                }
                currentRow += rowDirection;
                currentCol += colDirection;
            }
    
            // Check if the destination square is empty or occupied by an opponent's piece
            if (!board.hasPiece(endRow, endCol) || board.getPiece(endRow, endCol).getColor() != this.getColor()) {
                return true;
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
        // Possible movement directions for a bishop (diagonal)
        int[][] directions = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
    
        for (int[] direction : directions) {
            int row = startRow + direction[0];
            int col = startCol + direction[1];
    
            while (row >= 0 && row < 8 && col >= 0 && col < 8) {
                int[][] moveCoordinates = { { startRow, startCol }, { row, col } };
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