package com.example.checkers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class CheckersTest {
    @Test
    public void test_CopyConstructorState_Empty(){
        CheckersGameState checkersState = new CheckersGameState();
        CheckersGameState copyState = new CheckersGameState(checkersState);
        assertTrue("Copy Constructor did not produce equal states,",checkersState.equals(copyState));
    }
}
