/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Game State class - defines different states of the game
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import android.util.Log;
import android.widget.ImageButton;

import com.example.checkers.game.GameFramework.infoMessage.GameState;

public class CheckersGameState extends GameState {

    // instance variables
    public CheckersPiece[] p1Pieces;
    public CheckersPiece[] p2Pieces;
    private int p1NumPieces;
    private int p2NumPieces;
    private boolean pieceSelectedBoolean; //this determines if a piece has been selected yet. It was not here before
    private CheckersPiece pieceSelectedPiece; //this is the piece that is going to get moved.this was not here before
    private int playerTurn;
    private String message;

    public CheckersGameState(){
        playerTurn = 0;

        p1NumPieces = 12;
        p2NumPieces = 12;

        // p1 starting coordinates
        p1Pieces = new CheckersPiece[12];


        p1Pieces[0] = new CheckersPiece(0,0,1);
        p1Pieces[1] = new CheckersPiece(2,0,1);
        p1Pieces[2] = new CheckersPiece(4,0,1);
        p1Pieces[3] = new CheckersPiece(6,0,1);
        p1Pieces[4] = new CheckersPiece(1,1,1);
        p1Pieces[5] = new CheckersPiece(3,1,1);
        p1Pieces[6] = new CheckersPiece(5,1,1);
        p1Pieces[7] = new CheckersPiece(7,1,1);
        p1Pieces[8] = new CheckersPiece(6,2,1);
        p1Pieces[9] = new CheckersPiece(4,2,1);
        p1Pieces[10] = new CheckersPiece(2,2,1);
        p1Pieces[11] = new CheckersPiece(0,2,1);

        // p2 starting coordinates
        p2Pieces = new CheckersPiece[12];
        p2Pieces[0] = new CheckersPiece(1,5,2);
        p2Pieces[1] = new CheckersPiece(3,5,2);
        p2Pieces[2] = new CheckersPiece(5,5,2);
        p2Pieces[3] = new CheckersPiece(7,5,2);
        p2Pieces[4] = new CheckersPiece(0,6,2);
        p2Pieces[5] = new CheckersPiece(2,6,2);
        p2Pieces[6] = new CheckersPiece(4,6,2);
        p2Pieces[7] = new CheckersPiece(6,6,2);
        p2Pieces[8] = new CheckersPiece(1,7,2);
        p2Pieces[9] = new CheckersPiece(3,7,2);
        p2Pieces[10] = new CheckersPiece(5,7,2);
        p2Pieces[11] = new CheckersPiece(7,7,2);

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

        for(int height=0;height<8;height++) {
            for(int length=0; length<8;length++) {
                if((height + length) % 2 == 0){
                    board[length][height].setImageResource(R.drawable.red_tile);
                    board[length][height].setTag(R.drawable.red_tile);
                }
                else{
                    board[length][height].setImageResource(R.drawable.white_tile);
                    board[length][height].setTag(R.drawable.white_tile);
                }
            }
        }

        //this sets all of player ones pieces on the map.
        for(CheckersPiece piece :  p1Pieces){
            if(piece.getAlive()) {
                if(piece.getKing()){
                    board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(R.drawable.black_king);
                    board[piece.getXcoordinate()][piece.getYcoordinate()].setTag(R.drawable.black_king);
                }
                else {
                    board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(R.drawable.black_piece);
                    board[piece.getXcoordinate()][piece.getYcoordinate()].setTag(R.drawable.black_piece);
                }
            }
        }

        for(CheckersPiece piece :  p2Pieces){
            if(piece.getAlive()) {
                if(piece.getKing()){
                    board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(R.drawable.red_king);
                    board[piece.getXcoordinate()][piece.getYcoordinate()].setTag(R.drawable.red_king);
                }
                else {
                    board[piece.getXcoordinate()][piece.getYcoordinate()].setImageResource(R.drawable.red_piece);
                    board[piece.getXcoordinate()][piece.getYcoordinate()].setTag(R.drawable.red_piece);
                }
            }
        }
    }

