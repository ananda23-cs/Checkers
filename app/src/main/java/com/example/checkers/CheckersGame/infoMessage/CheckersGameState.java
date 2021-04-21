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

    // instance variables
    public CheckersPiece[] p1Pieces; // holds player 1's pieces
    public CheckersPiece[] p2Pieces; // holds player 2's pieces
    private int p1NumPieces;
    private int p2NumPieces;
    private boolean pieceSelectedBoolean; // determines if pieces has been selected
    private CheckersPiece pieceSelectedPiece; // pieces that will move
    private int playerTurn;
    private String message;
    //add grid here
    ImageButton[][] board; //displays the 8x8 checkerboard
    //TextView gameInfo;

    /**
     * constructor CheckersGameState
     *
     */
    public CheckersGameState(){
        // initializes 12 pieces to each player
        this.playerTurn = 0;

        this.p1NumPieces = 12;
        this.p2NumPieces = 12;

        // initializes pieces to player 1 and puts them in their starting coordinates
        this.p1Pieces = new CheckersPiece[12];
        this.p1Pieces[0] = new CheckersPiece(0,0,1);
        this.p1Pieces[1] = new CheckersPiece(2,0,1);
        this.p1Pieces[2] = new CheckersPiece(4,0,1);
        this.p1Pieces[3] = new CheckersPiece(6,0,1);
        this.p1Pieces[4] = new CheckersPiece(1,1,1);
        this.p1Pieces[5] = new CheckersPiece(3,1,1);
        this.p1Pieces[6] = new CheckersPiece(5,1,1);
        this.p1Pieces[7] = new CheckersPiece(7,1,1);
        this.p1Pieces[8] = new CheckersPiece(6,2,1);
        this.p1Pieces[9] = new CheckersPiece(4,2,1);
        this.p1Pieces[10] = new CheckersPiece(2,2,1);
        this.p1Pieces[11] = new CheckersPiece(0,2,1);

        // initializes pieces to player 2 and puts them in their starting coordinates
        this.p2Pieces = new CheckersPiece[12];
        this.p2Pieces[0] = new CheckersPiece(1,5,2);
        this.p2Pieces[1] = new CheckersPiece(3,5,2);
        this.p2Pieces[2] = new CheckersPiece(5,5,2);
        this.p2Pieces[3] = new CheckersPiece(7,5,2);
        this.p2Pieces[4] = new CheckersPiece(0,6,2);
        this.p2Pieces[5] = new CheckersPiece(2,6,2);
        this.p2Pieces[6] = new CheckersPiece(4,6,2);
        this.p2Pieces[7] = new CheckersPiece(6,6,2);
        this.p2Pieces[8] = new CheckersPiece(1,7,2);
        this.p2Pieces[9] = new CheckersPiece(3,7,2);
        this.p2Pieces[10] = new CheckersPiece(5,7,2);
        this.p2Pieces[11] = new CheckersPiece(7,7,2);

        this.pieceSelectedBoolean = false;
        this.pieceSelectedPiece = null;
        this.message = "";
    } //CheckersGameState

    /**
     * copy constructor CheckersGameState
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
        this.pieceSelectedBoolean = original.pieceSelectedBoolean;
        if(original.pieceSelectedPiece != null) {
            this.pieceSelectedPiece = new CheckersPiece(original.pieceSelectedPiece);
        }
        else{
            this.pieceSelectedPiece = null;
        }
    } //CheckersGameState

    /**
     * getter methods for players pieces
     *
     */
    public int getP1NumPieces()
    {
        return p1NumPieces;
    }
    public int getP2NumPieces()
    {
        return p2NumPieces;
    }
    public int getPlayerTurn() {
        return playerTurn;
    }


    /**
     * setter methods for players pieces
     *
     */

    public void setP1NumPieces(int p1NumPieces) {
        this.p1NumPieces = p1NumPieces;
    }
    public void setP2NumPieces(int p2NumPieces) {
        this.p2NumPieces = p2NumPieces;
    }
    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    } //setPlayerTurn

    /**
     * method setBoard
     * sets the board and displays all of the location of the coordinates
     *
     * @param board
     *      checkerboard for players pieces
     */
    public void setBoard(ImageButton[][] board){

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
            Log.e("setBoard: ",""+piece );
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
        Log.e( "setBoard: ",""+p1Pieces[10] );
    } //setBoard

    /**
     * method isEmpty
     * checks if the spot on the board is empty
     *
     * @param newXCord
     *      new x coordinate of piece
     * @param newYCord
     *      new y coordinate of piece
     * @return
     *      return if the space is empty or not
     */
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
    } //isEmpty

    /**
     * method inBounds
     * checks if the spot on the board is in bounds
     *
     * @param newXCord
     *      new x coordinate of piece
     * @param newYCord
     *      new y coordinate of piece
     * @return
     *      return if the spot is in bounds or not
     */
    public boolean inBounds(int newXCord,int newYCord){
        boolean valid = true;

        if(newXCord>7 || newYCord>7 || newXCord<0 || newYCord<0){
            valid = false;
        }

        return valid;

    } //inBounds

    /**
     * method capturepiece
     * checks if the spot on the board is in bounds
     *
     * @param piece
     *      players selected piece
     * @param id
     *      player id (either player 1 or 2)
     * @param enemyPieces
     *      opponents piece to capture
     * @param xDir
     *      x direction of piece
     * @param yDir
     *      y direction of piece
     * @return
     *      return if you can capture the piece or not
     */
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
                        piece.setCoordinates(piece1.getXcoordinate() + xDir+1,
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
     * checks if the piece is not yours
     *
     * @param xLocation
     *      x location of piece
     * @param yLocation
     *      y location of piece
     * @return
     *      return if enemy piece is in range
     */
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

    } //hasEnemyPieces

    /**
     * method findPiece
     * finds a players piece
     *
     * @param xCord
     *      x coordinate of piece
     * @param yCord
     *      y coordinate of piece
     * @return
     *      return if piece was found and what piece
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
     * checks if player can move
     *
     * @param piece
     *      players selected piece
     * @param xDir
     *      x coordinate of piece
     * @param yDir
     *      y coordinate of piece
     * @param id
     *      players turn (either player 1 or 2)
     * @return
     *      return if player can move
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
                else if (id == 1 && yDir == 1 && !piece.getKing()) {
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
     * checks if player can move
     *
     * @param piece
     *      players selected piece
     * @param xDir
     *      x coordinate of piece
     * @param yDir
     *      y coordinate of piece
     * @param id
     *      players turn (either player 1 or 2)
     * @return
     *      return if player can move the piece
     */
    public boolean movePiece(CheckersPiece piece, int xDir, int yDir, int id){
        //this if statement checks that the user has not tried to move more than one space
        if(inRange(xDir,yDir)){

            //this checks that user is not trying to move off the checker board as well as if the space is held by another piece
            if(inBounds(piece.getXcoordinate()+xDir,piece.getYcoordinate()+yDir) &&
                    isEmpty(piece.getXcoordinate()+xDir,piece.getYcoordinate()+yDir)){

                //this checks if player one is not trying to move a non king piece backwards
                if(id == 0 && yDir<1 && !piece.getKing()){
                    Log.e( "movePiece: ","Can't move backwards because not king" );
                    return false;
                }

                //this checks if player two is not trying to move a non king piece backwards
                else if(id == 1 && yDir>1 && !piece.getKing()){
                    Log.e( "movePiece: ","Can't move backwards because not king" );
                    return false;
                }

//                //if all the conditions are right the piece will move.
                else {
                    Log.e("tag","true");
                    piece.setCoordinates(piece.getXcoordinate()+xDir,piece.getYcoordinate()+yDir);
                    setPlayerTurn(1-playerTurn);
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

    } //movePiece

    /**
     * method inRange
     * checks if player is in range
     *
     * @param xDir
     *      x coordinate of piece
     * @param yDir
     *      y coordinate of piece
     * @return
     *      return if piece is in range
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
     * @return
     *      return if piece is in range
     */
    public void setPieceSelectedPieceAndPieceSelectedBoolean(int xCord,int yCord){
        int i = 0;
        if(!this.pieceSelectedBoolean) {
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
        else if(xCord == -1 && yCord ==-1){
            this.pieceSelectedPiece = null;
            this.pieceSelectedBoolean = false;
        }
    } //setPieceSelectedPieceAndPieceSelectedBoolean

    /**
     * Getter and setter methods for game state
     *
     */
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
    public void setPieceSelectedPieceAndPieceSelectedBoolean(){
        pieceSelectedPiece = null;
        pieceSelectedBoolean = false;
    } //getMessage

    /**
     * method move
     *
     * @param xDire
     *      x coordinate of piece
     * @param yDire
     *      y coordinate of piece
     */
    public void move(int xDire,int yDire){
        this.pieceSelectedPiece.setCoordinates(pieceSelectedPiece.getXcoordinate()+xDire,pieceSelectedPiece.getYcoordinate()+yDire);

        if(playerTurn == 0){
            //playerTurn = 1;
            if(pieceSelectedPiece.getYcoordinate() == 7){
                pieceSelectedPiece.setKing(true);
            }
        }

        //will turn to player 2's pieces king if the piece reaches the other side of the board
        else{
            if(pieceSelectedPiece.getYcoordinate() == 0){
                pieceSelectedPiece.setKing(true);
            }
            //playerTurn = 0;
        }

        setPieceSelectedPieceAndPieceSelectedBoolean(-1,-1);

    } //move

    /**
     * method setImageBoard
     * sets the image board
     * @param board
     *      new ImageButton board
     */
    public void setImageBoard(ImageButton[][] board){
        this.board = board;
    }

    /**
     * method CaptureEnemyPiece
     * checks if player can capture the enemies piece
     * @param xLocation
     *      x location of piece
     * @param yLocation
     *      y location of piece
     * @param nVX
     *      not valid x location
     * @param nVY
     *      not valid y location
     */
    public boolean CaptureEnemyPiece(int xLocation,int yLocation,int nVX,int nVY){
        if(playerTurn == 0) {
            for (CheckersPiece piece : p2Pieces) {
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {

                    int xDist = xLocation - nVX;
                    int yDist = yLocation - nVY;
                    if(inRange(xDist,yDist) && inBounds(nVX + xDist*2,nVY + yDist*2)){
                        int xFinal = nVX + xDist*2;
                        int yFinal = nVY + yDist*2;
                        if(isEmpty(xFinal,yFinal)){
                            piece.setAlive(false);
                            pieceSelectedPiece.setCoordinates(xFinal,yFinal);

                            setP2NumPieces(p2NumPieces-1);

                            if(pieceSelectedPiece.getYcoordinate() == 7){
                                pieceSelectedPiece.setKing(true);
                            }

                            setPieceSelectedPieceAndPieceSelectedBoolean();

                            return true;
                        }
                    }

                }

            }
        }
        else if(playerTurn == 1){
            for (CheckersPiece piece : p1Pieces) {
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {

                    int xDist = xLocation - nVX;
                    int yDist = yLocation - nVY;
                    if(inRange(xDist,yDist) && inBounds(nVX + xDist*2,nVY + yDist*2)){
                        int xFinal = nVX + xDist*2;
                        int yFinal = nVY + yDist*2;
                        if(isEmpty(xFinal,yFinal)){
                            piece.setAlive(false);
                            pieceSelectedPiece.setCoordinates(xFinal,yFinal);

                            setP1NumPieces(p1NumPieces-1);

                            if(pieceSelectedPiece.getYcoordinate() == 7){
                                pieceSelectedPiece.setKing(true);
                            }

                            setPieceSelectedPieceAndPieceSelectedBoolean();

                            return true;
                        }
                    }

                }

            }
        }
        return false;
    } //CaptureEnemyPiece

    public boolean equals(Object object){
        if(!(object instanceof CheckersGameState)) return false;
        CheckersGameState checkersGameState = (CheckersGameState) object;

        for(int k = 0; k < 12; k++){
            if(!(this.p1Pieces[k].equals(checkersGameState.p1Pieces[k])) ||
                !(this.p2Pieces[k].equals(checkersGameState.p2Pieces[k]))){
                return false;
            }
        }

        return this.playerTurn == checkersGameState.playerTurn &&
                this.numSetupTurns == checkersGameState.numSetupTurns &&
                this.currentSetupTurn == checkersGameState.currentSetupTurn &&
                this.p1NumPieces == checkersGameState.p1NumPieces &&
                this.p2NumPieces == checkersGameState.p2NumPieces &&
                this.pieceSelectedPiece == checkersGameState.pieceSelectedPiece &&
                this.pieceSelectedBoolean == checkersGameState.pieceSelectedBoolean &&
                this.message.equals(checkersGameState.message);
    }
}//CheckersGameState
