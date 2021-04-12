/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 1 - Dumb AI
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;
import android.util.Log;

import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameComputerPlayer;

import java.util.Random;

public class CheckersComputerPlayer1 extends GameComputerPlayer {

    public CheckersComputerPlayer1(String name){
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        int xDire = 0;
        int yDire = 0;
        int pieceIdx = 1;
        if (info instanceof NotYourTurnInfo) {
            return;
        }


        else if(!(info instanceof CheckersGameState)) {
            return;

        }

        else {
//            Log.e("xlr8 ", "" + ((CheckersGameState) info).p1Pieces[10] + "turn" + ((CheckersGameState) info).getPlayerTurn());
//            CheckersGameState current = ((CheckersGameState) info);
//
//            //((CheckersGameState) info).setPlayerTurn(4);
//            //sleep(0.5);
//            boolean invalid = true;
//
//
//
//            Log.e("xlr11", "" + current.p1Pieces[10] + "turn" + current.getPlayerTurn());
//
//
//            if (current.getPlayerTurn() == 1)
//                while (invalid) {
//                    pieceIdx = (int) (Math.random() * 12);
//                    int xIndex = (int) (Math.random() * 100);
//                    int yIndex = (int) (Math.random() * 100);
//
//                    if (yIndex > 50) {
//                        yDire = 1;
//                    } else {
//                        yDire = -1;
//                    }
//
//                    if (xIndex > 50) {
//                        xDire = 1;
//                    } else {
//                        xDire = -1;
//                    }
//
//
//                    Log.e("xlr9 ", "xDire: " + xDire + "yDire: " + yDire + "piece" + ((CheckersGameState) info).p2Pieces[pieceIdx]
//                            + "\nturn" + ((CheckersGameState) info).getPlayerTurn());
//                    if (((CheckersGameState) info).canMove(((CheckersGameState) info).p2Pieces[pieceIdx], xDire, yDire, ((CheckersGameState) info).getPlayerTurn())) {
//                        invalid = false;
//
//                    }
//
//
//                }
//                for(CheckersPiece piece: ((CheckersGameState) info).p2Pieces) {
//                    Log.e("xlr15","hello");
//                    if (((CheckersGameState) info).canMove(piece,1,1,((CheckersGameState) info).getPlayerTurn())) {
//                        game.sendAction(new ChooseAction(this, piece.getXcoordinate(),piece.getYcoordinate()));
//                        game.sendAction(new ChooseAction(this, piece.getXcoordinate()+1, piece.getYcoordinate() +1 ));
//
//                    }
//                }
            }
            //game.sendAction(new ChooseAction(this, ((CheckersGameState) info).p2Pieces[pieceIdx].getXcoordinate() ,((CheckersGameState) info).p2Pieces[pieceIdx].getYcoordinate()));
            //game.sendAction(new ChooseAction(this, ((CheckersGameState) info).p2Pieces[pieceIdx].getXcoordinate() +xDire ,((CheckersGameState) info).p2Pieces[pieceIdx].getYcoordinate()+yDire) );
        for(CheckersPiece piece: ((CheckersGameState) info).p2Pieces) {
                    Log.e("xlr15","hello"+((CheckersGameState) info).p1Pieces[10]);
            if (((CheckersGameState) info).canMove(piece,1,-1,((CheckersGameState) info).getPlayerTurn())) {
                game.sendAction(new ChooseAction(this, piece.getXcoordinate(),piece.getYcoordinate()));
                game.sendAction(new ChooseAction(this, piece.getXcoordinate()+1, piece.getYcoordinate() -1 ));

                }
            if (((CheckersGameState) info).canMove(piece,-1,-1,((CheckersGameState) info).getPlayerTurn())) {

                game.sendAction(new ChooseAction(this, piece.getXcoordinate(),piece.getYcoordinate()));
                game.sendAction(new ChooseAction(this, piece.getXcoordinate()-1, piece.getYcoordinate() -1 ));

            }
            if(((CheckersGameState) info).CaptureEnemyPiece(-1,-1,piece.getXcoordinate(),piece.getYcoordinate())){
                Log.e("xlr19","hello");
                game.sendAction(new ChooseAction(this, piece.getXcoordinate(),piece.getYcoordinate()));
                game.sendAction(new ChooseAction(this, piece.getXcoordinate()-1, piece.getYcoordinate() -1 ));
            }
       }
        //game.sendAction(new ChooseAction(this, 3 ,5));
        // game.sendAction(new ChooseAction(this, 4, 4));
    }
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


