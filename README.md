# ChessJava
This repo is an implementation of chess playing using Java.

<p align="center">
  <img src="https://github.com/yangfei4/ChessJava/blob/main/visuals/chess_demo.gif" width="400">
</p>

<p align="center">
  <img src="https://github.com/yangfei4/ChessJava/blob/main/visuals/UML.png" width="800">
</p>

## Commands to run the Game
```
mvn clean compile
java -cp target/classes Game.Game 
```

## Commands to run Unit Test
```
mvn clean test
```

## Project Structure
```
├── README.md
├── lib
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── Boards
│   │   │   │   ├── Board.java
│   │   │   │   └── Spot.java
│   │   │   ├── Drawing_utils
│   │   │   │   └── Drawing.java
│   │   │   ├── Game
│   │   │   │   ├── Constants.java
│   │   │   │   ├── DrawBoard.java
│   │   │   │   ├── Game.java
│   │   │   │   └── MoveCallback.java
│   │   │   └── Pieces
│   │   │       ├── Bishop.java
│   │   │       ├── King.java
│   │   │       ├── Knight.java
│   │   │       ├── Pawn.java
│   │   │       ├── Piece.java
│   │   │       ├── Queen.java
│   │   │       └── Rook.java
│   │   └── resourses
│   └── test
│       ├── java
│       │   ├── BishopUnitTest.java
│       │   ├── BoardTest.java
│       │   ├── KingUnitTest.java
│       │   ├── KnightUnitTest.java
│       │   ├── PawnUnitTest.java
│       │   ├── QueenUnitTest.java
│       │   ├── RookUnitTest.java
│       │   └── SpotUnitTest.java
│       └── resources
├────────────────────────────────
```
