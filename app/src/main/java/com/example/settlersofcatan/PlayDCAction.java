package com.example.settlersofcatan;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class PlayDCAction extends GameAction {
    public int cardPlayed;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlayDCAction(GamePlayer player, int cardPlayed) {
        super(player);
        this.cardPlayed = cardPlayed;
    }
}
