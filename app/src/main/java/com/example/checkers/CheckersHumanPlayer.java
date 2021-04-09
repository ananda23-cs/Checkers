/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Human Player class - User player that decides where to move using onclick and displays board
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.checkers.game.GameFramework.GameMainActivity;
import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameHumanPlayer;

public class CheckersHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    private ImageButton[][] board;
    private Button cancelButton;
    private TextView gameInfo;
    private int layoutID;
    /**
     * constructor
     *
     * @param name the name of the player
     * @param activity_main the layout ID of the game
     */
    public CheckersHumanPlayer(String name, int activity_main) {
        super(name);
        this.layoutID = activity_main;
    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if(info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo){
            flash(Color.RED, 100);
        }
        else if(!(info instanceof CheckersGameState)) return;
        else{
            ((CheckersGameState) info).setBoard(board);
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        activity.setContentView(layoutID);
        board = new ImageButton[8][8];

        //This is where we initialize all the image buttons. Their locations in the array
        //matches the location on the board
        board[0][0] = (ImageButton) activity.findViewById(R.id.tile11);
        board[1][0] = (ImageButton) activity.findViewById(R.id.tile21);
        board[2][0] = (ImageButton) activity.findViewById(R.id.tile31);
        board[3][0] = (ImageButton) activity.findViewById(R.id.tile41);
        board[4][0] = (ImageButton) activity.findViewById(R.id.tile51);
        board[5][0] = (ImageButton) activity.findViewById(R.id.tile61);
        board[6][0] = (ImageButton) activity.findViewById(R.id.tile71);
        board[7][0] = (ImageButton) activity.findViewById(R.id.tile81);

        board[0][1] = (ImageButton) activity.findViewById(R.id.tile12);
        board[1][1] = (ImageButton) activity.findViewById(R.id.tile22);
        board[2][1] = (ImageButton) activity.findViewById(R.id.tile32);
        board[3][1] = (ImageButton) activity.findViewById(R.id.tile42);
        board[4][1] = (ImageButton) activity.findViewById(R.id.tile52);
        board[5][1] = (ImageButton) activity.findViewById(R.id.tile62);
        board[6][1] = (ImageButton) activity.findViewById(R.id.tile72);
        board[7][1] = (ImageButton) activity.findViewById(R.id.tile82);

        board[0][2] = (ImageButton) activity.findViewById(R.id.tile13);
        board[1][2] = (ImageButton) activity.findViewById(R.id.tile23);
        board[2][2] = (ImageButton) activity.findViewById(R.id.tile33);
        board[3][2] = (ImageButton) activity.findViewById(R.id.tile43);
        board[4][2] = (ImageButton) activity.findViewById(R.id.tile53);
        board[5][2] = (ImageButton) activity.findViewById(R.id.tile63);
        board[6][2] = (ImageButton) activity.findViewById(R.id.tile73);
        board[7][2] = (ImageButton) activity.findViewById(R.id.tile83);

        board[0][3] = (ImageButton) activity.findViewById(R.id.tile14);
        board[1][3] = (ImageButton) activity.findViewById(R.id.tile24);
        board[2][3] = (ImageButton) activity.findViewById(R.id.tile34);
        board[3][3] = (ImageButton) activity.findViewById(R.id.tile44);
        board[4][3] = (ImageButton) activity.findViewById(R.id.tile54);
        board[5][3] = (ImageButton) activity.findViewById(R.id.tile64);
        board[6][3] = (ImageButton) activity.findViewById(R.id.tile74);
        board[7][3] = (ImageButton) activity.findViewById(R.id.tile84);

        board[0][4] = (ImageButton) activity.findViewById(R.id.tile15);
        board[1][4] = (ImageButton) activity.findViewById(R.id.tile25);
        board[2][4] = (ImageButton) activity.findViewById(R.id.tile35);
        board[3][4] = (ImageButton) activity.findViewById(R.id.tile45);
        board[4][4] = (ImageButton) activity.findViewById(R.id.tile55);
        board[5][4] = (ImageButton) activity.findViewById(R.id.tile65);
        board[6][4] = (ImageButton) activity.findViewById(R.id.tile75);
        board[7][4] = (ImageButton) activity.findViewById(R.id.tile85);

        board[0][5] = (ImageButton) activity.findViewById(R.id.tile16);
        board[1][5] = (ImageButton) activity.findViewById(R.id.tile26);
        board[2][5] = (ImageButton) activity.findViewById(R.id.tile36);
        board[3][5] = (ImageButton) activity.findViewById(R.id.tile46);
        board[4][5] = (ImageButton) activity.findViewById(R.id.tile56);
        board[5][5] = (ImageButton) activity.findViewById(R.id.tile66);
        board[6][5] = (ImageButton) activity.findViewById(R.id.tile76);
        board[7][5] = (ImageButton) activity.findViewById(R.id.tile86);

        board[0][6] = (ImageButton) activity.findViewById(R.id.tile17);
        board[1][6] = (ImageButton) activity.findViewById(R.id.tile27);
        board[2][6] = (ImageButton) activity.findViewById(R.id.tile37);
        board[3][6] = (ImageButton) activity.findViewById(R.id.tile47);
        board[4][6] = (ImageButton) activity.findViewById(R.id.tile57);
        board[5][6] = (ImageButton) activity.findViewById(R.id.tile67);
        board[6][6] = (ImageButton) activity.findViewById(R.id.tile77);
        board[7][6] = (ImageButton) activity.findViewById(R.id.tile87);

        board[0][7] = (ImageButton) activity.findViewById(R.id.tile18);
        board[1][7] = (ImageButton) activity.findViewById(R.id.tile28);
        board[2][7] = (ImageButton) activity.findViewById(R.id.tile38);
        board[3][7] = (ImageButton) activity.findViewById(R.id.tile48);
        board[4][7] = (ImageButton) activity.findViewById(R.id.tile58);
        board[5][7] = (ImageButton) activity.findViewById(R.id.tile68);
        board[6][7] = (ImageButton) activity.findViewById(R.id.tile78);
        board[7][7] = (ImageButton) activity.findViewById(R.id.tile88);
        
        //this is will be listening to the tiles. I made it 9 by 9 for the same reason I made the board 9 by 9
        CheckersTileListener[][] boardListener = new CheckersTileListener[8][8];

        //sets up the textview displaying information regarding the events of the game
        gameInfo = activity.findViewById(R.id.gameInfo);

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                boardListener[i][j] = new CheckersTileListener(i, j, , gameInfo, board);
                board[i][j].setOnClickListener(boardListener[i][j]);
            }
        }

        cancelButton = activity.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
    }

    /**
     * perform any initialization that needs to be done after player
     * knows what the game-position and opponents' names are
     */
    @Override
    protected void initAfterReady() {
        myActivity.setTitle("Checkers: " + allPlayerNames[0] + " vs. " + allPlayerNames[1]);
        super.initAfterReady();
    }

    @Override
    public void onClick(View button) {
        if(button instanceof Button){
            game.sendAction(new CheckersCancelMoveAction(CheckersHumanPlayer.this));
        }
        else if (button instanceof ImageButton){
            game.sendAction(new CheckersMoveAction(CheckersHumanPlayer.this));
        }
    }
}
