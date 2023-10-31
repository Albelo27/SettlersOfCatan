package com.example.settlersofcatan;

import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.GameFramework.players.GamePlayer;

public class CatanLocalGame extends LocalGame {

    CatanGameState gameState;

    /**
     * Creates a new LocalGame
     */
    public CatanLocalGame() {
        gameState = new CatanGameState();
;    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO implement this for beta release
    }

    @Override
    protected boolean canMove(int playerIdx) {
       if (playerIdx == gameState.getPlayerUp()) {
            return true;
       } else {
           return false;
       }
    }

    @Override
    protected String checkIfGameOver() {
        //gameState can not reach a point where the game is 'over'
        return "lol";
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
