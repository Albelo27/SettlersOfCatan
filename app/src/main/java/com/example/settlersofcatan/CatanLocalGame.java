package com.example.settlersofcatan;

import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.GameFramework.players.GamePlayer;
/**
 * CatanLocalGame creates and distributes the game state as the game is played and maintains turn order
 *
 * @author Anthony Albelo
 * @author Eric Su
 * @author Connor Santa Monica
 * @author Reiss Oliveross
 * @author Ryley vargas
 *
 * @Version October 30th 2023
 */
public class CatanLocalGame extends LocalGame {

    CatanGameState gameState;

    /**
     * Creates a new LocalGame
     */
    public CatanLocalGame() {
        gameState = new CatanGameState();
    }

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
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
