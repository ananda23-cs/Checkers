/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 1 - Dumb AI
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.players;
import android.util.Log;

import com.example.checkers.CheckersGame.Actions.ChooseAction;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;
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
        int xDire = 0;
        int yDire = 0;
        int pieceIdx = 1;
        // return if not computer plays turn or not an instance of game state
        if (info instanceof NotYourTurnInfo) {
            return;
        }
        else if(!(info instanceof CheckersGameState)) {
            return;

        }
        // loop until a valid move can be made
        for(CheckersPiece piece: ((CheckersGameState) info).p2Pieces) {
            Log.e("xlr15","hello"+((CheckersGameState) info).p1Pieces[10]);
            if (((CheckersGameState) info).canMove(piece,1,-1,((CheckersGameState) info).getPlayerTurn())) {
                sleep(1);
                game.sendAction(new ChooseAction(this, piece.getXcoordinate(),piece.getYcoordinate()));
                game.sendAction(new ChooseAction(this, piece.getXcoordinate()+1, piece.getYcoordinate() -1 ));

                }
            if (((CheckersGameState) info).canMove(piece,-1,-1,((CheckersGameState) info).getPlayerTurn())) {
                sleep(1);
                game.sendAction(new ChooseAction(this, piece.getXcoordinate(),piece.getYcoordinate()));
                game.sendAction(new ChooseAction(this, piece.getXcoordinate()-1, piece.getYcoordinate() -1 ));

            }
            if(((CheckersGameState) info).CaptureEnemyPiece(piece.getXcoordinate(),piece.getYcoordinate(),-1,-1)){
                Log.e("xlr19","hello");
                sleep(1);
                game.sendAction(new ChooseAction(this, piece.getXcoordinate(),piece.getYcoordinate()));
                game.sendAction(new ChooseAction(this, piece.getXcoordinate()-1, piece.getYcoordinate() -1 ));
            }
            if(((CheckersGameState) info).CaptureEnemyPiece(piece.getXcoordinate(),piece.getYcoordinate(),1,-1)){
                Log.e("xlr19","hello");
                sleep(1);
                game.sendAction(new ChooseAction(this, piece.getXcoordinate(),piece.getYcoordinate()));
                game.sendAction(new ChooseAction(this, piece.getXcoordinate()+1, piece.getYcoordinate() -1 ));
            }
       }
    } //receiveInfo
}


