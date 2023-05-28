package Game;

// import Pieces.*;

public class Move {
    // private Piece piece;
    private Position startPosition;
    private Position endPosition;

    public Move(Position starPosition, Position endPosition){
        this.startPosition = starPosition;
        this.endPosition = endPosition;
    }

    public Position getStartPosition(){
        return this.startPosition;
    }

    public Position getEndPosition(){
        return this.endPosition;
    }

}