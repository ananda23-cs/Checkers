/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Can not move action - check if the user can not make any more valid moves
 *
 * CS301A
 * @version 04/30/2021
 *
 * No outside sources needed for this class.
 */
package com.example.checkers.CheckersGame.Actions;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersCanNotMoveAction extends GameAction {
    public CheckersCanNotMoveAction(GamePlayer player) {
        super(player);
    }
}
