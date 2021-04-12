/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Game State class - defines different states of the game
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGame.infoMessage;

import android.util.Log;
import android.widget.ImageButton;

import com.example.checkers.R;
import com.example.checkers.game.GameFramework.infoMessage.GameState;

public class CheckersGameState extends GameState {

    // variables for player 1 and 2's pieces and player turns
    public CheckersPiece[] p1Pieces;
    public CheckersPiece[] p2Pieces;
    private int p1NumPieces;
    private int p2NumPieces;
    private boolean pieceSelectedBoolean; // determines if a piece has been selected
    private CheckersPiece pieceSelectedPiece; // piece that is selected and will move
    private int playerTurn;
    private String message;

    /**
     * constructor for CheckersGameState
     *
     */
    public CheckersGameState(){
        // player 1 makes first move and each player starts with 12 pieces
        playerTurn = 0;
        p1NumPieces = 12;
        p2NumPieces = 12;

        // initializes 12 pieces for player 1 and puts them in their starting coordinates
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

        // initializes 12 pieces for player 2 and puts them in their starting coordinates
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
    } //CheckersGameState

    /**
     * copy constructor for CheckersGameState
     *
     * @param original
     *      copy of original game state
     */
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
    } //CheckersGameState


    // getter method for players pieces
    public int getP1NumPieces()
    {
        return p1NumPieces;
    }
    public int getP2NumPieces()
    {
        return p1NumPieces;
    }
    public int getPlayerTurn() {
        return playerTurn;
    }

    // setter method for players pieces
    public void setP1NumPieces(int p1)
    {
        p1NumPieces = p1;
    }
    public void setP2NumPieces(int p2) { p2NumPieces = p2; }
    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

   /**
     * method setBoard
     * sets the board and displays all of the locations of the coordinates
     *
     * @param board
     *      checkerboard
     */
    public void setBoard(ImageButton[][] board){

        // loop to alternate and place red and white tiles
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

        // sets player 1's pieces on the board to black
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

        // sets player 2's pieces on the board to red
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
    } //setBoard

    /**
     * method isEmpty
     * checks if the spot on the board is empty
     *
     * @param newXCord
     *      new x coordinate on the board
     * @param newYCord
     *      new y coordinate on the board
     * @return
     *      return if the spot is empty or not
     */
    public boolean isEmpty(int newXCord,int newYCord){
        boolean returnValue  = true;
        // loops through player 1's coordinates to check if empty
        for(CheckersPiece piece : p1Pieces ){
            if(piece.getXcoordinate() == newXCord && piece.getYcoordinate() == newYCord
                    && piece.getAlive()){
                return false;
            }
        }
        // loops through player 2's coordinates to check if empty
        for(CheckersPiece piece : p2Pieces ){
            if(piece.getXcoordinate() == newXCord && piece.getYcoordinate() == newYCord
                    && piece.getAlive()){
                return false;
            }
        }
        return returnValue;
    } //isEmpty

    /**
     * method inBounds
     * checks if the spot on the board is in bounds
     *
     * @param newXCord
     *      new x coordinate on the board
     * @param newYCord
     *      new y coordinate on the board
     * @return
     *      return if the spot is in bounds or not
     */
    public boolean inBounds(int newXCord,int newYCord){
        boolean valid = true;

        if(newXCord>7 || newYCord>7 || newXCord<0 || newYCord<0){
            valid = false;
        }

        return valid;
    } //isBounds

    /**
     * method capturepiece
     * checks if player can capture the opponents piece
     *
     * @param piece
     *      players piece
     * @param id
     *      id of the player (either player 1 or 2)
     * @param enemyPieces
     *      opponents piece player wants to capture
     * @param xDir
     *      x direction of piece
     * @param yDir
     *      y direction of piece
     * @return
     *      return if the piece can be captured or not
     */
    public boolean capturepiece(CheckersPiece piece,int id,CheckersPiece[] enemyPieces,int xDir,int yDir) {
        // variables to be returned, must be set to true in order to return
        boolean returnValue = false;

        // checks if the player is in range
        if(!inRange(xDir,yDir)){
            return false;
        }

        // checks if player 1 a king if it wants to move backwards
        if(id == 0 && yDir<1 && !piece.getKing()){
            return false;
        }

        // checks if player 2 a king if it wants to move backwards
        if(id == 1 && yDir>0 && !piece.getKing()){
            return false;
        }

        // runs though all the enemies pieces
        for(CheckersPiece piece1 : enemyPieces ){

            // checks if pieces are alive
            if(piece.getAlive()) {

                // checks if enemy piece can be captured
                if (piece.getXcoordinate() + xDir == piece1.getXcoordinate()
                        && piece.getYcoordinate() +yDir == piece1.getYcoordinate()) {

                    // checks if spot is empty
                    if (isEmpty(piece1.getXcoordinate() + xDir, piece1.getYcoordinate() + yDir) &&
                            inBounds(piece1.getXcoordinate() + xDir, piece1.getYcoordinate() + yDir)) {
                        // removes piece and sets return value to true
                        piece1.setAlive(false);
                        returnValue = true;

                        if(piece.getYcoordinate() == 7 && playerTurn == 0){
                            piece.setKing(true);
                        }

                        if(piece.getYcoordinate() == 0 && playerTurn == 1){
                            piece.setKing(true);
                        }

                        // changes player's turn
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
    } //capturepiece


    /**
     * method hasEnemyPieces
     * checks if the player has any enemy pieces
     *
     * @param xLocation
     *      x location of  piece
     * @param yLocation
     *      y location of piece
     * @return
     *      returns if player has any enemy pieces or not
     */
    public boolean hasEnemyPieces(int xLocation,int yLocation){
        boolean returnValue = false;
        // checks if player 1 has any of player 2's pieces
        if(playerTurn == 0) {
            for (CheckersPiece piece : p2Pieces) {
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {
                    returnValue = true;
                }
            }
        }
        // checks if player 2 has any of player 1's pieces
        else{
            for (CheckersPiece piece : p1Pieces) {
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {
                    returnValue = true;
                }
            }
        }
        return returnValue;
    } //hasEnemyPieces

    /**
     * method findPiece
     * finds the players pieces
     *
     * @param xCord
     *      x coordinate of players piece
     * @param yCord
     *      y coordinate of players piece
     * @return
     *      returns the players pieces
     */
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
    } //findPiece


    /**
     * method canMove
     * finds the players pieces
     *
     * @param piece
     *      players selected piece
     * @param xDir
     *      x direction fo piece
     * @param yDir
     *      y direction of piece
     * @param id
     *      player who is moving (either player 1 or 2)
     * @return
     *      return if the player can move or not
     */
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
                else if (id == 1 && yDir > 0 && !piece.getKing()) {
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
    } //canMove

    /**
     * method movePiece
     * moves the players selected piece
     *
     * @param piece
     *      players selected piece
     * @param xDir
     *      x direction fo piece
     * @param yDir
     *      y direction of piece
     * @param id
     *      player who is moving (either player 1 or 2)
     */
    public void movePiece(CheckersPiece piece, int xDir, int yDir, int id){
        piece.setCoordinates(piece.getXcoordinate()+xDir,piece.getYcoordinate()+yDir);

        // turn player 1's piece to a king if it reaches the opposite side
        if(id == 0){
            if(piece.getYcoordinate() == 7){
                piece.setKing(true);
            }
            //playerTurn = 1;
        }

        // turn player 2's piece to a king if it reaches the opposite side
        else{
            if(piece.getYcoordinate() == 0){
                piece.setKing(true);
            }
            //playerTurn = 0;
        }
    } //movePiece

    /**
     * method inRange
     * checks if the players selection is in range
     *
     * @param xDir
     *      x direction fo piece
     * @param yDir
     *      y direction of piece
     * @return
     *      return if the selection is in range or not
     */
    public boolean inRange(int xDir,int yDir){
        if((xDir == 1 || xDir == -1) && (yDir == 1 || yDir == -1)){
            return true;
        }
        else{
            return false;
        }
    } //inRange

    /**
     * method setPieceSelectedPieceAndPieceSelectedBoolean
     *
     * @param xCord
     *      x coordinate of piece
     * @param yCord
     *      y coordinate of piece
     */
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

    // getter methods
    public String getMessage() {
        return message;
    }
    public CheckersPiece getPieceSelectedPiece() {
        return pieceSelectedPiece;
    }

    // setter methods
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isPieceSelectedBoolean() {
        return pieceSelectedBoolean;
    }


    /**
     * added the method to prevent crashing when the human player selects another piece.
     */
    public void setPieceSelectedPieceAndPieceSelectedBoolean(){
        pieceSelectedPiece = null;
        pieceSelectedBoolean = false;
    } //setPieceSelectedPieceAndPieceSelectedBoolean
}
