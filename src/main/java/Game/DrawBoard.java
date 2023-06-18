package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import Pieces.*;
import Boards.Board;

public class DrawBoard extends JFrame{
    private int boardSize=Constants.BOARD_SIZE;

    private Board board;
    private JPanel[][] squares;
    private JPanel selectedSquare;
    private JPanel destinationSquare;
    private JLabel statusLabel;

    private MoveCallback moveCallback;

    public DrawBoard(Board board, MoveCallback moveCallback){
        this.board = board;
        this.squares = new JPanel[boardSize][boardSize];
        this.moveCallback = moveCallback;
        initCanvas();
    }

    public void initCanvas(){
        setSize(600, 675);
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the chess board panel
        JPanel boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
        add(boardPanel, BorderLayout.CENTER);

        // Create a panel for the status label
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setPreferredSize(new Dimension(600, 75));
        add(statusPanel, BorderLayout.SOUTH);

        statusLabel = new JLabel("White's turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(statusLabel.getFont().deriveFont(30f));
        statusLabel.setPreferredSize(new Dimension(400, 75));
        statusPanel.add(statusLabel, BorderLayout.WEST);

        // Create the restart button
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(restartButton.getFont().deriveFont(20f));
        restartButton.setPreferredSize(new Dimension(200, 75));
        // Create a panel for the restart button
        statusPanel.add(restartButton, BorderLayout.EAST);
        restartButton.addActionListener(e -> moveCallback.restartGame());

        // Initialize and Draw the chess board squares
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                JPanel square = new JPanel();

                square.setBackground((x + y) % 2 == 0 ? Color.WHITE : Color.GRAY);
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                square.addMouseListener(new SquareMouseListener());
                boardPanel.add(square);

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

    private class SquareMouseListener implements MouseListener {
        // MouseListener methods
        @Override
        public void mouseClicked(MouseEvent e) {
            // Handle mouse click on the square
            JPanel square = (JPanel) e.getSource();
            moveCallback.Action(square);
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

    public int[][] selectSquare(JPanel square, int color){
        int[] rowCol = getRowCol(square);
        int row = rowCol[0], col = rowCol[1];
        Piece piece = board.getPiece(row, col);

        if((piece!=null && piece.getColor()==color)){
            cleanSelection();
            
            selectedSquare = square;
            selectedSquare.setBackground(Color.GREEN);
            return new int[][] {{-1, -1}, {-1, -1}}; // 第一次点击，返回无效坐标
        }

        if(selectedSquare!=null)
        {
            int[] pre_rowCol = getRowCol(selectedSquare);
            int pre_row = pre_rowCol[0], pre_col = pre_rowCol[1];

            destinationSquare = square;
            return new int[][] {{pre_row, pre_col}, {row, col}};
        }

        return new int[][] {{-1, -1}, {-1, -1}}; // 第一次点击，返回无效坐标
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

    public int[] getRowCol(JPanel square) {
        int row = (square.getY() + 30) / square.getWidth();
        int col = (square.getX() + 30) / square.getWidth();
        int[] rowCol = {row, col};
        return rowCol;
    }

    // remove a piece on a spot
    public void erasePiece(JPanel square) {
        // Remove any existing components in the square panel
        square.removeAll();

        // Repaint the square panel to reflect the changes
        square.revalidate();
        square.repaint();
    }

    public JPanel getDestinationSquare(){
        return this.destinationSquare;
    }

    public JPanel getSelectedSquare(){
        return this.selectedSquare;
    }

    public void cleanSelection(){
        if(selectedSquare!=null){
            int[] pre_rowCol = getRowCol(selectedSquare);
            int pre_row = pre_rowCol[0], pre_col = pre_rowCol[1];
            selectedSquare.setBackground((pre_row + pre_col) % 2 == 0 ? Color.WHITE : Color.GRAY);
            selectedSquare = null;
        }
        if(destinationSquare!=null)
            destinationSquare = null;
    }

    public void setSelectedSquare(JPanel square){
        this.selectedSquare = square;
        this.selectedSquare.setBackground(Color.GREEN);
    }

    public void updateBoard(Board board){
        this.board = board;
    }

    public void updateStatus(String stauts){
        statusLabel.setText(stauts);
    }

    public JPanel[][] getCanvas(){
        return this.squares;
    }
}