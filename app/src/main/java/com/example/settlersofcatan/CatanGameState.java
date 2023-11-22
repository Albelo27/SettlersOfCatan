package com.example.settlersofcatan;

import android.util.Log;
import android.view.inputmethod.InsertGesture;

import java.util.ArrayList;

import com.example.actions.PlayDCAction;
import com.example.util.Building;
import com.example.util.DoNotTouch;
import com.example.util.Hex;
import com.example.util.PlayerData;
import com.example.util.Road;

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
    int loops = 0;
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
    public int lastRoll1;
    public int lastRoll2;
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
    ArrayList<Road> checkedRoads = new ArrayList<Road>();
    private String lastMsg;
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
        lastMsg = "";
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
        //TODO this the initial fixed starting position for infrastructure and should be made variable for beta release
        playerWheat[0] = 10;
        playerBrick[0] = 10;
        playerWood[0] = 10;
        playerOre[0] = 10;
        playerSheep[0] = 10;
        //TODO set this to the proper innit for class
        playerWood[1] = 1;
        playerBrick[1] = 1;
        playerOre[1] = 1;
        data[0].buildings.add(new Building("settlement", c.X[3], c.Y[4], findAdjacent(c.X[3], c.Y[4])));
        data[0].buildings.add(new Building("settlement", c.X[8], c.Y[6], findAdjacent(c.X[8], c.Y[6])));
        data[0].roads.add(new Road(c.X[8], c.Y[6], c.X[8], c.Y[5]));
        data[0].roads.add(new Road(c.X[7], c.Y[4], c.X[8], c.Y[5]));
        data[0].roads.add(new Road(c.X[3], c.Y[4], c.X[2], c.Y[5]));
        data[1].buildings.add(new Building("settlement", c.X[3], c.Y[8], findAdjacent(c.X[3], c.Y[8])));
        data[1].buildings.add(new Building("settlement", c.X[7], c.Y[8], findAdjacent(c.X[7], c.Y[8])));
        data[1].roads.add(new Road(c.X[3], c.Y[8], c.X[4], c.Y[9]));
        data[1].roads.add(new Road(c.X[7], c.Y[8], c.X[7], c.Y[7]));
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
        this.lastRoll1 = copy.lastRoll1;
        this.lastRoll2 = copy.lastRoll2;
        this.canRoll = copy.canRoll;
        this.robberPos = copy.robberPos;
        this.lastMsg = copy.lastMsg;
        this.c = copy.c;
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
            lastRoll1 = (int)(Math.ceil(Math.random() * 6));
            lastRoll2 = (int)(Math.ceil(Math.random() * 6));
            //roll two dice to attempt to mimic the real odds of rolling two dice, eg. 7 is more common than 11
            lastRoll = lastRoll1 + lastRoll2;
            canRoll = false;
            int placeholder = playerId + 1;
            lastMsg = "Player " + placeholder + " rolled a " + lastRoll + "!\n";
            return true;
        } else {
            return false;
        }
    }

    public boolean getRes(int playerId) {
        Log.e("GameState", "getres");
        if (!canRoll && playerId == playerUp) {
            for (int a = 0; a < data.length; a++) {
                for(Building b : data[a].buildings) {
                    for (Hex h : b.getTiles()) {
                        if (h.getGenNum() == lastRoll){
                            if (b.getName().equals("city")) {
                                giveRes(a, h.getResource(), true);
                            } else if (b.getName().equals("settlement")) {
                                giveRes(a, h.getResource(), false);
                            }
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gives the player specified the resource identified, either 1 or two
     * @param playerId the player ot receive the resource
     * @param resID the resource being received
     * @param is2 true if the player should receive 2, false if the player should receive 1;
     */
    public void giveRes(int playerId, int resID, boolean is2) {
        switch(resID) {
            case WHEAT:
                if (is2) {playerWheat[playerId]+=2;} else {playerWheat[playerId]++;}
                break;
            case ORE:
                if (is2) {playerOre[playerId]+=2;} else {playerOre[playerId]++;}
                break;
            case BRICK:
                if (is2) {playerBrick[playerId]+=2;} else {playerBrick[playerId]++;}
                break;
            case SHEEP:
                if (is2) {playerSheep[playerId]+=2;} else {playerSheep[playerId]++;}
                break;
            case WOOD:
                if (is2) {playerWood[playerId]+=2;} else {playerWood[playerId]++;}
                break;
        }
    }

    /**
     * Builds a road for the player whose ID is passed into the function if they have the requisite resources and if it is their turn
     * @param playerID The Player building the road
     * @param X the X-coordinate of the first intersection of the road
     * @param Y the Y-coordinate of the first intersection of the road
     * @param Q  the X-coordinate of the second intersection of the road
     * @param Z the Y-coordinate of the second intersection of the road
     * @return true if the road got built, false if the player was out of turn or is out of resources.
     */
    public boolean buildRoad(int playerID, float X, float Y, float Z, float Q) {
        if(playerID != playerUp) return false;
        if(playerBrick[playerID] >= 1 && playerWood[playerID] >= 1) {
            Road rn = new Road(X, Y, Z, Q);
            if (checkLegalRoad(playerID, rn)) {
                playerRCs[playerID]++;
                playerBrick[playerID]--;
                playerWood[playerID]--;
                data[playerID].roads.add(rn);
                return true;
            }
        }
        return false;
    }

    public boolean checkLegalRoad(int playerID, Road road) {
        for (Building b : data[playerID].buildings) {
            if ((b.getX() == road.x && b.getY() == road.y)||(b.getX() == road.z && b.getY() == road.q)) {
                return true;
            }
        }
        for (Road r : data[playerID].roads) {
            if (!r.equals(road)) {
             if ((r.x == road.x && r.y == road.y) || (r.x == road.z && r.y == road.q)) {
                return true;
             } else if ((r.z == road.x && r.q == road.y) || (r.z == road.z && r.q == road.q)) {
                return true;
             }
            }
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
            if (checkLegalSettlement(playerID, X, Y)) {
                playerWheat[playerID]--;
                playerSheep[playerID]--;
                playerBrick[playerID]--;
                playerWood[playerID]--;
                playerVPs[playerID]++;
                data[playerID].buildings.add(new Building("settlement", X, Y, findAdjacent(X, Y)));
                return true;
            }
        }
        return false;
    }

    public boolean checkLegalSettlement(int playerID, float x, float y) {
        ArrayList<Road> rlist = new ArrayList<Road>();
//        checkedRoads.clear();
        for (Building b : data[playerID].buildings) {
            if (b.getX() == x && b.getY() == y) {
                return false;
            }
            if ((Math.abs((c.findXIndx(b.getX()) - c.findXIndx(x))) <= 1) && (Math.abs(c.findYIndx(b.getY()) - c.findYIndx(y)) <= 1)) {
                Log.e("gameState","too close to another settlement");
                return false;
            }
        }
        for (Road r : data[playerID].roads) {
            if ((x == r.x && y == r.y) || (x == r.z && y == r.q)) {
                rlist.add(r);
            }
        }
        for (Road r : rlist) {
            int t = checkRoadLength(playerID, r, 1);
            if (t >= 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks for a number of roads recursively based on parameters
     * @param playerID the owner of the roads
     * @param startRoad the road being checked for it's position in the current chain
     * @param l the length that is passed recursively, when starting to check roads this should always be passed as one
     * @return the number of roads in a row
     */
    public int checkRoadLength(int playerID, Road startRoad, int l) {
        int length = l;
        for (Road test : checkedRoads) {
            if (test.equals(startRoad)) {
                return length;
            }
        }
        for (Road r : data[playerID].roads) {
            loops++;
            if ((!r.equals(startRoad))) {
                if ((startRoad.x == r.x && startRoad.y == r.y) || (startRoad.x == r.z && startRoad.y == r.q)) {
                    length++;
                    checkedRoads.add(startRoad);
                    checkRoadLength(playerID, r, length);
                } else if ((startRoad.z == r.x && startRoad.q == r.y) || (startRoad.z == r.z && startRoad.q ==r.q)) {
                    length++;
                    checkedRoads.add(startRoad);
                    checkRoadLength(playerID, r, length);
                }
            }
        }
        checkedRoads.clear();
        loops = 0;
        return length;
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
            if (checkLegalCity(playerID, X, Y)) {
                playerVPs[playerID]++;
                playerWheat[playerID] -= 2;
                playerOre[playerID] -= 3;
                data[playerID].buildings.add(new Building("city", X, Y, findAdjacent(X, Y)));
                Building demo = null;
                for (Building b : data[playerID].buildings) {
                    if (b.getName().equals("settlement") && b.getX() == X && b.getY() == Y) {
                       demo = b;
                    }
                }
                if (demo != null) {
                    data[playerID].buildings.remove(demo);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Identifies if a point is a legal city for the player identified
     * @param playerID the player building the city
     * @param x the x coordinate of the city
     * @param y the y coordinate of the city
     * @return true or false if the point is legal or not
     */
    public boolean checkLegalCity(int playerID, float x, float y) {
        for (Building b : data[playerID].buildings){
            if (b.getName().equals("settlement")) {
                if (b.getX() == x && b.getY() == y) {
                    return true;
                }
            }
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
        //TODO each development card will have it's own function and they will be called by LocalGame, make sure they each update lastMsg so there is an accurate turn summary
        return false;
    }
    /**
     * Player selects 1 type of resource. All other players must give that player all of that selected resource they own.
     * @param playerID the player who played the Monopoly Development Card
     * @param resourceID the resource that was selected
     * @return true if the card was played successfully, false if the player did not have the card in their hand or they played out of turn
     */
    public boolean MONOPOLY (int playerID, int resourceID) {

        return true;
    }
    /**
     * Moves robber. Increases points for largest army.
     * @param playerID the player who played the Knight Development Card
     * @return true if the card was played successfully, false if the player did not have the card in their hand or they played out of turn
     */
    public boolean KNIGHT (int playerID) {
        if(playerID != playerUp) return false;
            playerKCs[playerID] ++;
        return true;
    }
    /**
     * Allows Player to build 2 legal roads
     * @param playerID the player who played the Road Builder Development Card
     * @param X the X-coordinate of the first intersection of the road
     * @param Y the Y-coordinate of the first intersection of the road
     * @param Q  the X-coordinate of the second intersection of the road
     * @param Z the Y-coordinate of the second intersection of the road
     * @return true if the card was played successfully, false if the player did not have the card in their hand or they played out of turn
     */
    public boolean ROAD_BUILDER (int playerID, float X, float Y, float Z, float Q) {

        if (playerID != playerUp) return false;
        buildRoad(playerID, X, Y, Z, Q);
        buildRoad(playerID, X, Y, Z, Q);
            return true;
        }
    /**
     * Player takes 2 of any resource.
     * @param playerID the player who played the Year of Plenty Development Card
     * @param resourceID the resource that was selected
     * @return true if the card was played successfully, false if the player did not have the card in their hand or they played out of turn
     */

    public boolean YEAR_OF_PLENTY ( int playerID, int resourceID)
    {
        if (playerID != playerUp) return false;
        for (int i = 0; i < 2; i++) {
            switch (resourceID) {
                case 0:
                    playerOre[playerID]++;
                    break;
                case 1:
                    playerWheat[playerID]++;
                    break;
                case 2:
                    playerBrick[playerID]++;
                    break;
                case 3:
                    playerSheep[playerID]++;
                    break;
                case 4:
                    playerWood[playerID]++;
                    break;
            }
        }
        return true;
    }

    /**
     * Player earns 1 Victory Point
     * @param playerID the player who played the Victory Point Development Card
     * @return true if the card was played successfully, false if the player did not have the card in their hand or they played out of turn
     */
    public boolean VictoryPoint (int playerID) {
        if(playerID != playerUp) return false;
            playerVPs[playerID]++;
        return true;
    }

    /**
     * Moves the robber to a new position
     * @param playerID the player who made the move
     * @param newPos the new position at which to set the robber
     * @return true if the robber was moved, false if the player moved out of turn
     */
    public boolean moveRobber(int playerID, int newPos) {
        //robber maybe for final
        if(playerID != playerUp) return false;
        setRobberPos(newPos);
        return true;
    }

    /**
     * Finds the three hexes adjacent to the given float coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a Hex array of all adjacent hexes, will be between 2 and 3 hexes
     */
    public Hex[] findAdjacent(float x, float y) {
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
    public int getPlayerUp() {return playerUp;}
    public void setPlayerUp(int p) {playerUp = p;}
    public boolean getCanRoll() {return canRoll;}
    public void setCanRoll(boolean c) {canRoll = c;}
    public void setRobberPos(int newPos) {
        robberPos = newPos;
    }
    public String getLastMsg() {return lastMsg;}
    public void resetLastMsg() {lastMsg = " ";}
    //My get functions for onCLick method messages
    public int getLastRoll(){
        return this.lastRoll;
    }
    public void setPlayerOre(int val, int playerId) {
        playerOre[playerId] = val;
    }
    public void setPlayerWheat(int val, int playerId) {
        playerWheat[playerId] = val;
    }
    public void setPlayerSheep(int val, int playerId) {
        playerSheep[playerId] = val;
    }
    public void setPlayerWood(int val, int playerId) {
        playerWood[playerId] = val;
    }
    public void setPlayerBrick(int val, int playerId) {
        playerBrick[playerId] = val;
    }

}
