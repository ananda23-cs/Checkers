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
        else if (!(info instanceof CheckersGameState)) return;

        CheckersGameState current = new CheckersGameState((CheckersGameState) info);

        //delay for a second so human can see the computer's movements
        sleep(1);

        //chooses a row and column randomly
        //int row = (int) (1+Math.random()*8);
        //int col = (int) (1+Math.random()*8);


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
            if(playerNum == 1) {
                if (current.movePiece(current.p1Pieces[pieceIdx],xdirection,ydirection,playerNum)) {
                    invalid = false;
                    game.sendAction(new CheckersMoveAction2(this, xdirection, ydirection));
                }
                else if(current.capturepiece(current.p1Pieces[pieceIdx],
                playerNum,current.p2Pieces,xdirection,ydirection)){
                    game.sendAction(new CheckersCaptureAciton(this,xdirection,ydirection));
                    invalid = false;
                }

            }
            else{
                if (current.movePiece(current.p2Pieces[pieceIdx],xdirection,ydirection,playerNum)) {
                    invalid = false;
                    game.sendAction(new CheckersMoveAction2(this, xdirection, ydirection));
                }

                else if(current.capturepiece(current.p2Pieces[pieceIdx],
                        playerNum,current.p1Pieces,xdirection,ydirection)){
                    game.sendAction(new CheckersCaptureAciton(this,xdirection,ydirection));
                    invalid = false;

                }

            }


        }



        //game.sendAction(new CheckersMoveAction(CheckersComputerPlayer1.this, row, col));
    }

}
