package com.example.checkers.CheckersGame.Actions;

import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;
import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersCaptureAction extends GameAction {
    private int XDire,YDire;
    private CheckersPiece checkersPiece;
    public CheckersCaptureAction(GamePlayer player, int XDir, int YDir,CheckersPiece checkersPiece) {
        super(player);
        this.XDire = XDir;
        this.YDire = YDir;
        this.checkersPiece = checkersPiece;
    }

    public int getXDire() {
        return XDire;
    }

    public int getYDire() {
        return YDire;
    }

    public CheckersPiece getCheckersPiece() {
        return checkersPiece;
    }
}
