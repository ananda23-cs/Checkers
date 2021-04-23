/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * CheckersFrameworkTests -- Tests for main activity in checkers game
 *
 * CS 301
 * @version 4/23/2021
 */
package com.example.checkers;

import com.example.checkers.CheckersGame.CheckersMainActivity;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.game.GameFramework.LocalGame;
import com.example.checkers.game.GameFramework.gameConfiguration.GameConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckersFrameworkTests {

    //This verifies the default config has the same port number as the activity
    //It also verifies the test_gameConfig returns true
    @Test
    public void test_createDefaultConfig(){
        CheckersMainActivity checkersActivity = new CheckersMainActivity();
        GameConfig gameConfig = checkersActivity.createDefaultConfig();
        assertEquals("Port Num Mismatch: GameConfig=" +gameConfig.getPortNum() +
                " != Activity=" + checkersActivity.PORT_NUMBER, gameConfig.getPortNum(), checkersActivity.PORT_NUMBER);
    }

    //Verifies a local Game can be created
    @Test
    public void test_createLocalGame(){
        CheckersMainActivity checkersActivity = new CheckersMainActivity();
        LocalGame localGame = checkersActivity.createLocalGame(new CheckersGameState());
        assertTrue("GameState was null", localGame.getGameState() != null);
    }

    // Verifies setGameOver still sets the Game as over
    @Test
    public void test_setGameOver(){
        CheckersMainActivity checkersActivity = new CheckersMainActivity();
        checkersActivity.setGameOver(true);
        assertTrue("setGameOver has been overwritten but isGameOver wasn't modified",
                checkersActivity.getGameOver());
        checkersActivity.setGameOver(false);
        assertFalse("setGameOver has been overwritten but isGameOver wasn't modified",
                checkersActivity.getGameOver());
    }
}
