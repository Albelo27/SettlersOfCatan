package com.example.actions;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class BuyDCAction extends GameAction {
    /**
     * constructor for GameAction
     * @param player the player who created the action
     */
    public BuyDCAction(GamePlayer player) {
        super(player);
    }
}
