/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 1 - Dumb AI. Makes random moves
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.players;

import com.example.checkers.CheckersGame.Actions.CheckersChoosePieceAction;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;
import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameComputerPlayer;

import java.util.ArrayList;

public class CheckersComputerPlayer1 extends GameComputerPlayer {

    /**
     * constructor of CheckersComputerPlayer1
     */

    public CheckersComputerPlayer1(String name){

        super(name);

    }

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


            sleep(1);
            //list of possible moves
            ArrayList<CheckersChoosePieceAction[]> possibleMoves = new ArrayList<CheckersChoosePieceAction[]>();
            CheckersPiece [] Pieces;

            if(((CheckersGameState) info).getPlayerTurn() == 1){
                Pieces = ((CheckersGameState) info).p2Pieces;
            }
            else{
                Pieces = ((CheckersGameState) info).p1Pieces;
            }


            //runs through all the computer players pieces checks all the moves. It adds all possible moves to the
            //arraylist above
            for(CheckersPiece piece: Pieces){

                //if moving left backwards is a possible move for this piece it gets added to the list of moves
                checkValidMove(((CheckersGameState)info),piece,-1,-1,possibleMoves);
                //if moving right backwards is a possible move for this piece it gets added to the list of moves
                checkValidMove(((CheckersGameState)info),piece,+1,-1,possibleMoves);
                //if moving right Forward is a possible move for this piece it gets added to the list of moves
                checkValidMove(((CheckersGameState)info),piece,+1,+1,possibleMoves);
                //if moving left Forward is a possible move for this piece it gets added to the list of moves
                checkValidMove(((CheckersGameState)info),piece,-1,+1,possibleMoves);

                //if capturing right backwards is a valid move it gets added to the list of possible moves
                checkValidCapture(((CheckersGameState)info),piece,possibleMoves,+1,-1);
                //if capturing left backwards is a valid move it gets added to the list of possible moves
                checkValidCapture(((CheckersGameState)info),piece,possibleMoves,-1,-1);
                //if capturing right forwards is a valid move it gets added to the list of possible moves
                checkValidCapture(((CheckersGameState)info),piece,possibleMoves,+1,+1);
                //if capturing left forwards is a valid move it gets added to the list of possible moves
                checkValidCapture(((CheckersGameState)info),piece,possibleMoves,-1,+1);




            }

