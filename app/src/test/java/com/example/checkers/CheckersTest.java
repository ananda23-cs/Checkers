/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * CheckersTest -- Unit tests for the Checkers game
 *
 * CS 301
 * @version 04/30/2021
 */

package com.example.checkers;

import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckersTest {

    /**
     * Tests if a piece can capture multiple pieces under the right conditions
     * @author Anand
     */
    @Test
    public void testCaptureMultiplePieces(){
        CheckersGameState checkersState = new CheckersGameState();

        /*this method is to clear the board so it is easier to test out methods
          because pieces have more space to move*/
        for(int i =0; i<12;i++){
            checkersState.p1Pieces[i].setAlive(false);
            checkersState.p1Pieces[i].setAlive(false);
        }

        checkersState.p1Pieces[0].setAlive(true);
        checkersState.p2Pieces[0].setAlive(true);
        checkersState.p2Pieces[1].setAlive(true);

        checkersState.p1Pieces[0].setCoordinates(0,0);
        checkersState.p2Pieces[0].setCoordinates(1,1);
        checkersState.p2Pieces[1].setCoordinates(3,3);

        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(0,0);
        checkersState.CaptureEnemyPiece(1,1,0,0);

        assertFalse("This piece is dead", checkersState.p2Pieces[0].getAlive());
        assertFalse("This piece is dead", checkersState.p2Pieces[1].getAlive());

        for(int i =0; i<12;i++){
            checkersState.p1Pieces[i].setAlive(false);
            checkersState.p1Pieces[i].setAlive(false);
        }

        checkersState.p1Pieces[0].setAlive(true);
        checkersState.p2Pieces[0].setAlive(true);
        checkersState.p2Pieces[1].setAlive(true);

        checkersState.p1Pieces[0].setCoordinates(0,0);
        checkersState.p2Pieces[0].setCoordinates(1,1);
        checkersState.p2Pieces[1].setCoordinates(1,3);

        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(0,0);
        checkersState.CaptureEnemyPiece(1,1,0,0);

        assertFalse("This piece is dead", checkersState.p2Pieces[0].getAlive());
        assertFalse("This piece is dead", checkersState.p2Pieces[1].getAlive());
    }

    /**
     * tests the movement of a regular piece
     * @author Anand
     */
    @Test
    public void testMovePiece(){
        CheckersGameState checkersState = new CheckersGameState();

        //valid move from player 1
        checkersState.setPlayerTurn(0);
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(2,2);
        CheckersPiece piece = checkersState.getPieceSelectedPiece();
        assertTrue("Invalid move.", checkersState.canMove(piece,1,1,0));
        checkersState.move(1,1);
        assertEquals("Failed move.", piece, checkersState.findPiece(3,3));

        //valid move from player 2
        checkersState.setPlayerTurn(1);
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(3,5);
        CheckersPiece piece2 = checkersState.getPieceSelectedPiece();
        assertTrue(checkersState.canMove(piece2,1,-1, 1));
        checkersState.move(1,-1);
        assertEquals(piece2, checkersState.findPiece(4,4));

        //illegal move made
        checkersState.setPlayerTurn(0);
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(0,0);
        CheckersPiece piece3 = checkersState.getPieceSelectedPiece();
        assertFalse(checkersState.canMove(piece3,1,1,0));
    }

    /**
     * tests the backward movement of a king piece
     * @author Aashish
     */
    @Test
    public void testMoveKingPiece(){
        CheckersGameState checkersState = new CheckersGameState();

        //valid move from player 1
        checkersState.setPlayerTurn(0);
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(2,2);
        CheckersPiece kingPiece = checkersState.getPieceSelectedPiece();
        kingPiece.setCoordinates(3,3);
        kingPiece.setKing(true);
        assertTrue(checkersState.canMove(kingPiece, -1, -1, 0));
        checkersState.move(-1,-1);
        assertEquals(kingPiece, checkersState.findPiece(2,2));

        //valid move from player 2
        checkersState.setPlayerTurn(1);
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(5,5);
        CheckersPiece kingPiece2 = checkersState.getPieceSelectedPiece();
        kingPiece2.setCoordinates(4,4);
        kingPiece2.setKing(true);
        assertTrue(checkersState.canMove(kingPiece2,1,1,1));
        checkersState.move(1,1);
        assertEquals(kingPiece2, checkersState.findPiece(5,5));

        //illegal move
        checkersState.setPlayerTurn(0);
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(1,1);
        CheckersPiece kingPiece3 = checkersState.getPieceSelectedPiece();
        kingPiece3.setKing(true);
        assertFalse(checkersState.canMove(kingPiece3,-1,-1,0));
    }

    /**
     * should de-select a piece from the board
     * @author Cian
     */
    @Test
    public void testUndoPieceSelection(){
        CheckersGameState checkersGameState = new CheckersGameState();
        checkersGameState.setPieceSelectedPieceAndPieceSelectedBoolean(0,0);
        checkersGameState.setPieceSelectedPieceAndPieceSelectedBoolean();
        assertFalse(checkersGameState.isPieceSelectedBoolean());
        assertNull(checkersGameState.getPieceSelectedPiece());
    }

    /**
     * should capture an enemy piece, if legal
     * @author Aashish
     */
    @Test
    public void testCaptureEnemyPiece(){
        CheckersGameState state = new CheckersGameState();
        CheckersPiece piece1 = state.p1Pieces[10];
        CheckersPiece piece2 = state.p2Pieces[2];
        piece1.setCoordinates(3,3);
        piece2.setCoordinates(4,4);

        //valid move from player 1
        state.setPlayerTurn(0);
        state.setPieceSelectedPieceAndPieceSelectedBoolean(3,3);
        state.CaptureEnemyPiece(piece2.getXcoordinate(),piece2.getYcoordinate(),
                                piece1.getXcoordinate(),piece1.getYcoordinate());
        state.setP2NumPieces(state.getP2NumPieces()-1);
        assertFalse(piece2.getAlive());
        assertEquals(piece1, state.findPiece(5, 5));

        //valid move from player 2
        state.setPlayerTurn(1);
        CheckersPiece piece3 = state.findPiece(5,5);
        state.setPieceSelectedPieceAndPieceSelectedBoolean(6,6);
        CheckersPiece piece4 = state.findPiece(6,6);
        state.CaptureEnemyPiece(piece3.getXcoordinate(), piece3.getYcoordinate(),
                                piece4.getXcoordinate(), piece4.getYcoordinate());
        state.setP1NumPieces(state.getP1NumPieces()-1);
        assertFalse(piece3.getAlive());
        assertEquals(4, piece4.getXcoordinate());
        assertEquals(4, piece4.getYcoordinate());

        //illegal capture
        state.setPieceSelectedPieceAndPieceSelectedBoolean(4,4);
        state.move(1,-1);
        state.setPieceSelectedPieceAndPieceSelectedBoolean(5,3);
        CheckersPiece piece5 = state.findPiece(5,3);
        CheckersPiece piece6 = state.findPiece(6,2);
        assertFalse(state.CaptureEnemyPiece(piece5.getXcoordinate(), piece5.getYcoordinate(),
                                            piece6.getXcoordinate(), piece6.getYcoordinate()));
    }

    /**
     * Tests if user can capture multiple enemy pieces
     * @author Aashish
     */
    @Test
    public void testCaptureMultipleEnemyPiece(){
        CheckersGameState state = new CheckersGameState();
        CheckersPiece piece1 = state.p1Pieces[10];
        CheckersPiece piece2 = state.p2Pieces[1];
        CheckersPiece piece3 = state.p2Pieces[2];
        CheckersPiece piece4 = state.p2Pieces[7];
        piece2.setCoordinates(3,3);
        piece4.setCoordinates(7,3);
        state.setPlayerTurn(0);
        state.setPieceSelectedPieceAndPieceSelectedBoolean(2,2);
        state.CaptureEnemyPiece(piece2.getXcoordinate(),piece2.getYcoordinate(),
                                piece1.getXcoordinate(),piece1.getYcoordinate());
        assertFalse(piece2.getAlive());
        assertFalse(piece3.getAlive());
        assertEquals(piece1, state.findPiece(6,6));
    }

    /**
     * Tests if user can capture multiple enemy pieces as a king
     * @author Aashish
     */
    @Test
    public void testCaptureMultipleEnemyPieceAsKing(){
        CheckersGameState state = new CheckersGameState();
        CheckersPiece piece1 = state.findPiece(5,5);
        CheckersPiece piece2 = state.findPiece(1,1);
        CheckersPiece piece3 = state.findPiece(2,2);
        piece2.setCoordinates(4,4);
        piece1.setKing(true);
        state.setPlayerTurn(1);
        state.setPieceSelectedPieceAndPieceSelectedBoolean(5,5);
        state.CaptureEnemyPiece(piece2.getXcoordinate(),piece2.getYcoordinate(),
                                piece1.getXcoordinate(),piece1.getYcoordinate());
        assertFalse(piece2.getAlive());
        assertFalse(piece3.getAlive());
        assertEquals(piece1,state.findPiece(1,1));
    }

    /**
     * Tests capturing an enemies piece as a king
     * @author Aashish
     */
    @Test
    public void testCaptureEnemyPieceAsKing(){
        CheckersGameState state = new CheckersGameState();
        CheckersPiece piece1 = state.p1Pieces[10];
        CheckersPiece piece2 = state.p2Pieces[2];
        piece1.setCoordinates(4,4);
        piece2.setCoordinates(3,3);
        piece1.setKing(true);
        //tests a valid move
        state.setPlayerTurn(0);
        state.setPieceSelectedPieceAndPieceSelectedBoolean(4,4);
        state.CaptureEnemyPiece(piece2.getXcoordinate(),piece2.getYcoordinate(),
                                piece1.getXcoordinate(),piece1.getYcoordinate());
        state.setP2NumPieces(state.getP2NumPieces()-1);
        assertFalse(piece2.getAlive());
        assertEquals(piece1, state.findPiece(2,2));

        //illegal move
        CheckersPiece kingPiece = state.findPiece(2,2);
        kingPiece.setCoordinates(4,4);
        state.p2Pieces[1].setCoordinates(5,3);
        CheckersPiece newPiece = state.findPiece(5,3);
        state.setPieceSelectedPieceAndPieceSelectedBoolean(4,4);
        assertFalse(state.CaptureEnemyPiece(newPiece.getXcoordinate(), newPiece.getYcoordinate(),
                                        kingPiece.getXcoordinate(), kingPiece.getYcoordinate()));
        state.setPieceSelectedPieceAndPieceSelectedBoolean();

        //valid move from player 2
        state.setPlayerTurn(1);
        newPiece.setKing(true);
        state.setPieceSelectedPieceAndPieceSelectedBoolean(5,3);
        state.CaptureEnemyPiece(kingPiece.getXcoordinate(), kingPiece.getYcoordinate(),
                newPiece.getXcoordinate(), newPiece.getYcoordinate());
        assertFalse(kingPiece.getAlive());
        assertEquals(3, newPiece.getXcoordinate());
        assertEquals(5, newPiece.getYcoordinate());
    }

    /**
     * Tests the copy constructor of the game state and if its empty
     * @author Caitlin
     */
    @Test
    public void test_CopyConstructorState_Empty(){
        CheckersGameState checkersState = new CheckersGameState();
        CheckersGameState copyState = new CheckersGameState(checkersState);
        assertTrue("Copy Constructor did not produce equal states,",
                checkersState.equals(copyState));
    }

    /**
     * Tests the copy constructor of the game state and if game is in progress
     * @author Cian
     */
    @Test
    public void test_CopyConstructorState_InProgress(){
        CheckersGameState checkersState = new CheckersGameState();
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(2,2);
        if(checkersState.canMove(checkersState.getPieceSelectedPiece(),1,1,
                                checkersState.getPlayerTurn())) {
            checkersState.move(1, 1);
            checkersState.setPlayerTurn(1);
        }
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(5,5);
        if(checkersState.canMove(checkersState.getPieceSelectedPiece(),1,-1,
                checkersState.getPlayerTurn())) {
            checkersState.move(1, -1);
            checkersState.setPlayerTurn(0);
        }
        CheckersGameState copyState = new CheckersGameState(checkersState);
        assertTrue("Copy Constructor did not produce equal states,",
                checkersState.equals(copyState));
    }

    /**
     * Tests if the game state constructor is equivalent to its copy constructor
     * when game is empty
     * @author Caitlin
     */
    @Test
    public void test_EqualsState_Empty(){
        CheckersGameState checkersGameState = new CheckersGameState();
        CheckersGameState otherState = new CheckersGameState();
        assertTrue("The game states are not equal.",
                checkersGameState.equals(otherState));
    }

    /**
     * Tests if the game state constructor is equivalent to its copy constructor
     * when game is in progress
     * @author Aashish
     */
    @Test
    public void test_EqualsState_InProgress(){
        CheckersGameState checkersState = new CheckersGameState();
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(2,2);
        if(checkersState.canMove(checkersState.getPieceSelectedPiece(),1,1,
                checkersState.getPlayerTurn())) {
            checkersState.move(1, 1);
            checkersState.setPlayerTurn(1);
        }
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(5,5);
        if(checkersState.canMove(checkersState.getPieceSelectedPiece(),1,-1,
                checkersState.getPlayerTurn())) {
            checkersState.move(1, -1);
            checkersState.setPlayerTurn(0);
        }
        CheckersGameState otherState = new CheckersGameState();
        otherState.setPieceSelectedPieceAndPieceSelectedBoolean(2,2);
        if(otherState.canMove(otherState.getPieceSelectedPiece(),1,1,
                otherState.getPlayerTurn())) {
            otherState.move(1, 1);
            otherState.setPlayerTurn(1);
        }
        otherState.setPieceSelectedPieceAndPieceSelectedBoolean(5,5);
        if(otherState.canMove(otherState.getPieceSelectedPiece(),1,-1,
                otherState.getPlayerTurn())) {
            otherState.move(1, -1);
            otherState.setPlayerTurn(0);
        }
        assertTrue("The game states are not equal.", checkersState.equals(otherState));
    }

}
