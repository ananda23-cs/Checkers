/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * CheckersFrameworkTests -- Tests for main activity in checkers game
 *
 * CS 301
 * @version 4/30/2021
 *
 * No outside sources needed for this class
 */
package com.example.checkers;

import com.example.checkers.CheckersGame.CheckersLocalGame;
import com.example.checkers.CheckersGame.CheckersMainActivity;
import com.example.checkers.CheckersGame.infoMessage.CheckersGameState;
import com.example.checkers.game.GameFramework.gameConfiguration.GameConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CheckersFrameworkTests {

    /**
     * This verifies that the default config has the same port number as the main activity
     * It also verifies that test_gameConfig returns true
     */
    @Test
    public void test_createDefaultConfig(){
        CheckersMainActivity checkersActivity = new CheckersMainActivity();
        GameConfig gameConfig = checkersActivity.createDefaultConfig();
        assertEquals("Port Num Mismatch: GameConfig=" +gameConfig.getPortNum() +
                " != Activity=" + CheckersMainActivity.PORT_NUMBER, gameConfig.getPortNum(),
                CheckersMainActivity.PORT_NUMBER);
    }

    /**
     * Verifies a local Game can be created
     * @author Caitlin
     */
    @Test
    public void test_createLocalGame(){
        CheckersMainActivity checkersActivity = new CheckersMainActivity();
        CheckersLocalGame localGame =
                (CheckersLocalGame) checkersActivity.createLocalGame(new CheckersGameState());
        assertNotNull("GameState was null", localGame.getGameState());
    }

    /**
     * Verifies setGameOver still sets the Game as over
     * @author Caitlin
     */
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
