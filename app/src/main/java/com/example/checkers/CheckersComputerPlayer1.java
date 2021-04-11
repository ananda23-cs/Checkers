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
        //ignore if not the computer's turn

        if (!(info instanceof CheckersGameState)) return;
        CheckersGameState current = new CheckersGameState((CheckersGameState) info);
        if(current.getPlayerTurn() != playerNum) return;
        //delay for a second so human can see the computer's movements
        sleep(1);

        boolean invalid = true;
        while(invalid) {

            Log.e("recieveInfo","hello");
            Random r = new Random();

            //chooses the index of a computer player's piece;
            int pieceIdx = (int) (Math.random() * 12);
            int[] directions = new int[2];
            directions[0] = -1;
            directions[1] = 1;
            int xdirection = directions[(int) (Math.random() * 2)];
            int ydirection = directions[(int) (Math.random() * 2)];

            int action = r.nextInt(100);
            //CheckersPiece piece, int xDir,int yDir,int id
            if (playerNum == 0) {
                if (action < 51) {

                    if( current.canMove(current.p1Pieces[pieceIdx],xdirection,ydirection,current.getPlayerTurn())) {
                        game.sendAction(new CheckersMoveAction2(CheckersComputerPlayer1.this, xdirection, ydirection, ((CheckersGameState)info).p1Pieces[pieceIdx]));
                        invalid = false;
                    }
                } else {
                    if(current.capturepiece(current.p1Pieces[pieceIdx],current.getPlayerTurn(),current.p2Pieces,xdirection,ydirection)) {
                        game.sendAction(new CheckersCaptureAction(CheckersComputerPlayer1.this, xdirection, ydirection, ((CheckersGameState)info).p1Pieces[pieceIdx]));
                        invalid = false;
                    }
                }

            } else if (playerNum == 1) {
                if (action < 51) {
                    if( current.canMove(current.p2Pieces[pieceIdx],xdirection,ydirection,current.getPlayerTurn())) {
                        Log.e("receiveInfo: ", "\nbefore"+((CheckersGameState)info).p2Pieces[pieceIdx]);
                        invalid = false;
                        game.sendAction(new CheckersMoveAction2(CheckersComputerPlayer1.this, xdirection, ydirection, ((CheckersGameState)info).p2Pieces[pieceIdx]));
                        Log.e("receiveInfo: ", "\nafter"+((CheckersGameState)info).p2Pieces[pieceIdx]);
                    }
                } else {
                    if(current.capturepiece(current.p2Pieces[pieceIdx],current.getPlayerTurn(),current.p1Pieces,xdirection,ydirection)) {
                        game.sendAction(new CheckersCaptureAction(CheckersComputerPlayer1.this, xdirection, ydirection, ((CheckersGameState)info).p2Pieces[pieceIdx]));
                        invalid = false;
                    }
                }
            }
        }
    }

}
