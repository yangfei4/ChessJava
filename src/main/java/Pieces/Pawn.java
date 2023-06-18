package Pieces;

import Boards.Board;
import java.util.*;

public class Pawn extends Piece{
    private boolean moved;

    public Pawn(int color){
        super(color);
        this.moved = false;
    }

    @Override
    public String getSymbol(){
        return (this.getColor()==WHITE)?"♙":"♟";
    }

    @Override
    public boolean isLegitMove(int[][] moveCoordinates, Board board){
        int startRow=moveCoordinates[0][0], startCol=moveCoordinates[0][1];
        int endRow=moveCoordinates[1][0], endCol=moveCoordinates[1][1];

        int legitDirection = (this.getColor()==WHITE)?-1:1;

        // Move one or two straight steps
        if (endCol == startCol) {
            int rowDiff = endRow - startRow;

            // Check if the path is blocked by other pieces
            if (rowDiff == legitDirection) {
                // Move one step forward
                if (board.hasPiece(endRow, endCol)) {
                    // There is a piece blocking the path
                    return false;
                }
            } else if (rowDiff == 2 * legitDirection && !this.getMoved()) {
                // Move two steps forward (only allowed if the pawn hasn't moved yet)
                int intermediateRow = startRow + legitDirection;

                // Check if both squares in the path are unoccupied
                if (board.hasPiece(intermediateRow, endCol) || board.hasPiece(endRow, endCol)) {
                    // There is a piece blocking the path
                    return false;
                }
            } else {
                // Invalid move distance
                return false;
            }
        }
        // Diagonal move to capture an enemy piece
        else if ((endRow - startRow == legitDirection) && (Math.abs(startCol - endCol) == 1)) {
            // Check if the destination square has an enemy piece
            if (!board.hasPiece(endRow, endCol)) {
                // There is no piece to capture
                return false;
            }
        } else {
            // Invalid move for a pawn
            return false;
        }

        return true;
    }

    public boolean getMoved(){
        return this.moved;
    }

    public void setMoved(){
        this.moved = true;;
    }


    @Override
    public List<int[][]> getValidMoves(int startRow, int startCol, Board board) {
        List<int[][]> validMoves = new ArrayList<>();
        int color = getColor();
        int forwardDirection = color == WHITE ? -1 : 1;
    
        // Check if the pawn can move forward one step
        int[][] forwardMoveCoordinates = {{startRow, startCol}, {startRow + forwardDirection, startCol}};
        if (isLegitMove(forwardMoveCoordinates, board)) {
            validMoves.add(forwardMoveCoordinates);
    
            // Check if the pawn is at its starting position and can move forward two steps
            if ((color == WHITE && startRow == 6) || (color != WHITE && startRow == 1)) {
                int[][] doubleForwardMoveCoordinates = {{startRow, startCol}, {startRow + 2 * forwardDirection, startCol}};
                if (isLegitMove(doubleForwardMoveCoordinates, board)) {
                    validMoves.add(doubleForwardMoveCoordinates);
                }
            }
        }
    
        // Check if the pawn can capture diagonally
        int[][] diagonalCaptureCoordinates1 = {{startRow, startCol}, {startRow + forwardDirection, startCol + 1}};
        int[][] diagonalCaptureCoordinates2 = {{startRow, startCol}, {startRow + forwardDirection, startCol - 1}};
        if (startCol + 1 >=0 && startCol + 1 < 8 && 
        board.hasPiece(diagonalCaptureCoordinates1[1][0], diagonalCaptureCoordinates1[1][1]) && 
        board.getPiece(diagonalCaptureCoordinates1[1][0], diagonalCaptureCoordinates1[1][1]).getColor()!=color) {
            validMoves.add(diagonalCaptureCoordinates1);
        }
        if (startCol - 1 >=0 && startCol - 1 < 8 && 
        board.hasPiece(diagonalCaptureCoordinates2[1][0], diagonalCaptureCoordinates2[1][1]) && 
        board.getPiece(diagonalCaptureCoordinates2[1][0], diagonalCaptureCoordinates2[1][1]).getColor()!=color) {
            validMoves.add(diagonalCaptureCoordinates2);
        }
    
        return validMoves;
    }
    
    
            
}