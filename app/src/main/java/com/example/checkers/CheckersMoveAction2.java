package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

/*
 *this is a new move action class. I did not want to delete code so I made a new move action class
 */
public class CheckersMoveAction2 extends GameAction {
    int XDire,YDire;
    public CheckersMoveAction2(GamePlayer player,int XDir,int YDir) {
        super(player);
        this.XDire = XDir;
        this.YDire = YDire;
    }
}
