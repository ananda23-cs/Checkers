package com.example.checkers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.checkers.game.GameFramework.GameMainActivity;
import com.example.checkers.game.GameFramework.LocalGame;
import com.example.checkers.game.GameFramework.gameConfiguration.GameConfig;
import com.example.checkers.game.GameFramework.infoMessage.GameState;

public class MainActivity extends GameMainActivity {

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
    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return null;
    }
}