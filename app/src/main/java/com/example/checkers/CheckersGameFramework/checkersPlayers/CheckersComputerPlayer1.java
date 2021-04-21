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
            sleep(1);
            //runs though all the players pieces
            if(((CheckersGameState) info).getPlayerTurn() == 1) {
                for (CheckersPiece piece : ((CheckersGameState) info).p2Pieces) {
                    Log.e("xlr15", "hello" + ((CheckersGameState) info).p1Pieces[10]);
                    //if the piece is alive
                    if (piece.getAlive()) {
                        //moves right forward if it can
                        if (((CheckersGameState) info).canMove(piece, 1, -1, ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));

                        }
                        //moves left forward if it can
                        if (((CheckersGameState) info).canMove(piece, -1, -1, ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));

                        }
                        //moves left backward if it can
                        if (((CheckersGameState) info).canMove(piece, -1, +1, ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));

                        }
                        //moves right forward if it can
                        if (((CheckersGameState) info).canMove(piece, 1, +1, ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));

                        }
                        //captures a piece to the left if it can
                        if (((CheckersGameState) info).CaptureEnemyPiece(piece.getXcoordinate() - 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
                        }
                        //captures a piece to the right if it can
                        if (((CheckersGameState) info).CaptureEnemyPiece(piece.getXcoordinate() + 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));
                        }
                    }

                }
            }
            else {
                for (CheckersPiece piece : ((CheckersGameState) info).p1Pieces) {
                    Log.e("xlr15", "hello" + ((CheckersGameState) info).p1Pieces[10]);
                    //if the piece is alive
                    if (piece.getAlive()) {
                        //moves right forward if it can
                        if (((CheckersGameState) info).canMove(piece, 1, -1, ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));

                        }
                        //moves left forward if it can
                        if (((CheckersGameState) info).canMove(piece, -1, -1, ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));

                        }
                        //moves left backward if it can
                        if (((CheckersGameState) info).canMove(piece, -1, +1, ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));

                        }
                        //moves right forward if it can
                        if (((CheckersGameState) info).canMove(piece, 1, +1, ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));

                        }
                        //captures a piece to the left if it can
                        if (((CheckersGameState) info).CaptureEnemyPiece(piece.getXcoordinate() - 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
                        }
                        //captures a piece to the right if it can
                        if (((CheckersGameState) info).CaptureEnemyPiece(piece.getXcoordinate() + 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate())) {
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new CheckersMoveAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));
                        }
                    }

                }
            }
        }
    } //receiveInfo


}

//ignore if not the computer's turn
//        if (!(info instanceof CheckersGameState)) return;
//
//        CheckersGameState current = new CheckersGameState((CheckersGameState) info);
//        if(current.getPlayerTurn() != playerNum) return;
//
//        //delay for a second so human can see the computer's movements
//        sleep(0.5);
//
//        boolean invalid = true;
//        while(invalid) {
//
//            Log.e("recieveInfo","hello");
//            Random r = new Random();
//
//            //chooses the index of a computer player's piece;
//            int pieceIdx = (int) (Math.random() * 12);
//            int[] directions = new int[2];
//            directions[0] = -1;
//            directions[1] = 1;
//            int xdirection = directions[(int) (Math.random() * 2)];
//            int ydirection = directions[(int) (Math.random() * 2)];
//
//            int action = r.nextInt(100);
//            //CheckersPiece piece, int xDir,int yDir,int id
//            if (playerNum == 0) {
//                if (action < 51) {
//
//                    if( current.movePiece(current.p1Pieces[pieceIdx],xdirection,ydirection,current.getPlayerTurn())) {
//                        game.sendAction(new CheckersMoveAction2(CheckersComputerPlayer1.this, xdirection, ydirection, ((CheckersGameState)info).p1Pieces[pieceIdx]));
//                        invalid = false;
//                    }
//                } else {
//                    if(current.capturepiece(current.p1Pieces[pieceIdx],current.getPlayerTurn(),current.p2Pieces,xdirection,ydirection)) {
//                        game.sendAction(new CheckersCaptureAction(CheckersComputerPlayer1.this, xdirection, ydirection, ((CheckersGameState)info).p1Pieces[pieceIdx]));
//                        invalid = false;
//                    }
//                }
//
//            } else if (playerNum == 1) {
//                if (action < 51) {
//                    if( current.movePiece(current.p2Pieces[pieceIdx],xdirection,ydirection,current.getPlayerTurn())) {
//                        Log.e("receiveInfo: ", "\nbefore"+((CheckersGameState)info).p2Pieces[pieceIdx]);
//                        invalid = false;
//                        game.sendAction(new CheckersMoveAction2(this, xdirection, ydirection, ((CheckersGameState)info).p2Pieces[pieceIdx]));
//                        Log.e("receiveInfo: ", "\nafter"+((CheckersGameState)info).p2Pieces[pieceIdx]);
//                    }
//                } else {
//                    if(current.capturepiece(current.p2Pieces[pieceIdx],current.getPlayerTurn(),current.p1Pieces,xdirection,ydirection)) {
//                        game.sendAction(new CheckersCaptureAction(CheckersComputerPlayer1.this, xdirection, ydirection, ((CheckersGameState)info).p2Pieces[pieceIdx]));
//                        invalid = false;
//                    }
//                }
//            }
//        }
//    }


