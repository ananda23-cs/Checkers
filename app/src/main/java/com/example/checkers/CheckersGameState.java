/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Game State class - defines different states of the game
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import android.media.Image;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.checkers.game.GameFramework.infoMessage.GameState;

public class CheckersGameState extends GameState {

    // instance variables
    public CheckersPiece[] p1Pieces;//if the grid is pieces this might not be needed
    public CheckersPiece[] p2Pieces;//if the grid is pieces this might not be needed
    private int p1NumPieces;
    private int p2NumPieces;
    public boolean pieceSelectedBoolean; //this determines if a piece has been selected yet. It was not here before
    public CheckersPiece pieceSelectedPiece; //this is the piece that is going to get moved.this was not here before
    private int playerTurn;
    private String message;
    //add grid here
    //private ImageButton[][] board; //displays the 8x8 checkerboard
    //TextView gameInfo;

    public CheckersGameState(){
        playerTurn = 0;

        p1NumPieces = 12;
        p2NumPieces = 12;

        // p1 starting coordinates
        p1Pieces = new CheckersPiece[12];


        p1Pieces[0] = new CheckersPiece(1,1,1);
        p1Pieces[1] = new CheckersPiece(3,1,1);
        p1Pieces[2] = new CheckersPiece(5,1,1);
        p1Pieces[3] = new CheckersPiece(7,1,1);
        p1Pieces[4] = new CheckersPiece(2,2,1);
        p1Pieces[5] = new CheckersPiece(4,2,1);
        p1Pieces[6] = new CheckersPiece(6,2,1);
        p1Pieces[7] = new CheckersPiece(8,2,1);
        p1Pieces[8] = new CheckersPiece(7,3,1);
        p1Pieces[9] = new CheckersPiece(5,3,1);
        p1Pieces[10] = new CheckersPiece(3,3,1);
        p1Pieces[11] = new CheckersPiece(1,3,1);

        // p2 starting coordinates
        p2Pieces = new CheckersPiece[12];
        p2Pieces[0] = new CheckersPiece(2,6,2);
        p2Pieces[1] = new CheckersPiece(4,6,2);
        p2Pieces[2] = new CheckersPiece(6,6,2);
        p2Pieces[3] = new CheckersPiece(8,6,2);
        p2Pieces[4] = new CheckersPiece(1,7,2);
        p2Pieces[5] = new CheckersPiece(3,7,2);
        p2Pieces[6] = new CheckersPiece(5,7,2);
        p2Pieces[7] = new CheckersPiece(7,7,2);
        p2Pieces[8] = new CheckersPiece(2,8,2);
        p2Pieces[9] = new CheckersPiece(4,8,2);
        p2Pieces[10] = new CheckersPiece(6,8,2);
        p2Pieces[11] = new CheckersPiece(8,8,2);

        message = "";
    }

    public CheckersGameState(CheckersGameState original){
        this.p1Pieces = new CheckersPiece[12];
        this.p2Pieces = new CheckersPiece[12];
        for(int i = 0; i < 12; i++){
            this.p1Pieces[i] = new CheckersPiece(original.p1Pieces[i]);
            this.p2Pieces[i] = new CheckersPiece(original.p2Pieces[i]);
        }
        this.p2NumPieces = original.p2NumPieces;
        this.p1NumPieces = original.p1NumPieces;
        this.playerTurn = original.playerTurn;
        super.currentSetupTurn = original.currentSetupTurn;
        super.numSetupTurns = original.numSetupTurns;
        this.message = original.message;
    }


