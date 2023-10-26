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
     * base constructor
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

    }

}
