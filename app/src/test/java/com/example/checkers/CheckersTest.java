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
        }
        checkersState.setPieceSelectedPieceAndPieceSelectedBoolean(5,5);
        if(checkersState.canMove(checkersState.getPieceSelectedPiece(),1,-1,
                checkersState.getPlayerTurn())) {
            checkersState.move(1, -1);
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
}