    public int getP1NumPieces()
    {
        return p1NumPieces;
    }
    public int getP2NumPieces()
    {
        return p1NumPieces;
    }
    public void setP1NumPieces(int p1)
    {
        p1NumPieces = p1;
    }
    public void setP2NumPieces(int p2) { p2NumPieces = p2; }
    /*
    public CheckersPiece[] getP1Pieces()
    {
        return p1Pieces;
    }

    public CheckersPiece[] getP2Pieces()
    {
        return p2Pieces;
        }

    public void setP1Pieces(CheckersPiece[] p1)
    {
        p1Pieces = p1;
    }

    public void setP2Pieces(CheckersPiece[] p2)
    {
        p1Pieces = p2;
    }
*/
    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    //this sets the board and displays all the locations of the coordinates
    //this method was not here before we turned it in.
    public void setBoard(ImageButton[][] board){
        //this nested for loop makes a checker board. The if statement helps with the checker pattern
        /*for(int height=0;height<8;height++) {
            for(int length=0; length<8;length++) {
                /*if(height%2 == 1) {
                    if(length%2 == 1) {
                        board[length][height].setImageResource(R.drawable.red_tile);
                    }
                    else{
                        board[length][height].setImageResource(R.drawable.white_tile);
                    }
                }
                else{
                    if(height%2 == 1){
                        board[length][height].setImageResource(R.drawable.white_tile);
                    }
                    else{
                        board[length][height].setImageResource(R.drawable.red_tile);
                    }
                }
                if((height + length) % 2 == 0){
                    board[length][height].setImageResource(R.drawable.red_tile);
                    board[length][height].setTag(R.drawable.red_tile);
                }
                else{
                    board[length][height].setImageResource(R.drawable.white_tile);
                    board[length][height].setTag(R.drawable.white_tile);
                }
            }
        }*/

        //this sets all of player ones pieces on the map.
        for(CheckersPiece piece :  p1Pieces){
            if(piece.getAlive()) {
                if(piece.getKing()){
                    board[piece.getXcoordinate()-1][piece.getYcoordinate()-1].setImageResource(R.drawable.black_king);
                    board[piece.getXcoordinate()-1][piece.getYcoordinate()-1].setTag(R.drawable.black_king);
                }
                else {
                    board[piece.getXcoordinate()-1][piece.getYcoordinate()-1].setImageResource(R.drawable.black_piece);
                    board[piece.getXcoordinate()-1][piece.getYcoordinate()-1].setTag(R.drawable.black_piece);
                }
            }
        }

        for(CheckersPiece piece :  p2Pieces){
            if(piece.getAlive()) {
                if(piece.getKing()){
                    board[piece.getXcoordinate()-1][piece.getYcoordinate()-1].setImageResource(R.drawable.red_king);
                    board[piece.getXcoordinate()-1][piece.getYcoordinate()-1].setTag(R.drawable.red_king);
                }
                else {
                    board[piece.getXcoordinate()-1][piece.getYcoordinate()-1].setImageResource(R.drawable.red_piece);
                    board[piece.getXcoordinate()-1][piece.getYcoordinate()-1].setTag(R.drawable.red_piece);
                }
            }
        }
    }

    public boolean isEmpty(int newXCord,int newYCord){
        boolean returnValue  = true;
        for(CheckersPiece piece : p1Pieces ){
            if(piece.getXcoordinate()-1 == newXCord && piece.getYcoordinate()-1 == newYCord
                    && piece.getAlive()){
                return false;
            }
        }
        for(CheckersPiece piece : p2Pieces ){
            if(piece.getXcoordinate()-1 == newXCord && piece.getYcoordinate()-1 == newYCord
                    && piece.getAlive()){
                return false;
            }
        }
        return returnValue;
    }

    public boolean inBounds(int newXCord,int newYCord){
        boolean valid = true;

        if(newXCord>7 || newYCord>7 || newXCord<0 || newYCord<0){
            valid = false;
        }

        return valid;

    }

