package com.example.settlersofcatan;

import android.os.Bundle;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.gameConfiguration.GameConfig;
import com.example.game.GameFramework.gameConfiguration.GamePlayerType;
import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.GameFramework.players.GamePlayer;

import java.util.ArrayList;

public class MainActivity extends GameMainActivity {

    //same port number as lab project so that ik this is not causing an error
    private static final int PORT_NUMBER = 2278;
    @Override
    public GameConfig createDefaultConfig() {
        //list of allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();
        //local human player
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new HumanPlayer(name);
            }});

        //create a default gameConfig
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 4, "Settlers of Catan", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Human 2: Electric Boogalo", 0);

        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return null;
    }

    public void start(GamePlayer[] players) {

    }
}