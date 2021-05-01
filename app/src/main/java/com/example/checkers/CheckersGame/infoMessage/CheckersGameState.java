/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Game State class - defines different states of the game
 *
 * CS301A
 * @version 04/30/2021
 *
 * No outside sources for this class
 */

package com.example.checkers.CheckersGame.infoMessage;

import android.widget.ImageButton;
import com.example.checkers.game.GameFramework.infoMessage.GameState;

public class CheckersGameState extends GameState {

    // instance variables
    public CheckersPiece[] p1Pieces; // holds player 1's pieces
    public CheckersPiece[] p2Pieces; // holds player 2's pieces
    private int p1NumPieces;
    private int p2NumPieces;
    private boolean pieceSelectedBoolean; // determines if piece has been selected
    private CheckersPiece pieceSelectedPiece; // piece that will move
    private int playerTurn;
    private String message;
    private boolean p1CanNotMove;
    private boolean p2CanNotMove;

    //add grid here
    ImageButton[][] board; //displays the 8x8 checkerboard

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
        this.message = "Welcome to checkers. Player one please click on the piece " +
                "you would like to move.";
        this.p2CanNotMove = false;
        this.p1CanNotMove = false;

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
        this.p1CanNotMove = original.p1CanNotMove;
        this.p2CanNotMove = original.p2CanNotMove;
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
    public boolean getP1CanNotMove(){ return this.p1CanNotMove; }
    public boolean getP2CanNotMove(){ return this.p2CanNotMove; }




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
    public void setP1CanNotMove(boolean p1CanNotMove){ this.p1CanNotMove = p1CanNotMove; }
    public void setP2CanNotMove(boolean p2CanNotMove){ this.p2CanNotMove = p2CanNotMove; }


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
        for(CheckersPiece piece : p1Pieces ){
            // run through pieces coordinates and return false if spot is not empty
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
        return true;
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

        // if coordinates exceed 7 or below 0, return false (out of bounds)
        if(newXCord>7 || newYCord>7 || newXCord<0 || newYCord<0){
            valid = false;
        }

        return valid;

    } //inBounds

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