    public boolean capturepiece(CheckersPiece piece,int id,CheckersPiece[] enemyPieces,int xDir,int yDir){
        //gets returned. Will be set to true if this method works.
        boolean returnValue = false;

        //checks that the player isn't trying to capture a piece that is out of range
        if(!inRange(xDir,yDir)){
            return false;
        }

        //checks if player one is trying to capture a piece behind it. If so, it makes sure it's a king
        if(id == 1 && yDir<1 && !piece.getKing()){
            return false;
        }

        //checks if player two is trying to capture a piece behind it. If so, it makes sure it's a king
        if(id == 2 && yDir>1 && !piece.getKing()){
            return false;
        }



        //runs through all the enemy pieces
        for(CheckersPiece piece1 : enemyPieces ){

            //makes sure a dead pieces isn't being captured
            if(piece.getAlive()) {
                Log.e("capturepiece: ", ""+piece1);
                //makes checks if any enemy pieces is in a position to be captured by piece
                if (piece.getXcoordinate()-1 + xDir == piece1.getXcoordinate()-1
                        && piece.getYcoordinate()-1 +yDir == piece1.getYcoordinate()-1) {

                    //makes sure the space ahead of the the capture pieces is empty
                    if (isEmpty(piece1.getXcoordinate()-1 + xDir, piece1.getYcoordinate()-1 + yDir) &&
                            inBounds(piece1.getXcoordinate()-1 + xDir, piece1.getYcoordinate()-1 + yDir)) {
                        //kills the pieces and sets the return value to true
                        piece1.setAlive(false);
                        returnValue = true;

                        if(piece.getYcoordinate()-1 == 7 && playerTurn == 0){
                            piece.setKing(true);
                        }

                        if(piece.getYcoordinate()-1 == 0 && playerTurn == 1){
                            piece.setKing(true);
                        }

                        //changes the turn number
                        piece.setCoordinates(piece1.getXcoordinate()-1 + xDir,
                                piece1.getYcoordinate()-1 + yDir);
                        if(playerTurn == 0){
                            playerTurn = 1;
                        }
                        else{
                            playerTurn = 0;
                        }
                    }
                }
            }
        }

        return returnValue;
    }


    public boolean hasEnemyPieces(int xLocation,int yLocation){
        boolean returnValue = false;
        if(playerTurn == 0) {
            for (CheckersPiece piece : p2Pieces) {
                if (piece.getXcoordinate()-1 == xLocation && piece.getYcoordinate()-1 == yLocation
                        && piece.getAlive()) {
                    returnValue = true;
                }
            }
        }
        else{
            for (CheckersPiece piece : p1Pieces) {
                if (piece.getXcoordinate()-1 == xLocation && piece.getYcoordinate()-1 == yLocation
                        && piece.getAlive()) {
                    returnValue = true;
                }
            }
        }
        return returnValue;

    }

    public CheckersPiece findPiece(int xCord,int yCord){
        for(int i = 0;i<12;i++){
            if(p1Pieces[i].getYcoordinate()-1 == yCord && p1Pieces[i].getXcoordinate()-1 == xCord){
                return p1Pieces[i];
            }
            else if (p2Pieces[i].getYcoordinate()-1 == yCord && p2Pieces[i].getXcoordinate()-1 == xCord){
                return p2Pieces[i];
            }
        }
        return null;
    }


    public boolean movePiece(CheckersPiece piece, int xDir,int yDir,int id){
        //this if statement checks that the user has not tried to move more than one space
        if(inRange(xDir,yDir)){

            //this checks that user is not trying to move off the checker board as well as if the space is held by another piece
            if(inBounds(piece.getXcoordinate()-1+xDir,piece.getYcoordinate()-1+yDir) &&
                    isEmpty(piece.getXcoordinate()-1+xDir,piece.getYcoordinate()-1+yDir)){

                //this checks if player one is not trying to move a non king piece backwards
                if(id == 1 && yDir<1 && !piece.getKing()){
                    Log.e( "movePiece: ","Can't move backwards because not king" );
                    return false;
                }

                //this checks if player two is not trying to move a non king piece backwards
                else if(id == 2 && yDir>0 && !piece.getKing()){
                    Log.e( "movePiece: ","Can't move backwards because not king" );
                    return false;
                }

                //if all the conditions are right the piece will move.
                else {
                    piece.setCoordinates(piece.getXcoordinate()-1+xDir,piece.getYcoordinate()-1+yDir);

                    //will turn to player 1's pieces king if the piece reaches the other side of the board
                    if(playerTurn == 0){
                        playerTurn = 1;
                        if(piece.getYcoordinate() == 7){
                            piece.setKing(true);
                        }
                    }

                    //will turn to player 2's pieces king if the piece reaches the other side of the board
                    else{
                        if(piece.getYcoordinate() == 0){
                            piece.setKing(true);
                        }
                        playerTurn = 0;
                    }
                    return true;
                }
            }

            //returns false if the move is out of bounds or tries to move into a spot with another piece
            else {
                Log.e( "movePiece: ","not in bounds" );
                return false;
            }
        }
        //will return false if the player tries to move
        else {
            Log.e( "movePiece: ","not in range" );
            return false;
        }

    }

