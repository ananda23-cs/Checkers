/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 1 - Dumb AI
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.players;

import com.example.checkers.CheckersGame.Actions.CheckersCancelMoveAction;
import com.example.checkers.CheckersGame.Actions.CheckersChoosePieceAction;
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
     * @param info the message from the game
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // return if not computer player's turn or not an instance of game state
        if (info instanceof NotYourTurnInfo) {
            return;
        }
        else if(!(info instanceof CheckersGameState)) {
            return;
        }
        else if(((CheckersGameState) info).getPlayerTurn() == playerNum){
            // loop until a valid move can be made
            sleep(0.5);
            if(((CheckersGameState) info).getPlayerTurn()==1) {
                for (CheckersPiece piece : ((CheckersGameState) info).p2Pieces) {
                    if (piece.getAlive()) {
                        // computer move method
                        game.sendAction(new CheckersChoosePieceAction(this, piece.getXcoordinate(),
                                piece.getYcoordinate()));
                        // move backwards diagonally left
                        if (((CheckersGameState) info).canMove(piece, 1, -1,
                                ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() + 1,
                                    piece.getYcoordinate() - 1));

                        }
                        // move move backwards diagonally right
                        if (((CheckersGameState) info).canMove(piece, -1, -1,
                                ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() - 1,
                                    piece.getYcoordinate() - 1));
                        }
                        // move forwards diagonally right
                        if (((CheckersGameState) info).canMove(piece, -1, +1,
                                ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() - 1,piece.getYcoordinate() + 1));
                        }
                        // move forwards diagonally left
                        if (((CheckersGameState) info).canMove(piece, 1, +1,
                                ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));

                        }

                        // computer capture method
                        // capture forwards diagonally left
                        if (((CheckersGameState) info).hasEnemyPieces(
                                piece.getXcoordinate() - 1,
                                piece.getYcoordinate() - 1)) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
                        }
                        // capture forwards diagonally right
                        if (((CheckersGameState) info).hasEnemyPieces(
                                piece.getXcoordinate() + 1,
                                piece.getYcoordinate() - 1)) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() + 1,
                                    piece.getYcoordinate() - 1));
                        }

                        if (piece.getKing()) {
                            // capture backwards diagonally left
                            if (((CheckersGameState) info).hasEnemyPieces(
                                    piece.getXcoordinate() - 1,
                                    piece.getYcoordinate() + 1)) {
                                game.sendAction(new CheckersChoosePieceAction(this,
                                        piece.getXcoordinate()-1, piece.getYcoordinate()+1));
                            }
                            // capture backwards diagonally right
                            if (((CheckersGameState) info).hasEnemyPieces(
                                    piece.getXcoordinate() + 1,
                                    piece.getYcoordinate() + 1)) {
                                game.sendAction(new CheckersChoosePieceAction(this,
                                        piece.getXcoordinate()+1, piece.getYcoordinate()+1));
                            }
                        }

                        else{
                            game.sendAction(new CheckersCancelMoveAction(this));
                        }
                    }

                }
            }
            else{
                for (CheckersPiece piece : ((CheckersGameState) info).p1Pieces) {
                    if (piece.getAlive()) {
                        // computer move method
                        game.sendAction(new CheckersChoosePieceAction(this,
                                piece.getXcoordinate(),
                                piece.getYcoordinate()));
                        // move backwards diagonally left
                        if (((CheckersGameState) info).canMove(piece, 1, -1,
                                ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));

                        }
                        // move move backwards diagonally right
                        if (((CheckersGameState) info).canMove(piece, -1, -1,
                                ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
                        }
                        // move forwards diagonally right
                        if (((CheckersGameState) info).canMove(piece, -1, +1,
                                ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));
                        }
                        // move forwards diagonally left
                        if (((CheckersGameState) info).canMove(piece, 1, +1,
                                ((CheckersGameState) info).getPlayerTurn())) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));

                        }

                        // computer capture method
                        // capture forwards diagonally left
                        if (((CheckersGameState) info).hasEnemyPieces(
                                piece.getXcoordinate() - 1,
                                piece.getYcoordinate() + 1)) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));
                        }
                        // capture forwards diagonally right
                        if (((CheckersGameState) info).hasEnemyPieces(
                                piece.getXcoordinate() + 1,
                                piece.getYcoordinate() + 1)) {
                            game.sendAction(new CheckersChoosePieceAction(this,
                                    piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));
                        }

                        if (piece.getKing()) {
                            // capture backwards diagonally left
                            if (((CheckersGameState) info).hasEnemyPieces(
                                    piece.getXcoordinate() - 1,
                                    piece.getYcoordinate() - 1)) {
                                game.sendAction(new CheckersChoosePieceAction(this,
                                        piece.getXcoordinate() - 1,
                                        piece.getYcoordinate() - 1));
                            }
                            // capture backwards diagonally right
                            if (((CheckersGameState) info).hasEnemyPieces(
                                    piece.getXcoordinate() + 1,
                                    piece.getYcoordinate() - 1)) {
                                game.sendAction(new CheckersChoosePieceAction(this,
                                        piece.getXcoordinate() + 1,
                                        piece.getYcoordinate() - 1));
                            }
                        }

                        else{
                            game.sendAction(new CheckersCancelMoveAction(this));
                        }
                    }

                }
            }
        }
    } //receiveInfo
}


