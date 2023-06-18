package UnitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Boards.Board;
import Pieces.Piece;
import Pieces.Rook;
import Game.Constants;

public class RookUnitTest {
    @Test
    public void testRookInitialization() {
        int color = Constants.WHITE;
        Rook rook = new Rook(color);

        Assertions.assertEquals(color, rook.getColor());
    }

    @Test
    public void testRookValidMove() {
        int color = Constants.WHITE;
        Rook rook = new Rook(color);
        Board board = new Board(); // Assuming a valid Board class implementation

        // Set up the board with a rook at (3, 2) position
        int startRow = 3;
        int startCol = 2;
        board.setPiece(startRow, startCol, new Rook(Constants.WHITE));

        int[][] validMoveCoordinates1 = {{3, 2}, {5, 2}}; // Valid vertical move
        Assertions.assertTrue(rook.isLegitMove(validMoveCoordinates1, board));

        int[][] validMoveCoordinates2 = {{3, 2}, {1, 2}}; // Valid vertical move
        Assertions.assertTrue(rook.isLegitMove(validMoveCoordinates2, board));

        int[][] validMoveCoordinates3 = {{3, 2}, {3, 0}}; // Valid horizontal move
        Assertions.assertTrue(rook.isLegitMove(validMoveCoordinates3, board));

        int[][] validMoveCoordinates4 = {{3, 2}, {3, 4}}; // Valid horizontal move
        Assertions.assertTrue(rook.isLegitMove(validMoveCoordinates4, board));
    }

    @Test
    public void testRookInvalidMove() {
        int color = Constants.WHITE;
        Rook rook = new Rook(color);
        Board board = new Board(); // Assuming a valid Board class implementation

        // Set up the board with a rook at (3, 2) position
        int startRow = 3;
        int startCol = 2;
        board.setPiece(startRow, startCol, new Rook(Constants.WHITE));

        int[][] invalidMoveCoordinates1 = {{3, 2}, {4, 3}}; // Invalid diagonal move
        Assertions.assertFalse(rook.isLegitMove(invalidMoveCoordinates1, board));

        int[][] invalidMoveCoordinates2 = {{3, 2}, {2, 1}}; // Invalid diagonal move
        Assertions.assertFalse(rook.isLegitMove(invalidMoveCoordinates2, board));
    }

    @Test
    void testGetValidMoves() {
        // Create a test board
        Board board = new Board();
        // Set up the board with a rook at (3, 2) position
        int startRow = 3;
        int startCol = 2;
        board.setPiece(startRow, startCol, new Rook(Constants.WHITE));

        // Get the valid moves for the rook
        Piece rook = board.getPiece(startRow, startCol);
        List<int[][]> validMoves = rook.getValidMoves(startRow, startCol, board);

        // Define the expected valid move coordinates
        int[][] expectedMove1 = {{3, 2}, {5, 2}};
        int[][] expectedMove2 = {{3, 2}, {1, 2}};
        int[][] expectedMove3 = {{3, 2}, {3, 0}};
        int[][] expectedMove4 = {{3, 2}, {3, 4}};
        int[][] expectedMove5 = {{3, 2}, {4, 2}};
        int[][] expectedMove6 = {{3, 2}, {2, 2}};    
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