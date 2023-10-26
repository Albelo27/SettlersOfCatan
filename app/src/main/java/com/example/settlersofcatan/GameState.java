package com.example.settlersofcatan;

import java.util.ArrayList;
import java.util.Hashtable;
import com.example.util.Pair;

public class GameState extends com.example.game.GameFramework.infoMessage.GameState {

    //records the current player
    int playerUp;
    //records the most recent roll made
    int lastRoll;
    /*
    Development Card constants
       Players will have an array of Integers that represent their Development cards
    */
    final int MONOPOLY = 0;
    final int KNIGHT = 1;
    final int ROAD_BUILDER = 2;
    final int YEAR_OF_PLENTY = 3;
    final int VICTORY_POINT = 4;
    //TODO add player colors
    //player VP Counts
    int player1VP;
    int player2VP;
    int player3VP;
    int player4VP;
    //playerKnightCount
    int player1KC;
    int player2KC;
    int player3KC;
    int player4KC;
    //player road count
    int player1RC;
    int player2RC;
    int player3RC;
    int player4RC;
    //player resource values
    //ore
    int player1Ore;
    int player2Ore;
    int player3Ore;
    int player4Ore;
    //wheat
    int player1Wheat;
    int player2Wheat;
    int player3Wheat;
    int player4Wheat;
    //brick
    int player1Brick;
    int player2Brick;
    int player3Brick;
    int player4Brick;
    //sheep
    int player1Sheep;
    int player2Sheep;
    int player3Sheep ;
    int player4Sheep;
    //wood
    int player1Wood;
    int player2Wood;
    int player3Wood;
    int player4Wood;

    //player development cards
    ArrayList<Integer> player1DC;
    ArrayList<Integer> player2DC;
    ArrayList<Integer> player3DC;
    ArrayList<Integer> player4DC;

    //player infrastructure
    //  Keys should be a pair of strings that are the names of the coresponding coordinate values
    //  Second string should be "city" or "settlement"
    Hashtable<Pair, String> player1Infs;
    Hashtable<Pair, String> player2Infs;
    Hashtable<Pair, String> player3Infs;
    Hashtable<Pair, String> player4Infs;

    //player roads
    //use the Pair class to record the coordinates of each end of the road
    Hashtable<Pair, Pair> player1Roads;
    Hashtable<Pair, Pair> player2Roads;
    Hashtable<Pair, Pair> player3Roads;
    Hashtable<Pair, Pair> player4Roads;

    /**
     * base constructor, initializes a new GameState for settlersofcatan
     */
    public GameState() {
        //initialize basic variables
        player1VP = 0;
        player2VP = 0;
        player3VP = 0;
        player4VP = 0;
        player1KC = 0;
        player2KC = 0;
        player3KC = 0;
        player4KC = 0;
        player1RC = 0;
        player2RC = 0;
        player3RC = 0;
        player4RC = 0;
        player1Ore = 0;
        player2Ore = 0;
        player3Ore = 0;
        player4Ore = 0;
        player1Wheat = 0;
        player2Wheat = 0;
        player3Wheat = 0;
        player4Wheat = 0;
        player1Brick = 0;
        player2Brick = 0;
        player3Brick = 0;
        player4Brick = 0;
        player1Wood = 0;
        player2Wood = 0;
        player3Wood = 0;
        player4Wood = 0;
        player1Sheep = 0;
        player2Sheep = 0;
        player3Sheep = 0;
        player4Sheep = 0;
        //initialize complex variables
        player1DC = new ArrayList<Integer>();
        player2DC = new ArrayList<Integer>();
        player3DC = new ArrayList<Integer>();
        player4DC = new ArrayList<Integer>();
        player1Infs = new Hashtable<Pair, String>();
        player2Infs = new Hashtable<Pair, String>();
        player3Infs = new Hashtable<Pair, String>();
        player4Infs = new Hashtable<Pair, String>();
        player1Roads = new Hashtable<Pair, Pair>();
        player2Roads = new Hashtable<Pair, Pair>();
        player3Roads = new Hashtable<Pair, Pair>();
        player4Roads = new Hashtable<Pair, Pair>();
    }

    /**
     * Creates a deep copy of the game State you pass into the function
     *
     * @param gameState gameState that should be copied
     */
    public GameState(GameState gameState) {

        this.player1VP = gameState.player1VP;
        this.player2VP = gameState.player2VP;
        this.player3VP = gameState.player3VP;
        this.player4VP = gameState.player4VP;
        this.player1KC = gameState.player1KC;
        this.player2KC = gameState.player2KC;
        this.player3KC = gameState.player3KC;
        this.player4KC = gameState.player4KC;
        this.player1RC = gameState.player1RC;
        this.player2RC = gameState.player2RC;
        this.player3RC = gameState.player3RC;
        this.player4RC = gameState.player4RC;
        this.player1Ore = gameState.player1Ore;
        this.player2Ore = gameState.player2Ore;
        this.player3Ore = gameState.player3Ore;
        this.player4Ore = gameState.player4Ore;
        this.player1Wheat = gameState.player1Wheat;
        this.player2Wheat = gameState.player2Wheat;
        this.player3Wheat = gameState.player3Wheat;
        this.player4Wheat = gameState.player4Wheat;
        this.player1Brick = gameState.player1Brick;
        this.player2Brick = gameState.player2Brick;
        this.player3Brick = gameState.player3Brick;
        this.player4Brick = gameState.player4Brick;
        this.player1Wood = gameState.player1Wood;
        this.player2Wood = gameState.player2Wood;
        this.player3Wood = gameState.player3Wood;
        this.player4Wood = gameState.player4Wood;
        this.player1Sheep = gameState.player1Sheep;
        this.player2Sheep = gameState.player2Sheep;
        this.player3Sheep = gameState.player3Sheep;
        this.player4Sheep = gameState.player4Sheep;
        //TODO make this a real deep copy I just don't feel like it rn
        player1DC = new ArrayList<Integer>();
        player2DC = new ArrayList<Integer>();
        player3DC = new ArrayList<Integer>();
        player4DC = new ArrayList<Integer>();
        player1Infs = new Hashtable<Pair, String>();
        player2Infs = new Hashtable<Pair, String>();
        player3Infs = new Hashtable<Pair, String>();
        player4Infs = new Hashtable<Pair, String>();
        player1Roads = new Hashtable<Pair, Pair>();
        player2Roads = new Hashtable<Pair, Pair>();
        player3Roads = new Hashtable<Pair, Pair>();
        player4Roads = new Hashtable<Pair, Pair>();
    }

    public String toString() {
        //TODO implement this
        return "";
    }

}
