/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 1 - Dumb AI. It makes not very smart moves
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGameFramework.checkersPlayers;
import android.util.Log;

import com.example.checkers.CheckersGameFramework.CheckersInfoMessage.CheckersGameState;
import com.example.checkers.CheckersGameFramework.checkersActionMessage.CheckersMoveAction;
import com.example.checkers.CheckersGameFramework.CheckersPiece;
import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameComputerPlayer;

public class CheckersComputerPlayer1 extends GameComputerPlayer {

    /**
     * constructor of CheckersComputerPlayer1
     */
    public CheckersComputerPlayer1(String name){
        super(name);
    }

    /**
     * Called when the player receives a game-state (or other info) from the
     * game.
     *
     * @param info
     * 		the message from the game
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // return if not computer plays turn or not an instance of game state
        if (info instanceof NotYourTurnInfo) {
            return;
        }
        //return if it does receive a CheckersGameState;
        else if(!(info instanceof CheckersGameState)) {
            return;

        }

        else if(((CheckersGameState) info).getPlayerTurn()==this.playerNum) {

            CheckersGameState copy = new CheckersGameState((CheckersGameState)info);

            CheckersPiece[] checkersPieces = new CheckersPiece[12];
            if(((CheckersGameState) info).getPlayerTurn() == 1){
                checkersPieces = copy.p2Pieces;
            }

            else{
                checkersPieces = copy.p1Pieces;
            }

            for (CheckersPiece piece : checkersPieces) {
                copy.setPieceSelectedPieceAndPieceSelectedBoolean(piece.getXcoordinate(),piece.getYcoordinate());
                //if the piece is alive
                if (piece.getAlive()) {

                    //moves right backwards if it can
                    if (copy.canMove(piece, 1, -1, copy.getPlayerTurn()) && !(piece.getYcoordinate() - 1 == 0 && copy.getPlayerTurn() ==1) ) {
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));

                    }

                    //moves left backwards if it can
                    else if (copy.canMove(piece, -1, -1, copy.getPlayerTurn()) && !(piece.getYcoordinate() - 1 == 0 && copy.getPlayerTurn() ==1)) {
                        if (copy.p2Pieces[1].equals(piece)){
                            Log.e("thishappened", ""+copy.p2Pieces[1]);
                        }
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));


                    }

                    //moves left forward if it can
                    else if (copy.canMove(piece, -1, +1, copy.getPlayerTurn()) && !(piece.getYcoordinate() + 1 == 7 && copy.getPlayerTurn() ==0 )) {
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));

                    }

                    //moves right forward if it can
                    else if (copy.canMove(piece, 1, +1, copy.getPlayerTurn()) && !(piece.getYcoordinate() + 1 == 7 && copy.getPlayerTurn() ==0 )) {
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));

                    }
                    //captures a piece to the left if it can
                    else if (copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() - 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())
                            && !(piece.getYcoordinate() - 2 == 0 && copy.getPlayerTurn() ==1)) {
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
                    }
                    //captures a piece to the right if it can
                    else if (copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() + 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())
                            && !(piece.getYcoordinate() - 2 == 0 && copy.getPlayerTurn() ==1)) {
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));
                    }

//                    else if(copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() + 1, piece.getYcoordinate() + 1, piece.getXcoordinate(), piece.getYcoordinate())){
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));
//                    }
//
//                    else if(copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() - 1, piece.getYcoordinate() + 1, piece.getXcoordinate(), piece.getYcoordinate())){
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));
//                    }




                }

            }

            for (CheckersPiece piece : checkersPieces) {
                copy.setPieceSelectedPieceAndPieceSelectedBoolean(piece.getXcoordinate(),piece.getYcoordinate());
                //if the piece is alive
                if (piece.getAlive()) {

                    //moves right backwards if it can
                    if (copy.canMove(piece, 1, -1, copy.getPlayerTurn()) ) {
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));

                    }

                    //moves left backwards if it can
                    else if (copy.canMove(piece, -1, -1, copy.getPlayerTurn()) ) {
                        if (copy.p2Pieces[1].equals(piece)){
                            Log.e("thishappened", ""+copy.p2Pieces[1]);
                        }
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));


                    }

                    //moves left forward if it can
                    else if (copy.canMove(piece, -1, +1, copy.getPlayerTurn()) ) {
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));

                    }

                    //moves right forward if it can
                    else if (copy.canMove(piece, 1, +1, copy.getPlayerTurn()) ) {
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));

                    }
                    //captures a piece to the left if it can
                    else if (copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() - 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())
                            ) {
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
                    }
                    //captures a piece to the right if it can
                    else if (copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() + 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())
                             ) {
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));
                    }

//                    else if(copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() + 1, piece.getYcoordinate() + 1, piece.getXcoordinate(), piece.getYcoordinate())){
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));
//                    }
//
//                    else if(copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() - 1, piece.getYcoordinate() + 1, piece.getXcoordinate(), piece.getYcoordinate())){
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));
//                    }




                }

            }





        }
    } //receiveInfo


}

