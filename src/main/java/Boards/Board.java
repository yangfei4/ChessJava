package Boards;

import Game.*;
import Pieces.*;
import java.util.*;

public class Board {
    public int BOARD_SIZE = Constants.BOARD_SIZE;
    public int WHITE = Constants.WHITE;
    public int BLACK = Constants.BLACK;

    private int whiteKingRow; // Row position of the white king
    private int whiteKingCol; // Column position of the white king
    private int blackKingRow; // Row position of the black king
    private int blackKingCol; // Column position of the black king    

    private Spot[][] board;

    public Board(){ // Initialize 8x8 Board
        this.resetBoard();
    }

    public void resetBoard(){
        this.board = new Spot[BOARD_SIZE][BOARD_SIZE];

        /* Assign some grids with a Spot(x,y,piece) for playes */ 
        for(int color=0; color<=1; color++){ // WHITE=0 BLACK=1
            int row1 = (color==WHITE)?7:0;
            int row2 = (color==WHITE)?6:1;
            // Rook
            this.board[row1][0] = new Spot(new Rook(color));
            this.board[row1][7] = new Spot(new Rook(color));
            // Knight
            this.board[row1][1] = new Spot(new Knight(color));
            this.board[row1][6] = new Spot(new Knight(color));
            // Bishop
            this.board[row1][2] = new Spot(new Bishop(color));
            this.board[row1][5] = new Spot(new Bishop(color));
            // Queen
            this.board[row1][3] = new Spot(new Queen(color));
            // King
            this.board[row1][4] = new Spot(new King(color));
            // Set King's position
            setKingPosition(color, row1, 4);
            // Pawn
            for(int y=0; y<8; y++)
                this.board[row2][y] = new Spot(new Pawn(color));
        }

        for(int x=2; x<=5; x++){
            for(int y=0; y<8; y++){
                this.board[x][y] = new Spot(null);
            }
        }
    }

    public Piece getPiece(int x, int y){ 
        if(x<0 || y<0 || x>=BOARD_SIZE || y>=BOARD_SIZE){
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        else
            return this.board[x][y].getPiece();
    }

    public void setPiece(int x, int y, Piece p){
        if(x<0 || y<0 || x>=BOARD_SIZE || y>=BOARD_SIZE){
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        else
            this.board[x][y].setPiece(p);
    }

    public boolean hasPiece(int x, int y){
        return board[x][y].hasPiece();
    }

    public boolean isSquareUnderAttack(int row, int col, int attackingColor) {
        // Check if the specified square is under attack by any opponent piece
        // Check for Rooks and Queens attacking horizontally or vertically
        int[][] rookDirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : rookDirections) {
            int x = row + direction[0];
            int y = col + direction[1];
    
            while (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE) {
                Piece piece = getPiece(x, y);
                if (piece != null) {
                    if (piece.getColor()!= attackingColor && (piece instanceof Rook || piece instanceof Queen)) {
                        return true; // Square is under attack by a Rook or Queen
                    } else {
                        break; // Path is blocked by another piece
                    }
                }
                x += direction[0];
                y += direction[1];
            }
        }
    
        // Check for Bishops and Queens attacking diagonally
        int[][] bishopDirections = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] direction : bishopDirections) {
            int x = row + direction[0];
            int y = col + direction[1];
    
            while (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE) {
                Piece piece = getPiece(x, y);
                if (piece != null) {
                    if (piece.getColor() != attackingColor && (piece instanceof Bishop || piece instanceof Queen)) {
                        return true; // Square is under attack by a Bishop or Queen
                    } else {
                        break; // Path is blocked by another piece
                    }
                }
                x += direction[0];
                y += direction[1];
            }
        }
    
        // Check for Knights attacking
        int[][] knightMoves = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        for (int[] move : knightMoves) {
            int x = row + move[0];
            int y = col + move[1];
    
            if (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE) {
                Piece piece = getPiece(x, y);
                if (piece != null && piece.getColor() != attackingColor && piece instanceof Knight) {
                        return true; // Square is under attack by a Knight
                }
            }
        }
    
        // Check for Pawns attacking
        int pawnDirection = (attackingColor == WHITE) ? 1 : -1;
        int[][] pawnMoves = {{pawnDirection, -1}, {pawnDirection, 1}};
        for (int[] move : pawnMoves) {
            int x = row + move[0];
            int y = col + move[1];
    
            if (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE) {
                Piece piece = getPiece(x, y);
                if (piece != null && piece.getColor() != attackingColor && piece instanceof Pawn) {
                        return true; // Square is under attack by a Pawn
                }
            }
        }
    
        // Check for Kings attacking
        int[][] kingMoves = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] move : kingMoves) {
            int x = row + move[0];
            int y = col + move[1];

            if (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE) {
                Piece piece = getPiece(x, y);
                if (piece != null && piece.getColor() != attackingColor && piece instanceof King) {
                    return true; // Square is under attack by a King
                }
            }
        }

        return false; // Square is not under attack by any opponent piece
    }

    public void setKingPosition(int color, int row, int col) {
        if (color == WHITE) {
            whiteKingRow = row;
            whiteKingCol = col;
        } else {
            blackKingRow = row;
            blackKingCol = col;
        }
    }

    public int[] getKingPosition(int color) {
        if (color == WHITE) {
            return new int[]{whiteKingRow, whiteKingCol};
        } else {
            return new int[]{blackKingRow, blackKingCol};
        }
    }

    public boolean isChecked(int color) {
        int[] kingPos = getKingPosition(color);
        int kingRow = kingPos[0];
        int kingCol = kingPos[1];
    
        // Check if the king is under attack
        return isSquareUnderAttack(kingRow, kingCol, color);
    }

    public List<int[][]> getAllValidMoves(int color) {
        List<int[][]> validMoves = new ArrayList<>();
    
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = getPiece(row, col);
    
                if (piece != null && piece.getColor() == color) {
                    List<int[][]> pieceValidMoves = piece.getValidMoves(row, col, this);
    
                    for (int[][] move : pieceValidMoves) {
                        // 尝试移动棋子并检查是否仍然被将军
                        int startRow = move[0][0];
                        int startCol = move[0][1];
                        int endRow = move[1][0];
                        int endCol = move[1][1];
    
                        Piece capturedPiece = getPiece(endRow, endCol);
    
                        // 尝试移动棋子
                        setPiece(endRow, endCol, piece);
                        setPiece(startRow, startCol, null);
    
                        if (!isChecked(color)) {
                            // 移动有效，添加到有效移动列表中
                            validMoves.add(move);
                        }
    
                        // 恢复棋盘状态
                        setPiece(startRow, startCol, piece);
                        setPiece(endRow, endCol, capturedPiece);
                    }
                }
            }
        }
    
        return validMoves;
    }
    
}
