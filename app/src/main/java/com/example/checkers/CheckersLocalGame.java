/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Local game class - creates a new game state, implement the move,
 * capture, and turn to king actions, and update it to the new game state
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import android.util.Log;

import com.example.checkers.Actions.CheckersCancelMoveAction;
import com.example.checkers.Actions.CheckersCaptureAction;
import com.example.checkers.game.GameFramework.LocalGame;
import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;
import com.example.checkers.Actions.CheckersMoveAction2;

public class CheckersLocalGame extends LocalGame {

    //CheckersGameState checkersGameState;//I added this
    /**
     * Constructor for the CheckersLocalGame.
     */
    public CheckersLocalGame(){
        //I am commenting this out
        super();
        super.state = new CheckersGameState();
        //checkersGameState = new CheckersGameState();
    }

    /**
     * Constructor for the CheckersLocalGame with loaded checkersGameState.
     * @param checkersGameState
     */
    public CheckersLocalGame(CheckersGameState checkersGameState){
        super();
        super.state = new CheckersGameState(checkersGameState);
    }

    /**
     * Notify the given player that its state has changed. This should involve sending
     * a GameInfo object to the player. If the game is not a perfect-information game
     * this method should remove any information from the game that the player is not
     * allowed to know.
     *
     * @param p
     * 			the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //make a copy of the state and send it to the player
        p.sendInfo(new CheckersGameState((CheckersGameState) state));
    }

    /**
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return
     * 		true iff the player is allowed to move
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == ((CheckersGameState) state).getPlayerTurn();
    }

    /**
     * checks if the game is over. If it's over, return a message
     * showing who won the game. If not, return null
     *
     * @return
     *          a String message showing the winner of the game
     *          or null
     */
    @Override
    protected String checkIfGameOver() {
        CheckersGameState state = (CheckersGameState) super.state;
        if(state.getP1NumPieces() == 0)
        {
            return "Player 2 wins.";
        }
        else if(state.getP2NumPieces() == 0)
        {
            return "Player 1 wins.";
        }
        else
        {
            return null;
        }

    }

    /**
     * Makes a move on behalf of a player.
     *
     * @param action
     * 			The move that the player has sent to the game
     * @return
     * 			Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        Log.e("Cancel Move", "this happened");
        if(action instanceof CheckersCancelMoveAction){
            CheckersCancelMoveAction cancelMoveAction = (CheckersCancelMoveAction) action;
            CheckersGameState state = (CheckersGameState) super.state;
            int cancelRow = cancelMoveAction.getSelectedRow();
            int cancelCol = cancelMoveAction.getSelectedCol();
            state.setPieceSelectedPieceAndPieceSelectedBoolean(cancelRow,cancelCol);
            state.setMessage("The piece at " + cancelRow + ", " + cancelCol +
                                " has been unselected.\nPlease select another piece.");
            return true;
        }
        else if (action instanceof CheckersMoveAction2){
            Log.e("Make Move", "this happened");
            CheckersMoveAction2 moveAction = (CheckersMoveAction2) action;
            CheckersGameState state = (CheckersGameState) super.state;
            int playerId = state.getPlayerTurn();
            int xDir = moveAction.getXDire();
            int yDir = moveAction.getYDire();
            CheckersPiece piece = moveAction.getPiece();
            if(state.canMove(state.getPieceSelectedPiece(),xDir,yDir,state.getPlayerTurn())) {
                state.movePiece(piece, xDir, yDir, playerId);
                state.setPieceSelectedPieceAndPieceSelectedBoolean(piece.getXcoordinate(),
                        piece.getYcoordinate());
                if (state.getPlayerTurn() == 0) {
                    state.setMessage("Player 1's move was valid.\nPlayer 2's turn.");
                } else {
                    state.setMessage("Player 2's move was valid.\nPlayer 1's turn");
                }
                state.setPlayerTurn(1 - playerId);
                return true;
            }
            else{
                state.setMessage("Invalid Move. Try again.");
                return false;
            }

        }
        else if (action instanceof CheckersCaptureAction){
            CheckersCaptureAction captureAction = (CheckersCaptureAction) action;
            CheckersGameState state = (CheckersGameState) super.state;
            int playerId = state.getPlayerTurn();
            int xDir = captureAction.getXDire();
            int yDir = captureAction.getYDire();
            CheckersPiece piece = captureAction.getCheckersPiece();
            if (canMove(playerId)){
                if(playerId == 0){
                    state.capturepiece(piece,playerId,state.p2Pieces,xDir,yDir);
                    state.setP2NumPieces(state.getP2NumPieces()-1);
                }
                else{
                    state.capturepiece(piece,playerId,state.p1Pieces,xDir,yDir);
                    state.setP1NumPieces(state.getP1NumPieces()-1);
                }
                state.setPlayerTurn(1-playerId);
                return true;
            }
            else{ return false; }
        }
        return false;
    }
}
