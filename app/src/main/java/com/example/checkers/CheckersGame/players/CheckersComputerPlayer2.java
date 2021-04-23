/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 2 - Smart AI
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.players;

import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameComputerPlayer;

import java.util.ArrayList;

public class CheckersComputerPlayer2 extends GameComputerPlayer {

    //int id;
    public CheckersComputerPlayer2(String name){
        super(name);
        //int id;
    } //CheckersComputerPlayer2

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof NotYourTurnInfo) return;
    } //receiveInfo
}
