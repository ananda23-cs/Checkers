package com.example.checkers;

import com.example.checkers.game.GameFramework.GameMainActivity;
import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameComputerPlayer;

public class CheckersComputerPlayer2 extends GameComputerPlayer {

    public CheckersComputerPlayer2(String name){
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof NotYourTurnInfo) return;

    }

}
