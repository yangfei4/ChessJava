package UnitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import Boards.Board;
import Pieces.Knight;
import Pieces.Piece;
import Game.Constants;

public class KnightUnitTest {
    @Test
    public void testKnightInitialization() {
        int color = Constants.WHITE;
        Knight knight = new Knight(color);
        
        Assertions.assertEquals(color, knight.getColor());
    }
    
    @Test
    public void testKnightValidMove() {
        int color = Constants.WHITE;
        Knight knight = new Knight(color);
        Board board = new Board(); // Assuming a valid Board class implementation
        
        // Set up the board with a knight at (3, 3) position
        int startRow = 3;
        int startCol = 3;
        board.setPiece(startRow, startCol, new Knight(Constants.WHITE));

        int[][] validMoveCoordinates1 = {{3, 3}, {5, 2}}; // Valid L-shaped move
        Assertions.assertTrue(knight.isLegitMove(validMoveCoordinates1, board));

        int[][] validMoveCoordinates2 = {{3, 3}, {4, 5}}; // Valid L-shaped move
        Assertions.assertTrue(knight.isLegitMove(validMoveCoordinates2, board));
    }
    
    @Test
    public void testKnightInvalidMove() {
        int color = Constants.WHITE;
        Knight knight = new Knight(color);
        Board board = new Board(); // Assuming a valid Board class implementation

        // Set up the board with a knight at (3, 3) position
        int startRow = 3;
        int startCol = 3;
        board.setPiece(startRow, startCol, new Knight(Constants.WHITE));

        int[][] invalidMoveCoordinates1 = {{3, 3}, {4, 3}}; // Invalid non-L-shaped move
        Assertions.assertFalse(knight.isLegitMove(invalidMoveCoordinates1, board));
        
        int[][] invalidMoveCoordinates2 = {{3, 3}, {2, 4}}; // Invalid non-L-shaped move
        Assertions.assertFalse(knight.isLegitMove(invalidMoveCoordinates2, board));
    }
    
    @Test
    void testGetValidMoves() {
        // Create a test board
        Board board = new Board();
        // Set up the board with a knight at (3, 3) position
        int startRow = 3;
        int startCol = 3;
        board.setPiece(startRow, startCol, new Knight(Constants.WHITE));

        // Get the valid moves for the knight
        Piece knight = board.getPiece(startRow, startCol);
        List<int[][]> validMoves = knight.getValidMoves(startRow, startCol, board);

        // Define the expected valid move coordinates
        int[][] expectedMove1 = {{3, 3}, {5, 2}};
        int[][] expectedMove2 = {{3, 3}, {5, 4}};
        int[][] expectedMove3 = {{3, 3}, {4, 1}};
        int[][] expectedMove4 = {{3, 3}, {4, 5}};
        int[][] expectedMove5 = {{3, 3}, {2, 1}};
        int[][] expectedMove6 = {{3, 3}, {2, 5}};
        int[][] expectedMove7 = {{3, 3}, {1, 2}};
        int[][] expectedMove8 = {{3, 3}, {1, 4}};

        // Convert the validMoves list to a List of Strings
        List<String> validMovesAsString = new ArrayList<>();
        for (int[][] move : validMoves) {
            validMovesAsString.add(Arrays.deepToString(move));
        }

        // Assert that the valid moves contain the expected move coordinates
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove1)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove2)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove3)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove4)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove5)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove6)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove7)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove8)));
    }
}
