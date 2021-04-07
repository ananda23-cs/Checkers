package com.example.checkers;

import android.widget.ImageButton;
import android.widget.TextView;

import com.example.checkers.game.GameFramework.infoMessage.GameState;

public class CheckersGameState extends GameState {

    // instance variables
    public CheckersPiece[] p1Pieces;//if the grid is pieces this might not be needed
    public CheckersPiece[] p2Pieces;//if the grid is pieces this might not be needed
    public int p1NumPieces;
    public int p2NumPieces;
    public boolean pieceSelectedBoolean;//this determines if a piece has been selected yet. It was not here before
    public CheckersPiece pieceSelectedPiece;//this is the piece that is going to get moved.this was not here before
    public int playerTurn;
    //add grid here
    public ImageButton[][] board; //displays the 8x8 checkerboard
    TextView gameInfo;

    public CheckersGameState(){
        playerTurn = 0;

        p1NumPieces = 12;
        p2NumPieces = 12;

        // p1 starting coordinates
        p1Pieces = new CheckersPiece[12];


        p1Pieces[0] = new CheckersPiece(1,1,1);
        p1Pieces[1] = new CheckersPiece(3,1,1);
        p1Pieces[2] = new CheckersPiece(5,1,1);
        p1Pieces[3] = new CheckersPiece(7,1,1);
        p1Pieces[4] = new CheckersPiece(2,2,1);
        p1Pieces[5] = new CheckersPiece(4,2,1);
        p1Pieces[6] = new CheckersPiece(6,2,1);
        p1Pieces[7] = new CheckersPiece(8,2,1);
        p1Pieces[8] = new CheckersPiece(7,3,1);
        p1Pieces[9] = new CheckersPiece(5,3,1);
        p1Pieces[10] = new CheckersPiece(3,3,1);
        p1Pieces[11] = new CheckersPiece(1,3,1);

        // p2 starting coordinates
        p2Pieces = new CheckersPiece[12];
        p2Pieces[0] = new CheckersPiece(2,6,2);
        p2Pieces[1] = new CheckersPiece(4,6,2);
        p2Pieces[2] = new CheckersPiece(6,6,2);
        p2Pieces[3]= new CheckersPiece(8,6,2);
        p2Pieces[4] = new CheckersPiece(1,7,2);
        p2Pieces[5] = new CheckersPiece(3,7,2);
        p2Pieces[6] = new CheckersPiece(5,7,2);
        p2Pieces[7] = new CheckersPiece(7,7,2);
        p2Pieces[8] = new CheckersPiece(2,8,2);
        p2Pieces[9] = new CheckersPiece(4,8,2);
        p2Pieces[10] = new CheckersPiece(6,8,2);
        p2Pieces[11] = new CheckersPiece(8,8,2);
    }

    public CheckersGameState(CheckersGameState original){
        for(int i = 0;i<12;i++){
            this.p1Pieces[i] = new CheckersPiece(original.p1Pieces[i]);
            this.p2Pieces[i] = new CheckersPiece(original.p2Pieces[i]);
        }
        this.p2NumPieces = original.p2NumPieces;
        this.p1NumPieces = original.p1NumPieces;
        this.playerTurn = original.playerTurn;
        super.currentSetupTurn = original.currentSetupTurn;
        super.numSetupTurns = original.numSetupTurns;
    }


    public int getP1NumPieces()
    {
        return p1NumPieces;
    }
    public int getP2NumPieces()
    {
        return p1NumPieces;
    }
    public void setP1Pieces(int p1)
    {
        p1NumPieces = p1;
    }
    public void setP2NumPieces(int p2)
    {
        p2NumPieces = p2;

    }
    /*
    public CheckersPiece[] getP1Pieces()
    {
        return p1Pieces;
    }

    public CheckersPiece[] getP2Pieces()
    {
        return p2Pieces;
        }

    public void setP1Pieces(CheckersPiece[] p1)
    {
        p1Pieces = p1;
    }

    public void setP2Pieces(CheckersPiece[] p2)
    {
        p1Pieces = p2;
    }
*/
    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    //this sets the board and displays all the locations of the coordinates
    //this method was not here before we turned it in.
    public void setBoard(ImageButton[][] board){
        //this nested forloop makes a black checker board. The if statements help with the checker pattern
        for(int height=1;height<=8;height++) {
            for(int length=1; length<=8;length++) {
                /*if(height%2 == 1) {
                    if(length%2 == 1) {
                        board[length][height].setImageResource(R.drawable.red_tile);
                    }
                    else{
                        board[length][height].setImageResource(R.drawable.white_tile);
                    }
                }
                else{
                    if(height%2 == 1){
                        board[length][height].setImageResource(R.drawable.white_tile);
                    }
                    else{
                        board[length][height].setImageResource(R.drawable.red_tile);
                    }
                }*/
                if((height + length) % 2 == 0){
                    board[length][height].setImageResource(R.drawable.red_tile);
                }
                else{
                    board[length][height].setImageResource(R.drawable.white_tile);
                }
            }
        }

        //this sets all of player ones pieces on the map.
        for(CheckersPiece piece :  p1Pieces){
            if(piece.getAlive()) {
                board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(R.drawable.black_piece);
            }
        }

        for(CheckersPiece piece :  p2Pieces){
            if(piece.getAlive()) {
                board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(R.drawable.red_piece);
            }
        }
    }

}
