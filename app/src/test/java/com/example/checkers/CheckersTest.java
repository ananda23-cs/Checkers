package com.example.checkers;

import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckersTest {
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
