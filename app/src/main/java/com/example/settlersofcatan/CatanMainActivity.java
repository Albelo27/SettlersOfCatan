package com.example.settlersofcatan;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.gameConfiguration.GameConfig;
import com.example.game.GameFramework.gameConfiguration.GamePlayerType;
import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.GameFramework.players.GamePlayer;

import java.util.ArrayList;
/*
We have implemented almost every feature from our requirements presentation. The Knight cards, robber as well as longest road and largts army
were not implemented as we classified them, after talking to Dr. Libby, as "extensions" and decided not to get them as we had lots of
kinks to work out.

Secondly, we did not implement development cards. This happened because our data structure was designed around representing
each development card around an Integer value but the Spinner required that we have Strings and by the time we had figure out how to fix it
we would have had to redesign several large chunks of our gameState data structure just to incorporate one feature.

For the final release we did however finish polishing the UI and fixed every bug in the github except for two, which were both caused by
the gameFramework itself and not our application.
 */
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
            public GamePlayer createPlayer(String name) {return new CatanHumanPlayer(name);}});
        playerTypes.add(new GamePlayerType("Rolls Dice") {
            public GamePlayer createPlayer(String name) {return new CatanAI(name);}});
        playerTypes.add(new GamePlayerType("Complex AI") {
            public GamePlayer createPlayer(String name) {return new CatanSimpleAI(name);}});

        //create a default gameConfig
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 4, "Settlers of Catan", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("SkyNet but it actually moves", 2);
        defaultConfig.setRemoteData("Remote Human Player", "", 0);
        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return new CatanLocalGame();
    }
}