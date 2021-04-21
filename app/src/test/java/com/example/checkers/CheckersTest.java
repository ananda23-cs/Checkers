package com.example.checkers;

import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.CheckersGame.infoMessage.CheckersPiece;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckersTest {

    @Test
    public void testMovePiece(){
        CheckersGameState checkersState = new CheckersGameState();
        checkersState.setPlayerTurn(0);
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(2,2);
        CheckersPiece piece = checkersState.getPieceSelectedPiece();
        assertTrue(checkersState.canMove(piece,1,1,0));
        checkersState.move(1,1);
    }

    @Test
    public void testUndoPieceSelection(){
        CheckersGameState checkersGameState = new CheckersGameState();
        checkersGameState.setPieceSelectedPieceAndPieceSelectedBoolean(0,0);
        checkersGameState.setPieceSelectedPieceAndPieceSelectedBoolean();
        assertFalse(checkersGameState.isPieceSelectedBoolean());
        assertNull(checkersGameState.getPieceSelectedPiece());
    }

    @Test
    public void testCaptureEnemyPiece(){
        CheckersGameState state = new CheckersGameState();
        CheckersPiece piece1 = state.p1Pieces[10];
        CheckersPiece piece2 = state.p2Pieces[2];
        piece1.setCoordinates(3,3);
        piece2.setCoordinates(4,4);
        state.setPlayerTurn(0);
        state.setPieceSelectedPieceAndPieceSelectedBoolean(3,3);
        state.CaptureEnemyPiece(piece2.getXcoordinate(),piece2.getYcoordinate(),
                                piece1.getXcoordinate(),piece1.getYcoordinate());
        assertEquals(false, piece2.getAlive());
    }

    @Test
    public void test_CopyConstructorState_Empty(){
        CheckersGameState checkersState = new CheckersGameState();
        CheckersGameState copyState = new CheckersGameState(checkersState);
        assertTrue("Copy Constructor did not produce equal states,",checkersState.equals(copyState));
    }

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
        assertTrue("Copy Constructor did not produce equal states,",checkersState.equals(copyState));
    }

    @Test
    public void test_EqualsState_Empty(){
        CheckersGameState checkersGameState = new CheckersGameState();
        CheckersGameState otherState = new CheckersGameState();
        assertTrue("The game states are not equal.", checkersGameState.equals(otherState));
    }

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
