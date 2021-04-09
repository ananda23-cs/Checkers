/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Move Action class - stores move actions
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersMoveAction extends GameAction {

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public CheckersMoveAction(GamePlayer player) {
        super(player);
    }
}
