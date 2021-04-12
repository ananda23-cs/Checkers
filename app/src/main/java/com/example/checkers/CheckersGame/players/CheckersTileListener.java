/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Tile listener class - Sets up onClick listener for the ImageButton tiles
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.players;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.checkers.CheckersGame.Actions.CheckersCaptureAction;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.Actions.CheckersMoveAction2;
import com.example.checkers.game.GameFramework.Game;

public class CheckersTileListener implements View.OnClickListener {

    int xCord; // x coordinate button listens to
    int yCord; // y coordinate button listens to
    CheckersGameState gameState; // current game state
    TextView gameInfo; // displays who's turn it is, if move is valid, etc.
    ImageButton[][] board;
    private boolean isClicked;
    CheckersHumanPlayer player;
    //CheckersHumanPlayer player2;
    Game game;

    /**
     * constructor for CheckersTileListener
     *
     */
    public CheckersTileListener(int xCord, int yCord, CheckersGameState gameState,TextView gameInfo,
                                ImageButton[][] board, CheckersHumanPlayer player, Game game){
        this.xCord = xCord;
        this.yCord = yCord;
        this.gameState = new CheckersGameState(gameState);
        this.gameInfo = gameInfo;
        this.board = board;
        this.player = player;
        //this.player2 = p2;
        this.game = game;
        isClicked = false;
    } //CheckersTileListener

    /**
     * method onClick
     * programs what happens when each button is clicked
     *
     */
    @Override
    public void onClick(View v) {
        // player chooses a piece to move
        Log.e("onClick: ", ""+gameState.getPlayerTurn());
        isClicked = true;
        CheckersGameState fake = new CheckersGameState((CheckersGameState)gameState);
        if(!fake.isPieceSelectedBoolean()){
            // checks if spot is empty
            if(fake.isEmpty(xCord,yCord)){
                gameInfo.setText("This tile is empty. Pick another square");
            }
            else {
                // check if piece belongs to the player
                if (fake.hasEnemyPieces(xCord,yCord)) {
                    gameInfo.setText("This piece is not yours. Please try again.");
                }
                // if all the conditions are right the piece is chosen
                else{
                    gameInfo.setText("This piece can be moved. Click on the spot where you want to move it." + gameState.getPlayerTurn());
                    gameState.setPieceSelectedPieceAndPieceSelectedBoolean(xCord,yCord);
                }
            }
        }

        // if a piece is chosen
        else{
            // distances of where the piece will move to, do nothing if invalid
            int newXCord = xCord - fake.getPieceSelectedPiece().getXcoordinate();
            int newYCord = yCord - fake.getPieceSelectedPiece().getYcoordinate();

            // if player wants to move, not capture
            if(!fake.hasEnemyPieces(xCord,yCord)){
                if(fake.movePiece(fake.getPieceSelectedPiece(),newXCord,newYCord,fake.getPlayerTurn())) {
                    game.sendAction(new CheckersMoveAction2(player, newXCord, newYCord, gameState.getPieceSelectedPiece()));
                }
            }
            // if player wants to capture a piece
            else{
                // capture player 2's piece
                if(fake.capturepiece(fake.getPieceSelectedPiece(),fake.getPlayerTurn(),fake.p2Pieces,newXCord,newYCord) && fake.getPlayerTurn() == 0 ) {
                    game.sendAction(new CheckersCaptureAction(player, newXCord, newYCord, gameState.getPieceSelectedPiece()));
                }

                // capture player 1's piece
                if(fake.capturepiece(fake.getPieceSelectedPiece(),fake.getPlayerTurn(),fake.p1Pieces,newXCord,newYCord) && fake.getPlayerTurn() == 1 ) {
                    game.sendAction(new CheckersCaptureAction(player, newXCord, newYCord, gameState.getPieceSelectedPiece()));
                }
            }
        }
    } //onClick

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
