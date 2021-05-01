/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Human Player class - User player that decides where to move using onclick and displays board
 *
 * CS301A
 * @version 04/30/2021
 */

package com.example.checkers.CheckersGame.players;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkers.CheckersGame.Actions.CheckersCanNotMoveAction;
import com.example.checkers.CheckersGame.Actions.CheckersCancelMoveAction;
import com.example.checkers.CheckersGame.Actions.CheckersChoosePieceAction;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;
import com.example.checkers.R;
import com.example.checkers.game.GameFramework.GameMainActivity;
import com.example.checkers.game.GameFramework.infoMessage.GameInfo;
import com.example.checkers.game.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.checkers.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.checkers.game.GameFramework.players.GameHumanPlayer;

public class CheckersHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    ImageButton[][] board;
    private Button nightMode;
    private Button cancelButton;
    private Button forfeit;
    private TextView gameInfo, gameTitle;
    private TextView humanPlayerID, computerPlayerID;
    private int layoutID;
    private int nightButtonClicks = 0;
    private ImageView key;

    /**
     * constructor CheckersHumanPlayer
     *
     * @param name
     *      the name of the player
     * @param activity_main
     *      the layout ID of the game
     */
    public CheckersHumanPlayer(String name, int activity_main) {
        super(name);
        this.layoutID = activity_main;
        board = new ImageButton[8][8];

    } //CheckersHumanPlayer

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * Called when the player receives a game-state (or other info) from the
     * game.
     *
     * @param info
     *      the message from the game
     */
    @Override
    public void receiveInfo(GameInfo info) {
        // flash red if move is invalid
        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            flash(Color.RED, 100);
        }
        else if (!(info instanceof CheckersGameState)){ return;}
        else {
            ((CheckersGameState) info).setImageBoard(board);
            gameInfo.setText(((CheckersGameState) info).getMessage());

            nightModeBoard();
            nightModeBackground((CheckersGameState) info);
            nightModePieces((CheckersGameState) info);


            if(((CheckersGameState) info).isPieceSelectedBoolean()){
                CheckersGameState copy =  ((CheckersGameState) info);
                showMoves(copy,1,1);
                showMoves(copy,-1,1);
                showMoves(copy,-1,-1);
                showMoves(copy,+1,-1);
                if(nightButtonClicks % 2 == 0) {
                    showCaptures(copy, 1, 1);
                    showCaptures(copy, -1, 1);
                    showCaptures(copy, -1, -1);
                    showCaptures(copy, 1, -1);
                    key.setImageResource(R.drawable.newkey);
                }
                else{
                    showNightModeCaptures(copy, 1, 1);
                    showNightModeCaptures(copy, -1, 1);
                    showNightModeCaptures(copy, -1, -1);
                    showNightModeCaptures(copy, 1, -1);
                    key.setImageResource(R.drawable.grey_key);
                }
                key.setVisibility(View.VISIBLE);
            }
            else{
                key.setVisibility(View.INVISIBLE);
            }
        }
    } //receiveInfo

    @Override
    public void setAsGui(GameMainActivity activity) {
        activity.setContentView(layoutID);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // set tiles to the correct coordinates on the board
        // row 1
        board[0][0] = (ImageButton) activity.findViewById(R.id.tile11);
        board[1][0] = (ImageButton) activity.findViewById(R.id.tile21);
        board[2][0] = (ImageButton) activity.findViewById(R.id.tile31);
        board[3][0] = (ImageButton) activity.findViewById(R.id.tile41);
        board[4][0] = (ImageButton) activity.findViewById(R.id.tile51);
        board[5][0] = (ImageButton) activity.findViewById(R.id.tile61);
        board[6][0] = (ImageButton) activity.findViewById(R.id.tile71);
        board[7][0] = (ImageButton) activity.findViewById(R.id.tile81);
        // row 2
        board[0][1] = (ImageButton) activity.findViewById(R.id.tile12);
        board[1][1] = (ImageButton) activity.findViewById(R.id.tile22);
        board[2][1] = (ImageButton) activity.findViewById(R.id.tile32);
        board[3][1] = (ImageButton) activity.findViewById(R.id.tile42);
        board[4][1] = (ImageButton) activity.findViewById(R.id.tile52);
        board[5][1] = (ImageButton) activity.findViewById(R.id.tile62);
        board[6][1] = (ImageButton) activity.findViewById(R.id.tile72);
        board[7][1] = (ImageButton) activity.findViewById(R.id.tile82);
        // row 3
        board[0][2] = (ImageButton) activity.findViewById(R.id.tile13);
        board[1][2] = (ImageButton) activity.findViewById(R.id.tile23);
        board[2][2] = (ImageButton) activity.findViewById(R.id.tile33);
        board[3][2] = (ImageButton) activity.findViewById(R.id.tile43);
        board[4][2] = (ImageButton) activity.findViewById(R.id.tile53);
        board[5][2] = (ImageButton) activity.findViewById(R.id.tile63);
        board[6][2] = (ImageButton) activity.findViewById(R.id.tile73);
        board[7][2] = (ImageButton) activity.findViewById(R.id.tile83);
        // row 4
        board[0][3] = (ImageButton) activity.findViewById(R.id.tile14);
        board[1][3] = (ImageButton) activity.findViewById(R.id.tile24);
        board[2][3] = (ImageButton) activity.findViewById(R.id.tile34);
        board[3][3] = (ImageButton) activity.findViewById(R.id.tile44);
        board[4][3] = (ImageButton) activity.findViewById(R.id.tile54);
        board[5][3] = (ImageButton) activity.findViewById(R.id.tile64);
        board[6][3] = (ImageButton) activity.findViewById(R.id.tile74);
        board[7][3] = (ImageButton) activity.findViewById(R.id.tile84);
        // row 5
        board[0][4] = (ImageButton) activity.findViewById(R.id.tile15);
        board[1][4] = (ImageButton) activity.findViewById(R.id.tile25);
        board[2][4] = (ImageButton) activity.findViewById(R.id.tile35);
        board[3][4] = (ImageButton) activity.findViewById(R.id.tile45);
        board[4][4] = (ImageButton) activity.findViewById(R.id.tile55);
        board[5][4] = (ImageButton) activity.findViewById(R.id.tile65);
        board[6][4] = (ImageButton) activity.findViewById(R.id.tile75);
        board[7][4] = (ImageButton) activity.findViewById(R.id.tile85);
        // row 6
        board[0][5] = (ImageButton) activity.findViewById(R.id.tile16);
        board[1][5] = (ImageButton) activity.findViewById(R.id.tile26);
        board[2][5] = (ImageButton) activity.findViewById(R.id.tile36);
        board[3][5] = (ImageButton) activity.findViewById(R.id.tile46);
        board[4][5] = (ImageButton) activity.findViewById(R.id.tile56);
        board[5][5] = (ImageButton) activity.findViewById(R.id.tile66);
        board[6][5] = (ImageButton) activity.findViewById(R.id.tile76);
        board[7][5] = (ImageButton) activity.findViewById(R.id.tile86);
        // row 7
        board[0][6] = (ImageButton) activity.findViewById(R.id.tile17);
        board[1][6] = (ImageButton) activity.findViewById(R.id.tile27);
        board[2][6] = (ImageButton) activity.findViewById(R.id.tile37);
        board[3][6] = (ImageButton) activity.findViewById(R.id.tile47);
        board[4][6] = (ImageButton) activity.findViewById(R.id.tile57);
        board[5][6] = (ImageButton) activity.findViewById(R.id.tile67);
        board[6][6] = (ImageButton) activity.findViewById(R.id.tile77);
        board[7][6] = (ImageButton) activity.findViewById(R.id.tile87);
        // row 8
        board[0][7] = (ImageButton) activity.findViewById(R.id.tile18);
        board[1][7] = (ImageButton) activity.findViewById(R.id.tile28);
        board[2][7] = (ImageButton) activity.findViewById(R.id.tile38);
        board[3][7] = (ImageButton) activity.findViewById(R.id.tile48);
        board[4][7] = (ImageButton) activity.findViewById(R.id.tile58);
        board[5][7] = (ImageButton) activity.findViewById(R.id.tile68);
        board[6][7] = (ImageButton) activity.findViewById(R.id.tile78);
        board[7][7] = (ImageButton) activity.findViewById(R.id.tile88);

        //get id's of texts, buttons, and widgets from GUI
        gameTitle = (TextView) activity.findViewById(R.id.gameTitle);
        gameInfo = (TextView) activity.findViewById(R.id.gameInfo);
        humanPlayerID = (TextView) activity.findViewById(R.id.humanPlayerId2);
        computerPlayerID = (TextView) activity.findViewById(R.id.computerPlayerId);
        cancelButton = (Button) activity.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
        nightMode = (Button) activity.findViewById(R.id.nightButton);
        nightMode.setOnClickListener(this);
        forfeit = (Button) activity.findViewById(R.id.forfeit);
        forfeit.setOnClickListener(this);
        key = activity.findViewById(R.id.key);
        key.setImageResource(R.drawable.newkey);
    } //setAsGui

    /**
     * External Citation:
     * Date: April 10, 2021
     * Problem: Couldn't click on the tiles when the game runs
     * Resource: Professor Tribelhorn
     * Solution: moved the onClickListener for the tiles into initAfterReady()
     *
     * perform any initialization that needs to be done after player
     * knows what the game-position and opponents' names are
     */
    @Override
    protected void initAfterReady() {
        myActivity.setTitle("Checkers: " + allPlayerNames[playerNum] + " vs. " +
                                            allPlayerNames[1-playerNum]);
        humanPlayerID.setText(allPlayerNames[0]);
        computerPlayerID.setText(allPlayerNames[1]);
        // sets board to listen to tiles
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].setOnClickListener(this);
            }
        }

    } //initAfterReady

    @Override
    public void onClick(View button) {
        if(button instanceof Button){
            //if clicked, the player forfeits the game and the other player wins
            if(button.getId() == R.id.forfeit){
                game.sendAction(new CheckersCanNotMoveAction(this));
            }

            else if(button.getId() == R.id.cancelButton) {
                game.sendAction(new CheckersCancelMoveAction(this));
            }

            // if clicked, turn game into night mode
            else {
                nightButtonClicks += 1;
                nightModeBoard();
                nightModePieces((CheckersGameState) game.getGameState());
                nightModeBackground((CheckersGameState) game.getGameState());
                if(nightButtonClicks % 2 == 1){
                    ((Button)button).setText("Light Mode");

                    gameTitle.setTextColor(Color.BLACK);
                    gameInfo.setTextColor(Color.BLACK);
                    humanPlayerID.setTextColor(Color.BLACK);
                    computerPlayerID.setTextColor(Color.BLACK);
                    key.setImageResource(R.drawable.grey_key);
                    if(((CheckersGameState) game.getGameState()).isPieceSelectedBoolean()){
                        showMoves(((CheckersGameState) game.getGameState()), 1,1);
                        showMoves(((CheckersGameState) game.getGameState()), -1,1);
                        showMoves(((CheckersGameState) game.getGameState()), -1,-1);
                        showMoves(((CheckersGameState) game.getGameState()), 1,-1);
                        showNightModeCaptures(
                                ((CheckersGameState) game.getGameState()), 1, 1);
                        showNightModeCaptures(
                                ((CheckersGameState) game.getGameState()), -1, 1);
                        showNightModeCaptures(
                                ((CheckersGameState) game.getGameState()), -1, -1);
                        showNightModeCaptures(
                                ((CheckersGameState) game.getGameState()), 1, -1);
                        key.setVisibility(View.VISIBLE);
                    }
                    else{
                        key.setVisibility(View.INVISIBLE);
                    }
                }
                // if clicked turn game into light mode
                else{
                    ((Button)button).setText("Night Mode");
                    gameTitle.setTextColor(0xFFF13A01);
                    gameInfo.setTextColor(Color.BLACK);
                    humanPlayerID.setTextColor(Color.BLACK);
                    computerPlayerID.setTextColor(Color.BLACK);
                    key.setImageResource(R.drawable.newkey);
                    if(((CheckersGameState) game.getGameState()).isPieceSelectedBoolean()){
                        showMoves(((CheckersGameState) game.getGameState()), 1,1);
                        showMoves(((CheckersGameState) game.getGameState()), -1,1);
                        showMoves(((CheckersGameState) game.getGameState()), -1,-1);
                        showMoves(((CheckersGameState) game.getGameState()), 1,-1);
                        showCaptures(((CheckersGameState) game.getGameState()), 1, 1);
                        showCaptures(((CheckersGameState) game.getGameState()), -1, 1);
                        showCaptures(((CheckersGameState) game.getGameState()), -1, -1);
                        showCaptures(((CheckersGameState) game.getGameState()), 1, -1);
                        key.setVisibility(View.VISIBLE);
                    }
                    else{
                        key.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
        else if (button instanceof ImageButton) {

            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (board[x][y].equals(button)) {
                        game.sendAction(new CheckersChoosePieceAction(this, x, y));
                    }
                }
            }

        }
    }//onClick

    /**
     * highlights the possible valid moves that the human player can make
     * @param copy the current game state
     * @param xdir the x direction of movement
     * @param ydir the y-direction of movement
     */
    public void showMoves(CheckersGameState copy,int xdir,int ydir){
        if(copy.canMove(copy.getPieceSelectedPiece(),xdir,ydir,copy.getPlayerTurn())){
            board[copy.getPieceSelectedPiece().getXcoordinate()+xdir]
                    [copy.getPieceSelectedPiece().getYcoordinate()+ydir]
                    .setImageResource(R.drawable.tan_square);
        }
    }//showMoves

    /**
     * highlights the possible valid captures that the human player can make
     * @param copy the current game state
     * @param xdir the x direction of movement
     * @param ydir the y-direction of movement
     */
    public void showCaptures(CheckersGameState copy, int xdir, int ydir){
        // check if the user can capture a piece
        if(copy.checkIfCanCaptureEnemyPiece(
                copy.getPieceSelectedPiece().getXcoordinate()+xdir,
                copy.getPieceSelectedPiece().getYcoordinate()+ydir,
                          copy.getPieceSelectedPiece().getXcoordinate(),
                          copy.getPieceSelectedPiece().getYcoordinate())){
            // if user is using black pieces, show red pieces that can be captured
            if(playerNum == 0 && playerNum == copy.getPlayerTurn()){
                board[copy.getPieceSelectedPiece().getXcoordinate()+xdir]
                        [copy.getPieceSelectedPiece().getYcoordinate()+ydir]
                        .setImageResource(R.drawable.tan_valid_redcapture);
            }
            // if user is using red pieces, show black pieces that can be captured
            else if(playerNum == 1 && playerNum == copy.getPlayerTurn()){
                board[copy.getPieceSelectedPiece().getXcoordinate()+xdir]
                        [copy.getPieceSelectedPiece().getYcoordinate()+ydir]
                        .setImageResource(R.drawable.tan_valid_blackcapture);
            }
        }
    } //showCaptures

    /**
     * highlights the possible valid captures that the human player can make in night mode
     * @param copy the current game state
     * @param xdir the x direction of movement
     * @param ydir the y-direction of movement
     */
    public void showNightModeCaptures(CheckersGameState copy, int xdir, int ydir){
        // check if the user can capture a piece
        if(copy.checkIfCanCaptureEnemyPiece(
                copy.getPieceSelectedPiece().getXcoordinate()+xdir,
                copy.getPieceSelectedPiece().getYcoordinate()+ydir,
                copy.getPieceSelectedPiece().getXcoordinate(),
                copy.getPieceSelectedPiece().getYcoordinate())){
            // if user is using black pieces, show grey pieces that can be captured
            if(playerNum == 0 && playerNum == copy.getPlayerTurn()){
                board[copy.getPieceSelectedPiece().getXcoordinate()+xdir]
                        [copy.getPieceSelectedPiece().getYcoordinate()+ydir]
                        .setImageResource(R.drawable.tan_night_validcapture);
            }
            // if user is using grey pieces, show black pieces that can be captured
            else if(playerNum == 1 && playerNum == copy.getPlayerTurn()){
                board[copy.getPieceSelectedPiece().getXcoordinate()+xdir]
                        [copy.getPieceSelectedPiece().getYcoordinate()+ydir]
                        .setImageResource(R.drawable.tan_valid_blackcapture);
            }
        }
    } //showNightModeCaptures

     /**
      * turns the board into a grey and white checkerboard
      */
    public void nightModeBoard(){
        // alternates between red and white tiles in a checkerboard pattern (or all grey tiles)
        for (int height = 0; height < 8; height++) {
            for (int length = 0; length < 8; length++) {
                if(nightButtonClicks % 2 == 1){
                    if((height + length) % 2 == 0) {
                        board[length][height].setImageResource(R.drawable.greytile);
                    }
                    else {
                        board[length][height].setImageResource(R.drawable.white_tile);
                    }
                }
                else {
                    if ((height + length) % 2 == 0) {
                        board[length][height].setImageResource(R.drawable.red_tile);
                    } else {
                        board[length][height].setImageResource(R.drawable.white_tile);
                    }
                }
            }
        }
    } //nightModeBoard

    /**
     * turns the background into a grey background for night mode
     */
    public void nightModeBackground(CheckersGameState state){
        if(nightButtonClicks % 2 == 1){
            if (state.getPlayerTurn() == 1) {
                getTopView().setBackgroundColor(0xff737373);
            } else {
                getTopView().setBackgroundColor(0xFFA6A6A6);
            }
        }
        else {
            if (state.getPlayerTurn() == 1) {
                getTopView().setBackgroundColor(0xffffaec2);
            } else {
                getTopView().setBackgroundColor(0xf5e5be);
            }
        }
    } //nightModeBackground

    /**
     * turns the pieces into grey pieces for night mode
     */
    public void nightModePieces(CheckersGameState state){
        // sets player 1's pieces on the board to black
        for (CheckersPiece piece : state.p1Pieces) {
            if (piece.getAlive()) {
                if (piece.getKing()) {
                    if(nightButtonClicks % 2 == 1){
                        board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(
                                R.drawable.night_black_king);
                    }
                    else {
                        board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(
                                R.drawable.black_king);
                    }
                } else {
                    if(nightButtonClicks % 2 == 1){
                        board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(
                                R.drawable.night_black_piece);
                    }
                    else {
                        board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(
                                R.drawable.black_piece);
                    }
                }
            }
        }

        // sets player 2's pieces on the board to red
        for (CheckersPiece piece : state.p2Pieces) {
            if (piece.getAlive()) {
                if (piece.getKing()) {
                    if(nightButtonClicks % 2 == 1) {
                        board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(
                                R.drawable.grey_light_grey_king);
                    }
                    else{
                        board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(
                                R.drawable.red_king);
                    }
                } else {
                    if (nightButtonClicks % 2 == 1) {
                        board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(
                                R.drawable.grey_light_grey_piece);
                    }
                    else{
                        board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(
                                R.drawable.red_piece);
                    }
                }
            }
        }
    } //nightModePieces
}//CheckersHumanPlayer
