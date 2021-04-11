/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Local game class - creates a new game state, implement the move,
 * capture, and turn to king actions, and update it to the new game state
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import com.example.checkers.game.GameFramework.LocalGame;
import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersLocalGame extends LocalGame {

    //CheckersGameState checkersGameState;//I added this

    public CheckersLocalGame(){
        //I am commenting this out
        super();
        super.state = new CheckersGameState();
        //checkersGameState = new CheckersGameState();
    }

    public CheckersLocalGame(CheckersGameState checkersGameState){
        super();
        super.state = new CheckersGameState(checkersGameState);
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new CheckersGameState((CheckersGameState) state));
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == ((CheckersGameState) state).getPlayerTurn();
    }

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

    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof CheckersCancelMoveAction){
            CheckersCancelMoveAction cancelMoveAction = (CheckersCancelMoveAction) action;
            CheckersGameState state = (CheckersGameState) super.state;
            int cancelRow = cancelMoveAction.getSelectedRow();
            int cancelCol = cancelMoveAction.getSelectedCol();
            state.setPieceSelectedPieceAndPieceSelectedBoolean(cancelRow,cancelCol);
            state.setMessage("The piece at " + cancelRow + ", " + cancelCol +
                                " has been unselected. Please select another piece.");
            return true;
        }
        else if (action instanceof CheckersMoveAction2){
            CheckersMoveAction2 moveAction = (CheckersMoveAction2) action;
            CheckersGameState state = (CheckersGameState) super.state;
            int playerId = state.getPlayerTurn();
            int xDir = moveAction.getXDire();
            int yDir = moveAction.getYDire();
            CheckersPiece piece = moveAction.getPiece();
            if(state.canMove(state.getPieceSelectedPiece(),xDir,yDir,state.getPlayerTurn())) {
                state.setPieceSelectedPieceAndPieceSelectedBoolean(piece.getXcoordinate(),
                        piece.getYcoordinate());
                state.movePiece(piece, xDir, yDir, playerId);
                if (state.getPlayerTurn() == 0) {
                    state.setMessage("That move was valid. Player two please choose a piece");
                } else {
                    state.setMessage("That move was valid. Player one please choose a piece");
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
                return true;
            }
            else{ return false; }
        }
        return false;
    }
}