    /**finds piece at xCord and yCord
     * @param xCord
     *        the given x-coordinate
     * @param yCord
     *        the given y-coordinate
     * @return piece at xCord and yCord or null if not found
     */
    public CheckersPiece findPiece(int xCord,int yCord){
        // run through all of the coordinates and return the pieces coordinate
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

            //this checks that user is not trying to move off the checker board
            // as well as if the space is held by another piece
            if (inBounds(piece.getXcoordinate() + xDir,
                    piece.getYcoordinate() + yDir) && isEmpty(
                            piece.getXcoordinate() + xDir,
                    piece.getYcoordinate() + yDir)) {

                //this checks if player one is not trying to move a non king piece backwards
                if (id == 0 && yDir < 1 && !piece.getKing()) {
                    return false;
                }

                //this checks if player two is not trying to move a non king piece backwards
                else if (id == 1 && yDir == 1 && !piece.getKing()) {
                    return false;
                }
                else{
                    return true;
                }
            }
            //returns false if the move is out of bounds or tries to move into a spot
            //with another piece
            else {
                return false;
            }
        }
        //will return false if the player tries to move
        else {
            return false;
        }
    } //canMove

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
        return (xDir == 1 || xDir == -1) && (yDir == 1 || yDir == -1);

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
                // if piece is alive, set the piece to the selected position
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
        this.pieceSelectedPiece.setCoordinates(pieceSelectedPiece.getXcoordinate()+xDire,
                                                pieceSelectedPiece.getYcoordinate()+yDire);

        // turn player's pieces to a king if it reaches the opposing side
        // turn player 1's pieces to a king
        if(playerTurn == 0){
            if(pieceSelectedPiece.getYcoordinate() == 7){
                pieceSelectedPiece.setKing(true);
            }
        }

        // turn player 2's pieces to a king
        else{
            if(pieceSelectedPiece.getYcoordinate() == 0){
                pieceSelectedPiece.setKing(true);
            }
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
     * @param xLocation x location of piece being
     * @param yLocation y location of piece
     * @param nVX not valid x location
     * @param nVY not valid y location
     */
    public boolean CaptureEnemyPiece(int xLocation,int yLocation,int nVX,int nVY){
        if(!pieceSelectedBoolean){
            return false;
        }
        // if user is player 0
        if(playerTurn == 0) {
            for (CheckersPiece piece : p2Pieces) {
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {

                    int xDist = xLocation - nVX;
                    int yDist = yLocation - nVY;
                    if(yDist < 1 && !pieceSelectedPiece.getKing()){
                        return false;
                    }

                    // checks if enemy piece is in range
                    if(inRange(xDist,yDist) && inBounds(nVX + xDist*2,
                            nVY + yDist*2)){
                        int xFinal = nVX + xDist*2;
                        int yFinal = nVY + yDist*2;

                        // checks if spot behind enemy piece is empty
                        if(isEmpty(xFinal,yFinal)){
                            piece.setAlive(false);
                            pieceSelectedPiece.setCoordinates(xFinal,yFinal);
                            setP2NumPieces(getP2NumPieces()-1);
                            if(pieceSelectedPiece.getYcoordinate() == 7){
                                pieceSelectedPiece.setKing(true);
                            }

                            // check if can capture the enemies piece
                            // forwards right/left and backwards right/left
                            if(checkIfCanCaptureEnemyPiece(xFinal-1,
                                    yFinal-1,xFinal,yFinal)){
                                CaptureEnemyPiece(xFinal-1,
                                        yFinal-1,xFinal,yFinal);
                            }

                            if(checkIfCanCaptureEnemyPiece(xFinal+1,
                                    yFinal-1,xFinal,yFinal)){
                                CaptureEnemyPiece(xFinal+1,
                                        yFinal-1,xFinal,yFinal);
                            }

                            if(checkIfCanCaptureEnemyPiece(xFinal+1,
                                    yFinal+1,xFinal,yFinal)){
                                CaptureEnemyPiece(xFinal+1,
                                        yFinal+1,xFinal,yFinal);
                            }

                            if(checkIfCanCaptureEnemyPiece(xFinal-1,
                                    yFinal+1,xFinal,yFinal)){
                                CaptureEnemyPiece(xFinal-1,
                                        yFinal+1,xFinal,yFinal);
                            }

                            setPieceSelectedPieceAndPieceSelectedBoolean();

                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
            }
        }
        // if user is player 1
        else if(playerTurn == 1){
            for (CheckersPiece piece : p1Pieces) {
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {

                    int xDist = xLocation - nVX;
                    int yDist = yLocation - nVY;
                    if(yDist > 0 && !pieceSelectedPiece.getKing()){
                        return false;
                    }
                    // checks if enemy piece is in range
                    if(inRange(xDist,yDist) && inBounds(nVX + xDist*2,
                            nVY + yDist*2)){
                        int xFinal = nVX + xDist*2;
                        int yFinal = nVY + yDist*2;

                        // checks if spot behind enemy piece is empty
                        if(isEmpty(xFinal,yFinal)){
                            piece.setAlive(false);
                            setP1NumPieces(getP1NumPieces()-1);
                            pieceSelectedPiece.setCoordinates(xFinal,yFinal);

                            if(pieceSelectedPiece.getYcoordinate() == 0){
                                pieceSelectedPiece.setKing(true);
                            }

                            // check if can capture the enemies piece
                            // forwards right/left and backwards right/left
                            if(checkIfCanCaptureEnemyPiece(xFinal+1,
                                    yFinal+1,xFinal,yFinal)){
                                CaptureEnemyPiece(xFinal+1,yFinal+1,
                                        xFinal,yFinal);
                            }

                            if(checkIfCanCaptureEnemyPiece(xFinal-1,
                                    yFinal+1,xFinal,yFinal)){
                                CaptureEnemyPiece(xFinal-1,yFinal+1,
                                        xFinal,yFinal);
                            }

                            if(checkIfCanCaptureEnemyPiece(xFinal-1,
                                    yFinal-1,xFinal,yFinal)){
                                CaptureEnemyPiece(xFinal-1,yFinal-1,
                                        xFinal,yFinal);
                            }

                            if(checkIfCanCaptureEnemyPiece(xFinal+1,
                                    yFinal-1,xFinal,yFinal)){
                                CaptureEnemyPiece(xFinal+1,yFinal-1,
                                        xFinal,yFinal);
                            }

                            setPieceSelectedPieceAndPieceSelectedBoolean();

                            return true;
                        }
                        else{
                            return false;
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
    } //equals

    public boolean checkIfCanCaptureEnemyPiece(int xLocation,int yLocation,int nVX,int nVY) {
        if(!pieceSelectedBoolean || pieceSelectedPiece == null){
            return false;
        }

        //if player one's turn
        if(playerTurn == 0) {
            //runs through all of player 2's pieces to see if they match the xLocation and yLocation
            for (CheckersPiece piece : p2Pieces) {
                //checks if piece matches the xLocation and yLocation
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {

                    //how far away the enemy piece is
                    int xDist = xLocation - nVX;
                    int yDist = yLocation - nVY;

                    if(yDist < 1 && !pieceSelectedPiece.getKing()){
                        return false;
                    }

                    //if distance is in range of the piece
                    if(inRange(xDist,yDist) && inBounds(nVX + xDist*2,
                            nVY+yDist*2)){

                        //final location of the piece that does the capturing
                        int xFinal = nVX + xDist*2;
                        int yFinal = nVY + yDist*2;

                        //checks if spot ahead of the victim is empty
                        if(isEmpty(xFinal,yFinal)){
                            return true;
                        }
                    }
                }
            }
        }

        //if player two's turn
        //this code is the same as before except it runs though p1's pieces
        else if(playerTurn == 1 ){
            for (CheckersPiece piece : p1Pieces) {
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {
                    int xDist = xLocation - nVX;
                    int yDist = yLocation - nVY;

                    if(yDist == 1 && !pieceSelectedPiece.getKing()){
                        return false;
                    }

                    if(inRange(xDist,yDist) && inBounds(nVX + xDist*2,
                            nVY + yDist*2)){
                        int xFinal = nVX + xDist*2;
                        int yFinal = nVY + yDist*2;
                        if(isEmpty(xFinal,yFinal)){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    } //checkIfCanCaptureEnemyPiece



}//CheckersGameState
