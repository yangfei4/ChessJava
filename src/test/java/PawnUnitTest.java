package UnitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Pieces.Pawn;
import Boards.Board;

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
        
        int[][] validMoveCoordinates = {{1, 0}, {2, 0}};
        Assertions.assertTrue(pawn.isLegitMove(validMoveCoordinates, board));
    }
    
    @Test
    public void testPawnInvalidMove() {
        int color = Constants.WHITE;
        Pawn pawn = new Pawn(color);
        Board board = new Board(); // Assuming a valid Board class implementation
        
        int[][] invalidMoveCoordinates = {{1, 0}, {3, 0}};
        Assertions.assertFalse(pawn.isLegitMove(invalidMoveCoordinates, board));
    }
}
