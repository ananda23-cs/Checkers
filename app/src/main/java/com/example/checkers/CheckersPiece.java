/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Piece class - initializes checker pieces coordinates, if its alive, and if its a king
 *
 * CS301A
 * @version 03/30/2021
 */

package com.example.checkers;

public class CheckersPiece {
    private int xCord;
    private int yCord;
    private boolean isKing;
    private boolean isAlive;
    //variable for player num or owner;
    private int owner;// this was not here before

    public CheckersPiece(int xCord, int yCord, int owner){
        this.xCord = xCord;
        this.yCord = yCord;
        this.isAlive = true;
        this.isKing = false;
        this.owner = owner;
    }

    public CheckersPiece(CheckersPiece p){
        this.xCord = p.xCord;
        this.yCord = p.yCord;
        this.isAlive = p.isAlive;
        this.isKing = p.isKing;
        this.owner = p.owner;
    }

    public String toString(){
        String returnValue = "";
        returnValue = returnValue+"Xcord = " + this.xCord;
        returnValue = returnValue + "\nYcord = " + this.yCord;
        returnValue = returnValue + "\nIs Alive = " + this.isAlive;
        returnValue = returnValue + "\nIs King = " + this.isKing;
        return returnValue;
    }

    public boolean getAlive(){
        return this.isAlive;
    }
    public void setCoordinates(int xCord,int yCord){
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public int getXcoordinate(){
        return this.xCord;
    }

    public int getYcoordinate(){
        return this.yCord;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getKing() { return isKing; }

    public void setKing(boolean king) {
        this.isKing = king;
    }
}