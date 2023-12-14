package com.example.settlersofcatan;

import android.util.Log;

import com.example.actions.RollDiceAction;
import com.example.game.GameFramework.actionMessage.EndTurnAction;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameComputerPlayer;

public class CatanAI extends GameComputerPlayer {

    CatanGameState gameState;
    /**
     * Creates a computer opponent for Settlers of Catan
     *
     * @param name the player's name (e.g., "John")
     */
    public CatanAI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        //algorithm goes here
        //AI just rolls and passes its turn to help with testing
        if (info instanceof CatanGameState) {
            gameState = (CatanGameState) info;
            if (gameState.getPlayerUp() != this.playerNum) {
                return;
            } else {
                sleep(1);
                game.sendAction(new RollDiceAction(this));
                sleep(1);
                game.sendAction(new EndTurnAction(this));
                Log.d("RollAI", "End Turn");
            }
        }
    }//receive info
}
