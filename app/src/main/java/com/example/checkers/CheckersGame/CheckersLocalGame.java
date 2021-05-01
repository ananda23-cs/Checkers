/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Local game class - creates a new game state, implement the move,
 * capture, and turn to king actions, and update it to the new game state
 *
 * CS301A
 * @version 04/30/2021
 */

package com.example.checkers.CheckersGame;

import com.example.checkers.CheckersGame.Actions.CheckersCanNotMoveAction;
import com.example.checkers.CheckersGame.Actions.CheckersCancelMoveAction;
import com.example.checkers.CheckersGame.Actions.CheckersChoosePieceAction;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;
import com.example.checkers.CheckersGame.players.CheckersHumanPlayer;
import com.example.checkers.game.GameFramework.LocalGame;
import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersLocalGame extends LocalGame {

    /**
     * Constructor for the CheckersLocalGame.
     */
    public CheckersLocalGame() {
        super();
        super.state = new CheckersGameState();
        //checkersGameState = new CheckersGameState();
    } //CheckersLocalGame

    /**
     * Constructor for the CheckersLocalGame with loaded checkersGameState.
     * @param checkersGameState the loaded game state
     */
    public CheckersLocalGame(CheckersGameState checkersGameState) {
        super();
        super.state = new CheckersGameState(checkersGameState);
    } //CheckersLocalGame

    /**
     * method sendUpdatedStateTo
     * notifies the players that the state of the game has changed
     * should involve sending a GameInfo object to the player
     *
     *
     * @param p
     * 		the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new CheckersGameState((CheckersGameState) state));
    } //sendUpdatedStateTo

    /**
     * method canMove
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
    } //canMove

    /**
     * External Citation:
     * Date: April 16, 2021
     * Problem: when one of the player's pieces got stuck in the game, it didn't do anything.
     * Resource: Professor Tribelhorn
     * Solution: added a condition to recognize a forfeit in the gameOver method
     *
     * method checkIfGameOver
     * checks if the game is over. If it's over, return a message
     * showing who won the game. If not, return null
     *
     * @return
     *       a String message showing the winner of the game
     *       or null
     */
    @Override
    protected String checkIfGameOver() {
        CheckersGameState state = (CheckersGameState) super.state;
        boolean p1Alive = false;
        boolean p2Alive = false;

        for(CheckersPiece piece : state.p1Pieces){
            if (piece.getAlive()) {
                p1Alive = true;
                break;
            }
        }

        for(CheckersPiece piece : state.p2Pieces){
            if (piece.getAlive()) {
                p2Alive = true;
                break;
            }
        }

        // end game is none of the opponents or users pieces are alive
        if (!p1Alive) {
            return "Player 2 won! ";
        } else if (!p2Alive) {
            return "Player 1 won! ";
        }
        // end game if user or opponent cannot make any more valid moves
        else if(state.getP2CanNotMove()){
            return "Player 2 can not move any more. Player one wins! ";
        }
        else if(state.getP1CanNotMove()){
            return "Player 1 can not move any more. Player two wins! ";
        }
        else {
            return null;
        }
    } //checkIfGameOver

    /**
     * Makes a move on behalf of a player.
     *
     * @param action
     * 	    The move that the player has sent to the game
     * @return
     * 		Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        // print message if cancel button is clicked
        if(action instanceof CheckersCancelMoveAction){
            CheckersGameState state = (CheckersGameState) super.state;
            state.setPieceSelectedPieceAndPieceSelectedBoolean();
            state.setMessage("You have unselected the piece." +
                    "\nPlease click a new piece you would like to move.");
            return true;
        }

        // checks if user can not make any more valid moves
        else if(action instanceof CheckersCanNotMoveAction){
            CheckersGameState state = (CheckersGameState) super.state;
            if(state.getPlayerTurn() == 1) {
                state.setP2CanNotMove(true);
            }
            else{
                state.setP1CanNotMove(true);
            }
            return true;
        }

        else if(action instanceof CheckersChoosePieceAction){

            CheckersChoosePieceAction ca = (CheckersChoosePieceAction) action;
            CheckersGameState state = (CheckersGameState) super.state;

            int x = ca.getXLoc();
            int y = ca.getYLoc();

            if(!state.isPieceSelectedBoolean()){
                // checks if spot is empty
                if(state.isEmpty(x,y)){
                    state.setMessage("This tile is empty. Pick another square.");
                    return false;
                }
                else {
                    // checks if piece belongs to player
                    if (state.hasEnemyPieces(x,y)) {
                        state.setMessage("This piece is not yours. Please try again.");
                        return false;
                    }
                    // if conditions are true, piece is chosen
                    else{
                        state.setPieceSelectedPieceAndPieceSelectedBoolean(x,y);

                        state.setMessage("This piece is yours." +
                                " Click a tile to move the piece or click the opponent's piece " +
                                "to capture." +
                                "\nIf you would like to select a different piece, " +
                                "click the cancel button.");
                        state.setPieceSelectedPieceAndPieceSelectedBoolean(x,y);
                        return true;
                    }
                }
            }
            else{
                int xDire=x-state.getPieceSelectedPiece().getXcoordinate();
                int yDire=y-state.getPieceSelectedPiece().getYcoordinate();
                if(state.hasEnemyPieces(x,y)){
                    // check if piece can be captured
                    if(state.CaptureEnemyPiece(x,y,state.getPieceSelectedPiece().getXcoordinate(),
                            state.getPieceSelectedPiece().getYcoordinate())){
                        state.setMessage("Valid capture.");
                        state.setPlayerTurn(1-getPlayerIdx(ca.getPlayer()));
                        return true;
                    }
                    // error message if piece cannot be captured
                    else{
                        state.setMessage("This piece can not be captured.");
                        return false;
                    }
                }
                else if(state.canMove(state.getPieceSelectedPiece(),xDire,yDire,
                                                            state.getPlayerTurn())){
                    // states if it was a valid move and whose turn it is
                    if(ca.getPlayer() instanceof CheckersHumanPlayer) {
                        if (state.getPlayerTurn() == 0) {
                            state.setMessage("That was a valid move. It is now player two's turn.");
                        }
                        else {
                            state.setMessage("That was a valid move. It is now player one's turn.");
                        }
                    }

                    else{

                        state.setMessage("Player "+(getPlayerIdx(ca.getPlayer())+1)+
                                " moved the piece at " +
                                (((CheckersChoosePieceAction) action).getXLoc()-xDire+1)+", " +
                                (((CheckersChoosePieceAction) action).getYLoc()-yDire+1) + " to " +
                                 (((CheckersChoosePieceAction) action).getXLoc()+1)+", " +
                                (((CheckersChoosePieceAction) action).getYLoc()+1));
                    }
                    state.move(xDire,yDire);
                    state.setPlayerTurn(1-getPlayerIdx(ca.getPlayer()));

                    return true;
                }
                else{
                    // error messages for users move
                    if(!state.isEmpty(x,y)){
                        state.setMessage("You can not capture your own piece");
                    }

                    else if(!state.inRange(xDire,yDire) ){
                        state.setMessage("Invalid move. You can only move a piece by one" +
                                "\nspace diagonally.");
                    }
                    else if(state.getPlayerTurn() == 0 && yDire<1 &&
                            !state.getPieceSelectedPiece().getKing()){
                        state.setMessage("You can not move this piece backwards because " +
                                "it is not a king.");
                    }
                    else if(state.getPlayerTurn() == 1 && yDire==1 &&
                            !state.getPieceSelectedPiece().getKing()){
                        state.setMessage("You can not move this piece backwards because " +
                                "it is not a king.");
                    }
                    else {
                        state.setMessage("Invalid move. Try again.");
                    }

                    return false;
                }
            }
        }
        else {return false;}
    } //makeMove

}