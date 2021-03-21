package com.example.checkers;

public class CheckersPiece {
    private int xCord;//not needed
    private int yCord;//not needed
    private boolean isKing;
    private boolean isAlive;

    public CheckersPiece(int xCord, int yCord){
        this.xCord = xCord;
        this.yCord = yCord;
        this.isAlive = true;
        this.isKing = false;
    }

    public CheckersPiece(CheckersPiece p){
        this.xCord = p.xCord;
        this.yCord = p.yCord;
        this.isAlive = p.isAlive;
        this.isKing = p.isKing;
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

    public boolean getAlive(){
        return this.isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getKing() { return isKing; }

    public void setKing(boolean king) {
        this.isKing = king;
    }
}
