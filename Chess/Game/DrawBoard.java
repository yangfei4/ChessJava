package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import Pieces.*;
import Boards.Board;

public class DrawBoard extends JFrame{
    private int boardSize=Constants.BOARD_SIZE;

    private Board board;
    private JPanel[][] squares;

    public DrawBoard(Board board){
        this.board = board;
        this.squares = new JPanel[boardSize][boardSize];

        initCanvas();
    }

    public void initCanvas(){
        setSize(600, 600);
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(boardSize, boardSize));

        // Initialize and Draw the chess board squares
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                JPanel square = new JPanel();

                square.setBackground((x + y) % 2 == 0 ? Color.WHITE : Color.GRAY);
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                // square.addMouseListener(new SquareMouseListener());
                add(square);

                // Get symbol for a piece
                if(board.hasPiece(x, y)){
                    Piece cur_piece = board.getPiece(x, y);
                    drawPiece(square, cur_piece.getSymbol()); // Set and repaint the piece
                }
                
                squares[x][y] = square;
            }
        }

        setVisible(true);
    }


    // Put a piece on a spot
    public void drawPiece(JPanel square, String pieceSymbol) {
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

    // remove a piece on a spot
    public void erasePiece(JPanel square) {
        // Remove any existing components in the square panel
        square.removeAll();

        // Repaint the square panel to reflect the changes
        square.revalidate();
        square.repaint();
    }

    public JPanel[][] getCanvas(){
        return this.squares;
    }

}