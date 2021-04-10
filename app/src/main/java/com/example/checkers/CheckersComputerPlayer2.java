/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 2 - Smart AI
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import com.example.checkers.game.GameFramework.GameMainActivity;
import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameComputerPlayer;

public class CheckersComputerPlayer2 extends GameComputerPlayer {

    int id;
    public CheckersComputerPlayer2(String name){
        super(name);
        int id;
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        //if (info instanceof NotYourTurnInfo) return

        CheckersGameState current = new CheckersGameState((CheckersGameState) info);
        if(current.getPlayerTurn() == this.id){
            return;
        }
        sleep(1);

        boolean invalid = true;

        while(invalid) {
            //chooses the index of a computer player's piece;
            int pieceIdx = (int) Math.random() * 11;
            int [] directions = new int[2];
            directions[0]= -1;
            directions[1] = 1;
            int xdirection = directions[(int)(Math.random()*2)];
            int ydirection = directions[(int)(Math.random()*2)];


            //CheckersPiece piece, int xDir,int yDir,int id
            if(id == 1) {
                if (current.movePiece(current.p1Pieces[pieceIdx],xdirection,ydirection,id)) {
                    invalid = false;
                    game.sendAction(new CheckersMoveAction2(this, xdirection, ydirection));
                }
                else if(current.capturepiece(current.p1Pieces[pieceIdx],
                        id,current.p2Pieces,xdirection,ydirection)){
                    game.sendAction(new CheckersCaptureAciton(this,xdirection,ydirection));
                    invalid = false;
                }

            }
            else{
                if (current.movePiece(current.p2Pieces[pieceIdx],xdirection,ydirection,id)) {
                    invalid = false;
                    game.sendAction(new CheckersMoveAction2(this, xdirection, ydirection));
                }

                else if(current.capturepiece(current.p2Pieces[pieceIdx],
                        id,current.p1Pieces,xdirection,ydirection)){
                    game.sendAction(new CheckersCaptureAciton(this,xdirection,ydirection));
                    invalid = false;

                }

            }


        }

    }

}
