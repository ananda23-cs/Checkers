/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Cancel Move Action class - cancels the players move
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.Actions;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersCancelMoveAction extends GameAction {
    private int selectedRow;
    private int selectedCol;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public CheckersCancelMoveAction(GamePlayer player, int selectedRow, int selectedCol) {
        super(player);
        this.selectedRow = selectedRow;
        this.selectedCol = selectedCol;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public int getSelectedCol() {
        return selectedCol;
    }
}
