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
    public CheckersLocalGame(){
        super();
        super.state = new CheckersGameState();
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
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof CheckersCancelMoveAction){
            CheckersCancelMoveAction cancelMoveAction = (CheckersCancelMoveAction) action;
            CheckersGameState state = (CheckersGameState) super.state;
            int cancelRow = cancelMoveAction.getSelectedRow();
            int cancelCol = cancelMoveAction.getSelectedCol();
            state.setPieceSelectedPieceAndPieceSelectedBoolean(cancelRow,cancelCol);
        }
        else if (action instanceof CheckersMoveAction){

        }
        return false;
    }
}
