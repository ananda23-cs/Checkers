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

    CheckersGameState checkersGameState;//I added this

    public CheckersLocalGame(){
        //I am commenting this out
        //super();
        //super.state = new CheckersGameState();
        checkersGameState = new CheckersGameState();
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
            if(checkersGameState.movePiece(((CheckersMoveAction2) action).getPiece(),
            ((CheckersMoveAction2)action).getXDire(),((CheckersMoveAction2) action).YDire,
            checkersGameState.getPlayerTurn())){
                return true;
            }
            else{
                return false;
            }

        }

        else if (action instanceof CheckersCaptureAction){
            CheckersPiece piece = ((CheckersCaptureAction) action).checkersPiece;
            if(checkersGameState.capturepiece(piece,checkersGameState.getPlayerTurn(),
            checkersGameState.p1Pieces,((CheckersCaptureAction) action).getXDire(),((CheckersCaptureAction) action).getYDire())){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }
}
