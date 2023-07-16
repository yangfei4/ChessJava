package Game;

import Boards.*;
import Pieces.*;

import java.util.*;

import javax.swing.*;

public class Game implements MoveCallback{
    private Board board;
    private DrawBoard drawBoard;
    private GAMESTATUS gameStatus;
    private int cur_color;

    public Game(){
        this.board = new Board();
        this.drawBoard = new DrawBoard(this.board, this);
        this.gameStatus = GAMESTATUS.PLAY;
        this.cur_color = Constants.WHITE;
    }

    @Override
    public void Action(JPanel square) {
        switch(gameStatus){
            case PLAY:
                play(square);
            case CHECKED:
                checked();
        }
    }

    public void play(JPanel square){
        int[][] moveCoordinates = drawBoard.selectSquare(square, cur_color);
        int color_opponent = (cur_color==Constants.WHITE)?Constants.BLACK:Constants.WHITE;
        String opponent = (cur_color==Constants.WHITE)?"Black":"White";

        if(checkMoveVadility(moveCoordinates)){
            movePiece(moveCoordinates);
            if(board.isChecked(color_opponent)){
                gameStatus = GAMESTATUS.CHECKED;
                this.drawBoard.updateStatus(opponent + " is checked!! ⚠⚠⚠");
            }
            else{
                this.drawBoard.updateStatus(opponent + "'s turn");
            }
            cur_color = color_opponent;
        }
    }

    public void checked(){
        List<int[][]> validMoves = board.getAllValidMoves(cur_color);
        String opponent = (cur_color==Constants.WHITE)?"Black":"White";

        if (validMoves.isEmpty()) {
            this.drawBoard.updateStatus("Checkmate! "+ opponent + " wins!");
            // Perform any other actions or display messages for a checkmate situation
        } else {
            // Handle the situation when white is checked and can escape check
            gameStatus = GAMESTATUS.PLAY;
        }
    }

    @Override
    public void restartGame() {
        board = new Board();
        drawBoard.updateBoard(board);
        drawBoard.initCanvas();
        this.gameStatus = GAMESTATUS.PLAY;
        this.cur_color = Constants.WHITE;
    }

    public void movePiece(int[][] moveCoordinates){
        int startRow=moveCoordinates[0][0], startCol=moveCoordinates[0][1];
        int endRow=moveCoordinates[1][0], endCol=moveCoordinates[1][1];
        //1.update board
        Piece piece = board.getPiece(startRow, startCol);
        board.setPiece(endRow, endCol, piece);
        board.setPiece(startRow, startCol, null);
        drawBoard.updateBoard(this.board);

        //2.repaint the canvas
        JPanel selectedSquare = drawBoard.getSelectedSquare();
        JPanel destinationSquare = drawBoard.getDestinationSquare();
        drawBoard.erasePiece(selectedSquare);
        drawBoard.drawPiece(destinationSquare, piece.getSymbol());
        drawBoard.cleanSelection();

        if (piece instanceof King) {
            board.setKingPosition(piece.getColor(), endRow, endCol);
        }

        System.out.println(piece.getSymbol() + " moved");
        System.out.println(Arrays.deepToString(moveCoordinates));
    }

    public boolean checkMoveVadility(int[][] moveCoordinates){
        int startRow=moveCoordinates[0][0], startCol=moveCoordinates[0][1];

        // only selected square, haven't move
        if(startCol==-1)
            return false;

        Piece piece = board.getPiece(startRow, startCol);
        // not follow the rule of piece movement
        if(piece==null)
            System.out.println("Piece is null!!!");
        else if(!piece.isLegitMove(moveCoordinates, board))
            return false;

        if(piece instanceof Pawn)
            piece.setMoved();
        return true;
    }

    enum GAMESTATUS{
        PLAY, CHECKED
        //WHITE_PLAY, BLACK_PLAY, WHITE_CHECKED, BLACK_CHECKED, WHITE_WIN, BLACK_WIN
        // CHECKMATE
        // STALEMATE, //和棋
        // DRAW, //平局
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}