package com.example.settlersofcatan;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class SendPlayerTradeAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SendPlayerTradeAction(GamePlayer player, GamePlayer reciever) {
        super(player);
    }
}
