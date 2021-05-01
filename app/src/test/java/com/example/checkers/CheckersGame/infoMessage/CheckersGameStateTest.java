/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Game state test - tests the getters and setters in the game state as well as
 * if the selection is in bounds, in range, etc.
 *
 * CS 301
 * @version 04/30/2021
 *
 * No outside sources needed for this class
 */

package com.example.checkers.CheckersGame.infoMessage;

import org.junit.Test;

import javax.annotation.RegEx;

import static org.junit.Assert.*;

public class CheckersGameStateTest {

    /**
     * Tests that player 1 starts with 12 pieces
     * @author Cian
     */
    @Test
    public void getP1NumPieces() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(12, state.getP1NumPieces());
    }

    /**
     * Tests that player 2 starts with 12 pieces
     * @author Cian
     */
    @Test
    public void getP2NumPieces() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(12, state.getP2NumPieces());
    }

    /**
     * Tests the players turn number
     * @author Cian
     */
    @Test
    public void getPlayerTurn() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(0, state.getPlayerTurn());
    }

    /**
     * Tests the number of pieces for player 1
     * @author Cian
     */
    @Test
    public void setP1NumPieces() {
        CheckersGameState state = new CheckersGameState();
        state.setP1NumPieces(11);
        assertEquals("Unequal number of P1 pieces", 11, state.getP1NumPieces());
    }

    /**
     * Tests the number of pieces for player 2
     * @author Anand
     */
    @Test
    public void setP2NumPieces() {
        CheckersGameState state = new CheckersGameState();
        state.setP2NumPieces(11);
        assertEquals("Unequal number of P2 pieces",11, state.getP2NumPieces());
    }

    /**
     * Tests the if its not the players turn number
     * @author Anand
     */
    @Test
    public void setPlayerTurn() {
        CheckersGameState gameState = new CheckersGameState();
        gameState.setPlayerTurn(1);
        assertEquals("Not the player's turn",1, gameState.getPlayerTurn());
    }


    /**
     * Tests the if the game state message matches
     * @author Anand
     */
    @Test
    public void setMessage(){
        CheckersGameState gameState = new CheckersGameState();
        gameState.setMessage("Test successful.");
        assertEquals("Test successful.", gameState.getMessage());
    }

    /**
     * Tests if the tile is empty
     * @author Caitlin
     */
    @Test
    public void isEmpty() {
        CheckersGameState state = new CheckersGameState();
        assertEquals("Tile not empty",true,
                                state.isEmpty(4,4));
    }

    /**
     * Tests if the tile is in bounds
     * @author Caitlin
     */
    @Test
    public void inBounds() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(true, state.inBounds(4,3));
    }

    /**
     * Tests if the piece is the users piece or an enemies piece
     * @author Caitlin
     */
    @Test
    public void hasEnemyPieces() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(false, state.hasEnemyPieces(4,4));
    }

    /**
     * Tests if the user can move to a tile
     * @author Caitlin
     */
    @Test
    public void canMove() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(true, state.canMove(state.p1Pieces[11],1,1,0));
    }

    /**
     * Tests if the piece is in range of the players move
     * @author Aashish
     */
    @Test
    public void inRange() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(true, state.inRange(1,1));
    }

    /**
     * Tests if the piece is selected
     * @author Aashish
     */
    @Test
    public void isPieceSelectedBoolean() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(false,state.isPieceSelectedBoolean());
    }

    /**
     * Tests if the piece can be selected
     * @author Aashish
     */
    @Test
    public void getPieceSelectedPiece() {
        CheckersGameState state = new CheckersGameState();
        assertEquals(null,state.getPieceSelectedPiece());
    }
}