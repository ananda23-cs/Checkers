package com.example.checkers;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

import com.example.checkers.game.GameFramework.GameMainActivity;
import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameHumanPlayer;

public class CheckersHumanPlayer extends GameHumanPlayer {

    private ImageButton[][] board;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public CheckersHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if(info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo){
            flash(Color.RED, 100);
        }
        else if(!(info instanceof CheckersGameState)) return;
        else{

        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        activity.setContentView(R.layout.activity_main);
        board = new ImageButton[9][9];
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                board[i][j].setOnClickListener(TileListener[i][j]);
            }
        }
    }

    /**
     * perform any initialization that needs to be done after player
     * knows what the game-position and opponents' names are
     */
    @Override
    protected void initAfterReady() {
        myActivity.setTitle("Checkers: " + allPlayerNames[0] + " vs. " + allPlayerNames[1]);
    }
}
