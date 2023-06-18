package UnitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import Pieces.Pawn;
import Pieces.Piece;
import Boards.Board;
import Game.Constants;

public class PawnUnitTest {
    @Test
    public void testPawnInitialization() {
        int color = Constants.WHITE;
        Pawn pawn = new Pawn(color);
        
        Assertions.assertEquals(color, pawn.getColor());
        Assertions.assertFalse(pawn.getMoved());
    }
    
    @Test
    public void testPawnValidMove() {
        int color = Constants.WHITE;
        Pawn pawn = new Pawn(color);
        Board board = new Board(); // Assuming a valid Board class implementation
        
        int[][] validMoveCoordinates1 = {{6, 1}, {5, 1}}; //move forward one step
        Assertions.assertTrue(pawn.isLegitMove(validMoveCoordinates1, board));

        int[][] validMoveCoordinates2 = {{6, 1}, {4, 1}}; //move forward two steps at the beginning
        Assertions.assertTrue(pawn.isLegitMove(validMoveCoordinates2, board));
    }
    
    @Test
    public void testPawnInvalidMove() {
        int color = Constants.WHITE;
        Pawn pawn = new Pawn(color);
        Board board = new Board(); // Assuming a valid Board class implementation
        
        int[][] invalidMoveCoordinates1 = {{6, 1}, {3, 1}}; // move backwards
        Assertions.assertFalse(pawn.isLegitMove(invalidMoveCoordinates1, board));
        int[][] invalidMoveCoordinates2 = {{6, 1}, {5, 2}}; //move diagonally
        Assertions.assertFalse(pawn.isLegitMove(invalidMoveCoordinates2, board));
    }

    @Test
    void testGetValidMoves() {
        // Create a test board
        Board board = new Board();
        // Set up the board with a pawn at (1, 1) position
        int startRow = 6;
        int startCol = 1;

        // Get the valid moves for the pawn
        Piece pawn = board.getPiece(startRow, startCol);
        List<int[][]> validMoves = pawn.getValidMoves(startRow, startCol, board);

        // for (int[][] move : validMoves) {
        //     int sr = move[0][0];
        //     int sc = move[0][1];
        //     int endRow = move[1][0];
        //     int endCol = move[1][1];
        //     System.out.println("Valid move: (" + sr + ", " + sc + ") to (" + endRow + ", " + endCol + ")");
        // }
        
        // Define the expected valid move coordinates
        int[][] expectedMove1 = {{6, 1}, {5, 1}};
        int[][] expectedMove2 = {{6, 1}, {4, 1}};

        // Convert the validMoves list to a List of Strings
        List<String> validMovesAsString = new ArrayList<>();
        for (int[][] move : validMoves) {
            validMovesAsString.add(Arrays.deepToString(move));
        }

        // Assert that the valid moves contain the expected move coordinates
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove1)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove2)));
    }
}