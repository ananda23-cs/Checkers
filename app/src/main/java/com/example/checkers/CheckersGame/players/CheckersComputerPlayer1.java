/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 1 - Dumb AI
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.players;
import com.example.checkers.CheckersGame.Actions.CheckersCaptureAction;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;
import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.players.GameComputerPlayer;
import com.example.checkers.CheckersGame.Actions.CheckersMoveAction2;

import java.util.Random;

public class CheckersComputerPlayer1 extends GameComputerPlayer {

    /**
     * ctor of CheckersComputerPlayer1
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
        //ignore if not the computer's turn
        //if(info instanceof NotYourTurnInfo) return;
        //if (!(info instanceof CheckersGameState)) return;
        //= new CheckersGameState((CheckersGameState) info);

        //delay for a second so human can see the computer's movements
        sleep(1);
        CheckersGameState current;
        if(info instanceof CheckersGameState) {
            current = new CheckersGameState((CheckersGameState) info);
            if(current.getPlayerTurn() == playerNum) {
                //Random r = new Random();
                boolean invalid = true;
                while(invalid) {
                    //chooses the index of a computer player's piece;
                    int pieceIdx = (int) (Math.random() * 12);
                    int[] directions = new int[2];
                    directions[0] = -1;
                    directions[1] = 1;
                    int xdirection = directions[(int) (Math.random() * 2)];
                    int ydirection = directions[(int) (Math.random() * 2)];

                    //int action = 1 + r.nextInt(100);
                    //CheckersPiece piece, int xDir,int yDir,int id
                    if (playerNum == 0) {
                        if (current.canMove(current.p1Pieces[pieceIdx], xdirection, ydirection, playerNum)) {
                            game.sendAction(new CheckersMoveAction2(CheckersComputerPlayer1.this, xdirection, ydirection, current.p1Pieces[pieceIdx]));
                            invalid = false;
                        } else if(current.capturepiece(current.p1Pieces[pieceIdx], playerNum, current.p2Pieces, xdirection, ydirection)){
                            game.sendAction(new CheckersCaptureAction(CheckersComputerPlayer1.this, xdirection, ydirection, current.p1Pieces[pieceIdx]));
                            invalid = false;
                        }

                    } else if (playerNum == 1) {
                        if (current.canMove(current.p2Pieces[pieceIdx], xdirection, ydirection, playerNum)) {
                            game.sendAction(new CheckersMoveAction2(CheckersComputerPlayer1.this, xdirection, ydirection, current.p2Pieces[pieceIdx]));
                            invalid = false;
                        }
                        else if(current.capturepiece(current.p2Pieces[pieceIdx], playerNum, current.p1Pieces, xdirection, ydirection)){
                            game.sendAction(new CheckersCaptureAction(CheckersComputerPlayer1.this, xdirection, ydirection, current.p2Pieces[pieceIdx]));
                            invalid = false;
                        }
                    }
                }
            }
        }
    }

}
