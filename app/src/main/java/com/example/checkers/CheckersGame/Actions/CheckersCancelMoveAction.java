/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Cancel Move Action class - deselects the piece the player selected
 *
 * CS301A
 * @version 04/30/2021
 *
 * No outside sources needed for this class.
 */

package com.example.checkers.CheckersGame.Actions;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersCancelMoveAction extends GameAction {

    /**
     * constructor for CheckersCancelMoveAction
     *
     * @param player
     *      the player who created the action
     */
    public CheckersCancelMoveAction(GamePlayer player) {
        super(player);
    } //CheckersCancelMoveAction

}
