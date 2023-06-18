package UnitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import Boards.Board;
import Pieces.Bishop;
import Pieces.Piece;
import Game.Constants;

public class BishopUnitTest {
    @Test
    public void testBishopInitialization() {
        int color = Constants.WHITE;
        Bishop bishop = new Bishop(color);
        
        Assertions.assertEquals(color, bishop.getColor());
    }
    
    @Test
    public void testBishopValidMove() {
        int color = Constants.WHITE;
        Bishop bishop = new Bishop(color);
        Board board = new Board(); // Assuming a valid Board class implementation
        
        // Set up the board with a bishop at (3, 2) position
        int startRow = 3;
        int startCol = 2;
        board.setPiece(startRow, startCol, new Bishop(Constants.WHITE));

        int[][] validMoveCoordinates1 = {{3, 2}, {5, 0}}; // Valid diagonal move
        Assertions.assertTrue(bishop.isLegitMove(validMoveCoordinates1, board));

        int[][] validMoveCoordinates2 = {{4, 3}, {2, 5}}; // Valid diagonal move
        Assertions.assertTrue(bishop.isLegitMove(validMoveCoordinates2, board));
    }
    
    @Test
    public void testBishopInvalidMove() {
        int color = Constants.WHITE;
        Bishop bishop = new Bishop(color);
        Board board = new Board(); // Assuming a valid Board class implementation

        // Set up the board with a bishop at (3, 2) position
        int startRow = 3;
        int startCol = 2;
        board.setPiece(startRow, startCol, new Bishop(Constants.WHITE));

        int[][] invalidMoveCoordinates1 = {{3, 2}, {4, 0}}; // Invalid non-diagonal move
        Assertions.assertFalse(bishop.isLegitMove(invalidMoveCoordinates1, board));
        
        int[][] invalidMoveCoordinates2 = {{4, 3}, {3, 5}}; // Invalid non-diagonal move
        Assertions.assertFalse(bishop.isLegitMove(invalidMoveCoordinates2, board));
    }
    
    @Test
    void testGetValidMoves() {
        // Create a test board
        Board board = new Board();
        // Set up the board with a bishop at (3, 2) position
        int startRow = 3;
        int startCol = 2;
        board.setPiece(startRow, startCol, new Bishop(Constants.WHITE));

        // Get the valid moves for the bishop
        Piece bishop = board.getPiece(startRow, startCol);
        List<int[][]> validMoves = bishop.getValidMoves(startRow, startCol, board);

        // Define the expected valid move coordinates
        int[][] expectedMove1 = {{3, 2}, {5, 0}};
        int[][] expectedMove2 = {{3, 2}, {5, 4}};
        int[][] expectedMove3 = {{3, 2}, {4, 1}};
        int[][] expectedMove4 = {{3, 2}, {4, 3}};
        int[][] expectedMove5 = {{3, 2}, {2, 1}};
        int[][] expectedMove6 = {{3, 2}, {2, 3}};

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
    }
}