/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Game state test - tests the getters and setters in the game state as well as
 * if the selection is in bounds, in range, etc.
 *
 * CS 301
 * @version 04/30/2021
 *
 * No outside sources needed
 */

package com.example.checkers.CheckersGame.infoMessage;

import org.junit.Test;

import javax.annotation.RegEx;

import static org.junit.Assert.*;

public class CheckersGameStateTest {

    // Cian
    @Test
    public void getP1NumPieces() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(12, state.getP1NumPieces());
    }

    // Cian
    @Test
    public void getP2NumPieces() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(12, state.getP2NumPieces());
    }

    // Cian
    @Test
    public void getPlayerTurn() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(0, state.getPlayerTurn());
    }

    // Cian
    @Test
    public void setP1NumPieces() {
        CheckersGameState state = new CheckersGameState();
        state.setP1NumPieces(11);
        assertEquals("Unequal number of P1 pieces", 11, state.getP1NumPieces());
    }

    // Anand
    @Test
    public void setP2NumPieces() {
        CheckersGameState state = new CheckersGameState();
        state.setP2NumPieces(11);
        assertEquals("Unequal number of P2 pieces",11, state.getP2NumPieces());
    }

    // Anand
    @Test
    public void setPlayerTurn() {
        CheckersGameState gameState = new CheckersGameState();
        gameState.setPlayerTurn(1);
        assertEquals("Not the player's turn",1, gameState.getPlayerTurn());
    }

    // Anand
    @Test
    public void getMessage(){
        CheckersGameState gameState = new CheckersGameState();
        assertEquals("", gameState.getMessage());
    }

    // Anand
    @Test
    public void setMessage(){
        CheckersGameState gameState = new CheckersGameState();
        gameState.setMessage("Test successful.");
        assertEquals("Test successful.", gameState.getMessage());
    }

    // Caitlin
    @Test
    public void isEmpty() {
        CheckersGameState state = new CheckersGameState();
        assertEquals("Tile not empty",true,
                                state.isEmpty(4,4));
    }

    // Caitlin
    @Test
    public void inBounds() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(true, state.inBounds(4,3));
    }

    // Caitlin
    @Test
    public void hasEnemyPieces() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(false, state.hasEnemyPieces(4,4));
    }

    // Caitlin
    @Test
    public void canMove() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(true, state.canMove(state.p1Pieces[11],1,1,0));
    }

    // Aashish
    @Test
    public void inRange() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(true, state.inRange(1,1));
    }

    // Aashish
    @Test
    public void isPieceSelectedBoolean() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(false,state.isPieceSelectedBoolean());
    }

    // Aashish
    @Test
    public void getPieceSelectedPiece() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(null,state.getPieceSelectedPiece());
    }

    // Aashish
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