/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Capture action class - capture opponents pieces
 *
 * CS301A
 * @version 04/11/2021
 */
package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersCaptureAction extends GameAction {
    // variables for coordinates and pieces
    private int XDire,YDire;
    private CheckersPiece checkersPiece;

    /**
     * constructor for CheckersCancelMoveAction
     *
     * @param player
     *      the player who created the action
     * @param XDir
     *      x direction of piece
     * @param YDir
     *      y direction of piece
     * @param checkersPiece
     *      checkers piece
     */
    public CheckersCaptureAction(GamePlayer player, int XDir, int YDir,CheckersPiece checkersPiece) {
        super(player);
        this.XDire = XDir;
        this.YDire = YDir;
        this.checkersPiece = checkersPiece;
    } //CheckersCaptureAction

    // getter methods for coordinates
    /*public int getXDire() {
        return XDire;
    }
    public int getYDire() {
        return YDire;
    }
    public CheckersPiece getCheckersPiece() {
        return checkersPiece;
    } */
}
