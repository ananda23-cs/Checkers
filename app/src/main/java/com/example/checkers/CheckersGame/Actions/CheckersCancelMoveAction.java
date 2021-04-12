/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Cancel Move Action class - deselects the piece the player selected
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.Actions;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersCancelMoveAction extends GameAction {
    // variables for row and column
    //private int selectedRow;
    //private int selectedCol;

    /**
     * constructor for CheckersCancelMoveAction
     *
     * @param player
     *      the player who created the action
     */
    public CheckersCancelMoveAction(GamePlayer player) {
        super(player);
        //this.selectedRow = selectedRow;
        //this.selectedCol = selectedCol;
    } //CheckersCancelMoveAction

    // getter method for variables
    /*public int getSelectedRow() {
        return selectedRow;
    }
    public int getSelectedCol() {
        return selectedCol;
    }*/
}
