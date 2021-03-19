package com.example.checkers;

import android.view.View;

import com.example.checkers.game.GameFramework.GameMainActivity;
import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.players.GameHumanPlayer;

public class CheckersHumanPlayer extends GameHumanPlayer {
    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void gameSetAsGui(GameMainActivity activity) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }

    @Override
    public void sendInfo(GameInfo info) {

    }
}
