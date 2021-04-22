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

import java.util.ArrayList;

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
            sleep(1);
            CheckersGameState copy = new CheckersGameState((CheckersGameState)info);
            ArrayList<CheckersMoveAction[]> possibleMoves = new ArrayList<CheckersMoveAction[]>();


            CheckersPiece[] checkersPieces;
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
                    if (copy.canMove(piece, 1, -1, copy.getPlayerTurn())  ) {
                        Log.e("cowl0" , "rb "+ piece  );
                        CheckersMoveAction[] moveRB = new CheckersMoveAction[2];
                        moveRB[0] = new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate());
                        moveRB[1] = new CheckersMoveAction( this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1);
                        possibleMoves.add(moveRB);

                    }

                    //moves left backwards if it can
                    if (copy.canMove(piece, -1, -1, copy.getPlayerTurn()) ) {
                        Log.e("cowl0" , "lb"+ piece  );
                        CheckersMoveAction[] moveLB = new CheckersMoveAction[2];

                        moveLB[0] = new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate());
                        moveLB[1] = new CheckersMoveAction( this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1);
                        possibleMoves.add(moveLB);

                    }

                    //moves left forward if it can
                    if (copy.canMove(piece, -1, +1, copy.getPlayerTurn())) {
                        Log.e("cowl0" , "lf"+ piece  );
                        CheckersMoveAction[] moveLF = new CheckersMoveAction[2];
                        moveLF[0] = new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate());
                        moveLF[1] = new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1);
                        possibleMoves.add(moveLF);
                    }

                    //moves right forward if it can
                    if (copy.canMove(piece, 1, +1, copy.getPlayerTurn()) ) {
                        Log.e("cowl0" , "rf"+ piece  );
                        CheckersMoveAction[] moveRF = new CheckersMoveAction[2];
                        moveRF[0] = new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate());
                        moveRF[1] = new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1);
                        possibleMoves.add(moveRF);

                    }
                    //captures a piece to the left backwards if it can
                    if (copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() - 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())) {
                        Log.e("cowl0" , "LC"+ piece  );
                        CheckersMoveAction[] moveLC = new CheckersMoveAction[2];
                        moveLC[0] = new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate());
                        moveLC[1] = new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1);
                        possibleMoves.add(moveLC);

                    }

                    //captures a piece to the right backwards if it can
                    if (copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() + 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())
                    ) {
                        Log.e("cowl0" , "RC"+ piece  );
                        CheckersMoveAction[] moveRC = new CheckersMoveAction[2];
                        moveRC[0] = new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate());
                        moveRC[1] = new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1);
                        possibleMoves.add(moveRC);

                    }

                    //captures a piece to the right forward if it can
                    if(copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() + 1, piece.getYcoordinate() + 1, piece.getXcoordinate(), piece.getYcoordinate())){
                        CheckersMoveAction[] moveRCF = new CheckersMoveAction[2];
                        moveRCF[0] = new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate());
                        moveRCF[1] = new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1);
                        possibleMoves.add(moveRCF);
                    }

                    if(copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() - 1, piece.getYcoordinate() + 1, piece.getXcoordinate(), piece.getYcoordinate())){
                        CheckersMoveAction[] moveLCF = new CheckersMoveAction[2];
                        moveLCF[0] = new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate());
                        moveLCF[1] = new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1);
                        possibleMoves.add(moveLCF);
                    }

                }

            }
            if(possibleMoves.size() != 0){
                Log.e("cowl1","possible moves" +possibleMoves.size() );
                int possibleMovesIndex = (int)(Math.random()*(possibleMoves.size()));
                for(CheckersMoveAction move : possibleMoves.get(possibleMovesIndex)){
                    game.sendAction(move);
                }
            }


        }
    } //receiveInfo


}

//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));


//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));


//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));



//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));


//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
//

//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));
//



//            for (CheckersPiece piece : checkersPieces) {
//                copy.setPieceSelectedPieceAndPieceSelectedBoolean(piece.getXcoordinate(),piece.getYcoordinate());
//                //if the piece is alive
//                if (piece.getAlive()) {
//
//                    //moves right backwards if it can
//                    if (copy.canMove(piece, 1, -1, copy.getPlayerTurn()) ) {
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));
//
//                    }
//
//                    //moves left backwards if it can
//                    else if (copy.canMove(piece, -1, -1, copy.getPlayerTurn()) ) {
//                        if (copy.p2Pieces[1].equals(piece)){
//                            Log.e("thishappened", ""+copy.p2Pieces[1]);
//                        }
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
//
//
//                    }
//
//                    //moves left forward if it can
//                    else if (copy.canMove(piece, -1, +1, copy.getPlayerTurn()) ) {
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));
//
//                    }
//
//                    //moves right forward if it can
//                    else if (copy.canMove(piece, 1, +1, copy.getPlayerTurn()) ) {
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));
//
//                    }
//                    //captures a piece to the left if it can
//                    else if (copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() - 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())
//                            ) {
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
//                    }
//                    //captures a piece to the right if it can
//                    else if (copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() + 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())
//                             ) {
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
//                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));
//                    }
//
////                    else if(copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() + 1, piece.getYcoordinate() + 1, piece.getXcoordinate(), piece.getYcoordinate())){
////                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
////                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));
////                    }
////
////                    else if(copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate() - 1, piece.getYcoordinate() + 1, piece.getXcoordinate(), piece.getYcoordinate())){
////                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
////                        game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));
////                    }
//
//
//
//
//                }
//
//            }

