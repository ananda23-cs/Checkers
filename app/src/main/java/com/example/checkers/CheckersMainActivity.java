/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Main activity class - load the startup screen for the user and an option for
 * the user to pick their opponent
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import com.example.checkers.game.GameFramework.GameMainActivity;
import com.example.checkers.game.GameFramework.LocalGame;
import com.example.checkers.game.GameFramework.gameConfiguration.GameConfig;
import com.example.checkers.game.GameFramework.gameConfiguration.GamePlayerType;
import com.example.checkers.game.GameFramework.infoMessage.GameState;
import com.example.checkers.game.GameFramework.players.GamePlayer;
import com.example.checkers.game.GameFramework.utilities.Logger;
import com.example.checkers.game.GameFramework.utilities.Saving;

import java.util.ArrayList;

public class CheckersMainActivity extends GameMainActivity {
    private static final String CHECKERS = "CheckersMainActivity";
    private static final int PORT_NUMBER = 5213;

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

        // Human's pieces are black pieces
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new CheckersHumanPlayer(name, R.layout.checkers_main);
            }
        });

        // dumb computer player
        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
            public GamePlayer createPlayer(String name) {
                return new CheckersComputerPlayer1(name);
            }
        });

        // smarter computer player
        playerTypes.add(new GamePlayerType("Computer Player (smart)") {
            public GamePlayer createPlayer(String name) {
                return new CheckersComputerPlayer2(name);
            }
        });

        // Create a game configuration class for Checkers
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2, "Checkers", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        


        // Set the initial information for the remote players
        //defaultConfig.setRemoteData("Remote Human Player", "", 0);

        //done!
        return defaultConfig;

    }//createDefaultConfig

    /**
     * @param CheckersGame The desired gameState to start at or null for new game
     * @return
     */
    public LocalGame createLocalGame(GameState CheckersGame) {
        if(CheckersGame == null) return new CheckersLocalGame();
        return new CheckersLocalGame((CheckersGameState) CheckersGame);
    }

    /**
     * saveGame, adds this games prepend to the filename
     *
     * @param CheckersGame
     * 				Desired save name
     * @return String representation of the save
     */
    @Override
    public GameState saveGame (String CheckersGame){
        return super.saveGame(getGameString(CheckersGame));
    }

    /**
     * loadGame, adds this games prepend to the desire file to open and creates the game specific state
     * @param CheckersGame
     * 				The file to open
     * @return The loaded GameState
     */
    @Override
    public GameState loadGame (String CheckersGame){
        String appName = getGameString(CheckersGame);
        super.loadGame(appName);
        Logger.log(CHECKERS, "Loading: " + CheckersGame);
        return (GameState) new CheckersGameState((CheckersGameState) Saving.readFromFile(appName,
                this.getApplicationContext()));
    }
}