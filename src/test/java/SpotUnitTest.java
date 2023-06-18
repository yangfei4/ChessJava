package UnitTest;

import Boards.Spot;
import Pieces.Pawn;
import Game.Constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpotUnitTest {
    @Test
    void testSetPieceAndGetPiece() {
        // Create a new piece
        Pawn piece = new Pawn(Constants.WHITE);
        
        // Create a spot and set the piece
        Spot spot = new Spot(null);
        spot.setPiece(piece);
        
        // Assert that the piece in the spot matches the set piece
        assertEquals(piece, spot.getPiece());
    }
    
    @Test
    void testRemovePieceAndHasPiece() {
        // Create a new piece
        Pawn piece = new Pawn(Constants.WHITE);
        
        // Create a spot and set the piece
        Spot spot = new Spot(piece);
        
        // Remove the piece from the spot
        spot.removePiece();
        
        // Assert that the spot no longer has a piece
        assertFalse(spot.hasPiece());
    }
}