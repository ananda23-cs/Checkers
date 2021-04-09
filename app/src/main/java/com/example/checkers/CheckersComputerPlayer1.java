/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 1 - Dumb AI
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameComputerPlayer;

public class CheckersComputerPlayer1 extends GameComputerPlayer {

    public CheckersComputerPlayer1(String name){
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        //ignore if not the computer's turn
        if(info instanceof NotYourTurnInfo) return;

        //chooses a row and column randomly
        int row = (int) (1+Math.random()*8);
        int col = (int) (1+Math.random()*8);

        //delay for a second so human can see the computer's movements
        sleep(1);

        game.sendAction(new CheckersMoveAction(CheckersComputerPlayer1.this, row, col));
    }

}
