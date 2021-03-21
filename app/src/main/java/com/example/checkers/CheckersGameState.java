package com.example.checkers;

import com.example.checkers.game.GameFramework.infoMessage.GameState;

public class CheckersGameState extends GameState {

    private CheckersPiece[] p1Pieces;//if the grid is pieces this might not be needed
    private CheckersPiece[] p2Pieces;//if the grid is pieces this might not be needed
    private int p1NumPieces;
    private int p2NumPieces;
    private int playerTurn;

    public CheckersGameState(){
        playerTurn = 0;

        p1NumPieces = 12;
        p2NumPieces = 12;

        // p1 starting coordinates
        p1Pieces = new CheckersPiece[12];


        p1Pieces[0] = new CheckersPiece(1,1);
        p1Pieces[1] = new CheckersPiece(3,1);
        p1Pieces[2] = new CheckersPiece(5,1);
        p1Pieces[3] = new CheckersPiece(7,1);
        p1Pieces[4] = new CheckersPiece(2,2);
        p1Pieces[5] = new CheckersPiece(4,2);
        p1Pieces[6] = new CheckersPiece(6,2);
        p1Pieces[7] = new CheckersPiece(8,2);
        p1Pieces[8] = new CheckersPiece(7,3);
        p1Pieces[9] = new CheckersPiece(5,3);
        p1Pieces[10] = new CheckersPiece(3,3);
        p1Pieces[11] = new CheckersPiece(1,3);

        // p2 starting coordinates
        p2Pieces = new CheckersPiece[12];
        p2Pieces[0] = new CheckersPiece(2,6);
        p2Pieces[1] = new CheckersPiece(4,6);
        p2Pieces[2] = new CheckersPiece(6,6);
        p2Pieces[3]= new CheckersPiece(8,6);
        p2Pieces[4] = new CheckersPiece(1,7);
        p2Pieces[5] = new CheckersPiece(3,7);
        p2Pieces[6] = new CheckersPiece(5,7);
        p2Pieces[7] = new CheckersPiece(7,7);
        p2Pieces[8] = new CheckersPiece(2,8);
        p2Pieces[9] = new CheckersPiece(4,8);
        p2Pieces[10] = new CheckersPiece(6,8);
        p2Pieces[11] = new CheckersPiece(8,8);
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
}