    /* prints board in logcat and will be used to print it on the canvas
        by converting string to bitmap
    public static void printBoard(String[][] board2,CheckersPiece [] P1, CheckersPiece [] P2) {
        //we set the board to be empty, then it will be converted to a bitmap
        for(int height=1;height<=8;height++) {
            //when it's converted to a bitmap, we will set every single element in the
              Bitmap array to a square
            for(int length=1; length<=8;length++) {
                board2[height][length]="___";
            }
        }

        /*right now, these if statements set P1[n] to
          board2[P1[n].getXcoordinate()][P1[n].getYcoordinate()]
          A string is used to represent these pieces and when converted to a bitmap,
          we will set it to be equal to a circle, which will be used to represent a piece
        if (P1[0].getAlive()==true) {
            board2[P1[0].getXcoordinate()][P1[0].getYcoordinate()]="O1_";
        }

        if (P1[1].getAlive()==true) {
            board2[P1[1].getXcoordinate()][P1[1].getYcoordinate()]="O2_";
        }
        if (P1[2].getAlive()==true) {
            board2[P1[2].getXcoordinate()][P1[2].getYcoordinate()]="O3_";
        }
        if (P1[3].getAlive()==true) {
            board2[P1[3].getXcoordinate()][P1[3].getYcoordinate()]="O4_";
        }
        if (P1[4].getAlive()==true) {
            board2[P1[4].getXcoordinate()][P1[4].getYcoordinate()]="O5_";
        }
        if (P1[5].getAlive()==true) {
            board2[P1[5].getXcoordinate()][P1[5].getYcoordinate()]="O6_";
        }
        if (P1[6].getAlive()==true) {
            board2[P1[6].getXcoordinate()][P1[6].getYcoordinate()]="O7_";
        }
        if (P1[7].getAlive()==true) {
            board2[P1[7].getXcoordinate()][P1[7].getYcoordinate()]="O8_";
        }
        if (P1[8].getAlive()==true) {
            board2[P1[8].getXcoordinate()][P1[8].getYcoordinate()]="O9_";
        }
        if (P1[9].getAlive()==true) {
            board2[P1[9].getXcoordinate()][P1[9].getYcoordinate()]="O10";
        }
        if (P1[10].getAlive()==true) {
            board2[P1[10].getXcoordinate()][P1[10].getYcoordinate()]="O11";
        }
        if (P1[11].getAlive()==true) {
            board2[P1[11].getXcoordinate()][P1[11].getYcoordinate()]="O12";
        }

        //same applies to P2[n]
        if (P2[0].getAlive()==true) {
            board2[P2[0].getXcoordinate()][P2[0].getYcoordinate()]="T1_";
        }
        if (P2[1].getAlive()==true) {
            board2[P2[1].getXcoordinate()][P2[1].getYcoordinate()]="T2_";
        }
        if (P2[2].getAlive()==true) {
            board2[P2[2].getXcoordinate()][P2[2].getYcoordinate()]="T3_";
        }
        if (P2[3].getAlive()==true) {
            board2[P2[3].getXcoordinate()][P2[3].getYcoordinate()]="T4_";
        }
        if (P2[4].getAlive()==true) {
            board2[P2[4].getXcoordinate()][P2[4].getYcoordinate()]="T5_";
        }
        if (P2[5].getAlive()==true) {
            board2[P2[5].getXcoordinate()][P2[5].getYcoordinate()]="T6_";
        }
        if (P2[6].getAlive()==true) {
            board2[P2[6].getXcoordinate()][P2[6].getYcoordinate()]="T7_";
        }
        if (P2[7].getAlive()==true) {
            board2[P2[7].getXcoordinate()][P2[7].getYcoordinate()]="T8_";
        }
        if (P2[8].getAlive()==true) {
            board2[P2[8].getXcoordinate()][P2[8].getYcoordinate()]="T9_";
        }
        if (P2[9].getAlive()==true) {
            board2[P2[9].getXcoordinate()][P2[9].getYcoordinate()]="T10";
        }
        if (P2[10].getAlive()==true) {
            board2[P2[10].getXcoordinate()][P2[10].getYcoordinate()]="T11";
        }
        if (P2[11].getAlive()==true) {
            board2[P2[11].getXcoordinate()][P2[11].getYcoordinate()]="T12";
        }

        //does the actual printing of the board
        Log.e( "printBoard: ", "_________________________________" );
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][8]+"|"+board2[2][8]+"|"+board2[3][8]+"|"+board2[4][8]+"|"+board2[5][8]+"|"+board2[6][8]+"|"+board2[7][8]+"|"+board2[8][8]+"|"+8);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][7]+"|"+board2[2][7]+"|"+board2[3][7]+"|"+board2[4][7]+"|"+board2[5][7]+"|"+board2[6][7]+"|"+board2[7][7]+"|"+board2[8][7]+"|"+7);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][6]+"|"+board2[2][6]+"|"+board2[3][6]+"|"+board2[4][6]+"|"+board2[5][6]+"|"+board2[6][6]+"|"+board2[7][6]+"|"+board2[8][6]+"|"+6);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][5]+"|"+board2[2][5]+"|"+board2[3][5]+"|"+board2[4][5]+"|"+board2[5][5]+"|"+board2[6][5]+"|"+board2[7][5]+"|"+board2[8][5]+"|"+5);
        Log.e( "printBoard: ", "\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][4]+"|"+board2[2][4]+"|"+board2[3][4]+"|"+board2[4][4]+"|"+board2[5][4]+"|"+board2[6][4]+"|"+board2[7][4]+"|"+board2[8][4]+"|"+4);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][3]+"|"+board2[2][3]+"|"+board2[3][3]+"|"+board2[4][3]+"|"+board2[5][3]+"|"+board2[6][3]+"|"+board2[7][3]+"|"+board2[8][3]+"|"+3);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][2]+"|"+board2[2][2]+"|"+board2[3][2]+"|"+board2[4][2]+"|"+board2[5][2]+"|"+board2[6][2]+"|"+board2[7][2]+"|"+board2[8][2]+"|"+2);
        Log.e( "printBoard: ","\n|   |   |   |   |   |   |   |   |");
        Log.e( "printBoard: ","\n|"+board2[1][1]+"|"+board2[2][1]+"|"+board2[3][1]+"|"+board2[4][1]+"|"+board2[5][1]+"|"+board2[6][1]+"|"+board2[7][1]+"|"+board2[8][1]+"|"+1);
        Log.e( "printBoard: ","\n  1   2   3   4   5   6   7   8");
    }*/

    public boolean inRange(int xDir,int yDir){
        if((xDir == 1 || xDir == -1) && (yDir == 1 || yDir == -1)){
            return true;
        }
        else{
            return false;
        }

    }

    public void setPieceSelectedPieceAndPieceSelectedBoolean(int xCord,int yCord){
        int i = 0;
        for(CheckersPiece piece : p2Pieces){
            if(p1Pieces[i].getAlive()){
                if(p1Pieces[i].getYcoordinate() == yCord && p1Pieces[i].getXcoordinate() == xCord){
                    this.pieceSelectedPiece = p1Pieces[i];
                    break;
                }
            }

            if(piece.getAlive()){
                if(piece.getYcoordinate() == yCord && piece.getXcoordinate() == xCord){
                    this.pieceSelectedPiece = piece;
                    break;
                }
            }
            i++;

        }
        if(this.pieceSelectedBoolean == false) {
            this.pieceSelectedBoolean = true;
        }
        else{
            this.pieceSelectedBoolean = false;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
