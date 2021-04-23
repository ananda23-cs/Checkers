/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 2 - Smart AI
 *This captures a piece when ever it can
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.players;

import android.util.Log;

import com.example.checkers.CheckersGame.Actions.CheckersChoosePieceAction;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;
import com.example.checkers.game.GameFramework.GameMainActivity;
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

    /**
     * Called when the player receives a game-state (or other info) from the
     * game.
     *
     * @param info the message from the game
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // return if not computer player's turn or not an instance of game state
        if (info instanceof NotYourTurnInfo) {
            return;
        }
        else if(!(info instanceof CheckersGameState)) {
            return;
        }
        else if(((CheckersGameState) info).getPlayerTurn() == playerNum){


            //sleep(1);

            //list of possible moves
            ArrayList<CheckersChoosePieceAction[]> possibleSafeMoves = new ArrayList<CheckersChoosePieceAction[]>();
            ArrayList<CheckersChoosePieceAction[]> possibleMoves = new ArrayList<CheckersChoosePieceAction[]>();
            ArrayList<CheckersChoosePieceAction[]> possibleCaptures = new ArrayList<CheckersChoosePieceAction[]>();

            CheckersPiece[] Pieces;
            CheckersPiece[] EnemyPieces;

            if(((CheckersGameState) info).getPlayerTurn() == 1){
                Pieces = ((CheckersGameState) info).p2Pieces;
                EnemyPieces = ((CheckersGameState) info).p1Pieces;
            }
            else{
                Pieces = ((CheckersGameState) info).p1Pieces;
                EnemyPieces = ((CheckersGameState) info).p2Pieces;
            }


            //runs through all the computer players pieces checks all the moves. It adds all possible moves to the
            //arraylist above
            for(CheckersPiece piece: Pieces){
                if(piece.getAlive()) {
                    //if moving left backwards is a possible move for this piece it gets added to the list of moves
                    checkValidMove(((CheckersGameState) info), piece, -1, -1, possibleMoves);
                    //if moving right backwards is a possible move for this piece it gets added to the list of moves
                    checkValidMove(((CheckersGameState) info), piece, +1, -1, possibleMoves);
                    //if moving right Forward is a possible move for this piece it gets added to the list of moves
                    checkValidMove(((CheckersGameState) info), piece, +1, +1, possibleMoves);
                    //if moving left Forward is a possible move for this piece it gets added to the list of moves
                    checkValidMove(((CheckersGameState) info), piece, -1, +1, possibleMoves);

                    //if capturing right backwards is a valid move it gets added to the list of possible moves
                    checkValidCapture(((CheckersGameState) info), piece, possibleCaptures, +1, -1);
                    //if capturing left backwards is a valid move it gets added to the list of possible moves
                    checkValidCapture(((CheckersGameState) info), piece, possibleCaptures, -1, -1);
                    //if capturing right forwards is a valid move it gets added to the list of possible moves
                    checkValidCapture(((CheckersGameState) info), piece, possibleCaptures, +1, +1);
                    //if capturing left forwards is a valid move it gets added to the list of possible moves
                    checkValidCapture(((CheckersGameState) info), piece, possibleCaptures, -1, +1);

                    //adds all possible safe moves
                    safeMove(((CheckersGameState) info), EnemyPieces,piece,-1,-1,possibleSafeMoves);
                    safeMove(((CheckersGameState) info), EnemyPieces,piece,+1,-1,possibleSafeMoves);
                    safeMove(((CheckersGameState) info), EnemyPieces,piece,-1,+1,possibleSafeMoves);
                    safeMove(((CheckersGameState) info), EnemyPieces,piece,+1,+1,possibleSafeMoves);
                }



            }

            int possibleCapturesIndex = (int)(Math.random()*(possibleCaptures.size()));
            int possibleMovesIndex = (int)(Math.random()*(possibleMoves.size()));
            int possibleSaveMovesIndex = (int) (Math.random()*possibleSafeMoves.size());

            if(possibleCaptures.size()>0){
                for(CheckersChoosePieceAction action : possibleCaptures.get(possibleCapturesIndex)){

                    game.sendAction(action);
                }
            }

            if(possibleSafeMoves.size()>0){
                for(CheckersChoosePieceAction action : possibleSafeMoves.get(possibleSaveMovesIndex)){
                    game.sendAction(action);

                }
            }

            else if(possibleMoves.size() >0) {
                for (CheckersChoosePieceAction action : possibleMoves.get(possibleMovesIndex)) {
                    game.sendAction(action);
                }
            }




        }
    } //receiveInfo

    public void checkValidMove(CheckersGameState info,CheckersPiece piece,int xDire,
                               int yDire,ArrayList<CheckersChoosePieceAction[]> possibleMoves ){

        if(info.canMove(piece,xDire,yDire,info.getPlayerTurn())){
            CheckersChoosePieceAction[] mL = new CheckersChoosePieceAction[2];
            mL[0] = new CheckersChoosePieceAction(this, piece.getXcoordinate(), piece.getYcoordinate());
            mL[1] = new CheckersChoosePieceAction(this, piece.getXcoordinate()+ xDire,piece.getYcoordinate() + yDire);
            possibleMoves.add(mL);

        }

    }

    public void checkValidCapture(CheckersGameState info,CheckersPiece piece,ArrayList<CheckersChoosePieceAction[]> possibleMoves
            ,int xDire, int yDire){

        ((CheckersGameState) info).setPieceSelectedPieceAndPieceSelectedBoolean(piece.getXcoordinate(),piece.getYcoordinate());

        if(((CheckersGameState) info).checkIfCanCaptureEnemyPiece(piece.getXcoordinate()+xDire,piece.getYcoordinate()+yDire,piece.getXcoordinate(),piece.getYcoordinate())){
            CheckersChoosePieceAction[] mL = new CheckersChoosePieceAction[2];
            mL[0] = new CheckersChoosePieceAction(this, piece.getXcoordinate(), piece.getYcoordinate());
            mL[1] = new CheckersChoosePieceAction(this, piece.getXcoordinate()+xDire,piece.getYcoordinate() + yDire);
            possibleMoves.add(mL);
            ((CheckersGameState) info).setPieceSelectedPieceAndPieceSelectedBoolean();

        }
        ((CheckersGameState) info).setPieceSelectedPieceAndPieceSelectedBoolean();

    }

    public void safeMove(CheckersGameState copy,CheckersPiece[] enemyPieces, CheckersPiece piece,int xdire,int ydire,ArrayList<CheckersChoosePieceAction[]> possibleSafeMoves){
        if (copy.canMove(piece, xdire, ydire, copy.getPlayerTurn()) ) {
            boolean safeSpot = true;


            for(CheckersPiece enemyPiece : enemyPieces){
                copy.setPieceSelectedPieceAndPieceSelectedBoolean(enemyPiece.getXcoordinate(), enemyPiece.getYcoordinate());

                if(copy.getPlayerTurn() == 0 ) {
                    copy.setPlayerTurn(1);
                }
                else{
                    copy.setPlayerTurn(0);
                }

                piece.setCoordinates(piece.getXcoordinate()+xdire,piece.getYcoordinate()+ydire);

                if(copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate(), piece.getYcoordinate(),enemyPiece.getXcoordinate(), enemyPiece.getYcoordinate())){
                    safeSpot = false;
                }
                piece.setCoordinates(piece.getXcoordinate()-xdire,piece.getYcoordinate()-ydire);


                if(copy.getPlayerTurn() == 1 ) {
                    copy.setPlayerTurn(0);
                }
                else{
                    copy.setPlayerTurn(1);
                }

            }

            copy.setPieceSelectedPieceAndPieceSelectedBoolean(piece.getXcoordinate(), piece.getYcoordinate());

            if(safeSpot) {
                CheckersChoosePieceAction[] sM = new CheckersChoosePieceAction[2];
                sM[0] = (new CheckersChoosePieceAction(this, piece.getXcoordinate(), piece.getYcoordinate()));
                sM[1] = (new CheckersChoosePieceAction(this, piece.getXcoordinate() + xdire, piece.getYcoordinate() + ydire));
                possibleSafeMoves.add(sM);
            }
        }
    }
}
