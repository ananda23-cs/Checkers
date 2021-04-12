/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Move Action class - stores move actions
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.Actions;

import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;
import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersMoveAction2 extends GameAction {
    // variables for row and columns and pieces
    private int XDire,YDire;
    private CheckersPiece piece;

    /**
     * constructor for CheckersMoveAction2
     *
     * @param player
     *      the player who created the action
     * @param XDir
     *      x direction of piece
     * @param YDir
     *      y direction of piece
     * @param piece
     *      players selected piece
     */
    public CheckersMoveAction2(GamePlayer player,int XDir,int YDir, CheckersPiece piece) {
        super(player);
        this.XDire = XDir;
        this.YDire = YDir;
        this.piece = piece;
    } //CheckersMoveAction2

    // getter methods for variables
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
