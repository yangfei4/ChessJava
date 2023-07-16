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
        
        // Create a spot and set the piece
        Spot spot = new Spot(null);
        assertEquals(spot.getPiece(), null);
    
        // Assert that the piece in the spot matches the set piece
        Pawn piece = new Pawn(Constants.WHITE);
        spot.setPiece(piece);
        assertEquals(piece, spot.getPiece());
    }
    
    @Test
    void testHasPiece(){
        Spot spot_no = new Spot(null);
        assertFalse(spot_no.hasPiece());

        Pawn piece = new Pawn(Constants.WHITE);
        Spot spot_yes = new Spot(piece);
        assertTrue(spot_yes.hasPiece());
    }

    @Test
    void testRemovePiece(){
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