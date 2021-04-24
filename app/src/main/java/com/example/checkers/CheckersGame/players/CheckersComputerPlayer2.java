/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Computer Player 2 - Smart AI
 *
 * CS301A
 * @version 04/23/2021
 */

package com.example.checkers.CheckersGame.players;

import com.example.checkers.CheckersGame.Actions.CheckersChoosePieceAction;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;
import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameComputerPlayer;

import java.util.ArrayList;

public class CheckersComputerPlayer2 extends GameComputerPlayer {

    public CheckersComputerPlayer2(String name){
        super(name);
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

            if(((CheckersGameState) info).getPieceSelectedPiece() != null) {
                return;
            }

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


            /* runs through all the computer players pieces checks all the moves.
               It adds all possible moves to the arraylist above*/
            for(CheckersPiece piece: Pieces){
                if(piece.getAlive()) {

                    //if moving left backwards is a possible move for this piece it gets added to the list of moves
                    checkValidMove(((CheckersGameState) info), piece,-1,-1, possibleMoves);
                    //if moving right backwards is a possible move for this piece it gets added to the list of moves
                    checkValidMove(((CheckersGameState) info), piece,+1,-1, possibleMoves);
                    //if moving right Forward is a possible move for this piece it gets added to the list of moves
                    checkValidMove(((CheckersGameState) info), piece,+1,+1, possibleMoves);
                    //if moving left Forward is a possible move for this piece it gets added to the list of moves
                    checkValidMove(((CheckersGameState) info), piece,-1,+1, possibleMoves);

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
            int possibleSafeMovesIndex = (int) (Math.random()*possibleSafeMoves.size());

            sleep(0.5);

            if(possibleCaptures.size() > 0){
                for(CheckersChoosePieceAction action : possibleCaptures.get(possibleCapturesIndex)){
                    game.sendAction(action);
                }
            }

            if(possibleSafeMoves.size() > 0){
                for(CheckersChoosePieceAction action : possibleSafeMoves.get(possibleSafeMovesIndex)){
                    game.sendAction(action);
                }
            }

            else if(possibleMoves.size() > 0) {
                for (CheckersChoosePieceAction action : possibleMoves.get(possibleMovesIndex)) {
                    game.sendAction(action);
                }
            }
        }
    } //receiveInfo

    /**
     * Checks for all possible valid moves and adds them to an ArrayList.
     * @param info the current game state
     * @param piece the selected piece
     * @param xDire the x direction of the piece selected when it will move
     * @param yDire the y direction of the selected piece when it will move
     * @param possibleMoves the array list of all the possible moves made by the piece
     */
    public void checkValidMove(CheckersGameState info,CheckersPiece piece,int xDire,
                               int yDire,ArrayList<CheckersChoosePieceAction[]> possibleMoves ){
        if(info.canMove(piece,xDire,yDire,info.getPlayerTurn())){
            CheckersChoosePieceAction[] mL = new CheckersChoosePieceAction[2];
            mL[0] = new CheckersChoosePieceAction(this, piece.getXcoordinate(),
                                                               piece.getYcoordinate());
            mL[1] = new CheckersChoosePieceAction(this, piece.getXcoordinate()+ xDire,
                                                               piece.getYcoordinate() + yDire);
            possibleMoves.add(mL);
        }
    }

    /**
     * Checks for all possible valid captures and adds them to an ArrayList.
     * @param info the current game state
     * @param piece the selected piece
     * @param possibleMoves the array list of all the possible moves made by the piece
     * @param xDire the x direction of the piece selected
     *              when it will capture an enemy piece
     * @param yDire the y direction of the selected piece when it will
     *              capture an enemy piece
     */
    public void checkValidCapture(CheckersGameState info, CheckersPiece piece,
                                  ArrayList<CheckersChoosePieceAction[]> possibleMoves,
                                  int xDire, int yDire){

        info.setPieceSelectedPieceAndPieceSelectedBoolean(
                                                    piece.getXcoordinate(),piece.getYcoordinate());
        if(info.checkIfCanCaptureEnemyPiece(piece.getXcoordinate()+xDire,
                                            piece.getYcoordinate()+yDire,
                                                piece.getXcoordinate(), piece.getYcoordinate())){
            CheckersChoosePieceAction[] mL = new CheckersChoosePieceAction[2];
            mL[0] = new CheckersChoosePieceAction(this, piece.getXcoordinate(),
                                                               piece.getYcoordinate());
            mL[1] = new CheckersChoosePieceAction(this, piece.getXcoordinate()+xDire,
                                                               piece.getYcoordinate() + yDire);
            possibleMoves.add(mL);
        }
        info.setPieceSelectedPieceAndPieceSelectedBoolean();
    } //checkValidCapture

    /**
     * Checks for all possible safe moves a piece can make and adds them to an ArrayList
     * @param copy the current game state
     * @param enemyPieces the array containing the enemy's pieces
     * @param piece the selected piece
     * @param xDire the x direction of the piece selected when moving
     * @param yDire the y direction of the selected piece when moving
     * @param possibleSafeMoves an array list of all the possible safe moves
     */
    public void safeMove(CheckersGameState copy, CheckersPiece[] enemyPieces, CheckersPiece piece,
                         int xDire, int yDire,
                         ArrayList<CheckersChoosePieceAction[]> possibleSafeMoves){
        if (copy.canMove(piece, xDire, yDire, copy.getPlayerTurn()) ) {
            boolean safeSpot = true;

            for(CheckersPiece enemyPiece : enemyPieces){
                copy.setPieceSelectedPieceAndPieceSelectedBoolean(
                        enemyPiece.getXcoordinate(), enemyPiece.getYcoordinate());

                if(copy.getPlayerTurn() == 0 ) {
                    copy.setPlayerTurn(1);
                }
                else{
                    copy.setPlayerTurn(0);
                }

                piece.setCoordinates(piece.getXcoordinate()+xDire,
                                     piece.getYcoordinate()+yDire);

                if(copy.checkIfCanCaptureEnemyPiece(piece.getXcoordinate(), piece.getYcoordinate(),
                                    enemyPiece.getXcoordinate(), enemyPiece.getYcoordinate())){
                    safeSpot = false;
                }
                piece.setCoordinates(piece.getXcoordinate()-xDire,
                                     piece.getYcoordinate()-yDire);


                if(copy.getPlayerTurn() == 1 ) {
                    copy.setPlayerTurn(0);
                }
                else{
                    copy.setPlayerTurn(1);
                }

            }

            copy.setPieceSelectedPieceAndPieceSelectedBoolean(piece.getXcoordinate(),
                                                                            piece.getYcoordinate());

            if(safeSpot) {
                CheckersChoosePieceAction[] sM = new CheckersChoosePieceAction[2];
                sM[0] = (new CheckersChoosePieceAction(this, piece.getXcoordinate(),
                                                                    piece.getYcoordinate()));
                sM[1] = (new CheckersChoosePieceAction(this,piece.getXcoordinate()+xDire,
                                                                 piece.getYcoordinate()+yDire));
                possibleSafeMoves.add(sM);
            }
        }
    } //safeMove
}//CheckersComputerPlayer2
