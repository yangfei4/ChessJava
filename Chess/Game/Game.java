package Game;

import Boards.*;
import Pieces.*;

import java.util.*;

import javax.swing.*;


public class Game implements MoveCallback{
    private Board board;
    private DrawBoard drawBoard;
    private GAMESTATUS gameStatus;

    public Game(){
        this.board = new Board();
        this.drawBoard = new DrawBoard(this.board, this);
        this.gameStatus = GAMESTATUS.WHITE_PLAY;
    }

    @Override
    public void Action(JPanel square) {
        int[][] moveCoordinates;
        List<int[][]> validMoves;

        switch(gameStatus){
            case WHITE_PLAY:
                moveCoordinates = drawBoard.selectSquare(square, Constants.WHITE);
                if(checkMoveVadility(moveCoordinates)){
                    movePiece(moveCoordinates);
                    if(board.isChecked(Constants.BLACK)){
                        gameStatus = GAMESTATUS.BLACK_CHECKED;
                        this.drawBoard.updateStatus("Black is checked!! ⚠⚠⚠");
                    }
                    else{
                        gameStatus = GAMESTATUS.BLACK_PLAY;
                        this.drawBoard.updateStatus("Black's turn");
                        break;
                    }
                }
                else
                    break;
            case BLACK_PLAY:
                moveCoordinates = drawBoard.selectSquare(square, Constants.BLACK);
                if(checkMoveVadility(moveCoordinates)){
                    movePiece(moveCoordinates);
                    if(board.isChecked(Constants.WHITE)){
                        gameStatus = GAMESTATUS.WHITE_CHECKED;
                        this.drawBoard.updateStatus("White is checked!! ⚠⚠⚠");
                    }
                    else{
                        gameStatus = GAMESTATUS.WHITE_PLAY;
                        this.drawBoard.updateStatus("White's turn");
                        break;
                    }                
                }
                else
                    break;
            case WHITE_CHECKED:
                validMoves = board.getAllValidMoves(Constants.WHITE);
                if (validMoves.isEmpty()) {
                    // Handle the situation when white is checked and cannot escape check
                    gameStatus = GAMESTATUS.BLACK_WIN;
                    this.drawBoard.updateStatus("Checkmate! Black wins!");
                    // Perform any other actions or display messages for a checkmate situation
                } else {
                    // Handle the situation when white is checked and can escape check
                    gameStatus = GAMESTATUS.WHITE_PLAY;
                }
            case BLACK_CHECKED:
                validMoves = board.getAllValidMoves(Constants.BLACK);
                if (validMoves.isEmpty()) {
                    // Handle the situation when black is checked and cannot escape check
                    gameStatus = GAMESTATUS.WHITE_WIN;
                    this.drawBoard.updateStatus("Checkmate! White wins!");
                    // Perform any other actions or display messages for a checkmate situation
                } else {
                    // Handle the situation when black is checked and can escape check
                    gameStatus = GAMESTATUS.BLACK_PLAY;
                }
            case WHITE_WIN:
                break;
            case BLACK_WIN:
                break;
        }
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

        String c =(piece.getColor()==Constants.WHITE)?"White":"Black";
        System.out.println(c + " moved");
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
        WHITE_PLAY, BLACK_PLAY, WHITE_CHECKED, BLACK_CHECKED, WHITE_WIN, BLACK_WIN
        // CHECKMATE
        // STALEMATE, //和棋
        // DRAW, //平局
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}