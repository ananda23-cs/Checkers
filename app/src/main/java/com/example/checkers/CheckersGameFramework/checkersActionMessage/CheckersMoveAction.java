/**
 *  @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 *  * Move Action class - stores move actions
 *  *
 *  * CS301A
 *  * @version 04/11/2021
 *
 */
package com.example.checkers.CheckersGameFramework.checkersActionMessage;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersMoveAction extends GameAction {
    private int x,y;//x and y location of where the player is moving to or if a piece has not been chosen then it
    //is where the piece that about to selcted should be


    public CheckersMoveAction(GamePlayer player, int x, int y){
        super(player);
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
