package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersCaptureAciton  extends GameAction {
    int XDire,YDire;
    public CheckersCaptureAciton(GamePlayer player, int XDir, int YDir) {
        super(player);
        this.XDire = XDir;
        this.YDire = YDire;
    }
}
