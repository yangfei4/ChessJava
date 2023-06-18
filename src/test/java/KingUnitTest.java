package UnitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Boards.Board;
import Pieces.King;
import Pieces.Piece;
import Game.Constants;

public class KingUnitTest {
    @Test
    public void testKingInitialization() {
        int color = Constants.WHITE;
        King king = new King(color);

        Assertions.assertEquals(color, king.getColor());
    }

    @Test
    public void testKingValidMove() {
        int color = Constants.WHITE;
        King king = new King(color);
        Board board = new Board(); // Assuming a valid Board class implementation

        // Set up the board with a king at (3, 3) position
        int startRow = 3;
        int startCol = 3;
        board.setPiece(startRow, startCol, new King(Constants.WHITE));

        int[][] validMoveCoordinates1 = {{3, 3}, {2, 3}}; // Valid move one step up
        Assertions.assertTrue(king.isLegitMove(validMoveCoordinates1, board));

        int[][] validMoveCoordinates2 = {{3, 3}, {4, 3}}; // Valid move one step down
        Assertions.assertTrue(king.isLegitMove(validMoveCoordinates2, board));

        int[][] validMoveCoordinates3 = {{3, 3}, {3, 2}}; // Valid move one step left
        Assertions.assertTrue(king.isLegitMove(validMoveCoordinates3, board));

        int[][] validMoveCoordinates4 = {{3, 3}, {3, 4}}; // Valid move one step right
        Assertions.assertTrue(king.isLegitMove(validMoveCoordinates4, board));

        int[][] validMoveCoordinates5 = {{3, 3}, {4, 4}}; // Valid move one step diagonally down-right
        Assertions.assertTrue(king.isLegitMove(validMoveCoordinates5, board));
    }

    @Test
    public void testKingInvalidMove() {
        int color = Constants.WHITE;
        King king = new King(color);
        Board board = new Board(); // Assuming a valid Board class implementation

        // Set up the board with a king at (3, 3) position
        int startRow = 3;
        int startCol = 3;
        board.setPiece(startRow, startCol, new King(Constants.WHITE));

        int[][] invalidMoveCoordinates1 = {{3, 3}, {3, 1}}; // Invalid move two steps left
        Assertions.assertFalse(king.isLegitMove(invalidMoveCoordinates1, board));

        int[][] invalidMoveCoordinates2 = {{3, 3}, {1, 3}}; // Invalid move two steps up
        Assertions.assertFalse(king.isLegitMove(invalidMoveCoordinates2, board));

        int[][] invalidMoveCoordinates3 = {{3, 3}, {5, 3}}; // Invalid move two steps down
        Assertions.assertFalse(king.isLegitMove(invalidMoveCoordinates3, board));

        int[][] invalidMoveCoordinates4 = {{3, 3}, {3, 5}}; // Invalid move two steps right
        Assertions.assertFalse(king.isLegitMove(invalidMoveCoordinates4, board));

        int[][] invalidMoveCoordinates5 = {{3, 3}, {1, 1}}; // Invalid move two steps diagonally up-left
        Assertions.assertFalse(king.isLegitMove(invalidMoveCoordinates5, board));

        int[][] invalidMoveCoordinates6 = {{3, 3}, {5, 5}}; // Invalid move two steps diagonally down-left
        Assertions.assertFalse(king.isLegitMove(invalidMoveCoordinates6, board));

        int[][] invalidMoveCoordinates7 = {{3, 3}, {1, 5}}; // Invalid move two steps diagonally up-right
        Assertions.assertFalse(king.isLegitMove(invalidMoveCoordinates7, board));

        int[][] invalidMoveCoordinates8 = {{3, 3}, {5, 5}}; // Invalid move two steps diagonally down-right
        Assertions.assertFalse(king.isLegitMove(invalidMoveCoordinates8, board));
    }

    @Test
    void testGetValidMoves() {
        // Create a test board
        Board board = new Board();
        // Set up the board with a king at (3, 3) position
        int startRow = 3;
        int startCol = 3;
        board.setPiece(startRow, startCol, new King(Constants.WHITE));

        // Get the valid moves for the king
        Piece king = board.getPiece(startRow, startCol);
        List<int[][]> validMoves = king.getValidMoves(startRow, startCol, board);

        // Define the expected valid move coordinates
        int[][] expectedMove1 = {{3, 3}, {2, 3}};
        int[][] expectedMove2 = {{3, 3}, {4, 3}};
        int[][] expectedMove3 = {{3, 3}, {3, 2}};
        int[][] expectedMove4 = {{3, 3}, {3, 4}};
        int[][] expectedMove5 = {{3, 3}, {2, 2}};
        int[][] expectedMove6 = {{3, 3}, {4, 4}};
        
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
        Assertions.assertFalse(validMovesAsString.contains(Arrays.deepToString(expectedMove5)));
        Assertions.assertTrue(validMovesAsString.contains(Arrays.deepToString(expectedMove6)));
    }
}