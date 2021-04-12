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

public class CheckersMoveAction extends GameAction {
    // variables for row and columns and pieces
    private int toRow;
    private int toCol;
    private int selectedRow;
    private int selectedCol;
    private CheckersPiece checkersPiece;

    /**
     * constructor for CheckersMoveAction
     *
     * @param player
     *      the player who created the action
     * @param selectedRow
     *      gets the players selected row
     * @param selectedCol
     *      gets the players selected column
     * @param toRow
     *      pieces row
     * @param toCol
     *      pieces column
     * @param piece
     *      players piece
     */
    public CheckersMoveAction(GamePlayer player, int selectedRow, int selectedCol,
                              int toRow, int toCol, CheckersPiece piece) {
        super(player);
        this.selectedRow = selectedRow;
        this.selectedCol = selectedCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.checkersPiece = piece;
    } //CheckersMoveAction

    // getter methods for variables
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
