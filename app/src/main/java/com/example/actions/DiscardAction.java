package com.example.actions;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.GameFramework.players.GamePlayer;
import com.example.settlersofcatan.CatanGameState;

public class DiscardAction extends GameAction {

    CatanGameState updatedState;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public DiscardAction(GamePlayer player, CatanGameState newState) {
        super(player);
        updatedState = newState;
    }

    public CatanGameState getUpdatedState() {
        return updatedState;
    }
}
