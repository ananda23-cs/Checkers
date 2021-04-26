package com.example.checkers.CheckersGame.Actions;

import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersCanNotMoveAction extends GameAction {
    public CheckersCanNotMoveAction(GamePlayer player) {
        super(player);
    }
}
