package com.example.settlersofcatan;

import android.os.Bundle;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.gameConfiguration.GameConfig;
import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.GameFramework.players.GamePlayer;

public class MainActivity extends GameMainActivity {

    @Override
    public GameConfig createDefaultConfig() {
        return null;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return null;
    }

    public void start(GamePlayer[] players) {

    }
}