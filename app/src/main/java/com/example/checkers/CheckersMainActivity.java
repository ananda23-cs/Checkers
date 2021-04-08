/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Main activity class - load the startup screen for the user and an option for
 * the user to pick their opponent
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkers.game.GameFramework.GameMainActivity;
import com.example.checkers.game.GameFramework.LocalGame;
import com.example.checkers.game.GameFramework.gameConfiguration.GameConfig;
import com.example.checkers.game.GameFramework.gameConfiguration.GamePlayerType;
import com.example.checkers.game.GameFramework.infoMessage.GameState;
import com.example.checkers.game.GameFramework.players.GamePlayer;
import com.example.checkers.game.GameFramework.utilities.Saving;

import java.util.ArrayList;

public class CheckersMainActivity extends GameMainActivity {
    private static final int PORT_NUMBER = 2278;

    // we need to override the abstract methods from GameMainActivity
    // I can see what that looks like in the TTT main activity

    /**
     * sets up a default of one human and one computer player
     *
     * @return
     */
    @Override
    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Pig has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new CheckersHumanPlayer(name);
            }
        });
        // checkpoint 5
        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new CheckersComputerPlayer1(name);
            }
        });
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new CheckersComputerPlayer2(name);
            }
        });

        // Create a game configuration class for Pig:
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2, "Checkers", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }//createDefaultConfig

    /**
     * @param gameState The desired gameState to start at or null for new game
     * @return
     */
    public LocalGame createLocalGame(GameState gameState) {
        if(gameState == null) return new CheckersLocalGame();
        return new CheckersLocalGame((CheckersGameState) gameState);
    }

    /**
     * saveGame, adds this games prepend to the filename
     *
     * @param Checkers
     * 				Desired save name
     * @return String representation of the save
     */
    @Override
    public GameState saveGame (String Checkers){
        return super.saveGame(getGameString(Checkers));
    }

    /**
     * loadGame, adds this games prepend to the desire file to open and creates the game specific state
     * @param Checkers
     * 				The file to open
     * @return The loaded GameState
     */
    @Override
    public GameState loadGame (String Checkers){
        String appName = getGameString(Checkers);
        super.loadGame(appName);
        return (GameState) new CheckersGameState((CheckersGameState) Saving.readFromFile(appName,
                this.getApplicationContext()));
    }
}