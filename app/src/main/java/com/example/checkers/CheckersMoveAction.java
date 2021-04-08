package com.example.checkers;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersMoveAction extends GameAction {

    private int row;
    private int col;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public CheckersMoveAction(GamePlayer player, int row, int col) {
        super(player);
        this.row = Math.max(0,Math.min(7,row));
        this.col = Math.max(0,Math.min(7,col));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
