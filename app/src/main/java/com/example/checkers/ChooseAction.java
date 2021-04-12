package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class ChooseAction extends GameAction {
    int x,y;
    public ChooseAction(GamePlayer player,int x,int y){
        super(player);
        this.x = x;
        this.y = y;
    }
}
