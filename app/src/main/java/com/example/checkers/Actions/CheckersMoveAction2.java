package com.example.checkers.Actions;

import com.example.checkers.CheckersPiece;
import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

/*
 *this is a new move action class. I did not want to delete code so I made a new move action class
 */
public class CheckersMoveAction2 extends GameAction {
    private int XDire,YDire;
    private CheckersPiece piece;
    public CheckersMoveAction2(GamePlayer player,int XDir,int YDir, CheckersPiece piece) {
        super(player);
        this.XDire = XDir;
        this.YDire = YDir;
        this.piece = piece;
    }

    public int getXDire() {
        return XDire;
    }

    public int getYDire() {
        return YDire;
    }

    public CheckersPiece getPiece() {
        return piece;
    }
}
