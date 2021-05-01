/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Choose action class - choose the action between move and capture
 *
 * CS301A
 * @version 04/30/2021
 *
 * No outside sources needed for this class.
 */

package com.example.checkers.CheckersGame.Actions;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersChoosePieceAction extends GameAction {
    private int x,y;

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
    public CheckersChoosePieceAction(GamePlayer player, int x, int y){
        super(player);
        this.x = Math.max(0,Math.min(7,x));
        this.y = Math.max(0,Math.min(7,y));
    } //ChooseAction

    // getter methods for x and y location
    public int getXLoc() {
        return x;
    }
    public int getYLoc() {
        return y;
    }
}
