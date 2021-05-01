/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Piece class - initializes checker pieces coordinates, if its alive, and if its a king
 *
 * CS301A
 * @version 04/30/2021
 *
 * No outside sources needed for this class.
 */

package com.example.checkers.CheckersGame.infoMessage;

public class CheckersPiece {
    private int xCord;
    private int yCord;
    private boolean isKing;
    private boolean isAlive;
    private int owner; // player num or owner

    /**
     * creates a new instance of a checker piece
     *
     * @param xCord
     *      x-coordinate of a piece
     * @param yCord
     *      y-coordinate of a piece
     * @param owner
     *      the player that owns the piece
     */
    public CheckersPiece(int xCord, int yCord, int owner) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.isAlive = true; // set all pieces to be alive, when starting game
        this.isKing = false; // set none of the pieces to be a king, when starting game
        this.owner = owner;
    } //CheckersPiece

    /**
     * deep copy constructor of the piece class
     * @param p
     *      the saved Checker piece
     */
    public CheckersPiece(CheckersPiece p){
        if(p == null){
            return;
        }
        this.xCord = p.xCord;
        this.yCord = p.yCord;
        this.isAlive = p.isAlive;
        this.isKing = p.isKing;
        this.owner = p.owner;
    } //CheckersPiece

    public String toString() {
        String returnValue = "";
        returnValue = returnValue+"Xcord = " + this.xCord;
        returnValue = returnValue + "\nYcord = " + this.yCord;
        returnValue = returnValue + "\nIs Alive = " + this.isAlive;
        returnValue = returnValue + "\nIs King = " + this.isKing;
        return returnValue;
    }

    /**
     * getter and setter methods for coordinates, king, and is alive
     *
     */
    public int getXcoordinate(){
        return this.xCord;
    }
    public int getYcoordinate(){
        return this.yCord;
    }
    public boolean getAlive(){
        return this.isAlive;
    }
    public boolean getKing() { return isKing; }
    public void setCoordinates(int xCord,int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    public void setKing(boolean king) {
        this.isKing = king;
    }

    public boolean equals(Object object){
        if(!(object instanceof CheckersPiece)) return false;
        CheckersPiece newPiece = (CheckersPiece) object;
        return this.isAlive == newPiece.isAlive &&
                this.isKing == newPiece.isKing &&
                this.owner == newPiece.owner &&
                this.xCord == newPiece.xCord &&
                this.yCord == newPiece.yCord;
    }
}