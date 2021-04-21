/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Game State class - defines different states of the game
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers.CheckersGameFramework.CheckersInfoMessage;

import android.util.Log;
import android.widget.ImageButton;

import com.example.checkers.CheckersGameFramework.CheckersPiece;
import com.example.checkers.R;
import com.example.checkers.game.GameFramework.infoMessage.GameState;

public class CheckersGameState extends GameState {

    // instance variables
    public CheckersPiece[] p1Pieces;//Player one's pieces
    public CheckersPiece[] p2Pieces;//Player two's pieces
    private int p1NumPieces;//amount of pieces player one has
    private int p2NumPieces;//amount of pieces player two has
    private boolean pieceSelectedBoolean; //this determines if a piece has been selected yet.
    private CheckersPiece pieceSelectedPiece; //this is the piece that is going to get moved.
    private int playerTurn; // It if it is 0 then then it's player ones turn. If it's 1 then it's player 2's turn.
    //playerTurn is the same as player id.
    //if it is any other number something is wrong and game has likely stopped working
    private String message;// this is what will be displayed for
    //add grid here
    ImageButton[][] board; //displays the 8x8 checkerboard


    //initial constructor for the beginning of the game
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


//        p1Pieces[9].setCoordinates(2,4);
//        p1Pieces[11].setCoordinates(0,4);

//        p1Pieces[10].setAlive(false);
//        p2Pieces[2].setCoordinates(2,2);
//        p2Pieces[7].setCoordinates(4,4);
//        //p2Pieces[11].setCoordinates(6,6);
//        p2Pieces[9].setAlive(false);


//        p2Pieces[0].setKing(true);
//        p2Pieces[0].setCoordinates(3,3);
//        p1Pieces[9].setCoordinates(4,4);
//        p2Pieces[1].setCoordinates(4,2);
//        p1Pieces[5].setCoordinates(6,6);
//        p2Pieces[2].setCoordinates(2,4);