            int possibleMovesIndex = (int)(Math.random()*(possibleMoves.size()));
            if(possibleMoves.size() >0) {
                for (CheckersChoosePieceAction action : possibleMoves.get(possibleMovesIndex)) {
                    game.sendAction(action);
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




}
// loop until a valid move can be made
//                for (CheckersPiece piece : ((CheckersGameState) info).p2Pieces) {
//                    if (piece.getAlive()) {
//                        // computer move method
//                        game.sendAction(new CheckersChoosePieceAction(this, piece.getXcoordinate(),
//                                piece.getYcoordinate()));
//                        // move backwards diagonally left
//                        if (((CheckersGameState) info).canMove(piece, 1, -1,
//                                            ((CheckersGameState) info).getPlayerTurn())) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                                                piece.getXcoordinate() + 1,
//                                                                piece.getYcoordinate() - 1));
//
//                        }
//                        // move move backwards diagonally right
//                        if (((CheckersGameState) info).canMove(piece, -1, -1,
//                                ((CheckersGameState) info).getPlayerTurn())) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                                    piece.getXcoordinate() - 1,
//                                                    piece.getYcoordinate() - 1));
//                        }
//                        // move forwards diagonally right
//                        if (((CheckersGameState) info).canMove(piece, -1, +1,
//                                ((CheckersGameState) info).getPlayerTurn())) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                    piece.getXcoordinate() - 1,piece.getYcoordinate() + 1));
//                        }
//                        // move forwards diagonally left
//                        if (((CheckersGameState) info).canMove(piece, 1, +1,
//                                ((CheckersGameState) info).getPlayerTurn())) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                    piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));
//
//                        }
//
//                        // computer capture method
//                        // capture forwards diagonally left
//                        if (((CheckersGameState) info).hasEnemyPieces(
//                                piece.getXcoordinate() - 1,
//                                piece.getYcoordinate() - 1)) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                    piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
//                        }
//                        // capture forwards diagonally right
//                        if (((CheckersGameState) info).hasEnemyPieces(
//                                piece.getXcoordinate() + 1,
//                                piece.getYcoordinate() - 1)) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                    piece.getXcoordinate() + 1,
//                                    piece.getYcoordinate() - 1));
//                        }
//
//                        if (piece.getKing()) {
//                            // capture backwards diagonally left
//                            if (((CheckersGameState) info).hasEnemyPieces(
//                                    piece.getXcoordinate() - 1,
//                                    piece.getYcoordinate() + 1)) {
//                                game.sendAction(new CheckersChoosePieceAction(this,
//                                        piece.getXcoordinate()-1, piece.getYcoordinate()+1));
//                            }
//                            // capture backwards diagonally right
//                            if (((CheckersGameState) info).hasEnemyPieces(
//                                    piece.getXcoordinate() + 1,
//                                    piece.getYcoordinate() + 1)) {
//                                game.sendAction(new CheckersChoosePieceAction(this,
//                                        piece.getXcoordinate()+1, piece.getYcoordinate()+1));
//                            }
//                        }
//
//                        else{
//                            game.sendAction(new CheckersCancelMoveAction(this));
//                        }
//                    }
//
//                }
//            }
//            else{
//                for (CheckersPiece piece : ((CheckersGameState) info).p1Pieces) {
//                    if (piece.getAlive()) {
//                        // computer move method
//                        game.sendAction(new CheckersChoosePieceAction(this,
//                                                                        piece.getXcoordinate(),
//                                                                        piece.getYcoordinate()));
//                        // move backwards diagonally left
//                        if (((CheckersGameState) info).canMove(piece, 1, -1,
//                                ((CheckersGameState) info).getPlayerTurn())) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                    piece.getXcoordinate() + 1, piece.getYcoordinate() - 1));
//
//                        }
//                        // move move backwards diagonally right
//                        if (((CheckersGameState) info).canMove(piece, -1, -1,
//                                ((CheckersGameState) info).getPlayerTurn())) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                    piece.getXcoordinate() - 1, piece.getYcoordinate() - 1));
//                        }
//                        // move forwards diagonally right
//                        if (((CheckersGameState) info).canMove(piece, -1, +1,
//                                ((CheckersGameState) info).getPlayerTurn())) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                    piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));
//                        }
//                        // move forwards diagonally left
//                        if (((CheckersGameState) info).canMove(piece, 1, +1,
//                                ((CheckersGameState) info).getPlayerTurn())) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                    piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));
//
//                        }
//
//                        // computer capture method
//                        // capture forwards diagonally left
//                        if (((CheckersGameState) info).hasEnemyPieces(
//                                piece.getXcoordinate() - 1,
//                                piece.getYcoordinate() + 1)) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                    piece.getXcoordinate() - 1, piece.getYcoordinate() + 1));
//                        }
//                        // capture forwards diagonally right
//                        if (((CheckersGameState) info).hasEnemyPieces(
//                                piece.getXcoordinate() + 1,
//                                piece.getYcoordinate() + 1)) {
//                            game.sendAction(new CheckersChoosePieceAction(this,
//                                    piece.getXcoordinate() + 1, piece.getYcoordinate() + 1));
//                        }
//
//                        if (piece.getKing()) {
//                            // capture backwards diagonally left
//                            if (((CheckersGameState) info).hasEnemyPieces(
//                                    piece.getXcoordinate() - 1,
//                                    piece.getYcoordinate() - 1)) {
//                                game.sendAction(new CheckersChoosePieceAction(this,
//                                        piece.getXcoordinate() - 1,
//                                        piece.getYcoordinate() - 1));
//                            }
//                            // capture backwards diagonally right
//                            if (((CheckersGameState) info).hasEnemyPieces(
//                                    piece.getXcoordinate() + 1,
//                                    piece.getYcoordinate() - 1)) {
//                                game.sendAction(new CheckersChoosePieceAction(this,
//                                        piece.getXcoordinate() + 1,
//                                        piece.getYcoordinate() - 1));
//                            }
//                        }
//
//                        else{
//                            game.sendAction(new CheckersCancelMoveAction(this));
//                        }
//                    }
//
//                }



//                    if(((CheckersGameState) info).canMove(piece,-1,-1,1)){
//                       CheckersChoosePieceAction[] mL = new CheckersChoosePieceAction[2];
//                       mL[0] = new CheckersChoosePieceAction(this, piece.getXcoordinate(), piece.getYcoordinate());
//                       mL[1] = new CheckersChoosePieceAction(this, piece.getXcoordinate() - 1,piece.getYcoordinate() - 1);
//                       possibleMoves.add(mL);
//
//                    }


