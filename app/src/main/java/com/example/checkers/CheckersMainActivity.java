package com.example.checkers;

import com.example.checkers.game.GameFramework.GameMainActivity;
import com.example.checkers.game.GameFramework.LocalGame;
import com.example.checkers.game.GameFramework.gameConfiguration.GameConfig;
import com.example.checkers.game.GameFramework.infoMessage.GameState;
import com.example.checkers.game.GameFramework.utilities.Saving;

public class CheckersMainActivity extends GameMainActivity {

    // we need to override the abstract methods from GameMainActivity
    // I can see what that looks like in the TTT main activity

    /**
     * sets up a default of one human and one computer player
     * @return
     */
    @Override
    public GameConfig createDefaultConfig() {
        return null;
    }

    /**
     *
     * @param gameState
     *              The desired gameState to start at or null for new game
     *
     * @return
     */
    public LocalGame createLocalGame(GameState gameState) {
        return null;
    }

    /**
     * saveGame, adds this games prepend to the filename
     *
     * @param gameName
     * 				Desired save name
     * @return String representation of the save
     */
    @Override
    public GameState saveGame(String gameName) {
        return super.saveGame(getGameString(gameName));
    }

    /**
     * loadGame, adds this games prepend to the desire file to open and creates the game specific state
     * @param gameName
     * 				The file to open
     * @return The loaded GameState
     */
    @Override
    public GameState loadGame(String gameName){
        String appName = getGameString(gameName);
        super.loadGame(appName);
        return (GameState) new CheckersGameState((CheckersGameState) Saving.readFromFile(appName,
                                                                    this.getApplicationContext()));
    }
}