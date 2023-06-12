package Game;

import Game.*;
import Boards.*;
import Pieces.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.desktop.SystemSleepEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Principal;

public class Game{
    private int pawnId;
    private int rookId;
    private int knightId;
    private int bishopId;
    private int queenId;
    private int kingId;
    private int boardSize;

    private Board board;
    private JPanel selectedSquare;
    private JPanel[][] canvas;
    private DrawBoard drawBoard;

    public Game(){
        initConstants();

        this.board = new Board();
        this.drawBoard = new DrawBoard(this.board);
        this.canvas = drawBoard.getCanvas();

        for(int row=0; row<boardSize; row++){
            for(int col=0; col<boardSize; col++){
                JPanel square = this.canvas[row][col];
                square.addMouseListener(new SquareMouseListener());
            }
        }

    }

    private void initConstants(){
        board = new Board();

        this.boardSize = Constants.BOARD_SIZE;
        this.pawnId = Constants.PAWN_ID;
        this.rookId = Constants.ROOK_ID;
        this.knightId = Constants.KNIGHT_ID;
        this.bishopId = Constants.BISHOP_ID;
        this.queenId = Constants.QUEEN_ID;
        this.kingId = Constants.KING_ID;
    }

    // enum GameStatus{
    //     ONGOING,
    //     CHECK,
    //     CHECKMATE,
    //     // STALEMATE, //和棋
    //     // DRAW, //平局
    //     WHITE_WIN,
    //     BLACK_WIN;
    // }

    private class SquareMouseListener implements MouseListener {
        // MouseListener methods
        @Override
        public void mouseClicked(MouseEvent e) {
            // Handle mouse click on the square
            JPanel square = (JPanel) e.getSource();

            // first click to select a piece
            if (selectedSquare == null) {
                selectSquare(square);
            }
            // second click to move a selected piece
            else{
                operateSquare(square);
            }
        }
        
        public void selectSquare(JPanel square){
            int[] rowCol = getRowCol(square);
            int row = rowCol[0], col = rowCol[1];
            
            if(board.hasPiece(row, col)){
                selectedSquare = square;
                selectedSquare.setBackground(Color.GREEN);
            }
        }

        public void operateSquare(JPanel square){
            // Second click - move piece

            int[] pre_rowCol = getRowCol(selectedSquare);
            int pre_row = pre_rowCol[0], pre_col = pre_rowCol[1];

            Piece piece = board.getPiece(pre_row, pre_col);

            int[] rowCol = getRowCol(square);
            int row = rowCol[0], col = rowCol[1];

            if(piece.isLegitMove(pre_col, pre_col, row, col)){

                // remove piece from its original position
                board.setPiece(pre_row, pre_col, null);
                
                // move piece to end position
                board.setPiece(row, col, piece);

                // clean previous squares
                drawBoard.erasePiece(selectedSquare);
                
                // repaint current square
                drawBoard.drawPiece(square, piece.getSymbol());
            }

            // reset the first clicked square
            selectedSquare.setBackground((pre_row + pre_col) % 2 == 0 ? Color.WHITE : Color.GRAY);
            selectedSquare = null;
        }

        private int[] getRowCol(JPanel square) {
            int row = (square.getY() + 30) / square.getWidth();
            int col = (square.getX() + 30) / square.getWidth();
            int[] rowCol = {row, col};
            return rowCol;
        }
        

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}