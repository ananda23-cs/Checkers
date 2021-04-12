package com.example.checkers.CheckersGame.Actions;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class ChooseAction extends GameAction {
    private int x,y;
    public ChooseAction(GamePlayer player,int x,int y){
        super(player);
        this.x = x;
        this.y = y;
    }

    //getter methods for x and y coordinates

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
