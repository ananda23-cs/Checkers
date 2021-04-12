/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Choose action class - choose the action between move and capture
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class ChooseAction extends GameAction {
    int x,y;

    /**
     * constructor for ChooseAction
     *
     * @param player
     *      the player who created the action
     * @param x
     *      x coordinate of piece
     * @param y
     *      y coordinate of piece
     */
    public ChooseAction(GamePlayer player,int x,int y){
        super(player);
        this.x = x;
        this.y = y;
    } //ChooseAction
}
