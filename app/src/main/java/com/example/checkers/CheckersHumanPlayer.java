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

import org.w3c.dom.Text;

public class CheckersHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    private ImageButton[][] board;
    private Button cancelButton;
    private TextView gameInfo;
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
        activity.setContentView(R.layout.activity_main);
        board = new ImageButton[9][9];

        //This is where we initialize all the image buttons. Their locations in the array
        //matches the location on the board
        board[1][1] = (ImageButton) activity.findViewById(R.id.tile11);
        board[2][1] = (ImageButton) activity.findViewById(R.id.tile21);
        board[3][1] = (ImageButton) activity.findViewById(R.id.tile31);
        board[4][1] = (ImageButton) activity.findViewById(R.id.tile41);
        board[5][1] = (ImageButton) activity.findViewById(R.id.tile51);
        board[6][1] = (ImageButton) activity.findViewById(R.id.tile61);
        board[7][1] = (ImageButton) activity.findViewById(R.id.tile71);
        board[8][1] = (ImageButton) activity.findViewById(R.id.tile81);

        board[1][2] = (ImageButton) activity.findViewById(R.id.tile12);
        board[2][2] = (ImageButton) activity.findViewById(R.id.tile22);
        board[3][2] = (ImageButton) activity.findViewById(R.id.tile32);
        board[4][2] = (ImageButton) activity.findViewById(R.id.tile42);
        board[5][2] = (ImageButton) activity.findViewById(R.id.tile52);
        board[6][2] = (ImageButton) activity.findViewById(R.id.tile62);
        board[7][2] = (ImageButton) activity.findViewById(R.id.tile72);
        board[8][2] = (ImageButton) activity.findViewById(R.id.tile82);

        board[1][3] = (ImageButton) activity.findViewById(R.id.tile13);
        board[2][3] = (ImageButton) activity.findViewById(R.id.tile23);
        board[3][3] = (ImageButton) activity.findViewById(R.id.tile33);
        board[4][3] = (ImageButton) activity.findViewById(R.id.tile43);
        board[5][3] = (ImageButton) activity.findViewById(R.id.tile53);
        board[6][3] = (ImageButton) activity.findViewById(R.id.tile63);
        board[7][3] = (ImageButton) activity.findViewById(R.id.tile73);
        board[8][3] = (ImageButton) activity.findViewById(R.id.tile83);

        board[1][4] = (ImageButton) activity.findViewById(R.id.tile14);
        board[2][4] = (ImageButton) activity.findViewById(R.id.tile24);
        board[3][4] = (ImageButton) activity.findViewById(R.id.tile34);
        board[4][4] = (ImageButton) activity.findViewById(R.id.tile44);
        board[5][4] = (ImageButton) activity.findViewById(R.id.tile54);
        board[6][4] = (ImageButton) activity.findViewById(R.id.tile64);
        board[7][4] = (ImageButton) activity.findViewById(R.id.tile74);
        board[8][4] = (ImageButton) activity.findViewById(R.id.tile84);

        board[1][5] = (ImageButton) activity.findViewById(R.id.tile15);
        board[2][5] = (ImageButton) activity.findViewById(R.id.tile25);
        board[3][5] = (ImageButton) activity.findViewById(R.id.tile35);
        board[4][5] = (ImageButton) activity.findViewById(R.id.tile45);
        board[5][5] = (ImageButton) activity.findViewById(R.id.tile55);
        board[6][5] = (ImageButton) activity.findViewById(R.id.tile65);
        board[7][5] = (ImageButton) activity.findViewById(R.id.tile75);
        board[8][5] = (ImageButton) activity.findViewById(R.id.tile85);

        board[1][6] = (ImageButton) activity.findViewById(R.id.tile16);
        board[2][6] = (ImageButton) activity.findViewById(R.id.tile26);
        board[3][6] = (ImageButton) activity.findViewById(R.id.tile36);
        board[4][6] = (ImageButton) activity.findViewById(R.id.tile46);
        board[5][6] = (ImageButton) activity.findViewById(R.id.tile56);
        board[6][6] = (ImageButton) activity.findViewById(R.id.tile66);
        board[7][6] = (ImageButton) activity.findViewById(R.id.tile76);
        board[8][6] = (ImageButton) activity.findViewById(R.id.tile86);

        board[1][6] = (ImageButton) activity.findViewById(R.id.tile16);
        board[2][6] = (ImageButton) activity.findViewById(R.id.tile26);
        board[3][6] = (ImageButton) activity.findViewById(R.id.tile36);
        board[4][6] = (ImageButton) activity.findViewById(R.id.tile46);
        board[5][6] = (ImageButton) activity.findViewById(R.id.tile56);
        board[6][6] = (ImageButton) activity.findViewById(R.id.tile66);
        board[7][6] = (ImageButton) activity.findViewById(R.id.tile76);
        board[8][6] = (ImageButton) activity.findViewById(R.id.tile86);

        board[1][7] = (ImageButton) activity.findViewById(R.id.tile17);
        board[2][7] = (ImageButton) activity.findViewById(R.id.tile27);
        board[3][7] = (ImageButton) activity.findViewById(R.id.tile37);
        board[4][7] = (ImageButton) activity.findViewById(R.id.tile47);
        board[5][7] = (ImageButton) activity.findViewById(R.id.tile57);
        board[6][7] = (ImageButton) activity.findViewById(R.id.tile67);
        board[7][7] = (ImageButton) activity.findViewById(R.id.tile77);
        board[8][7] = (ImageButton) activity.findViewById(R.id.tile87);

        board[1][8] = (ImageButton) activity.findViewById(R.id.tile18);
        board[2][8] = (ImageButton) activity.findViewById(R.id.tile28);
        board[3][8] = (ImageButton) activity.findViewById(R.id.tile38);
        board[4][8] = (ImageButton) activity.findViewById(R.id.tile48);
        board[5][8] = (ImageButton) activity.findViewById(R.id.tile58);
        board[6][8] = (ImageButton) activity.findViewById(R.id.tile68);
        board[7][8] = (ImageButton) activity.findViewById(R.id.tile78);
        board[8][8] = (ImageButton) activity.findViewById(R.id.tile88);
        
        //this is will be listening to the tiles. I made it 9 by 9 for the same reason I made the board 9 by 9
        CheckersTileListener[][] boardListener = new CheckersTileListener[9][9];

        //sets up the textview displaying information regarding the events of the game
        gameInfo = activity.findViewById(R.id.gameInfo);

        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                boardListener[i][j] = new CheckersTileListener();
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
    }

    @Override
    public void onClick(View button) {
        if(button instanceof Button){
            game.sendAction(new CheckersCancelMoveAction(CheckersHumanPlayer.this));
        }
    }
}
