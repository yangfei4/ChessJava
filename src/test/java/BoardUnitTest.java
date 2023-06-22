package UnitTest;

import Boards.Board;
import Game.Constants;
import Pieces.*;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardUnitTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testGetPiece() {
        // Test getting a piece at a valid position
        Piece piece = board.getPiece(0, 0);
        assertNotNull(piece);
        assertTrue(piece instanceof Rook);

        // Test getting a piece at an invalid position
        assertThrows(IndexOutOfBoundsException.class, () -> board.getPiece(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> board.getPiece(8, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> board.getPiece(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> board.getPiece(0, 8));
    }

    @Test
    void testHasPiece() {
        // Test checking if a position has a piece
        assertTrue(board.hasPiece(0, 0));
        assertFalse(board.hasPiece(2, 2));
    }

    @Test
    void testSetPiece() {
        // Test setting a piece at a valid position
        Piece piece = new Pawn(Constants.BLACK);
        board.setPiece(2, 2, piece);
        assertTrue(board.hasPiece(2, 2));
        assertEquals(piece, board.getPiece(2, 2));

        // Test setting a piece at an invalid position
        assertThrows(IndexOutOfBoundsException.class, () -> board.setPiece(-1, 0, piece));
        assertThrows(IndexOutOfBoundsException.class, () -> board.setPiece(8, 0, piece));
        assertThrows(IndexOutOfBoundsException.class, () -> board.setPiece(0, -1, piece));
        assertThrows(IndexOutOfBoundsException.class, () -> board.setPiece(0, 8, piece));
    }

    @Test
    void testIsSquareUnderAttack() {
        // Test when the square is not under attack
        assertFalse(board.isSquareUnderAttack(4, 4, Constants.WHITE));

        // Test when the square is under attack
        assertTrue(board.isSquareUnderAttack(5, 0, Constants.BLACK));
    }

    @Test
    void testGetKingPosition() {
        // Test getting the position of the white king
        int[] whiteKingPos = board.getKingPosition(Constants.WHITE);
        assertEquals(7, whiteKingPos[0]);
        assertEquals(4, whiteKingPos[1]);

        // Test getting the position of the black king
        int[] blackKingPos = board.getKingPosition(Constants.BLACK);
        assertEquals(0, blackKingPos[0]);
        assertEquals(4, blackKingPos[1]);
    }

    @Test
    void testIsChecked() {
        // Test when the white king is checked
        board.setKingPosition(0, 1, Constants.WHITE);
        assertTrue(board.isChecked(Constants.WHITE));

        // Test when the black king is not checked
        assertFalse(board.isChecked(Constants.BLACK));
    }

    @Test
    void testGetAllValidMoves() {
        // Test getting all valid moves for white
        // board.setKingPosition(2, 2, Constants.WHITE);
        List<int[][]> validMovesWhite = board.getAllValidMoves(Constants.WHITE);
        int whiteValidMovesCount = validMovesWhite.size();
        // At the beginning, there 20 ways for openning
        assertEquals(20, whiteValidMovesCount); 
        

        // for (int[][] move : validMoves) {
        //     System.out.println("Valid move: (" + move[0][0] + ", " + move[0][1] + ") to (" + move[1][0] + ", " + move[1][1] + ")");
        // }

        // Test getting all valid moves for black
        // int blackValidMovesCount = board.getAllValidMoves(Constants.BLACK).size();
        // assertEquals(20, blackValidMovesCount);
        List<int[][]> validMovesBalck = board.getAllValidMoves(Constants.BLACK);
        int blackValidMovesCount = validMovesBalck.size();
        assertEquals(20, blackValidMovesCount);
    }
}