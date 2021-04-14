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

import java.util.Random;

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
        //int xDire = 0;
        //int yDire = 0;
        //int pieceIdx = 1;
        // return if not computer plays turn or not an instance of game state
        if (info instanceof NotYourTurnInfo) {
            return;
        }
        else if(!(info instanceof CheckersGameState)) {
            return;

        }
        else if(((CheckersGameState) info).getPlayerTurn()==1) {
            // loop until a valid move can be made
            sleep(0.5);
            for (CheckersPiece piece : ((CheckersGameState) info).p2Pieces) {
                Log.e("xlr15", "hello" + ((CheckersGameState) info).p1Pieces[10]);
                if (piece.getAlive()) {
                    // computer move method
                    // move backwards diagonally left
                    if (((CheckersGameState) info).canMove(piece, 1, -1, ((CheckersGameState) info).getPlayerTurn())) {
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));

                    }
                    // move move backwards diagonally right
                    if (((CheckersGameState) info).canMove(piece, -1, -1, ((CheckersGameState) info).getPlayerTurn())) {
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
                    }
                    // move forwards diagonally right
                    if (((CheckersGameState) info).canMove(piece, -1, +1, ((CheckersGameState) info).getPlayerTurn())) {
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));
                    }
                    // move forwards diagonally left
                    if (((CheckersGameState) info).canMove(piece, 1, +1, ((CheckersGameState) info).getPlayerTurn())) {
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));

                    }

                    // computer capture method
                    // capture forwards diagonally left
                    if (((CheckersGameState) info).CaptureEnemyPieceCP(piece.getXcoordinate() - 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate()) || checkIfStatement(info)) {
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
                    }
                    // capture forwards diagonally right
                    if (((CheckersGameState) info).CaptureEnemyPieceCP(piece.getXcoordinate() + 1, piece.getYcoordinate() - 1, piece.getXcoordinate(), piece.getYcoordinate()) || checkIfStatement(info)) {
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                        game.sendAction(new ChooseAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));
                    }

                    if (piece.getKing()) {
                        // capture backwards diagonally left
                        if (((CheckersGameState) info).CaptureEnemyPieceCP(piece.getXcoordinate() - 1, piece.getYcoordinate() + 1, piece.getXcoordinate(), piece.getYcoordinate()) || checkIfStatement(info)) {
                            game.sendAction(new ChooseAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new ChooseAction(this, piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));
                        }
                        // capture backwards diagonally right
                        if (((CheckersGameState) info).CaptureEnemyPieceCP(piece.getXcoordinate() + 1, piece.getYcoordinate() + 1, piece.getXcoordinate(), piece.getYcoordinate()) || checkIfStatement(info)) {
                            game.sendAction(new ChooseAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                            game.sendAction(new ChooseAction(this, piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));
                        }
                    }
                }

            }
        }
    } //receiveInfo

    // check the statements above
    public boolean checkIfStatement(GameInfo info){
        Log.e("checkIfStatement","this happened" + ((CheckersGameState) info).getPlayerTurn());
        return false;
    }
}


