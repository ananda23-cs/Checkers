package com.example.checkers;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckersGameStateTest {

    @Test
    public void getP1NumPieces() {
        CheckersGameState checkersGameState = new CheckersGameState();
        int p1NumPieces = checkersGameState.getP1NumPieces();
        assertEquals(p1NumPieces, 12);
    }

    @Test
    public void getP2NumPieces() {
        CheckersGameState checkersGameState = new CheckersGameState();
        int p2NumPieces = checkersGameState.getP2NumPieces();
        assertEquals(p2NumPieces, 12);
    }

    @Test
    public void setP1NumPieces() {
        CheckersGameState checkersGameState = new CheckersGameState();
        checkersGameState.setP1NumPieces(11);
        assertEquals(checkersGameState.getP1NumPieces(), 11);
    }

    @Test
    public void setP2NumPieces() {
        CheckersGameState checkersGameState = new CheckersGameState();
        checkersGameState.setP1NumPieces(10);
        assertEquals(checkersGameState.getP1NumPieces(), 10);
    }

    @Test
    public void getPlayerTurn() {
        CheckersGameState checkersGameState = new CheckersGameState();
        int playerTurn = checkersGameState.getPlayerTurn();
        assertEquals(playerTurn, 0);
    }

    @Test
    public void setPlayerTurn() {
        CheckersGameState checkersGameState = new CheckersGameState();
        checkersGameState.setPlayerTurn(1);
        assertEquals(checkersGameState.getPlayerTurn(), 1);
    }
}