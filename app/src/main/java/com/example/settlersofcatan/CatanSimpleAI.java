package com.example.settlersofcatan;

import android.util.Log;

import com.example.actions.BuildAction;
import com.example.actions.RollDiceAction;
import com.example.game.GameFramework.actionMessage.EndTurnAction;
import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameComputerPlayer;
import com.example.util.Building;
import com.example.util.DoNotTouch;
import com.example.settlersofcatan.CatanGameState;
import com.example.util.Hex;
import com.example.util.Road;

/**
 * A simple AI game player that makes moves in priority order.
 */
public class CatanSimpleAI extends GameComputerPlayer {
    CatanGameState gameState;

    /**
     * Constructor for the SimpleAIPlayer.
     *
     * @param name the player's name
     */
    public CatanSimpleAI(String name) {
        super(name);
    }

    /**
     * Receives updated game information and decides whether to make a move.
     *
     * @param info the information about the game's current state
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        Log.d("CatanSimpleAI", "receiveInfo called");

        // Check if the information received is an instance of CatanGameState
        if (info instanceof CatanGameState) {
            CatanGameState newGameState = (CatanGameState) info;

            // Check if the received GameState is different from the current gameState
            if (this.gameState != newGameState) {
                this.gameState = newGameState;
                Log.d("CatanSimpleAI", "New GameState received");

                // Check if it's currently this AI's turn
                if (gameState.getPlayerUp() == this.playerNum) {
                    Log.d("CatanSimpleAI", "It's AI's turn");

                    // Decide on a move
                    game.sendAction(new RollDiceAction(this));
                    Log.d("CatanSimpleAI", "Rolls Dice");
                    sleep(1); // Small delay for better log readability and process synchronization

                    GameAction action = chooseMove();
                    if (action != null) {
                        game.sendAction(action); // Send the chosen move to the game
                    }

                    // End Turn
                    Log.d("CatanSimpleAI", "Turn ends");
                    sleep(1); // Small delay
                    game.sendAction(new EndTurnAction(this));
                    sleep(1); // Small delay
                } else {
                    Log.d("CatanSimpleAI", "Not this AI's turn");
                }
            } else {
                Log.d("CatanSimpleAI", "Received GameState is the same as the current one");
            }
        } else {
            Log.d("CatanSimpleAI", "Received info is not a GameState instance");
        }
    }


    /**
     * Chooses a random move according to the game's rules. (Checks if legal or not)
     *
     * @return a GameAction representing the chosen move
     */
    private GameAction chooseMove() {
        DoNotTouch c = new DoNotTouch();

        // Upgrade to City
        // Add if statements for if its upgradeable, if not; skip to next case
        if (gameState.playerWheat[1] >= 2 && gameState.playerOre[1] >= 3) {
            for (Building b : gameState.data[this.playerNum].buildings) {
                if (b.getName().equals("settlement")) {
                    float x = b.getX();
                    float y = b.getY();
                    return new BuildAction(this, "city", x, y);
                }
            }
        }

        // Build Settlement
        // Add if statements for if its buildable, if not; skip to the next case
        else if (gameState.playerWheat[1] >= 1 && gameState.playerSheep[1] >= 1 && gameState.playerBrick[1] >= 1 && gameState.playerWood[1] >= 1) { // Checks for resources
            for (Road road : gameState.data[this.playerNum].roads) {
                if (isValidSettlementLocation((int) road.x, (int) road.y)) {
                    return new BuildAction(this, "settlement", road.x, road.y);
                } else if (isValidSettlementLocation((int) road.z, (int) road.q)) {
                    return new BuildAction(this, "settlement", road.z, road.q);
                }
            }
        }


        // Build Road
        // Add if statements for if its buildable, if not; skip to next case
        else if (gameState.playerBrick[1] >= 1 && gameState.playerWood[1] >= 1) {

            // Loop through existing roads to find a legal extension point
            for (Road road : gameState.data[this.playerNum].roads) {
                // Check both ends of each road
                Boolean test1 = canPlaceRoadAt((int) road.x, (int) road.y);
                Boolean test2 = canPlaceRoadAt((int) road.z, (int) road.q);
                if (test1) {
                    Log.e("SimpleAI  canPlaceRoadAt", Boolean.toString(test1));
                    return new BuildAction(this, "road", road.x, road.y);
                }
                if (test2) {
                    Log.e("SimpleAI  canPlaceRoadAt", Boolean.toString(test2));
                    return new BuildAction(this, "road", road.z, road.q);
                }
            }

            // Loop through settlements as potential starting points for new roads
            for (Building building : gameState.data[this.playerNum].buildings) {
                Boolean test3 = canPlaceRoadAt((int) building.getX(), (int) building.getY());
                Log.e("SimpleAI  canPlaceRoadAt", Boolean.toString(test3));
                if (test3) {
                    return new BuildAction(this, "road", building.getX(), building.getY());
                }
            }
        }


        return null;
    }

    private boolean isValidSettlementLocation(int x, int y) {
        // Check if there's already a building at (x, y)
        if (isBuildingAtLocation(x, y)) {
            return false;
        }

        // Check if the location is at the end of the player's road
        if (!isConnectedToPlayerRoad(x, y, this.playerNum)) {
            return false;
        }

        return true;
    }

    private boolean isBuildingAtLocation(int x, int y) {
        for (Building building : gameState.data[this.playerNum].buildings) {
            if (building.getX() == x && building.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private boolean isConnectedToPlayerRoad(int x, int y, int playerNum) {
        for (Road road : gameState.data[playerNum].roads) {
            if ((road.x == x && road.y == y) || (road.z == x && road.q == y)) {
                return true;
            }
        }
        return false;
    }

    private boolean canPlaceRoadAt(int x, int y) {
        // Check for overlapping with existing roads
        if (isRoadAtLocation(x, y)) {
            return false;
        }

        // Check connectivity to the player's existing roads
        if (!isConnectedToPlayerStructure(x, y, this.playerNum)) {
            return false;
        }


        return true;
    }

    private boolean isRoadAtLocation(int x, int y) {
        for (Road road : gameState.data[this.playerNum].roads) {
            if ((road.x == x && road.y == y) || (road.z == x && road.q == y)) {
                return true;
            }
        }
        return false;
    }

    private boolean isConnectedToPlayerStructure(int x, int y, int playerNum) {
        // Check if the point is connected to the player's existing roads
        Log.d("CatanSimpleAI", "isConnected Called");
        for (Road road : gameState.data[playerNum].roads) {
            if ((road.x == x && road.y == y) || (road.z == x && road.q == y)) {
                return true;
            }
        }

        // Check if the point is adjacent to the player's buildings
        for (Building building : gameState.data[playerNum].buildings) {
            if (isAdjacentToBuilding(x, y, building)) {
                return true;
            }
        }

        return false;
    }

    private boolean isAdjacentToBuilding(int x, int y, Building building) {

        // Compare the road's coordinates with the building's coordinates
        if (building.getX() == x && building.getY() == y) {
            return true;
        }

        return false;
    }


}