        pieceSelectedBoolean = false;
        message = "";
    }

    //copy constructor
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
        this.pieceSelectedPiece = new CheckersPiece(original.pieceSelectedPiece);

    }

    //getter and setter methods. Nothing special or complicated about them
    public int getP1NumPieces() { return p1NumPieces; }
    public int getP2NumPieces()
    {
        return p2NumPieces;
    }
    public void setP1NumPieces(int p1)
    {
        p1NumPieces = p1;
    }
    public void setP2NumPieces(int p2) { p2NumPieces = p2; }
    public int getPlayerTurn() {
        return playerTurn;
    }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isPieceSelectedBoolean() { return pieceSelectedBoolean; }
    public CheckersPiece getPieceSelectedPiece() { return pieceSelectedPiece; }
    public void setImageBoard(ImageButton[][] board){ this.board = board; }

    /**Changes the turn.
    * @param playerTurn: it used to make this.playerTurn equal to playerTurn but I found this to be more consistent.
    * This param is not needed but I have not deleted it because then I would have to change it every where.
    */
    public void setPlayerTurn(int playerTurn) {
        if(this.playerTurn == 0) {
            this.playerTurn = 1;
        }
        else{
            this.playerTurn = 0;
        }
        //this.playerTurn = playerTurn
    }

    /**This sets the board and displays all the locations of the coordinates
     *@param board: board of image buttons that displays all the tiles. The tiles can be red,white or have a piece on them
     */
    public void setBoard(ImageButton[][] board){
        //These forloops set the board to be an empty checkers board
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

        //this sets all of player two's pieces on the map.
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

        //used for debugging.
        Log.e( "setBoard: ",""+p1Pieces[10] );
    }

    /**checks if coordinates are held by another piece
     * @param newXCord:xlocation of new coordinate
     * @param newYCord:ylocation of new coordinate
     * @return
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
    }

    /**checks if coordinates are on the board
     * @param newXCord:xlocation of new coordinate
     * @param newYCord:ylocation of new coordinate
     * @return
     */
    public boolean inBounds(int newXCord,int newYCord){
        boolean valid = true;

        if(newXCord>7 || newYCord>7 || newXCord<0 || newYCord<0){
            valid = false;
        }

        return valid;

    }

    /** Capture method. Can be used for player one and player two pieces. This method is not used in favor of
     * a more simple capture method
     * @param piece the piece doing the capturing
     * @param id  if id = 1 then it's player one's turn
     *            if id = 2 then its player two's turn
     * @param xDir xDir is used to determine the horizontal direction of the capturing piece
     *   If the conditions are met then the piece's x-coordinate is set as x-coordinate + xdir*2
     *   xDir must equal to one or negative one or this method will return false
     * @param yDir xDir and yDir are used to determine the direction of the capturing piece
     *     If the conditions are met then the piece's y-coordinate is set as y-coordinate + ydir*2
     *     yDir must equal to one or negative one or this method will return false
     * @param enemyPieces the array that targeted piece belongs to
     * @return true if capture is legal move and false otherwise
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
    }

    /**Checks if there is enemy piece in the location.
     * @param xLocation:Is what it sounds like
     * @param yLocation:Is what it sounds like
     */
    public boolean hasEnemyPieces(int xLocation,int yLocation){
        boolean returnValue = false;
        if(playerTurn == 0) {
            for (CheckersPiece piece : p2Pieces) {
                //this makes sure the enemy piece is alive
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {
                    returnValue = true;
                }
            }
        }
        else{
            for (CheckersPiece piece : p1Pieces) {
                //this makes sure the enemy piece is alive
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {
                    returnValue = true;
                }
            }
        }
        return returnValue;

    }

    /**finds piece at xCord and yCord
     * @param xCord
     * @param yCord
     * @return piece at xCord and yCord
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
    }

    /**determines if a piece can move
     * @param piece the piece that moves
     * @param xDir the direction as well as the distance of the x loaction it wants to move to
     * @param yDir the direction as well as the distance of the y loaction it wants to move to
     * @param id whoever's turn it is
     */
    public boolean canMove(CheckersPiece piece, int xDir,int yDir,int id){
        if(piece.equals(p2Pieces[1])){
            Log.e("Lego1","x = " + piece.getXcoordinate()+"y = " + piece.getYcoordinate());
        }
        if(inRange(xDir,yDir)) {


            //this checks that user is not trying to move off the checker board as well as if the space is held by another piece
            if (inBounds(piece.getXcoordinate() + xDir, piece.getYcoordinate() + yDir) &&
                    isEmpty(piece.getXcoordinate() + xDir, piece.getYcoordinate() + yDir)) {

                if(piece.equals(p2Pieces[1])){
                    Log.e("Lego2" , ""+piece);
                    Log.e("Lego2" , "xdire = "+xDir +"yDir = "+yDir);
                }

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
                    if(piece.equals(p2Pieces[1])) {
                        Log.e("lego3", "True x = " + piece.getXcoordinate() + "y = " + piece.getYcoordinate());
                        Log.e("lego3", "xDire = " + xDir + "yDire = " + yDir);

                    }
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
            if(piece.equals(p2Pieces[1])) {
                Log.e("lego4", "True x = " + piece.getXcoordinate() + "y = " + piece.getYcoordinate());
                Log.e("lego4", "xDire = " + xDir + "yDire = " + yDir);
            }
            return false;
        }
    }

    /**
     * This is the movePiece method. It is simple to use and can
     * used on any piece in any of the 4 possible directions.
     * It checks for all invalid moves as well
     * @param piece the piece that is moved.
     * @param id if id = 1 then it's player one's turn
     *           if id = 2 then its player two's turn
     * @param xDir xDir can only equal one and negative one. Any other number will return false
     *             If the move is valid then the piece's x-coordinate is set as
     *             x-coordinate + xdir
     * @param yDir yDir can only equal one and negative one. Any other number will return false
     *             If the move is valid then the piece's y-coordinate is set as
     *             y-coordinate + ydir
     * @return true if the move is legal and false otherwise
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
                    setPlayerTurn(1);
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

    }

    /**determines if a piece is trying to move farther than it can
     * @param xDir the direction as well as the distance of the x loaction it wants to move to
     * @param yDir the direction as well as the distance of the y loaction it wants to move to
     */
    public boolean inRange(int xDir,int yDir){
        if((xDir == 1 || xDir == -1) && (yDir == 1 || yDir == -1)){
            return true;
        }
        else{
            return false;
        }

    }

    /**This sets PieceSelectedPiece and PieceSelectedBoolean. It sets PieceSelectedPiece to what ever is in the
     * Xcord and YCord unless xCord and yCord is equal to -1. then it sets pieceSelectedPiece to null and pieceSelectedBoolean to false;
     * @param xCord: xlocation  of the piece.
     * @param yCord: ylocation  of the piece.
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

//        else if(xCord == -1 && yCord ==-1){
//            this.pieceSelectedPiece = null;
//            this.pieceSelectedBoolean = false;
//        }
    }


    /**This does what the above method does if the above method is passed in -1 and -1. I might delete it later
     */
    public void setPieceSelectedPieceAndPieceSelectedBoolean(){
        pieceSelectedPiece = null;
        pieceSelectedBoolean = false;
    }

    /**Moves a piece. It does not error checking. It goes through a lot of error checking before being called.
     * We may have to revise this later
     * @param xDire the direction as well as the distance of the x loaction it wants to move to
     * @param yDire the direction as well as the distance of the y loaction it wants to move to
     */
    public void move(int xDire,int yDire){

        this.pieceSelectedPiece.setCoordinates(pieceSelectedPiece.getXcoordinate()+xDire,pieceSelectedPiece.getYcoordinate()+yDire);

        ////will turn to player 1's pieces king if the piece reaches the other side of the board
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

        setPieceSelectedPieceAndPieceSelectedBoolean();
        //it does not matter what parameter goes in setPlayerTurn. See the setPlayerTurn(1) for a description on why that is
        setPlayerTurn(1);

    }


    /**
     *
     * @param xLocation xLocation of piece it is trying to capture
     * @param yLocation yLocation of piece it is trying to capture
     * @param nVX xLocation of piece that does the capturing.nV stands for not victim
     * @param nVY yLocation of piece that does the capturing.nV stands for not victim
     * @return
     */
    public boolean CaptureEnemyPiece(int xLocation,int yLocation,int nVX,int nVY){
        if(pieceSelectedBoolean == false){
            return false;
        }
        boolean returnValue = false;//gets returned if conditions are not valid
        int xDist = xLocation - nVX;
        int yDist = yLocation - nVY;

        Log.e("Coordinates","nVY = " +nVY );
        if(playerTurn == 0 && yDist < 0  && !pieceSelectedPiece.getKing()){
            return false;
        }

        if(playerTurn == 1 && yDist >0 && !pieceSelectedPiece.getKing()){
            return false;
        }
        Log.e("hello","hello");
        //if player one's turn
        if(playerTurn == 0) {
            //runs through all of player 2's pieces to see if they match the xLocation and yLocation
            for (CheckersPiece piece : p2Pieces) {
                //checks if piece matches the xLocation and yLocation
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {

                    //how far away the enemy piece is
                    //int xDist = xLocation - nVX;
                    //int yDist = yLocation - nVY;



                    //if distance is in range of the piece
                    if(inRange(xDist,yDist) && inBounds(nVX + xDist*2,nVY+yDist*2)){
                        //final location of the piece that does the capturing
                        int xFinal = nVX + xDist*2;
                        int yFinal = nVY + yDist*2;

                        //checks if spot ahead of the victim is empty
                        if(isEmpty(xFinal,yFinal)){
                            //if all conditions are met it captures the piece
                            piece.setAlive(false);
                            if( pieceSelectedPiece != null) {
                                pieceSelectedPiece.setCoordinates(xFinal, yFinal);
                            }
                            p2NumPieces = p2NumPieces-1;
                            if(playerTurn == 0){
                                //playerTurn = 1;
                                if(pieceSelectedPiece.getYcoordinate() == 7){
                                    pieceSelectedPiece.setKing(true);
                                }
                            }

//                            if(checkIfCanCaptureEnemyPiece(xFinal-1,yFinal-1,xFinal,yFinal)){
//                                CaptureEnemyPiece(xFinal-1,yFinal-1,xFinal,yFinal);
//                            }
//
//                            if(checkIfCanCaptureEnemyPiece(xFinal+1,yFinal-1,xFinal,yFinal)){
//                                CaptureEnemyPiece(xFinal+1,yFinal-1,xFinal,yFinal);
//                            }
//
//                            if(checkIfCanCaptureEnemyPiece(xFinal+1,yFinal+1,xFinal,yFinal)){
//                                CaptureEnemyPiece(xFinal+1,yFinal+1,xFinal,yFinal);
//                            }
//
//                            if(checkIfCanCaptureEnemyPiece(xFinal-1,yFinal+1,xFinal,yFinal)){
//                                CaptureEnemyPiece(xFinal-1,yFinal+1,xFinal,yFinal);
//                            }



                            setPlayerTurn(1);
                            setPieceSelectedPieceAndPieceSelectedBoolean();

                            return true;
                        }
                    }

                }

            }
            return false;
        }

        //if player two's turn
        //this code is the same as before except it runs though p1's pieces
        else if(playerTurn == 1 ){
            for (CheckersPiece piece : p1Pieces) {
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {
                    //int xDist = xLocation - nVX;
                    //int yDist = yLocation - nVY;
                    if(inRange(xDist,yDist) && inBounds(nVX + xDist*2,nVY + yDist*2)){
                        int xFinal = nVX + xDist*2;
                        int yFinal = nVY + yDist*2;
                        if(isEmpty(xFinal,yFinal)){

                            piece.setAlive(false);
                            pieceSelectedPiece.setCoordinates(xFinal,yFinal);


                            p1NumPieces = p1NumPieces-1;
                            if(playerTurn == 1){
                                //playerTurn = 1;
                                if(pieceSelectedPiece.getYcoordinate() == 0){
                                    pieceSelectedPiece.setKing(true);
                                }
                            }


//                            if(checkIfCanCaptureEnemyPiece(xFinal+1,yFinal+1,xFinal,yFinal)){
//                                CaptureEnemyPiece(xFinal+1,yFinal+1,xFinal,yFinal);
//                            }
//
//                            if(checkIfCanCaptureEnemyPiece(xFinal-1,yFinal+1,xFinal,yFinal)){
//                                CaptureEnemyPiece(xFinal-1,yFinal+1,xFinal,yFinal);
//                            }
//
//                            if(checkIfCanCaptureEnemyPiece(xFinal-1,yFinal-1,xFinal,yFinal)){
//                                CaptureEnemyPiece(xFinal-1,yFinal-1,xFinal,yFinal);
//                            }
//
//                            if(checkIfCanCaptureEnemyPiece(xFinal+1,yFinal-1,xFinal,yFinal)){
//                                CaptureEnemyPiece(xFinal+1,yFinal-1,xFinal,yFinal);
//                            }

                            setPlayerTurn(0);
                            setPieceSelectedPieceAndPieceSelectedBoolean();


                            return true;


                        }
                    }

                }
            }
            return false;
        }


        else {
            return false;
        }


    }

    public boolean checkIfCanCaptureEnemyPiece(int xLocation,int yLocation,int nVX,int nVY){
        boolean returnValue = false;//gets returned if conditions are not valid

        //if player one's turn
        if(playerTurn == 0) {
            //runs through all of player 2's pieces to see if they match the xLocation and yLocation
            for (CheckersPiece piece : p2Pieces) {
                //checks if piece matches the xLocation and yLocation
                Log.e("xuix2","" + pieceSelectedPiece);
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {
                    Log.e("xuix2","" + pieceSelectedPiece);
                    //how far away the enemy piece is
                    int xDist = xLocation - nVX;
                    int yDist = yLocation - nVY;
                    //Log.e("calculator","xLocation ");

                    //if distance is in range of the piece
                    if(inRange(xDist,yDist) && inBounds(nVX + xDist*2,yDist*2)){
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
        if(playerTurn == 1 ){
            for (CheckersPiece piece : p1Pieces) {
                if (piece.getXcoordinate() == xLocation && piece.getYcoordinate() == yLocation
                        && piece.getAlive()) {
                    int xDist = xLocation - nVX;
                    int yDist = yLocation - nVY;
                    Log.e("xuix","" + pieceSelectedPiece);
                    if(inRange(xDist,yDist) && inBounds(nVX + xDist*2,nVY + yDist*2)){
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


    }



}