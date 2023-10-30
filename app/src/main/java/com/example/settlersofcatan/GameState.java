package com.example.settlersofcatan;

import java.util.ArrayList;
import java.util.Hashtable;
import com.example.util.Pair;


public class GameState extends com.example.game.GameFramework.infoMessage.GameState {

    //records the current player
    private int playerUp;
    //records the most recent roll made
    private int lastRoll;
    //can the player who's turn it is roll the dice
    private boolean canRoll;
    //can the player who's turn it is move the robber
    private boolean knightPlayed;
    //String for the gameState toString() method
    private String output;
    //int that represents the robbers position o tiles 0-18
    private int robberPos;
    /*
    Development Card constants
       Players will have an array of Integers that represent their Development cards
    */
    final int MONOPOLY = 0;
    final int KNIGHT = 1;
    final int ROAD_BUILDER = 2;
    final int YEAR_OF_PLENTY = 3;
    final int VICTORY_POINT = 4;
    //resource constants, used to inform GameSate and PlayerState when passing messages about which resources should be updated
    final int ORE = 0;
    final int WHEAT = 1;
    final int BRICK = 2;
    final int SHEEP = 3;
    final int WOOD = 4;
    //player color values
    final float player1Red = 0xc12a43;
    final float player2Blue = 0x2a3ec1;
    final float player3Orange = 0xc17b2a;
    final float player4Cream = 0xe5c8a7;
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
        canRoll = true;
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
        this.canRoll = gameState.canRoll;
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
        player1DC = new ArrayList<Integer>();
        this.player1DC.addAll(gameState.player1DC);
        player2DC = new ArrayList<Integer>();
        this.player2DC.addAll(gameState.player2DC);
        player3DC = new ArrayList<Integer>();
        this.player3DC.addAll(gameState.player3DC);
        player4DC = new ArrayList<Integer>();
        this.player4DC.addAll(gameState.player4DC);
        player1Infs = new Hashtable<Pair, String>();
        for (Pair key: gameState.player1Infs.keySet()) {
            this.player1Infs.put(new Pair(key), new String(gameState.player1Infs.get(key)));
        }
        player2Infs = new Hashtable<Pair, String>();
        for (Pair key: gameState.player2Infs.keySet()) {
            this.player2Infs.put(new Pair(key), new String(gameState.player2Infs.get(key)));
        }
        player3Infs = new Hashtable<Pair, String>();
        for (Pair key: gameState.player3Infs.keySet()) {
            this.player3Infs.put(new Pair(key), new String(gameState.player3Infs.get(key)));
        }
        player4Infs = new Hashtable<Pair, String>();
        for (Pair key: gameState.player4Infs.keySet()) {
            this.player4Infs.put(new Pair(key), new String(gameState.player4Infs.get(key)));
        }
        player1Roads = new Hashtable<Pair, Pair>();
        for (Pair key : gameState.player1Roads.keySet()) {
            this.player1Roads.put(new Pair(key), new Pair(gameState.player1Roads.get(key)));
        }
        player2Roads = new Hashtable<Pair, Pair>();
        for (Pair key : gameState.player2Roads.keySet()) {
            this.player2Roads.put(new Pair(key), new Pair(gameState.player2Roads.get(key)));
        }
        player3Roads = new Hashtable<Pair, Pair>();
        for (Pair key : gameState.player3Roads.keySet()) {
            this.player3Roads.put(new Pair(key), new Pair(gameState.player3Roads.get(key)));
        }
        player4Roads = new Hashtable<Pair, Pair>();
        for (Pair key : gameState.player4Roads.keySet()) {
            this.player4Roads.put(new Pair(key), new Pair(gameState.player4Roads.get(key)));
        }
    }

    /**
     * The toString() method returns a text-only output of the current gameState;
     * variables and data that are stored as members of an array are listed under the name of the array and preceded with a # sign
     * @return String
     */
    @Override
    public String toString() {
        output = "";
        output += "playerUp: " + playerUp + " ";
        output += "lastRoll: " + lastRoll + " ";
        output += "player1VP: " + player1VP + " ";
        output += "player2VP: " + player2VP + " ";
        output += "player3VP: " + player3VP + " ";
        output += "player4VP: " + player4VP + " ";
        output += "player1KC: " + player1KC + " ";
        output += "player2KC: " + player2KC + " ";
        output += "player3KC: " + player3KC + " ";
        output += "player4KC: " + player4KC + " ";
        output += "player1RC: " + player1KC + " ";
        return output;
    }

    /**
     * Creates two random integer values between 1 and 6 and adds them together
     * @param playerId the player who sent the rollDice action call
     * @return true if the player rolled the dice and update the gameState, false if the player rolled out of turn
     */
    public boolean rollDice(int playerId) {
        if (playerId == playerUp && canRoll) {
            //roll two dice to attempt to mimic the real odds of rolling two dice, eg. 7 is more common than 11
            lastRoll = (int)(Math.ceil(Math.random() * 6) + Math.ceil(Math.random()*6));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Builds a road for the plaeyr whose ID is passed into the function if they have the requisite resources and if it is their turn
     * @param playerID The Player building the road
     * @return true if the road got built, false if the player was out of turn or is out of resources.
     */
    public boolean buildRoad(int playerID) {
        if (playerID == playerUp) {
            switch (playerID) {
                case 0:
                    if (player1Brick >= 1 && player1Wood >= 1) {
                        player1RC++;
                        player1Wood--;
                        player1Brick--;
                        return true;
                    }
                    break;
                case 1:
                    if (player2Brick >= 1 && player2Wood >= 1) {
                        player2RC++;
                        player2Wood--;
                        player2Brick--;
                        return true;
                    }
                    break;
                case 2:
                    if (player3Brick >= 1 && player3Wood >= 1) {
                        player3RC++;
                        player3Wood--;
                        player3Brick--;
                        return true;
                    }
                    break;
                case 3:
                    if (player4Brick >= 1 && player4Wood >= 1) {
                        player4RC++;
                        player4Wood--;
                        player4Brick--;
                        return true;
                    }
                    break;
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Builds a settlement for the player whose ID is passed in if they have the requisite materials
     * @param playerID the player building the settlement
     * @return true if the build was legal, false if the player does not have the resources or played out of turn
     */
    public boolean buildSettlement(int playerID) {
        if (playerID == playerUp) {
            switch (playerID) {
                case 0:
                    if (player1Brick >= 1 && player1Wood >= 1 && player1Wheat >= 1 && player1Sheep >= 1) {
                        player1VP++;
                        player1Brick--;
                        player1Sheep--;
                        player1Wheat--;
                        player1Wood--;
                        return true;
                    }
                break;
                case 1:
                    if (player2Brick >= 1 && player2Wood >= 1 && player2Wheat >= 1 && player2Sheep >= 1) {
                        player2VP++;
                        player2Brick--;
                        player2Sheep--;
                        player2Wheat--;
                        player2Wood--;
                        return true;
                    }
                break;
                case 2:
                    if (player3Brick >= 1 && player3Wood >= 1 && player3Wheat >= 1 && player3Sheep >= 1) {
                        player3VP++;
                        player3Brick--;
                        player3Sheep--;
                        player3Wheat--;
                        player3Wood--;
                        return true;
                    }
                break;
                case 3:
                    if (player4Brick >= 1 && player4Wood >= 1 && player4Wheat >= 1 && player4Sheep >= 1) {
                        player4VP++;
                        player4Brick--;
                        player4Sheep--;
                        player4Wheat--;
                        player4Wood--;
                        return true;
                    }
                break;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Builds a city for the player who's ID is passed in if they have the requisite materails
     * @param playerID The player building the city
     * @return true if the city is built, false if the player played out of turn or does not have the needed materials
     */
    public boolean upgradeToCity(int playerID) {
        if (playerID == playerUp) {
            //VP count only increases by one because cities replace
            // settlements already on the board so net score increase is only 1
            switch (playerID) {//no break statements because each case ends with a return instead
                case 0:
                    if (player1Wheat >= 2 && player1Ore >= 3) {
                        player1Wheat -= 2;
                        player1Ore -= 3;
                        player1VP++;
                        return true;
                    } else {
                        return false;
                    }
                case 1:
                    if (player2Wheat >= 2 && player2Ore >= 3) {
                        player2Wheat -= 2;
                        player2Ore -= 3;
                        player2VP++;
                        return true;
                    } else {
                        return false;
                    }
                case 2:
                    if (player3Wheat >= 2 && player3Ore >= 3) {
                        player3Wheat -= 2;
                        player3Ore -= 3;
                        player3VP++;
                        return true;
                    } else {
                        return false;
                    }
                case 3:
                    if (player4Wheat >= 2 && player4Ore >= 3) {
                        player4Wheat -= 2;
                        player4Ore -= 3;
                        player4VP++;
                        return true;
                    } else {
                        return false;
                    }
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Purchases a Development Card for the player whose ID is passed in if the have the requisite materials
     * @param playerID the player buying the development cards
     * @return true if the card was purchased, false if the player does not have the required materials or played out of turn
     */
    public boolean purchaseDC(int playerID) {
        //player is awarded a random development card using a random int between 0 and 4
        if (playerID == playerUp) {
            switch (playerID) {
                case 0:
                    if (player1Wheat >= 1 && player1Sheep >= 1 && player1Ore >= 1) {
                        player1Wheat--;
                        player1Sheep--;
                        player1Ore--;
                        int cardID =(int) (Math.random() * 5);
                        player1DC.add(cardID);
                        if (cardID == VICTORY_POINT) {
                            player1VP++;
                        }
                        return true;
                    } else {
                        return false;
                    }
                case 1:
                    if (player2Wheat >= 1 && player2Sheep >= 1 && player2Ore >= 1) {
                        player2Wheat--;
                        player2Sheep--;
                        player2Ore--;
                        int cardID =(int) (Math.random() * 5);
                        player2DC.add(cardID);
                        if (cardID == VICTORY_POINT) {
                            player2VP++;
                        }
                        return true;
                    } else {
                        return false;
                    }
                case 2:
                    if (player3Wheat >= 1 && player3Sheep >= 1 && player3Ore >= 1) {
                        player3Wheat--;
                        player3Sheep--;
                        player3Ore--;
                        int cardID =(int) (Math.random() * 5);
                        player3DC.add(cardID);
                        if (cardID == VICTORY_POINT) {
                            player3VP++;
                        }
                        return true;
                    } else {
                        return false;
                    }
                case 3:
                    if (player4Wheat >= 1 && player4Sheep >= 1 && player4Ore >= 1) {
                        player4Wheat--;
                        player4Sheep--;
                        player4Ore--;
                        int cardID =(int) (Math.random() * 5);
                        player4DC.add(cardID);
                        if (cardID == VICTORY_POINT) {
                            player4VP++;
                        }
                        return true;
                    } else {
                        return false;
                    }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Plays a development card from a players hand
     * @param playerID the player who played the card
     * @param cardID the card that was played
     * @param resourceID the resource that was selected (only used for the Monopoly and Year of Plenty cards)
     * @return true if the card was played successfully, false if the player did not have the card in their hand or they played out of turn
     */
    //TODO development card interactions are going to need GameActions later
    public boolean playDC(int playerID, int cardID, int resourceID) {
        if (playerID == playerUp && cardID <= 4) {
            switch (playerID) {
                case 0://player 1
                    if (player1DC.contains(cardID)) {
                        player1DC.remove(cardID);
                        switch (cardID) {//check which development card was played
                            case MONOPOLY:
                                switch (resourceID) {
                                    case WHEAT:
                                        int myWheat = player2Wheat + player3Wheat + player4Wheat;
                                        player1Wheat += myWheat;
                                        player2Wheat = 0;
                                        player3Wheat = 0;
                                        player4Wheat = 0;
                                    break;
                                    case SHEEP:
                                        int mySheep = player2Sheep + player3Sheep + player4Sheep;
                                        player1Sheep += mySheep;
                                        player2Sheep = 0;
                                        player3Sheep = 0;
                                        player4Sheep = 0;
                                    break;
                                    case BRICK:
                                        int myBrick = player2Brick + player3Brick + player4Brick;
                                        player1Brick += myBrick;
                                        player2Brick = 0;
                                        player3Brick = 0;
                                        player4Brick = 0;
                                    break;
                                    case ORE:
                                        int myOre = player2Ore + player3Ore + player4Ore;
                                        player1Ore += myOre;
                                        player2Ore = 0;
                                        player3Ore = 0;
                                        player4Ore = 0;
                                    break;
                                    case WOOD:
                                        int myWood = player2Wood + player3Wood + player4Wood;
                                        player1Wood += myWood;
                                        player2Wood = 0;
                                        player3Wood = 0;
                                        player4Wood = 0;
                                    break;
                                }
                            break;
                            case KNIGHT:
                                player1KC++;
                                //robber GameAction
                            break;
                            case ROAD_BUILDER:
                                player1RC += 2;
                                //place roads GameAction
                            break;
                            case YEAR_OF_PLENTY:
                                switch(resourceID) {
                                    case WHEAT:
                                        player1Wheat += 2;
                                    break;
                                    case SHEEP:
                                        player1Sheep += 2;
                                    break;
                                    case BRICK:
                                        player1Brick += 2;
                                    break;
                                    case ORE:
                                        player1Ore += 2;
                                    break;
                                    case WOOD:
                                        player1Wood += 2;
                                    break;
                                }
                            break;
                        }
                        return true;
                    } else {
                        return false;
                    }
                case 1://player 2
                    if (player2DC.contains(cardID)) {
                        player2DC.remove(cardID);
                        switch (cardID) {//check which development card was played
                            case MONOPOLY:
                                switch (resourceID) {
                                    case WHEAT:
                                        int myWheat = player1Wheat + player3Wheat + player4Wheat;
                                        player2Wheat += myWheat;
                                        player1Wheat = 0;
                                        player3Wheat = 0;
                                        player4Wheat = 0;
                                        break;
                                    case SHEEP:
                                        int mySheep = player1Sheep + player3Sheep + player4Sheep;
                                        player2Sheep += mySheep;
                                        player1Sheep = 0;
                                        player3Sheep = 0;
                                        player4Sheep = 0;
                                        break;
                                    case BRICK:
                                        int myBrick = player1Brick + player3Brick + player4Brick;
                                        player2Brick += myBrick;
                                        player1Brick = 0;
                                        player3Brick = 0;
                                        player4Brick = 0;
                                        break;
                                    case ORE:
                                        int myOre = player1Ore + player3Ore + player4Ore;
                                        player2Ore += myOre;
                                        player1Ore = 0;
                                        player3Ore = 0;
                                        player4Ore = 0;
                                        break;
                                    case WOOD:
                                        int myWood = player1Wood + player3Wood + player4Wood;
                                        player2Wood += myWood;
                                        player1Wood = 0;
                                        player3Wood = 0;
                                        player4Wood = 0;
                                        break;
                                }
                                break;
                            case KNIGHT:
                                player2KC++;
                                //robber GameAction
                                break;
                            case ROAD_BUILDER:
                                player2RC += 2;
                                //place roads GameAction
                                break;
                            case YEAR_OF_PLENTY:
                                switch(resourceID) {
                                    case WHEAT:
                                        player2Wheat += 2;
                                        break;
                                    case SHEEP:
                                        player2Sheep += 2;
                                        break;
                                    case BRICK:
                                        player2Brick += 2;
                                        break;
                                    case ORE:
                                        player2Ore += 2;
                                        break;
                                    case WOOD:
                                        player2Wood += 2;
                                        break;
                                }
                                break;
                        }
                        return true;
                    } else {
                        return false;
                }
                case 2://player 3
                    if (player3DC.contains(cardID)) {
                        player3DC.remove(cardID);
                        switch (cardID) {//check which development card was played
                            case MONOPOLY:
                                switch (resourceID) {
                                    case WHEAT:
                                        int myWheat = player2Wheat + player1Wheat + player4Wheat;
                                        player3Wheat += myWheat;
                                        player2Wheat = 0;
                                        player1Wheat = 0;
                                        player4Wheat = 0;
                                        break;
                                    case SHEEP:
                                        int mySheep = player2Sheep + player1Sheep + player4Sheep;
                                        player3Sheep += mySheep;
                                        player2Sheep = 0;
                                        player1Sheep = 0;
                                        player4Sheep = 0;
                                        break;
                                    case BRICK:
                                        int myBrick = player2Brick + player1Brick + player4Brick;
                                        player3Brick += myBrick;
                                        player2Brick = 0;
                                        player1Brick = 0;
                                        player4Brick = 0;
                                        break;
                                    case ORE:
                                        int myOre = player2Ore + player1Ore + player4Ore;
                                        player3Ore += myOre;
                                        player2Ore = 0;
                                        player1Ore = 0;
                                        player4Ore = 0;
                                        break;
                                    case WOOD:
                                        int myWood = player2Wood + player1Wood + player4Wood;
                                        player3Wood += myWood;
                                        player2Wood = 0;
                                        player1Wood = 0;
                                        player4Wood = 0;
                                        break;
                                }
                                break;
                            case KNIGHT:
                                player3KC++;
                                //robber GameAction
                                break;
                            case ROAD_BUILDER:
                                player3RC += 2;
                                //place roads GameAction
                                break;
                            case YEAR_OF_PLENTY:
                                switch(resourceID) {
                                    case WHEAT:
                                        player3Wheat += 2;
                                        break;
                                    case SHEEP:
                                        player3Sheep += 2;
                                        break;
                                    case BRICK:
                                        player3Brick += 2;
                                        break;
                                    case ORE:
                                        player3Ore += 2;
                                        break;
                                    case WOOD:
                                        player3Wood += 2;
                                        break;
                                }
                                break;
                        }
                        return true;
                    } else {
                        return false;
                    }
                case 3://player 4
                    if (player4DC.contains(cardID)) {
                        player4DC.remove(cardID);
                        switch (cardID) {//check which development card was played
                            case MONOPOLY:
                                switch (resourceID) {
                                    case WHEAT:
                                        int myWheat = player2Wheat + player1Wheat + player3Wheat;
                                        player4Wheat += myWheat;
                                        player2Wheat = 0;
                                        player1Wheat = 0;
                                        player1Wheat = 0;
                                        break;
                                    case SHEEP:
                                        int mySheep = player2Sheep + player1Sheep + player3Sheep;
                                        player4Sheep += mySheep;
                                        player2Sheep = 0;
                                        player1Sheep = 0;
                                        player3Sheep = 0;
                                        break;
                                    case BRICK:
                                        int myBrick = player2Brick + player1Brick + player3Brick;
                                        player4Brick += myBrick;
                                        player2Brick = 0;
                                        player1Brick = 0;
                                        player3Brick = 0;
                                        break;
                                    case ORE:
                                        int myOre = player2Ore + player1Ore + player3Ore;
                                        player4Ore += myOre;
                                        player2Ore = 0;
                                        player1Ore = 0;
                                        player3Ore = 0;
                                        break;
                                    case WOOD:
                                        int myWood = player2Wood + player1Wood + player3Wood;
                                        player4Wood += myWood;
                                        player2Wood = 0;
                                        player1Wood = 0;
                                        player3Wood = 0;
                                        break;
                                }
                                break;
                            case KNIGHT:
                                player4KC++;
                                //robber GameAction
                                break;
                            case ROAD_BUILDER:
                                player4RC += 2;
                                //place roads GameAction
                                break;
                            case YEAR_OF_PLENTY:
                                switch(resourceID) {
                                    case WHEAT:
                                        player4Wheat += 2;
                                        break;
                                    case SHEEP:
                                        player4Sheep += 2;
                                        break;
                                    case BRICK:
                                        player4Brick += 2;
                                        break;
                                    case ORE:
                                        player4Ore += 2;
                                        break;
                                    case WOOD:
                                        player4Wood += 2;
                                        break;
                                }
                                break;
                        }
                        return true;
                    } else {
                        return false;
                    }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Moves the robber to a new position
     * @param playerID the player who made the move
     * @param newPos the new position at which to set the robber
     * @return true if the robber was moved, false if the player moved out of turn
     */
    public boolean moveRobber(int playerID, int newPos) {
        if (playerID == playerUp) {
            if (lastRoll == 7 || knightPlayed) {
                setRobberPos(newPos);
                knightPlayed = false;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /*
    getters and setters for gameState variables
     */

    public int getRobberPos() {
        return robberPos;
    }

    public void setRobberPos(int newPos) {
        robberPos = newPos;
    }
    public float getPlayer1Red() {
        return player1Red;
    }

    public float getPlayer2Blue() {
        return player2Blue;
    }

    public float getPlayer3Orange() {
        return player3Orange;
    }

    public float getPlayer4Cream() {
        return player4Cream;
    }

    public int getPlayer1VP() {
        return player1VP;
    }

    public void setPlayer1VP(int player1VP) {
        this.player1VP = player1VP;
    }

    public int getPlayer2VP() {
        return player2VP;
    }

    public void setPlayer2VP(int player2VP) {
        this.player2VP = player2VP;
    }

    public int getPlayer3VP() {
        return player3VP;
    }

    public void setPlayer3VP(int player3VP) {
        this.player3VP = player3VP;
    }

    public int getPlayer4VP() {
        return player4VP;
    }

    public void setPlayer4VP(int player4VP) {
        this.player4VP = player4VP;
    }

    public int getPlayer1KC() {
        return player1KC;
    }

    public void setPlayer1KC(int player1KC) {
        this.player1KC = player1KC;
    }

    public int getPlayer2KC() {
        return player2KC;
    }

    public void setPlayer2KC(int player2KC) {
        this.player2KC = player2KC;
    }

    public int getPlayer3KC() {
        return player3KC;
    }

    public void setPlayer3KC(int player3KC) {
        this.player3KC = player3KC;
    }

    public int getPlayer4KC() {
        return player4KC;
    }

    public void setPlayer4KC(int player4KC) {
        this.player4KC = player4KC;
    }

    public int getPlayer1RC() {
        return player1RC;
    }

    public void setPlayer1RC(int player1RC) {
        this.player1RC = player1RC;
    }

    public int getPlayer2RC() {
        return player2RC;
    }

    public void setPlayer2RC(int player2RC) {
        this.player2RC = player2RC;
    }

    public int getPlayer3RC() {
        return player3RC;
    }

    public void setPlayer3RC(int player3RC) {
        this.player3RC = player3RC;
    }

    public int getPlayer4RC() {
        return player4RC;
    }

    public void setPlayer4RC(int player4RC) {
        this.player4RC = player4RC;
    }

    public int getPlayer1Ore() {
        return player1Ore;
    }

    public void setPlayer1Ore(int player1Ore) {
        this.player1Ore = player1Ore;
    }

    public int getPlayer2Ore() {
        return player2Ore;
    }

    public void setPlayer2Ore(int player2Ore) {
        this.player2Ore = player2Ore;
    }

    public int getPlayer3Ore() {
        return player3Ore;
    }

    public void setPlayer3Ore(int player3Ore) {
        this.player3Ore = player3Ore;
    }

    public int getPlayer4Ore() {
        return player4Ore;
    }

    public void setPlayer4Ore(int player4Ore) {
        this.player4Ore = player4Ore;
    }

    public int getPlayer1Wheat() {
        return player1Wheat;
    }

    public void setPlayer1Wheat(int player1Wheat) {
        this.player1Wheat = player1Wheat;
    }

    public int getPlayer2Wheat() {
        return player2Wheat;
    }

    public void setPlayer2Wheat(int player2Wheat) {
        this.player2Wheat = player2Wheat;
    }

    public int getPlayer3Wheat() {
        return player3Wheat;
    }

    public void setPlayer3Wheat(int player3Wheat) {
        this.player3Wheat = player3Wheat;
    }

    public int getPlayer4Wheat() {
        return player4Wheat;
    }

    public void setPlayer4Wheat(int player4Wheat) {
        this.player4Wheat = player4Wheat;
    }

    public int getPlayer1Brick() {
        return player1Brick;
    }

    public void setPlayer1Brick(int player1Brick) {
        this.player1Brick = player1Brick;
    }

    public int getPlayer2Brick() {
        return player2Brick;
    }

    public void setPlayer2Brick(int player2Brick) {
        this.player2Brick = player2Brick;
    }

    public int getPlayer3Brick() {
        return player3Brick;
    }

    public void setPlayer3Brick(int player3Brick) {
        this.player3Brick = player3Brick;
    }

    public int getPlayer4Brick() {
        return player4Brick;
    }

    public void setPlayer4Brick(int player4Brick) {
        this.player4Brick = player4Brick;
    }

    public int getPlayer1Sheep() {
        return player1Sheep;
    }

    public void setPlayer1Sheep(int player1Sheep) {
        this.player1Sheep = player1Sheep;
    }

    public int getPlayer2Sheep() {
        return player2Sheep;
    }

    public void setPlayer2Sheep(int player2Sheep) {
        this.player2Sheep = player2Sheep;
    }

    public int getPlayer3Sheep() {
        return player3Sheep;
    }

    public void setPlayer3Sheep(int player3Sheep) {
        this.player3Sheep = player3Sheep;
    }

    public int getPlayer4Sheep() {
        return player4Sheep;
    }

    public void setPlayer4Sheep(int player4Sheep) {
        this.player4Sheep = player4Sheep;
    }

    public int getPlayer1Wood() {
        return player1Wood;
    }

    public void setPlayer1Wood(int player1Wood) {
        this.player1Wood = player1Wood;
    }

    public int getPlayer2Wood() {
        return player2Wood;
    }

    public void setPlayer2Wood(int player2Wood) {
        this.player2Wood = player2Wood;
    }

    public int getPlayer3Wood() {
        return player3Wood;
    }

    public void setPlayer3Wood(int player3Wood) {
        this.player3Wood = player3Wood;
    }

    public int getPlayer4Wood() {
        return player4Wood;
    }

    public void setPlayer4Wood(int player4Wood) {
        this.player4Wood = player4Wood;
    }

    //My get functions for onCLick method messages

    public int getLastRoll(){
        return this.lastRoll;
    }
    public int getCardID(){
        int max = player1DC.size();
        return player1DC.get(max);
    }

}