    public boolean isEmpty(int newXCord,int newYCord){
        boolean returnValue  = true;
        for(CheckersPiece piece : p1Pieces ){
            if(piece.getXcoordinate() == newXCord && piece.getYcoordinate() == newYCord
                    && piece.getAlive()){
                return false;
            }
        }
        for(CheckersPiece piece : p2Pieces ){
            if(piece.getXcoordinate() == newXCord && piece.getYcoordinate() == newYCord
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
        if(id == 0 && yDir<1 && !piece.getKing()){
            return false;
        }

        //checks if player two is trying to capture a piece behind it. If so, it makes sure it's a king
        if(id == 1 && yDir>0 && !piece.getKing()){
            return false;
        }



        //runs through all the enemy pieces
        for(CheckersPiece piece1 : enemyPieces ){

            //makes sure a dead pieces isn't being captured
            if(piece.getAlive()) {

                //makes checks if any enemy pieces is in a position to be captured by piece
                if (piece.getXcoordinate() + xDir == piece1.getXcoordinate()
                        && piece.getYcoordinate() +yDir == piece1.getYcoordinate()) {

                    //makes sure the space ahead of the the capture pieces is empty
                    if (isEmpty(piece1.getXcoordinate() + xDir, piece1.getYcoordinate() + yDir) &&
                            inBounds(piece1.getXcoordinate() + xDir, piece1.getYcoordinate() + yDir)) {
                        //kills the pieces and sets the return value to true
                        piece1.setAlive(false);
                        returnValue = true;

                        if(piece.getYcoordinate() == 7 && playerTurn == 0){
                            piece.setKing(true);
                        }

                        if(piece.getYcoordinate() == 0 && playerTurn == 1){
                            piece.setKing(true);
                        }

                        //changes the turn number
                        piece.setCoordinates(piece1.getXcoordinate() + xDir,
                                piece1.getYcoordinate() + yDir);
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
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {
                    returnValue = true;
                }
            }
        }
        else{
            for (CheckersPiece piece : p1Pieces) {
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {
                    returnValue = true;
                }
            }
        }
        return returnValue;

    }

    public CheckersPiece findPiece(int xCord,int yCord){
        for(int i = 0;i<12;i++){
            if(p1Pieces[i].getYcoordinate() == yCord && p1Pieces[i].getXcoordinate() == xCord){
                return p1Pieces[i];
            }
            else if (p2Pieces[i].getYcoordinate() == yCord && p2Pieces[i].getXcoordinate() == xCord){
                return p2Pieces[i];
            }
        }
        return null;
    }


    public boolean canMove(CheckersPiece piece, int xDir,int yDir,int id){
        if(inRange(xDir,yDir)) {

            //this checks that user is not trying to move off the checker board as well as if the space is held by another piece
            if (inBounds(piece.getXcoordinate() + xDir, piece.getYcoordinate() + yDir) &&
                    isEmpty(piece.getXcoordinate() + xDir, piece.getYcoordinate() + yDir)) {

                //this checks if player one is not trying to move a non king piece backwards
                if (id == 0 && yDir < 1 && !piece.getKing()) {
                    Log.e("movePiece: ", "Can't move backwards because not king");
                    return false;
                }

                //this checks if player two is not trying to move a non king piece backwards
                else if (id == 1 && yDir > 1 && !piece.getKing()) {
                    Log.e("movePiece: ", "Can't move backwards because not king");
                    return false;
                }
                else{
                    return true;
                }
            }
            //returns false if the move is out of bounds or tries to move into a spot with another piece
            else {
                Log.e("movePiece: ", "not in bounds");
                return false;
            }
        }
        //will return false if the player tries to move
        else {
            Log.e( "movePiece: ","not in range" );
            return false;
        }
    }

    public void movePiece(CheckersPiece piece, int xDir, int yDir, int id){
        //this if statement checks that the user has not tried to move more than one space
//        if(inRange(xDir,yDir)){
//
//            //this checks that user is not trying to move off the checker board as well as if the space is held by another piece
//            if(inBounds(piece.getXcoordinate()+xDir,piece.getYcoordinate()+yDir) &&
//                    isEmpty(piece.getXcoordinate()+xDir,piece.getYcoordinate()+yDir)){
//
//                //this checks if player one is not trying to move a non king piece backwards
//                if(id == 0 && yDir<1 && !piece.getKing()){
//                    Log.e( "movePiece: ","Can't move backwards because not king" );
//                    return false;
//                }
//
//                //this checks if player two is not trying to move a non king piece backwards
//                else if(id == 1 && yDir>1 && !piece.getKing()){
//                    Log.e( "movePiece: ","Can't move backwards because not king" );
//                    return false;
//                }
//
//                //if all the conditions are right the piece will move.
//                else {
                    piece.setCoordinates(piece.getXcoordinate()+xDir,piece.getYcoordinate()+yDir);

                    //will turn to player 1's pieces king if the piece reaches the other side of the board
                    if(id == 0){
                        //playerTurn = 1;
                        if(piece.getYcoordinate() == 7){
                            piece.setKing(true);
                        }
                    }

                    //will turn to player 2's pieces king if the piece reaches the other side of the board
                    else{
                        if(piece.getYcoordinate() == 0){
                            piece.setKing(true);
                        }
                        //playerTurn = 0;
                    }
//                    return true;
//                }
//            }
//
//            //returns false if the move is out of bounds or tries to move into a spot with another piece
//            else {
//                Log.e( "movePiece: ","not in bounds" );
//                return false;
//            }
//        }
//        //will return false if the player tries to move
//        else {
//            Log.e( "movePiece: ","not in range" );
//            return false;
//        }

    }


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
        if(this.pieceSelectedBoolean == false) {
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
            this.pieceSelectedBoolean = true;
        }
        else{
            this.pieceSelectedPiece = null;
            this.pieceSelectedBoolean = false;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isPieceSelectedBoolean() {
        return pieceSelectedBoolean;
    }

    public CheckersPiece getPieceSelectedPiece() {
        return pieceSelectedPiece;
    }
}
