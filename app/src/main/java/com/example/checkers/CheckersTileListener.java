/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Tile listener class - Sets up onClick listener for the ImageButton tiles
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class CheckersTileListener implements View.OnClickListener {

    int xCord;//the xcord of the button its litenting to
    int yCord;//the xcord of the button its litenting to
    CheckersGameState gameState;//the current game state
    TextView gameInfo;//the text view that displays who's turn it is
    ImageButton[][] board;

    //initial constructor
    public CheckersTileListener(int xCord, int yCord, CheckersGameState gameState,TextView gameInfo,ImageButton[][] board){
        this.xCord = xCord;
        this.yCord = yCord;
        this.gameState = gameState;
        this.gameInfo = gameInfo;
        this.board = board;
    }

    //this programs what actually happens when a button is clicked
    @Override
    public void onClick(View v) {
        //this if statement is to choose a piece to move
        if(gameState.pieceSelectedBoolean == false){
            //this checks if they chose an empty spot
            if(gameState.isEmpty(xCord,yCord)){
                gameInfo.setText("This tile is empty. Pick another square");
            }
            else {
                //this checks if the piece belongs to the player
                if (gameState.hasEnemyPieces(xCord,yCord)) {
                    gameInfo.setText("This piece is not yours. Please try again.");
                }

                //if all the conditions are right the piece is chosen
                else{
                    gameInfo.setText("This piece can be moved. Click on the spot where you want to move it.");
                    gameState.setPieceSelectedPieceAndPieceSelectedBoolean(xCord,yCord);

                }

            }

        }

        //if a piece has been chosen
        else{
            //these are the distances of where the piece will move to. If they are
            //invalid it will not do anything
            int newXCord = xCord - gameState.pieceSelectedPiece.getXcoordinate();
            int newYCord = yCord - gameState.pieceSelectedPiece.getYcoordinate();

            //if the player is trying to move and not capture
            if(!gameState.hasEnemyPieces(xCord,yCord)){

                //if the location is valid it moves
                if(gameState.movePiece(gameState.pieceSelectedPiece,newXCord,newYCord,gameState.getPlayerTurn())){
                    gameState.pieceSelectedBoolean = false;//sets the piece selected back to false
                    gameState.setBoard(board);
                    if(gameState.getPlayerTurn() == 1) {
                        gameInfo.setText("That move was valid. Player two please choose a piece");
                    }
                    else{
                        gameInfo.setText("That move was valid. Player one please choose a piece");
                    }

                }
                //Prints an error message if they can't move
                else{
                    gameInfo.setText("This move is invalid. Try again.");

                }

            }
            //if they are trying to capture a piece
            else{

                //player 1 capturing a piece
                if(gameState.getPlayerTurn() == 0) {
                    if (gameState.capturepiece(gameState.pieceSelectedPiece, gameState.getPlayerTurn(),
                            gameState.p2Pieces, newXCord, newYCord)) {
                        gameInfo.setText("You have captured a piece. Player 2, it's your turn");
                        gameState.setBoard(board);
                        gameState.pieceSelectedBoolean = false;
                    }
                    //prints an error message if they are trying to capture a piece that is not valid.
                    else{
                        gameInfo.setText("You can not capture this piece. Try again.");
                    }
                }

                //player 2 capturing a piece
                else if(gameState.getPlayerTurn() == 1){
                    if (gameState.capturepiece(gameState.pieceSelectedPiece, gameState.getPlayerTurn(),
                            gameState.p1Pieces, newXCord, newYCord)) {
                        gameInfo.setText("You have captured a piece. Player 1, it's your turn.");
                        gameState.setBoard(board);
                        gameState.pieceSelectedBoolean = false;
                    }
                    //prints an error message if they are trying to capture a piece that is not valid.
                    else{
                        gameInfo.setText("You can not capture this piece. Try again.");
                    }
                }



            }
        }
    }
}
