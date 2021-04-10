package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersCaptureAction extends GameAction {
    int XDire,YDire;
    public CheckersCaptureAction(GamePlayer player, int XDir, int YDir) {
        super(player);
        this.XDire = XDir;
        this.YDire = YDir;
    }

    public int getXDire() {
        return XDire;
    }

    public int getYDire() {
        return YDire;
    }
}
