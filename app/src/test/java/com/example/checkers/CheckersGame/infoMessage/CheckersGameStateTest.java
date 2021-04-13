package com.example.checkers.CheckersGame.infoMessage;

import org.junit.Test;

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
        assertEquals("Not the player's turn",1,gameState.getPlayerTurn());
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void inBounds() {
    }

    @Test
    public void hasEnemyPieces() {
    }

    @Test
    public void canMove() {
    }

    @Test
    public void inRange() {
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
}