/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 1 - Dumb AI. Makes random moves
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.players;

import android.util.Log;

import com.example.checkers.CheckersGame.Actions.CheckersChoosePieceAction;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;
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
     * @param info the message from the game
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // return if not computer player's turn or not an instance of game state
        if(!(info instanceof CheckersGameState)) {
            return;
        }

        else if (info instanceof NotYourTurnInfo ||
                ((CheckersGameState) info).getPlayerTurn() != playerNum) {
            return;
        }

        //list of possible moves and pieces
        ArrayList<CheckersChoosePieceAction[]> possibleMoves = new ArrayList<CheckersChoosePieceAction[]>();
        CheckersPiece [] Pieces;

        if(((CheckersGameState) info).getPlayerTurn() == 1){
            Pieces = ((CheckersGameState) info).p2Pieces;
        }
        else{
            Pieces = ((CheckersGameState) info).p1Pieces;
        }

        /* runs through all the computer players pieces checks all the moves.
          It adds all possible moves to the ArrayList above */
        for(CheckersPiece piece: Pieces){
            if(piece.getAlive()) {

                if(((CheckersGameState) info).getPieceSelectedPiece() != null) {
                    return;
                }

                //if moving left backwards is a possible move for this piece it gets added to the list of moves
                checkValidMove(((CheckersGameState) info), piece, -1, -1, possibleMoves);
                //if moving right backwards is a possible move for this piece it gets added to the list of moves
                checkValidMove(((CheckersGameState) info), piece, +1, -1, possibleMoves);
                //if moving right Forward is a possible move for this piece it gets added to the list of moves
                checkValidMove(((CheckersGameState) info), piece, +1, +1, possibleMoves);
                //if moving left Forward is a possible move for this piece it gets added to the list of moves
                checkValidMove(((CheckersGameState) info), piece, -1, +1, possibleMoves);

                //if capturing right backwards is a valid move it gets added to the list of possible moves
                checkValidCapture(((CheckersGameState) info), piece, possibleMoves, +1, -1);
                //if capturing left backwards is a valid move it gets added to the list of possible moves
                checkValidCapture(((CheckersGameState) info), piece, possibleMoves, -1, -1);
                //if capturing right forwards is a valid move it gets added to the list of possible moves
                checkValidCapture(((CheckersGameState) info), piece, possibleMoves, +1, +1);
                //if capturing left forwards is a valid move it gets added to the list of possible moves
                checkValidCapture(((CheckersGameState) info), piece, possibleMoves, -1, +1);
            }
        }

        int possibleMovesIndex = (int)(Math.random()*(possibleMoves.size()));

        if(possibleMoves.size() > 0) {
            sleep(0.5);
            for (CheckersChoosePieceAction action : possibleMoves.get(possibleMovesIndex)) {
                game.sendAction(action);

            }
        }
    } //receiveInfo

    /**
     * Checks for all possible valid moves and adds them to an ArrayList.
     * @param info the current game state
     * @param piece the selected piece
     * @param xDire the x direction of the piece selected when it will move
     * @param yDire the y direction of the selected piece when it will move
     * @param possibleMoves the array list of all the possible moves made by the piece
     */
    public void checkValidMove(CheckersGameState info,CheckersPiece piece, int xDire, int yDire,
                               ArrayList<CheckersChoosePieceAction[]> possibleMoves ){
        if(info.canMove(piece,xDire,yDire,info.getPlayerTurn())){
            CheckersChoosePieceAction[] mL = new CheckersChoosePieceAction[2];
            mL[0] = new CheckersChoosePieceAction(this, piece.getXcoordinate(),
                                                            piece.getYcoordinate());
            mL[1] = new CheckersChoosePieceAction(this, piece.getXcoordinate()+ xDire,
                                                                piece.getYcoordinate() + yDire);
            possibleMoves.add(mL);
        }
    }//checkValidMove

    /**
     * Checks for all possible valid captures and adds them to an ArrayList.
     * @param info the current game state
     * @param piece the selected piece
     * @param xDire the x direction of the piece selected when it will move
     * @param yDire the y direction of the selected piece when it will move
     * @param possibleMoves the array list of all the possible moves made by the piece
     */
    public void checkValidCapture(CheckersGameState info,CheckersPiece piece,
                                  ArrayList<CheckersChoosePieceAction[]> possibleMoves,
                                  int xDire, int yDire){
        info.setPieceSelectedPieceAndPieceSelectedBoolean(piece.getXcoordinate(),
                                                    piece.getYcoordinate());
        if(info.checkIfCanCaptureEnemyPiece(piece.getXcoordinate()+xDire,
                                            piece.getYcoordinate()+yDire,
                                                piece.getXcoordinate(),piece.getYcoordinate())){
            CheckersChoosePieceAction[] mL = new CheckersChoosePieceAction[2];
            mL[0] = new CheckersChoosePieceAction(this, piece.getXcoordinate(),
                                                                piece.getYcoordinate());
            mL[1] = new CheckersChoosePieceAction(this, piece.getXcoordinate()+xDire,
                                                                piece.getYcoordinate() + yDire);
            possibleMoves.add(mL);
        }
        info.setPieceSelectedPieceAndPieceSelectedBoolean();
    }//checkValidCapture
}//CheckersComputerPlayer1