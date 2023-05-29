import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;


// import javax.swing.SwingUtilities;
// import javax.swing.BorderFactory;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.SwingConstants;
import java.util.*;

import Game.*;
import Pieces.*;
import Boards.Board;
import Boards.Spot;

public class ChessGame extends JFrame{
    private int boardSize;
    
    private int pawnId;
    private int rookId;
    private int knightId;
    private int bishopId;
    private int queenId;
    private int kingId;

    private Map<Integer, String> WhiteSymbols;
    private Map<Integer, String> BlackSymbols;

    public ChessGame(){
        Board board = new Board();
        init();
        
        setSize(600, 600);
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(boardSize, boardSize));

        JPanel[][] squares = new JPanel[boardSize][boardSize];

        // Draw the chess board squares
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                JPanel square = new JPanel();
                
                square.setBackground((x + y) % 2 == 0 ? Color.WHITE : Color.GRAY);
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(square);

                Spot cur_spot = board.getSpot(x, y);
                Piece cur_piece = cur_spot.getPiece().orElse(null);

                // Get symbol for a piece
                if(cur_piece!=null){
                    int cur_color = cur_piece.getColor();
                    int cur_pieceID = cur_piece.getPieceID();
                    String cur_symbol = (cur_color==Constants.WHITE)?WhiteSymbols.get(cur_pieceID):BlackSymbols.get(cur_pieceID);
                    setPiece(square, cur_symbol); // Set and repaint the piece
                }
                
                squares[x][y] = square;
            }
        }

        setVisible(true);
    }

    private void init(){
        this.boardSize = Constants.BOARD_SIZE;
        this.pawnId = Constants.PAWN_ID;
        this.rookId = Constants.ROOK_ID;
        this.knightId = Constants.KNIGHT_ID;
        this.bishopId = Constants.BISHOP_ID;
        this.queenId = Constants.QUEEN_ID;
        this.kingId = Constants.KING_ID;

        this.WhiteSymbols = Map.of(this.pawnId, "♙", this.rookId, "♖", this.knightId, "♘",
                                    this.bishopId, "♗", this.queenId, "♕", this.kingId, 
                                    "♔");
        this.BlackSymbols = Map.of(this.pawnId, "♟", this.rookId, "♜", this.knightId, "♞",
                                    this.bishopId, "♝", this.queenId, "♛", this.kingId, 
                                    "♚");
    }

    private static void setPiece(JPanel square, String pieceSymbol) {
        JLabel pieceLabel = new JLabel(pieceSymbol, SwingConstants.CENTER);
        pieceLabel.setFont(new Font("Arial", Font.PLAIN, 48));

        // Remove any existing components in the square panel
        square.removeAll();

        // Set the new pieceLabel as the only component in the square panel
        square.add(pieceLabel);

        // Repaint the square panel to reflect the changes
        square.revalidate();
        square.repaint();
    }

    private static void removePiece(JPanel square, String pieceSymbol) {
        // Remove any existing components in the square panel
        square.removeAll();

        // Repaint the square panel to reflect the changes
        square.revalidate();
        square.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGame::new);
    }

}