package com.example.settlersofcatan;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class PlayDCAction extends GameAction {

    private int devCardPlayed;
    private int resourceID;
    /**
     * constructor for creating a PlayDCAction to be sent to the LocalGame with received
     * parameters
     *
     * @param player the player who created the action
     * @param cardPlayed the type of development card that was played
     * @param resID the type of resource chosen by the player for applicable DCs
     */
    public PlayDCAction(GamePlayer player, int cardPlayed, int resID) {
        super(player);
    }



    public int getDCPlayed() {
        return devCardPlayed;
    }

    public int getResID() {
        return resourceID;
    }
}
