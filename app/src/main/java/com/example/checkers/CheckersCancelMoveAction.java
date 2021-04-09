/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Cancel Move Action class - cancels the players move
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersCancelMoveAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public CheckersCancelMoveAction(GamePlayer player) {
        super(player);
    }
}
