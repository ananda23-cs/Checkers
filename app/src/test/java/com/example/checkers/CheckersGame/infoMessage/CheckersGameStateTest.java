package com.example.checkers.CheckersGame.infoMessage;

import org.junit.Test;

import javax.annotation.RegEx;

import static org.junit.Assert.*;

public class CheckersGameStateTest {

    @Test
    public void getP1NumPieces() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(12, state.getP1NumPieces());
    }

    @Test
    public void getP2NumPieces() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(12, state.getP2NumPieces());
    }

    @Test
    public void getPlayerTurn() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(0, state.getPlayerTurn());
    }

    @Test
    public void setP1NumPieces() {
        CheckersGameState state = new CheckersGameState();
        state.setP1NumPieces(11);
        assertEquals("Unequal number of P1 pieces", 11, state.getP1NumPieces());
    }

    @Test
    public void setP2NumPieces() {
        CheckersGameState state = new CheckersGameState();
        state.setP2NumPieces(11);
        assertEquals("Unequal number of P2 pieces",11, state.getP2NumPieces());
    }

    @Test
    public void setPlayerTurn() {
        CheckersGameState gameState = new CheckersGameState();
        gameState.setPlayerTurn(1);
        assertEquals("Not the player's turn",1, gameState.getPlayerTurn());
    }

    @Test
    public void getMessage(){
        CheckersGameState gameState = new CheckersGameState();
        assertEquals("", gameState.getMessage());
    }

    @Test
    public void setMessage(){
        CheckersGameState gameState = new CheckersGameState();
        gameState.setMessage("Test successful.");
        assertEquals("Test successful.", gameState.getMessage());
    }

    @Test
    public void isEmpty() {
        CheckersGameState state = new CheckersGameState();
        assertEquals("Tile not empty",true,
                                state.isEmpty(4,4));
    }

    @Test
    public void inBounds() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(true, state.inBounds(4,3));
    }

    @Test
    public void hasEnemyPieces() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(false, state.hasEnemyPieces(4,4));
    }

    @Test
    public void canMove() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(true, state.canMove(state.p1Pieces[11],1,1,0));
    }

    @Test
    public void inRange() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(true, state.inRange(1,1));
    }

    @Test
    public void isPieceSelectedBoolean() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(false,state.isPieceSelectedBoolean());
    }

    @Test
    public void getPieceSelectedPiece() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(null,state.getPieceSelectedPiece());
    }

    @Test
    public void checkIfCanCaptureEnemyPiece(){
        CheckersGameState state = new CheckersGameState();
        state.setPieceSelectedPieceAndPieceSelectedBoolean(2,2);
        state.move(1,1);
        state.setPlayerTurn(1);
        state.setPieceSelectedPieceAndPieceSelectedBoolean(5,5);
        state.move(-1,-1);
        state.setPlayerTurn(0);
        assertTrue(state.checkIfCanCaptureEnemyPiece(4,4,3,3));
    }
}