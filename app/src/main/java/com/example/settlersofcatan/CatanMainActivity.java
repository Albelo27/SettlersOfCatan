package com.example.settlersofcatan;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.gameConfiguration.GameConfig;
import com.example.game.GameFramework.gameConfiguration.GamePlayerType;
import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.GameFramework.players.GamePlayer;

import java.util.ArrayList;
/**
 * CatanMainActivity initializes and kicks off the GameFramework, as well as maintaining the configuration and default configuration of the game
 *
 * @author Anthony Albelo
 * @author Eric Su
 * @author Connor Santa Monica
 * @author Reiss Oliveross
 * @author Ryley vargas
 *
 * @Version October 30th 2023
 */
public class CatanMainActivity extends GameMainActivity {

    //same port number as lab project so that ik this is not causing an error
    private static final int PORT_NUMBER = 2278;

    /**
     * Create a default configuration for our game
     * @return a GameConfig object that represents the default configuration that you specify
     */
    @Override
    public GameConfig createDefaultConfig() {
        //list of allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();
        //local human player
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new CatanHumanPlayer(name);
            }});

        //create a default gameConfig
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 4, "Settlers of Catan", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Human 2: Electric Boogalo", 0);

        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return new CatanLocalGame();
    }
}