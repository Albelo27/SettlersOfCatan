package com.example.settlersofcatan;

import java.util.ArrayList;
import java.util.Hashtable;

import com.example.util.Building;
import com.example.util.DoNotTouch;
import com.example.util.Hex;
import com.example.util.Pair;
import com.example.util.PlayerData;

/**
 * CatanGameState is the GameState that controls the internal state of the Catan board, player hands and player score
 *
 * @author Anthony Albelo
 * @author Eric Su
 * @author Connor Santa Monica
 * @author Reiss Oliveros
 * @author Ryley vargas
 *
 * @version November 12th 2023
 */
public class CatanGameState extends com.example.game.GameFramework.infoMessage.GameState {
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
    //player color values
    final float[] playerColors = {0xFFc12a43, 0xFF2a3ec1, 0xFFc17b2a, 0xFFe5c8a7};
    //records the current player
    private int playerUp;
    //records the most recent roll made
    private int lastRoll;
    //can the player who's turn it is roll the dice
    private boolean canRoll;
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
    PlayerData[] data = new PlayerData[4];
    DoNotTouch c = new DoNotTouch();
    private int robberPos;
    //these are the hard-coded values of the hexagons
    Hex[] boardValues = {//first row
            new Hex(ORE, 10, c.h0), new Hex(SHEEP, 2, c.h1), new Hex(WOOD, 9, c.h2),
        //second row
        new Hex(WHEAT, 12, c.h3), new Hex(BRICK, 6, c.h4), new Hex(SHEEP, 4, c.h5), new Hex(BRICK, 10, c.h6),
        //third row
        new Hex(WHEAT, 9, c.h7), new Hex(WOOD, 11, c.h8), new Hex(-1, -1, c.h9), new Hex(WOOD, 3, c.h10), new Hex(ORE, 8, c.h11),
        //forth row
        new Hex(WOOD, 8, c.h12), new Hex(ORE, 3, c.h13), new Hex(WHEAT, 4, c.h14), new Hex(SHEEP, 5, c.h15),
        //final row
        new Hex(BRICK, 5, c.h16), new Hex(WHEAT, 6, c.h17), new Hex(SHEEP, 11, c.h18)};
    /**
     * base constructor, initializes a new GameState for settlersofcatan
     */
    public CatanGameState() {
        //initialize basic variables
        playerUp = 0;
        lastRoll = 7;//most common roll, placeholder for pre-setup
        canRoll = true;
        robberPos = 9;//based on the pre-set board we are using for alpha release
        for (int k = 0; k < 4; k++) {//all of these arrays are 4 in length
            playerVPs[k] = 2;//players start with two settlements on the board
            playerKCs[k] = 0;
            playerRCs[k] = 1;//players start with roads that are one segment long
            playerOre[k] = 0;
            playerWheat[k] = 0;
            playerBrick[k] = 0;
            playerSheep[k] = 0;
            playerWood[k] = 0;
            data[k] = new PlayerData(); //create a new class that contains the player's roads, cards and buildings
        }
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
            this.data[k] = copy.data[k];//copy each of the playerData classes individually from the data array
        }
        for (int a = 0; a < boardValues.length; a++) {//copy board layout
            for (int u = 0; u < boardValues.length; u++) {
                //this copy ctcr is a deep copy
                this.boardValues[u] = new Hex(copy.boardValues[u]);
            }
        }
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
     * @param X the X-coordinate of the first intersection of the road
     * @param Y the Y-coordinate of the first intersection of the road
     * @param Q  the X-coordinate of the second intersection of the road
     * @param Z the Y-coordinate of the first intersection of the road
     * @return true if the road got built, false if the player was out of turn or is out of resources.
     */
    public boolean buildRoad(int playerID, float X, float Y, float Z, float Q) {
        if(playerID != playerUp) return false;
        if(playerBrick[playerID] >= 1 && playerWood[playerID] >= 1) {
            playerRCs[playerID]++;
            playerBrick[playerID]--;
            playerWood[playerID]--;
            data[playerID].roads.put(new Pair(X, Y), new Pair(Z,Q));
            return true;
        }
        return false;
    }

    /**
     * Builds a settlement for the player whose ID is passed in if they have the requisite materials
     * @param playerID the player building the settlement
     * @param X the X-coordinate of the first intersection of the road
     * @param Y the Y-coordinate of the first intersection of the road
     * @return true if the build was legal, false if the player does not have the resources or played out of turn
     */
    public boolean buildSettlement(int playerID, float X, float Y) {
        if(playerID != playerUp) return false;
        if(playerWheat[playerID] >= 1 && playerSheep[playerID] >= 1 && playerBrick[playerID] >= 1 && playerWood[playerID] >= 1) {
            playerWheat[playerID]--;
            playerSheep[playerID]--;
            playerBrick[playerID]--;
            playerWood[playerID]--;
            data[playerID].buildings.add(new Building("settlement", X, Y,findAdjacents(X, Y)));
            return true;
        }
        return false;
    }

    /**
     * Builds a city for the player who's ID is passed in if they have the requisite materails
     * @param playerID The player building the city
     * @param X the X-coordinate of the first intersection of the road
     * @param Y the Y-coordinate of the first intersection of the road
     * @return true if the city is built, false if the player played out of turn or does not have the needed materials
     */
    public boolean upgradeToCity(int playerID, float X, float Y) {
        if (playerID != playerUp) return false;
        if(playerWheat[playerID] >= 2 && playerOre[playerID] >= 3) {
            playerVPs[playerID]++;
            playerWheat[playerID] -= 2;
            playerOre[playerID] -= 3;
            data[playerID].buildings.add(new Building("city", X, Y,findAdjacents(X, Y)));
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
            data[playerID].devCards.add(cardID);
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
    public boolean playDC(int playerID, int cardID, int resourceID) {
        //TODO each development card will have it's own function and they will be called by LocalGame
        return false;
    }

    /**
     * Moves the robber to a new position
     * @param playerID the player who made the move
     * @param newPos the new position at which to set the robber
     * @return true if the robber was moved, false if the player moved out of turn
     */
    public boolean moveRobber(int playerID, int newPos) {
        //TODO update for new Hex object
        if(playerID != playerUp) return false;
        setRobberPos(newPos);
        return true;
    }

    public Hex[] findAdjacents(float x, float y) {
        ArrayList<Hex> list = new ArrayList<Hex>();
        Hex[] output = new Hex[3];
        for (Hex hex : boardValues) {
            if (hex.hasCorner(x, y)) {
                list.add(hex);
            }
        }
        list.toArray(output);
        return output;
    }

    //Getters and Setters
    public PlayerData getData(int playerID) {
        return data[playerID];
    }
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
}
