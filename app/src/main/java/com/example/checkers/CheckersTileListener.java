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

import com.example.checkers.game.GameFramework.Game;

public class CheckersTileListener implements View.OnClickListener {

    int xCord;//the xcord of the button its litenting to
    int yCord;//the xcord of the button its litenting to
    CheckersGameState gameState;//the current game state
    TextView gameInfo;//the text view that displays who's turn it is
    ImageButton[][] board;
    private boolean isClicked;
    CheckersHumanPlayer player;
    //CheckersHumanPlayer player2;
    Game game;

    //initial constructor
    public CheckersTileListener(int xCord, int yCord, CheckersGameState gameState,TextView gameInfo,ImageButton[][] board,
                                CheckersHumanPlayer player, Game game){
        this.xCord = xCord;
        this.yCord = yCord;
        this.gameState = gameState;
        this.gameInfo = gameInfo;
        this.board = board;
        this.player = player;
        //this.player2 = p2;
        this.game = game;
        isClicked = false;
    }

    //this programs what actually happens when a button is clicked
    @Override
    public void onClick(View v) {
        //this if statement is to choose a piece to move
        isClicked = true;
        CheckersGameState fake = (CheckersGameState) gameState;
        if(!fake.isPieceSelectedBoolean()){
            //this checks if they chose an empty spot
            if(fake.isEmpty(xCord,yCord)){
                gameInfo.setText("This tile is empty. Pick another square");
            }
            else {
                //this checks if the piece belongs to the player
                if (fake.hasEnemyPieces(xCord,yCord)) {
                    gameInfo.setText("This piece is not yours. Please try again.");
                }

                //if all the conditions are right the piece is chosen
                else{
                    gameInfo.setText("This piece can be moved. Click on the spot where you want to move it." + gameState.getPlayerTurn());
                    fake.setPieceSelectedPieceAndPieceSelectedBoolean(xCord,yCord);

                }

            }

        }

        //if a piece has been chosen
        else{
            //these are the distances of where the piece will move to. If they are
            //invalid it will not do anything
            int newXCord = xCord - fake.getPieceSelectedPiece().getXcoordinate();
            int newYCord = yCord - fake.getPieceSelectedPiece().getYcoordinate();

            //if the player is trying to move and not capture
            if(!fake.hasEnemyPieces(xCord,yCord)){

                //if the location is valid it moves
                //if(fake.canMove(fake.getPieceSelectedPiece(),newXCord,newYCord,fake.getPlayerTurn())){
                    game.sendAction(new CheckersMoveAction2(player, newXCord,newYCord, fake.getPieceSelectedPiece()));
                    /*fake.setPieceSelectedPieceAndPieceSelectedBoolean(fake.getPieceSelectedPiece().getXcoordinate(),
                            fake.getPieceSelectedPiece().getYcoordinate());//sets the piece selected back to false
                    //fake.setBoard(board);
                    /*if(fake.getPlayerTurn() == 0) {
                        gameInfo.setText("That move was valid. Player two please choose a piece");
                    }
                    else{
                        gameInfo.setText("That move was valid. Player one please choose a piece");
                    }*/

                //}
                //Prints an error message if they can't move
                //else{
                    //gameInfo.setText("This move is invalid. Try again.");

                //}

            }
            //if they are trying to capture a piece
            else{
                game.sendAction(new CheckersCaptureAction(player,newXCord,newYCord,fake.getPieceSelectedPiece()));
                //player 1 capturing a piece
                /*if(fake.getPlayerTurn() == 0) {
                    if (fake.capturepiece(fake.getPieceSelectedPiece(), fake.getPlayerTurn(),
                            fake.p2Pieces, newXCord, newYCord)) {

                        gameInfo.setText("You have captured a piece. Player 2, it's your turn");
                        game.sendAction(new CheckersCaptureAction(player,newXCord,newYCord,fake.getPieceSelectedPiece()));
                        fake.setBoard(board);
                        fake.setPieceSelectedPieceAndPieceSelectedBoolean(fake.getPieceSelectedPiece().getXcoordinate(),
                                fake.getPieceSelectedPiece().getYcoordinate());
                    }
                    //prints an error message if they are trying to capture a piece that is not valid.
                    else{
                        gameInfo.setText("You can not capture this piece. Try again.");
                    }
                }

                //player 2 capturing a piece
                else if(fake.getPlayerTurn() == 1){
                    if (fake.capturepiece(fake.getPieceSelectedPiece(), fake.getPlayerTurn(),
                            fake.p1Pieces, newXCord, newYCord)) {
                        gameInfo.setText("You have captured a piece. Player 1, it's your turn.");
                        game.sendAction(new CheckersCaptureAction(player,newXCord,newYCord,fake.getPieceSelectedPiece()));
                        fake.setBoard(board);
                        fake.setPieceSelectedPieceAndPieceSelectedBoolean(fake.getPieceSelectedPiece().getXcoordinate(),
                                fake.getPieceSelectedPiece().getYcoordinate());
                    }
                    //prints an error message if they are trying to capture a piece that is not valid.
                    else{
                        gameInfo.setText("You can not capture this piece. Try again.");
                    }
                }*/



            }
        }
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
