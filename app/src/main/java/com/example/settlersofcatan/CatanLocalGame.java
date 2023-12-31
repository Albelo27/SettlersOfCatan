package com.example.settlersofcatan;

import android.util.Log;

import com.example.actions.BuildAction;
import com.example.actions.BuyDCAction;
import com.example.actions.DiscardAction;
import com.example.actions.PlayDCAction;
import com.example.actions.RollDiceAction;
import com.example.actions.SendPlayerTradeAction;
import com.example.actions.SendTradeAction;
import com.example.game.GameFramework.Game;
import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.actionMessage.EndTurnAction;
import com.example.game.GameFramework.actionMessage.GameAction;
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
        this.state = gameState;
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new CatanGameState(gameState));
    }

    @Override
    protected boolean canMove(int playerIdx) {
       return playerIdx == gameState.getPlayerUp();
    }

    @Override
    protected String checkIfGameOver() {
        for (int a = 0; a < gameState.playerVPs.length; a++) {
            if (gameState.playerVPs[a] >= 10) {
                Log.e("GameOver", "GameOver");
                return playerNames[a] + " Won!";
            }
        }
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        int player = gameState.getPlayerUp();
        int playerID = this.getPlayerIdx(action.getPlayer());
        if(getPlayerIdx(action.getPlayer()) != player)
            return false;
        if(action instanceof SendTradeAction) {
            //TODO implement later
            return true; // legal move
        } else if(action instanceof SendPlayerTradeAction) {
            //TODO implement for final network play
            return true; // legal move
        } else if(action instanceof RollDiceAction) {
            gameState.rollDice(playerID);
            gameState.getRes(playerID);
            if (gameState.getLastRoll() == 7) {
                for (GamePlayer p : players) {
                    if (p instanceof CatanHumanPlayer) {
                        ((CatanHumanPlayer) p).setClassState(7);
                    }
                }
            }
            return true; // legal move
        } else if(action instanceof BuildAction) {
            BuildAction buildAction = (BuildAction)action;
            if(buildAction.getBuilding().equals("city")) {
                gameState.upgradeToCity(playerID, buildAction.getX(), buildAction.getY());
            } else if(buildAction.getBuilding().equals("settlement")) {
                gameState.buildSettlement(playerID, buildAction.getX(), buildAction.getY());
            } else if(buildAction.getBuilding().equals("road")){
                gameState.buildRoad(playerID, buildAction.getX(), buildAction.getY(), buildAction.getZ(), buildAction.getQ());
            }
            return true; // legal move
        } else if(action instanceof PlayDCAction) {
            PlayDCAction dcAction = (PlayDCAction)action;
            if(dcAction.getDCPlayed() == 0) { //MONOPOLY
                gameState.playMonopoly(player, dcAction.getResID());
            } else if(dcAction.getDCPlayed() == 1) { //KNIGHT
                gameState.playKnight(player);
            } else if(dcAction.getDCPlayed() == 2) { //ROAD_BUILDER
                gameState.playRoadBuilder(playerID, dcAction.getX(), dcAction.getY(), dcAction.getZ(), dcAction.getQ());
            } else if(dcAction.getDCPlayed() == 3) { //YEAR_OF_PLENTY
                gameState.playYearOfPlenty(playerID, dcAction.getResID());
            } else if(dcAction.getDCPlayed() == 4) { //VICTORY_POINT
                gameState.playVictoryPoint(playerID);
            }
            return true; // legal move
        } else if (action instanceof DiscardAction) {
            gameState = ((DiscardAction) action).getUpdatedState();

        } else if (action instanceof BuyDCAction) {
            gameState.purchaseDC(playerID);
        }
        else if (action instanceof EndTurnAction) {
           if (gameState.getPlayerUp() == 0) {
               gameState.setPlayerUp(1);
           } else {
               gameState.setPlayerUp(0);
           }
           gameState.setCanRoll(true);
            return true; //legal move
        }
        return false;
    }
}
