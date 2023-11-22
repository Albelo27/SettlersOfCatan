package com.example.actions;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class PlayDCAction extends GameAction {

    private int devCardPlayed;
    private int resourceID;
    private float x;
    private float y;
    private float z;
    private float q;

    public PlayDCAction(GamePlayer player, int cardPlayed) {
        super(player);
        this.devCardPlayed = cardPlayed;
    }

    public PlayDCAction(GamePlayer player, int cardPlayed, int resID) {
        super(player);
        this.devCardPlayed = cardPlayed;
        this.resourceID = resID;
    }

    public PlayDCAction(GamePlayer player, int cardId, float x, float y, float z, float q) {
        super(player);
        this.devCardPlayed = cardId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.q = q;
    }

    public int getDCPlayed() {
        return devCardPlayed;
    }

    public int getResID() {
        return resourceID;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getQ() {
        return q;
    }
}
