/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Move Action class - stores move actions
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersMoveAction extends GameAction {
    private int toRow;
    private int toCol;
    private int selectedRow;
    private int selectedCol;
    private CheckersPiece checkersPiece;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public CheckersMoveAction(GamePlayer player, int selectedRow, int selectedCol,
                              int toRow, int toCol, CheckersPiece piece) {
        super(player);
        this.selectedRow = selectedRow;
        this.selectedCol = selectedCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.checkersPiece = piece;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public int getSelectedCol() {
        return selectedCol;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }

    public CheckersPiece getCheckersPiece() {
        return checkersPiece;
    }
}
