package com.example.actions;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class BuildAction extends GameAction {

    private GamePlayer gamePlayer;
    private String building;
    private float X;
    private float Y;
    private float Z;
    private float Q;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     * @param building type of building being built (city or settlement)
     * @param X x coordinate of settlement
     * @param Y y coordinate of settlement
     */
    public BuildAction(GamePlayer player, String  building, float X, float Y) {
        super(player);
        this.building = building;
        this.gamePlayer = player;
        this.X = X;
        this.Y = Y;

    }
    /**
     * constructor for GameAction when building a road
     * @param player the player who created the action
     * @param building type of building being built (road)
     * @param X x coordinate of the road
     * @param Y y coordinate of the road
     * @param Z z coordinate of the road
     * @param Q q coordinate of the road
     */
    public BuildAction(GamePlayer player, String building, float X, float Y, float Z, float Q) {
        super(player);
        this.building = building;
        this.gamePlayer = player;
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        this.Q = Q;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    public String getBuilding() {
        return building;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }

    public float getZ() {
        return Z;
    }

    public float getQ() {
        return Q;
    }
}
