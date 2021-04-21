package com.example.checkers;

import com.example.checkers.CheckersGameFramework.CheckersInfoMessage.CheckersGameState;

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
    public void test_EqualsState_Empty(){
        CheckersGameState checkersGameState = new CheckersGameState();
        CheckersGameState otherState = new CheckersGameState();
        assertTrue("The game states are not equal.", checkersGameState.equals(otherState));
    }
}
