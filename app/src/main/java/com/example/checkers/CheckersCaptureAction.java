/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Cancel Move Action class - deselects the piece the player selected
 * this class is not needed.
 * CS301A
 * @version 04/11/2021
 */
package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;


public class CheckersCaptureAction extends GameAction {
    private int XDire;//xlocation and  of where the piece is going
    private int YDire;//ylocation and  of where the piece is going
    private CheckersPiece checkersPiece;//the piece that is doing the capturing
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
