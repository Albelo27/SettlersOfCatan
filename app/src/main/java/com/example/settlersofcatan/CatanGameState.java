package com.example.settlersofcatan;

import java.util.ArrayList;
import java.util.Hashtable;
import com.example.util.Pair;

/**
 * CatanGameState is the GameState that controls the internal state of the Catan board, player hands and player score
 *
 * @author Anthony Albelo
 * @author Eric Su
 * @author Connor Santa Monica
 * @author Reiss Oliveros
 * @author Ryley vargas
 *
 * @Version October 30th 2023
 */
public class CatanGameState extends com.example.game.GameFramework.infoMessage.GameState {
    //TODO fix this godawful data structure that I made
    /*
    Development Card constants
       Players will have an array of Integers that represent their Development cards
    */
    final int MONOPOLY = 0;
    final int KNIGHT = 1;
    final int ROAD_BUILDER = 2;
    final int YEAR_OF_PLENTY = 3;
    final int VICTORY_POINT = 4;
    /*resource constants
    used to inform GameSate and PlayerState when passing messages about which resources should be updated
     */
    final int ORE = 0;
    final int WHEAT = 1;
    final int BRICK = 2;
    final int SHEEP = 3;
    final int WOOD = 4;
    //records the current player
    private int playerUp;
    //records the most recent roll made
    private int lastRoll;
    //can the player who's turn it is roll the dice
    private boolean canRoll;
    //String for the gameState toString() method
    //DEPRECATED
    private String output;
    //int that represents the robbers position o tiles 0-18
    private int robberPos;
    /*The board values array should hold two values
        boardValues[X][0] should be the number on which that tile produces resources
        and boardVallues[X][1] should be which resource is produced
     */
    int[][] boardValues = new int[19][2];
    //player color values
    final float[] playerColors = {0xc12a43, 0x2a3ec1, 0xc17b2a, 0xe5c8a7};
    //player VP Counts
    int[] playerVPs = new int[4];
    //playerKnightCount
    int[] playerKCs = new int[4];
    //player road count
    int[] playerRCs = new int[4];
    //player resource values
    //ore
    int[] playerOre = new int[4];
    //wheat
    int[] playerWheat = new int[4];
    //brick
    int[] playerBrick = new int[4];
    //sheep
    int[] playerSheep = new int[4];
    //wood
    int[] playerWood = new int[4];

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
    public CatanGameState() {
        //initialize basic variables
        playerUp = 0;
        lastRoll = 7;//most common roll, placeholder for pre-setup
        canRoll = true;
        output = "test";
        robberPos = -1;//TODO should be set to the same position as the desert
        for (int k = 0; k < 4; k++) {//all of these arrays are 4 in length
            playerVPs[k] = 2;//players start with two settlements on the board
            playerKCs[k] = 0;
            playerRCs[k] = 1;//players start with roads that are one segment long
            playerOre[k] = 0;
            playerWheat[k] = 0;
            playerBrick[k] = 0;
            playerSheep[k] = 0;
            playerWood[k] = 0;
        }
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
     * @param copy gameState that should be copied
     */
    public CatanGameState(CatanGameState copy) {
        //copy basic variables
        this.playerUp = copy.playerUp;
        this.lastRoll = copy.lastRoll;
        this.canRoll = copy.canRoll;
        this.robberPos = copy.robberPos;
        for (int k = 0; k < 4; k++) {//4-long arrays
            this.playerVPs[k] = copy.playerVPs[k];
            this.playerKCs[k] = copy.playerKCs[k];
            this.playerRCs[k] = copy.playerRCs[k];
            this.playerOre[k] = copy.playerOre[k];
            this.playerWheat[k] = copy.playerWheat[k];
            this.playerBrick[k] = copy.playerBrick[k];
            this.playerSheep[k] = copy.playerSheep[k];
            this.playerWood[k] = copy.playerWood[k];
        }
        for (int a = 0; a < boardValues.length; a++) {//copy board layout
            for (int y = 0; y < boardValues[0].length; y++) {
                this.boardValues[a][y] = copy.boardValues[a][y];
            }
        }
        //copy complex variables
        this.player1DC = new ArrayList<Integer>();
        this.player1DC.addAll(copy.player1DC);
        this.player2DC = new ArrayList<Integer>();
        this.player2DC.addAll(copy.player2DC);
        this.player3DC = new ArrayList<Integer>();
        this.player3DC.addAll(copy.player3DC);
        this.player4DC = new ArrayList<Integer>();
        this.player4DC.addAll(copy.player4DC);
        this.player1Infs = new Hashtable<Pair, String>();
        for (Pair key: copy.player1Infs.keySet()) {
            this.player1Infs.put(new Pair(key), new String(copy.player1Infs.get(key)));
        }
        this.player2Infs = new Hashtable<Pair, String>();
        for (Pair key: copy.player2Infs.keySet()) {
            this.player2Infs.put(new Pair(key), new String(copy.player2Infs.get(key)));
        }
        this.player3Infs = new Hashtable<Pair, String>();
        for (Pair key: copy.player3Infs.keySet()) {
            this.player3Infs.put(new Pair(key), new String(copy.player3Infs.get(key)));
        }
        this.player4Infs = new Hashtable<Pair, String>();
        for (Pair key: copy.player4Infs.keySet()) {
            this.player4Infs.put(new Pair(key), new String(copy.player4Infs.get(key)));
        }
        this.player1Roads = new Hashtable<Pair, Pair>();
        for (Pair key : copy.player1Roads.keySet()) {
            this.player1Roads.put(new Pair(key), new Pair(copy.player1Roads.get(key)));
        }
        this.player2Roads = new Hashtable<Pair, Pair>();
        for (Pair key : copy.player2Roads.keySet()) {
            this.player2Roads.put(new Pair(key), new Pair(copy.player2Roads.get(key)));
        }
        this.player3Roads = new Hashtable<Pair, Pair>();
        for (Pair key : copy.player3Roads.keySet()) {
            this.player3Roads.put(new Pair(key), new Pair(copy.player3Roads.get(key)));
        }
        this.player4Roads = new Hashtable<Pair, Pair>();
        for (Pair key : copy.player4Roads.keySet()) {
            this.player4Roads.put(new Pair(key), new Pair(copy.player4Roads.get(key)));
        }
    }

    /**
     * The toString() method returns a text-only output of the current gameState;
     * variables and data that are stored as members of an array are listed under the name of the array and preceded with a # sign
     * @return String
     */
    @Override
    public String toString() {
        return null;
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
     * Builds a road for the player whose ID is passed into the function if they have the requisite resources and if it is their turn
     * @param playerID The Player building the road
     * @return true if the road got built, false if the player was out of turn or is out of resources.
     */
    public boolean buildRoad(int playerID) {
        if(playerID != playerUp) return false;
        if(playerBrick[playerID] >= 1 && playerWood[playerID] >= 1) {
            playerRCs[playerID]++;
            playerBrick[playerID]--;
            playerWood[playerID]--;
            return true;
        }
        return false;
    }

    /**
     * Builds a settlement for the player whose ID is passed in if they have the requisite materials
     * @param playerID the player building the settlement
     * @return true if the build was legal, false if the player does not have the resources or played out of turn
     */
    public boolean buildSettlement(int playerID) {
        if(playerID != playerUp) return false;
        if(playerWheat[playerID] >= 1 && playerSheep[playerID] >= 1 && playerBrick[playerID] >= 1 && playerWood[playerID] >= 1) {
            playerWheat[playerID]--;
            playerSheep[playerID]--;
            playerBrick[playerID]--;
            playerWood[playerID]--;
            return true;
        }
        return false;
    }

    /**
     * Builds a city for the player who's ID is passed in if they have the requisite materails
     * @param playerID The player building the city
     * @return true if the city is built, false if the player played out of turn or does not have the needed materials
     */
    public boolean upgradeToCity(int playerID) {
        if (playerID != playerUp) return false;
        if(playerWheat[playerID] >= 2 && playerOre[playerID] >= 3) {
            playerVPs[playerID]++;
            playerWheat[playerID] -= 2;
            playerOre[playerID] -= 3;
            return true;
        }
        return false;
    }

    /**
     * Purchases a Development Card for the player whose ID is passed in if the have the requisite materials
     * @param playerID the player buying the development cards
     * @return true if the card was purchased, false if the player does not have the required materials or played out of turn
     */
    public boolean purchaseDC(int playerID) {
        //player is awarded a random development card using a random int between 0 and 4

        if(playerID != playerUp) return false;
        if(playerWheat[playerID] >= 1 && playerSheep[playerID] >= 1 && playerOre[playerID] >= 1) {
            playerWheat[playerID]--;
            playerSheep[playerID]--;
            playerOre[playerID]--;
            int cardID = (int) (Math.random() * 5);
            switch(playerID) {
                case 0: //player 1
                    player1DC.add(cardID); break;
                case 1: //player 2
                    player2DC.add(cardID); break;
                case 2: //player 3
                    player3DC.add(cardID); break;
                case 3: //player 4
                    player4DC.add(cardID); break;
            }
            if(cardID == VICTORY_POINT) {
                playerVPs[playerID]++;
            }
            return true;
        }
        return false;
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
        if(!(playerID != playerUp && cardID <= 4)) return false;
        switch(playerID) {
            case 0: //player 1
                if(!player1DC.contains(cardID)) return false; // if player 1 doesn't have the DC returns false (illegal move)
                player1DC.remove(cardID);
                switch(cardID) {
                    case MONOPOLY:
                        switch (resourceID) {
                            case WHEAT:
                                int myWheat = playerWheat[1] + playerWheat[2] + playerWheat[3];
                                playerWheat[0] += myWheat;
                                playerWheat[1] = 0;
                                playerWheat[2] = 0;
                                playerWheat[3] = 0;
                                break;
                            case SHEEP:
                                int mySheep = playerSheep[1] + playerSheep[2] + playerSheep[3];
                                playerSheep[0] += mySheep;
                                playerSheep[1] = 0;
                                playerSheep[2] = 0;
                                playerSheep[3] = 0;
                                break;
                            case BRICK:
                                int myBrick = playerBrick[1] + playerBrick[2] + playerBrick[3];
                                playerBrick[0] += myBrick;
                                playerBrick[1] = 0;
                                playerBrick[2] = 0;
                                playerBrick[3] = 0;
                                break;
                            case ORE:
                                int myOre = playerOre[1] + playerOre[2] + playerOre[3];
                                playerOre[0] += myOre;
                                playerOre[1] = 0;
                                playerOre[2] = 0;
                                playerOre[3] = 0;
                                break;
                            case WOOD:
                                int myWood = playerWood[1] + playerWood[2] + playerWood[3];
                                playerWood[0] += myWood;
                                playerWood[1] = 0;
                                playerWood[2] = 0;
                                playerWood[3] = 0;
                                break;
                        }
                        break;
                    case KNIGHT:
                        playerKCs[0]++; // knight GameAction
                        break;
                    case ROAD_BUILDER:
                        playerRCs[0]++; // place roads GameAction
                        break;
                    case YEAR_OF_PLENTY:
                        switch(resourceID) {
                            case WHEAT:
                                playerWheat[0] += 2; break;
                            case SHEEP:
                                playerSheep[0] += 2; break;
                            case BRICK:
                                playerBrick[0] += 2; break;
                            case ORE:
                                playerOre[0] += 2; break;
                            case WOOD:
                                playerWood[0] += 2; break;
                        }
                        break;
                }
                return true;
            case 1: //player 2
                if(!player2DC.contains(cardID)) return false; // if player 2 doesn't have the DC returns false (illegal move)
                player2DC.remove(cardID);
                switch(cardID) {
                    case MONOPOLY:
                        switch (resourceID) {
                            case WHEAT:
                                int myWheat = playerWheat[0] + playerWheat[2] + playerWheat[3];
                                playerWheat[1] += myWheat;
                                playerWheat[0] = 0;
                                playerWheat[2] = 0;
                                playerWheat[3] = 0;
                                break;
                            case SHEEP:
                                int mySheep = playerSheep[0] + playerSheep[2] + playerSheep[3];
                                playerSheep[1] += mySheep;
                                playerSheep[0] = 0;
                                playerSheep[2] = 0;
                                playerSheep[3] = 0;
                                break;
                            case BRICK:
                                int myBrick = playerBrick[0] + playerBrick[2] + playerBrick[3];
                                playerBrick[1] += myBrick;
                                playerBrick[0] = 0;
                                playerBrick[2] = 0;
                                playerBrick[3] = 0;
                                break;
                            case ORE:
                                int myOre = playerOre[0] + playerOre[2] + playerOre[3];
                                playerOre[1] += myOre;
                                playerOre[0] = 0;
                                playerOre[2] = 0;
                                playerOre[3] = 0;
                                break;
                            case WOOD:
                                int myWood = playerWood[0] + playerWood[2] + playerWood[3];
                                playerWood[1] += myWood;
                                playerWood[0] = 0;
                                playerWood[2] = 0;
                                playerWood[3] = 0;
                                break;
                        }
                        break;
                    case KNIGHT:
                        playerKCs[1]++;
                        break;
                    case ROAD_BUILDER:
                        playerRCs[1]++;
                        break;
                    case YEAR_OF_PLENTY:
                        switch(resourceID) {
                            case WHEAT:
                                playerWheat[1] += 2; break;
                            case SHEEP:
                                playerSheep[1] += 2; break;
                            case BRICK:
                                playerBrick[1] += 2; break;
                            case ORE:
                                playerOre[1] += 2; break;
                            case WOOD:
                                playerWood[1] += 2; break;
                        }
                        break;
                }
                break;
            case 2: //player 3
                if(!player3DC.contains(cardID)) return false; // if player 3 doesn't have the DC returns false (illegal move)
                player3DC.remove(cardID);
                switch(cardID) {
                    case MONOPOLY:
                        switch (resourceID) {
                            case WHEAT:
                                int myWheat = playerWheat[0] + playerWheat[1] + playerWheat[3];
                                playerWheat[2] += myWheat;
                                playerWheat[0] = 0;
                                playerWheat[1] = 0;
                                playerWheat[3] = 0;
                                break;
                            case SHEEP:
                                int mySheep = playerSheep[0] + playerSheep[1] + playerSheep[3];
                                playerSheep[2] += mySheep;
                                playerSheep[0] = 0;
                                playerSheep[1] = 0;
                                playerSheep[3] = 0;
                                break;
                            case BRICK:
                                int myBrick = playerBrick[0] + playerBrick[1] + playerBrick[3];
                                playerBrick[2] += myBrick;
                                playerBrick[0] = 0;
                                playerBrick[1] = 0;
                                playerBrick[3] = 0;
                                break;
                            case ORE:
                                int myOre = playerOre[0] + playerOre[1] + playerOre[3];
                                playerOre[2] += myOre;
                                playerOre[0] = 0;
                                playerOre[1] = 0;
                                playerOre[3] = 0;
                                break;
                            case WOOD:
                                int myWood = playerWood[0] + playerWood[1] + playerWood[3];
                                playerWood[2] += myWood;
                                playerWood[0] = 0;
                                playerWood[1] = 0;
                                playerWood[3] = 0;
                                break;
                        }
                        break;
                    case KNIGHT:
                        playerKCs[2]++;
                        break;
                    case ROAD_BUILDER:
                        playerRCs[2]++;
                        break;
                    case YEAR_OF_PLENTY:
                        switch(resourceID) {
                            case WHEAT:
                                playerWheat[2] += 2; break;
                            case SHEEP:
                                playerSheep[2] += 2; break;
                            case BRICK:
                                playerBrick[2] += 2; break;
                            case ORE:
                                playerOre[2] += 2; break;
                            case WOOD:
                                playerWood[2] += 2; break;
                        }
                        break;
                }
                break;
            case 3: //player 4
                if(!player4DC.contains(cardID)) return false; // if player 4 doesn't have the DC returns false (illegal move)
                player4DC.remove(cardID);
                switch(cardID) {
                    case MONOPOLY:
                        switch (resourceID) {
                            case WHEAT:
                                int myWheat = playerWheat[0] + playerWheat[1] + playerWheat[2];
                                playerWheat[3] += myWheat;
                                playerWheat[0] = 0;
                                playerWheat[1] = 0;
                                playerWheat[2] = 0;
                                break;
                            case SHEEP:
                                int mySheep = playerSheep[0] + playerSheep[1] + playerSheep[2];
                                playerSheep[3] += mySheep;
                                playerSheep[0] = 0;
                                playerSheep[1] = 0;
                                playerSheep[2] = 0;
                                break;
                            case BRICK:
                                int myBrick = playerBrick[0] + playerBrick[1] + playerBrick[2];
                                playerBrick[3] += myBrick;
                                playerBrick[0] = 0;
                                playerBrick[1] = 0;
                                playerBrick[2] = 0;
                                break;
                            case ORE:
                                int myOre = playerOre[0] + playerOre[1] + playerOre[2];
                                playerOre[3] += myOre;
                                playerOre[0] = 0;
                                playerOre[1] = 0;
                                playerOre[2] = 0;
                                break;
                            case WOOD:
                                int myWood = playerWood[0] + playerWood[1] + playerWood[2];
                                playerWood[3] += myWood;
                                playerWood[0] = 0;
                                playerWood[1] = 0;
                                playerWood[2] = 0;
                                break;
                        }
                        break;
                    case KNIGHT:
                        playerKCs[3]++;
                        break;
                    case ROAD_BUILDER:
                        playerRCs[3]++;
                        break;
                    case YEAR_OF_PLENTY:
                        switch(resourceID) {
                            case WHEAT:
                                playerWheat[3] += 2; break;
                            case SHEEP:
                                playerSheep[3] += 2; break;
                            case BRICK:
                                playerBrick[3] += 2; break;
                            case ORE:
                                playerOre[3] += 2; break;
                            case WOOD:
                                playerWood[3] += 2; break;
                        }
                        break;
                }
                break;
        }
        return true;
    }

    /**
     * Moves the robber to a new position
     * @param playerID the player who made the move
     * @param newPos the new position at which to set the robber
     * @return true if the robber was moved, false if the player moved out of turn
     */
    public boolean moveRobber(int playerID, int newPos) {
        if(playerID != playerUp) return false;
        setRobberPos(newPos);
        return true;
    }

    /*
    getters and setters for gameState variables
     */

    public int getPlayerUp() {
        return playerUp;
    }

    public int getRobberPos() {
        return robberPos;
    }

    public void setRobberPos(int newPos) {
        robberPos = newPos;
    }
    public float getPlayer1Color() {
        return playerColors[0];
    }

    public float getPlayer2Color() {
        return playerColors[1];
    }

    public float getPlayer3Color() {
        return playerColors[2];
    }

    public float getPlayer4Color() {
        return playerColors[3];
    }

    public int getPlayer1VP() {
        return playerVPs[0];
    }

    public void setPlayer1VP(int player1VP) {
        this.playerVPs[0] = player1VP;
    }

    public int getPlayer2VP() {
        return playerVPs[1];
    }

    public void setPlayer2VP(int player2VP) {
        this.playerVPs[1] = player2VP;
    }

    public int getPlayer3VP() {
        return playerVPs[2];
    }

    public void setPlayer3VP(int player3VP) {
        this.playerVPs[2] = player3VP;
    }

    public int getPlayer4VP() {
        return playerVPs[3];
    }

    public void setPlayer4VP(int player4VP) {
        this.playerVPs[3] = player4VP;
    }

    public int getPlayer1KC() {
        return playerKCs[0];
    }

    public void setPlayer1KC(int player1KC) {
        this.playerKCs[0] = player1KC;
    }

    public int getPlayer2KC() {
        return playerKCs[1];
    }

    public void setPlayer2KC(int player2KC) {
        this.playerKCs[1] = player2KC;
    }

    public int getPlayer3KC() {
        return playerKCs[2];
    }

    public void setPlayer3KC(int player3KC) {
        this.playerKCs[2] = player3KC;
    }

    public int getPlayer4KC() {
        return playerKCs[3];
    }

    public void setPlayer4KC(int player4KC) {
        this.playerKCs[3] = player4KC;
    }

    public int getPlayer1RC() {
        return playerRCs[0];
    }

    public void setPlayer1RC(int player1RC) {
        this.playerRCs[0] = player1RC;
    }

    public int getPlayer2RC() {
        return playerRCs[1];
    }

    public void setPlayer2RC(int player2RC) {
        this.playerRCs[1] = player2RC;
    }

    public int getPlayer3RC() {
        return playerRCs[2];
    }

    public void setPlayer3RC(int player3RC) {
        this.playerRCs[2] = player3RC;
    }

    public int getPlayer4RC() {
        return playerRCs[3];
    }

    public void setPlayer4RC(int player4RC) {
        this.playerRCs[3] = player4RC;
    }

    public int getPlayer1Ore() {
        return playerOre[0];
    }

    public void setPlayer1Ore(int player1Ore) {
        this.playerOre[0] = player1Ore;
    }

    public int getPlayer2Ore() {
        return playerOre[1];
    }

    public void setPlayer2Ore(int player2Ore) {
        this.playerOre[1] = player2Ore;
    }

    public int getPlayer3Ore() {
        return playerOre[2];
    }

    public void setPlayer3Ore(int player3Ore) {
        this.playerOre[2] = player3Ore;
    }

    public int getPlayer4Ore() {
        return playerOre[3];
    }

    public void setPlayer4Ore(int player4Ore) {
        this.playerOre[3] = player4Ore;
    }

    public int getPlayer1Wheat() {
        return playerWheat[0];
    }

    public void setPlayer1Wheat(int player1Wheat) {
        this.playerWheat[0] = player1Wheat;
    }

    public int getPlayer2Wheat() {
        return playerWheat[1];
    }

    public void setPlayer2Wheat(int player2Wheat) {
        this.playerWheat[1] = player2Wheat;
    }

    public int getPlayer3Wheat() {
        return playerWheat[2];
    }

    public void setPlayer3Wheat(int player3Wheat) {
        this.playerWheat[2] = player3Wheat;
    }

    public int getPlayer4Wheat() {
        return playerWheat[3];
    }

    public void setPlayer4Wheat(int player4Wheat) {
        this.playerWheat[3] = player4Wheat;
    }

    public int getPlayer1Brick() {
        return playerBrick[0];
    }

    public void setPlayer1Brick(int player1Brick) {
        this.playerBrick[0] = player1Brick;
    }

    public int getPlayer2Brick() {
        return playerBrick[1];
    }

    public void setPlayer2Brick(int player2Brick) {
        this.playerBrick[1] = player2Brick;
    }

    public int getPlayer3Brick() {
        return playerBrick[2];
    }

    public void setPlayer3Brick(int player3Brick) {
        this.playerBrick[2] = player3Brick;
    }

    public int getPlayer4Brick() {
        return playerBrick[3];
    }

    public void setPlayer4Brick(int player4Brick) {
        this.playerBrick[3] = player4Brick;
    }

    public int getPlayer1Sheep() {
        return playerSheep[0];
    }

    public void setPlayer1Sheep(int player1Sheep) {
        this.playerSheep[0] = player1Sheep;
    }

    public int getPlayer2Sheep() {
        return playerSheep[1];
    }

    public void setPlayer2Sheep(int player2Sheep) {
        this.playerSheep[1] = player2Sheep;
    }

    public int getPlayer3Sheep() {
        return playerSheep[2];
    }

    public void setPlayer3Sheep(int player3Sheep) {
        this.playerSheep[2] = player3Sheep;
    }

    public int getPlayer4Sheep() {
        return playerSheep[3];
    }

    public void setPlayer4Sheep(int player4Sheep) {
        this.playerSheep[3] = player4Sheep;
    }

    public int getPlayer1Wood() {
        return playerWood[0];
    }

    public void setPlayer1Wood(int player1Wood) {
        this.playerWood[0] = player1Wood;
    }

    public int getPlayer2Wood() {
        return playerWood[1];
    }

    public void setPlayer2Wood(int player2Wood) {
        this.playerWood[1] = player2Wood;
    }

    public int getPlayer3Wood() {
        return playerWood[2];
    }

    public void setPlayer3Wood(int player3Wood) {
        this.playerWood[2] = player3Wood;
    }

    public int getPlayer4Wood() {
        return playerWood[3];
    }

    public void setPlayer4Wood(int player4Wood) {
        this.playerWood[3] = player4Wood;
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
