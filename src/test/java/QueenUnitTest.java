package UnitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import Boards.Board;
import Pieces.Piece;
import Pieces.Queen;
import Game.Constants;

public class QueenUnitTest {
    @Test
    public void testQueenInitialization() {
        int color = Constants.WHITE;
        Queen queen = new Queen(color);
        
        Assertions.assertEquals(color, queen.getColor());
    }
    
    @Test
    public void testQueenValidMove() {
        int color = Constants.WHITE;
        Queen queen = new Queen(color);
        Board board = new Board(); // Assuming a valid Board class implementation
        
        // Set up the board with a queen at (3, 3) position
        int startRow = 3;
        int startCol = 3;
        board.setPiece(startRow, startCol, new Queen(Constants.WHITE));

        int[][] validMoveCoordinates1 = {{3, 3}, {2, 2}}; // Valid horizontal move
        Assertions.assertTrue(queen.isLegitMove(validMoveCoordinates1, board));

        int[][] validMoveCoordinates2 = {{3, 3}, {2, 4}}; // Valid vertical move
        Assertions.assertTrue(queen.isLegitMove(validMoveCoordinates2, board));

        int[][] validMoveCoordinates3 = {{3, 3}, {5, 1}}; // Valid diagonal move
        Assertions.assertTrue(queen.isLegitMove(validMoveCoordinates3, board));

        int[][] validMoveCoordinates4 = {{3, 3}, {5, 5}}; // Valid diagonal move
        Assertions.assertTrue(queen.isLegitMove(validMoveCoordinates4, board));
    }
    
    @Test
    public void testQueenInvalidMove() {
        int color = Constants.WHITE;
        Queen queen = new Queen(color);
        Board board = new Board(); // Assuming a valid Board class implementation

        // Set up the board with a queen at (3, 3) position
        int startRow = 3;
        int startCol = 3;
        board.setPiece(startRow, startCol, new Queen(Constants.WHITE));

        int[][] invalidMoveCoordinates1 = {{3, 3}, {4, 0}}; // Invalid non-linear move
        Assertions.assertFalse(queen.isLegitMove(invalidMoveCoordinates1, board));
        
        int[][] invalidMoveCoordinates2 = {{3, 3}, {2, 5}}; // Invalid non-linear move
        Assertions.assertFalse(queen.isLegitMove(invalidMoveCoordinates2, board));
    }
    
    @Test
    void testGetValidMoves() {
        // Create a test board
        Board board = new Board();
        // Set up the board with a queen at (3, 3) position
        int startRow = 3;
        int startCol = 3;
        board.setPiece(startRow, startCol, new Queen(Constants.WHITE));

        // Get the valid moves for the queen
        Piece queen = board.getPiece(startRow, startCol);
        List<int[][]> validMoves = queen.getValidMoves(startRow, startCol, board);

        // Define the expected valid move coordinates
        int[][] expectedMove1 = {{3, 3}, {3, 0}};
        int[][] expectedMove2 = {{3, 3}, {3, 1}};
        int[][] expectedMove3 = {{3, 3}, {3, 2}};
        int[][] expectedMove4 = {{3, 3}, {3, 4}};
        int[][] expectedMove5 = {{3, 3}, {3, 5}};
        int[][] expectedMove6 = {{3, 3}, {3, 6}};
        int[][] expectedMove7 = {{3, 3}, {3, 7}};
        int[][] expectedMove8 = {{3, 3}, {2, 3}};
        int[][] expectedMove9 = {{3, 3}, {4, 3}};
        int[][] expectedMove10 = {{3, 3}, {5, 3}};
        int[][] expectedMove11 = {{3, 3}, {5, 1}};
        int[][] expectedMove12 = {{3, 3}, {4, 2}};
        int[][] expectedMove13 = {{3, 3}, {2, 4}};
        int[][] expectedMove14 = {{3, 3}, {2, 2}};
        int[][] expectedMove15 = {{3, 3}, {4, 4}};
        int[][] expectedMove16 = {{3, 3}, {5, 5}};

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
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove9)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove10)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove11)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove12)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove13)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove14)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove15)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove16)));
    }
}
