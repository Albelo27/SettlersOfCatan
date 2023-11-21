package com.example.settlersofcatan;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.actions.BuildAction;
import com.example.actions.RollDiceAction;
import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.actionMessage.EndTurnAction;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;
import com.example.util.Building;
import com.example.util.DoNotTouch;
import com.example.util.Hex;
import com.example.util.Road;

import java.util.ArrayList;

/**
 * CatanHumanPlayer maintains the GUI as well as interprets the moves and interactions by a local user
 *
 * @author Anthony Albelo
 * @author Eric Su
 * @author Connor Santa Monica
 * @author Reiss Oliveross
 * @author Ryley vargas
 *
 * @version October 30th 2023
 */
public class CatanHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {

    //current android activity for GUI integration etc.
    private GameMainActivity theActivity;
    /*
    class state represents what the class is doing for UI purposes:
    -1 test state
    0 nothing
    1 building settlement
    2 building city
    3 building road pt1
    4 building road pt2
    5 selecting a knight location
    6 selecting a resource for DC
    7 rolled a 7 (Oh no!!)
    more coming soon with more functionality in the beta release
     */
    //TODO add 5 and 6 functionality
    private int classState = 0;
    private String state = "";
    private Button tradeButton;
    private Button rollButton;
    private Button endTurnButton;
    private Button roadButton;
    private Button settlementButton;
    private Button cityButton;
    private ImageView die1;
    private ImageView die2;
    private TextView updateTxt;
    private Button oreText;
    private Button wheatText;
    private Button sheepText;
    private Button woodText;
    private Button brickText;
    private TextView p1vp;
    private TextView p1KC;
    private TextView p2vp;
    private TextView p2KC;
    private TextView stateView;
    private CatanGameState gameState;
    CatanBoardView view;
    private final DoNotTouch c = new DoNotTouch();
    private final float[] vl = c.vertexList;
    private ArrayList<Float> xHitCords = new ArrayList<Float>();
    private ArrayList<Float> yHitCords = new ArrayList<Float>();
    float[] roadCords = new float[4];
    final int ORE = 0;
    final int WHEAT = 1;
    final int BRICK = 2;
    final int SHEEP = 3;
    final int WOOD = 4;
    Hex[] hexes = {//first row
            new Hex(ORE, 10, c.h0), new Hex(SHEEP, 2, c.h1), new Hex(WOOD, 9, c.h2),
            //second row
            new Hex(WHEAT, 12, c.h3), new Hex(BRICK, 6, c.h4), new Hex(SHEEP, 4, c.h5), new Hex(BRICK, 10, c.h6),
            //third row
            new Hex(WHEAT, 9, c.h7), new Hex(WOOD, 11, c.h8), new Hex(-1, -1, c.h9), new Hex(WOOD, 3, c.h10), new Hex(ORE, 8, c.h11),
            //forth row
            new Hex(WOOD, 8, c.h12), new Hex(ORE, 3, c.h13), new Hex(WHEAT, 4, c.h14), new Hex(SHEEP, 5, c.h15),
            //final row
            new Hex(BRICK, 5, c.h16), new Hex(WHEAT, 6, c.h17), new Hex(SHEEP, 11, c.h18)};
    /**
     * Constructor for the HumanPlayer class, creates a new HumanPlayer which extends GameHumanPlayer
     *
     * @param name The name of the human player to be created
     */
    public CatanHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if (info instanceof CatanGameState) {
            gameState = (CatanGameState) info;
        }
        if (view != null) {
            view.setGameState(gameState);
            updateUI();
            view.invalidate();
        }
    }//receiveInfo

    @Override
    public void setAsGui(GameMainActivity activity) {
        //pass the activity correctly
        theActivity = activity;
        //load the prefered layout file
        theActivity.setContentView(R.layout.activity_main);
        view = theActivity.findViewById(R.id.boardView);
        view.setHexVals(hexes);
        view.setGameState(gameState);
        view.setOnTouchListener(this);
        p1vp = theActivity.findViewById(R.id.P1VP);
        p1KC = theActivity.findViewById(R.id.P1KnightCount);
        p2vp = theActivity.findViewById(R.id.P2VP);
        p2KC = theActivity.findViewById(R.id.P2KnightCount);
        stateView = theActivity.findViewById(R.id.stateTextView);
        die1 = theActivity.findViewById(R.id.die1);
        die2 = theActivity.findViewById(R.id.die2);
        updateTxt = theActivity.findViewById(R.id.updateText);
        tradeButton = theActivity.findViewById(R.id.tradeButton);
        rollButton = theActivity.findViewById(R.id.rollButton);
        endTurnButton = theActivity.findViewById(R.id.endButton);
        roadButton = theActivity.findViewById(R.id.roadButton);
        settlementButton = theActivity.findViewById(R.id.settlementButton);
        cityButton = theActivity.findViewById(R.id.cityButton);
//        wheatButton = theActivity.findViewById(R.id.wheatButton);
//        oreButton = theActivity.findViewById(R.id.oreButton);
//        sheepButton = theActivity.findViewById(R.id.sheepButton4);
//        brickButton = theActivity.findViewById(R.id.brickButton3);
//        woodButton = theActivity.findViewById(R.id.woodButton5);
        oreText = theActivity.findViewById(R.id.oreButton);
        wheatText = theActivity.findViewById(R.id.wheatButton);
        sheepText = theActivity.findViewById(R.id.sheepButton);
        brickText = theActivity.findViewById(R.id.brickButton);
        woodText = theActivity.findViewById(R.id.woodButton);
        tradeButton.setOnClickListener(this);
        rollButton.setOnClickListener(this);
        endTurnButton.setOnClickListener(this);
        roadButton.setOnClickListener(this);
        settlementButton.setOnClickListener(this);
        cityButton.setOnClickListener(this);
        oreText.setOnClickListener(this);
        wheatText.setOnClickListener(this);
        sheepText.setOnClickListener(this);
        brickText.setOnClickListener(this);
        woodText.setOnClickListener(this);
        view.invalidate();//double check the GUI is gonna innit correctly
    }//setAsGui

    @Override
    public void onClick(View view) {
        if (view.equals(tradeButton)) { //trade
            //TODO implement trading
        } else if (view.equals(rollButton) ) { //roll
            if (gameState.getCanRoll()) {
                game.sendAction(new RollDiceAction(this));
            }
        } else if (view.equals(endTurnButton)) { //end turn
            gameState.resetLastMsg();
            game.sendAction(new EndTurnAction(this));
        } else if (view.equals(roadButton)) { //road
            if (classState == 3) {
                classState = 0;
            } else if (classState == 4) {
                classState = 0;
            } else {
                classState = 3;
            }
        } else if (view.equals(settlementButton)) { //settlement
            if (classState == 1) {
                classState = 0;
            } else {
                classState = 1;
            }
        } else if (view.equals(cityButton)) { //city
            if (classState == 2) {
                classState = 0;
            } else {
                classState = 2;
            }
        }
        if (classState == 7) {
            if (view.equals(oreText)) {
                gameState.setPlayerOre(gameState.playerOre[playerNum]-1, playerNum);
            } else if (view.equals(wheatText)) {
                gameState.setPlayerWheat(gameState.playerWheat[playerNum]-1, playerNum);
            }else if (view.equals(sheepText)) {
                gameState.setPlayerSheep(gameState.playerSheep[playerNum]-1, playerNum);
            }else if (view.equals(woodText)) {
                gameState.setPlayerWood(gameState.playerWood[playerNum]-1, playerNum);
            }else if (view.equals(brickText)) {
                gameState.setPlayerBrick(gameState.playerBrick[playerNum]-1, playerNum);
            }
        }
        updateUI();
    }//onclick

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        float xPos = -1;
        float yPos = -1;
        boolean goodClick = false;
        for (int l = 0; l < vl.length; l+=2) {
            if (motionEvent.getX() > vl[l] - (view.radius * 2) && motionEvent.getX() < vl[l] + (view.radius * 2)) {
                xPos = vl[l];
            }
            if (motionEvent.getY() > vl[l+1] - (view.radius * 2) && motionEvent.getY() < vl[l+1] + (view.radius * 2)) {
                yPos = vl[l+1];
            }
        }
        for (int q = 0; q < c.vertexList.length; q++) {
            if (xPos == c.vertexList[q] && yPos == c.vertexList[q + 1]) {
                goodClick = true;
                break;
            }
        }
        if (v.equals(this.view) && goodClick) {
            if (classState == 0) {
                return false;
            }  else if (classState == 1) {
                game.sendAction(new BuildAction(this, "settlement", xPos, yPos));
            } else if (classState == 2) {
                game.sendAction(new BuildAction(this, "city", xPos, yPos));
            } else if (classState == 3) {
                roadCords[0] = xPos;
                roadCords[1] = yPos;
                classState = 4;
            } else if (classState == 4) {
                roadCords[2] = xPos;
                roadCords[3] = yPos;
                game.sendAction(new BuildAction(this, "road", roadCords[0], roadCords[1], roadCords[2], roadCords[3]));
                classState = 0;
            }
            updateUI();
        }
        return false;
    }//onTouch

    public void updateUI() {
        //TODO add a turn summary instead of just the last roll
        updateTxt.setText(gameState.getLastMsg());
        String ore = "Ore: " + gameState.playerOre[playerNum];
        oreText.setText(ore);
        String wheat = "Wheat: " + gameState.playerWheat[playerNum];
        wheatText.setText(wheat);
        String wood = "Wood: " + gameState.playerWood[playerNum];
        woodText.setText(wood);
        String sheep = "Sheep: " + gameState.playerSheep[playerNum];
        sheepText.setText(sheep);
        String brick = "Brick: " + gameState.playerBrick[playerNum];
        brickText.setText(brick);
        String p1points = "VP: " + gameState.playerVPs[0];
        p1vp.setText(p1points);
        String p1knights = "KC: " + gameState.playerKCs[0];
        p1KC.setText(p1knights);
        String p2points = "VP: " + gameState.playerVPs[1];
        p2vp.setText(p2points);
        String p2knight = "KC: " + gameState.playerKCs[1];
        switch (gameState.lastRoll1) {
            case 1:
                die1.setImageResource(R.mipmap.face1); break;
            case 2:
                die1.setImageResource(R.mipmap.face2); break;
            case 3:
                die1.setImageResource(R.mipmap.face3); break;
            case 4:
                die1.setImageResource(R.mipmap.face4); break;
            case 5:
                die1.setImageResource(R.mipmap.face5); break;
            case 6:
                die1.setImageResource(R.mipmap.face6); break;
        }
        switch (gameState.lastRoll2) {
            case 1:
                die2.setImageResource(R.mipmap.face1); break;
            case 2:
                die2.setImageResource(R.mipmap.face2); break;
            case 3:
                die2.setImageResource(R.mipmap.face3); break;
            case 4:
                die2.setImageResource(R.mipmap.face4); break;
            case 5:
                die2.setImageResource(R.mipmap.face5); break;
            case 6:
                die2.setImageResource(R.mipmap.face6); break;
        }
        if (classState == 7) {
            rollButton.setEnabled(false);
            rollButton.setTextColor(0xFF000000);
            tradeButton.setEnabled(false);
            tradeButton.setTextColor(0xFF000000);
            settlementButton.setEnabled(false);
            settlementButton.setTextColor(0xFF000000);
            cityButton.setEnabled(false);
            cityButton.setTextColor(0xFF000000);
            roadButton.setEnabled(false);
            roadButton.setTextColor(0xFF000000);

            oreText.setEnabled(true);
            wheatText.setEnabled(true);
            sheepText.setEnabled(true);
            woodText.setEnabled(true);
            brickText.setEnabled(true);
            int resSum = gameState.playerOre[playerNum] + gameState.playerWheat[playerNum] +gameState.playerSheep[playerNum] +gameState.playerWood[playerNum] +gameState.playerBrick[playerNum];
            if (resSum > 7) {
                endTurnButton.setEnabled(false);
                endTurnButton.setTextColor(0xFF000000);
                state = "You rolled a 7, discard resources until there are 7 or fewer in your hand.";
            } else {
                endTurnButton.setEnabled(true);
                endTurnButton.setTextColor(0xFFFFFFFF);
                oreText.setEnabled(false);
                wheatText.setEnabled(false);
                sheepText.setEnabled(false);
                woodText.setEnabled(false);
                brickText.setEnabled(false);
                classState = 0;
            }
        } else if (gameState.getPlayerUp() == playerNum) {
           endTurnButton.setEnabled(true);
           endTurnButton.setTextColor(0xFFFFFFFF);
           tradeButton.setEnabled(false);
           tradeButton.setTextColor(0xFF000000);
            oreText.setEnabled(false);
            wheatText.setEnabled(false);
            sheepText.setEnabled(false);
            woodText.setEnabled(false);
            brickText.setEnabled(false);
           if (gameState.playerWood[playerNum] < 1 || gameState.playerBrick[playerNum] < 1) {
               roadButton.setEnabled(false);
               roadButton.setTextColor(0xFF000000);
           } else {
               roadButton.setEnabled(true);
               roadButton.setTextColor(0xFFFFFFFF);
           }
           if (gameState.playerBrick[playerNum] < 1 || gameState.playerWood[playerNum] < 1 || gameState.playerWheat[playerNum] < 1 || gameState.playerSheep[playerNum] < 1) {
               settlementButton.setEnabled(false);
               settlementButton.setTextColor(0xFF000000);
           } else {
               settlementButton.setEnabled(true);
               settlementButton.setTextColor(0xFFFFFFFF);
           }
           if (gameState.playerOre[playerNum] < 3 || gameState.playerWheat[playerNum] < 2) {
               cityButton.setEnabled(false);
               cityButton.setTextColor(0xFF000000);
           } else {
               cityButton.setEnabled(true);
               cityButton.setTextColor(0xFFFFFFFF);
           }
           if (gameState.getCanRoll()) {
               rollButton.setEnabled(true);
               rollButton.setTextColor(0xFFFFFFFF);
           } else {
               rollButton.setEnabled(false);
               rollButton.setTextColor(0xFF000000);
           }
           switch (classState) {//TODO add other class states
               case 0:
                   state = "You are idle."; break;
               case 1:
                   state = "You are building a settlement!"; break;
               case 2:
                   state = "You are upgrading to a city!"; break;
               case 3:
                   state = "Choose your first road point!"; break;
               case 4:
                   state = "choose your second road point!"; break;
               case 7:
                   state = "Discard resources until your hand total is <= 7";
               default:
                   state = "";
           }
           stateView.setText(state);
       } else {//not your turn
           String cat = "Player " + Integer.toString(gameState.getPlayerUp() + 1) + " is playing";
           updateTxt.setText(cat);
           roadButton.setEnabled(false);
           roadButton.setTextColor(0xFF000000);
           rollButton.setEnabled(false);
           rollButton.setTextColor(0xFF000000);
           cityButton.setEnabled(false);
           cityButton.setTextColor(0xFF000000);
           settlementButton.setEnabled(false);
           settlementButton.setTextColor(0xFF000000);
           tradeButton.setEnabled(false);
           tradeButton.setTextColor(0xFF000000);
           endTurnButton.setEnabled(false);
           endTurnButton.setTextColor(0xFF000000);
       }
        view.setHDraw(true);//don't change this
       xHitCords.clear();
       yHitCords.clear();
       view.setHitBoxVals(xHitCords, yHitCords);
       highlights();
       view.invalidate();
    }//updateUI

    public void highlights() {
        ArrayList<Building> buildings = gameState.data[this.playerNum].buildings;
            switch(classState) {
                case 1:
                    for (Road test : getGameState().data[this.playerNum].roads) {
                        if (getGameState().checkLegalSettlement(this.playerNum, test.x, test.y) || getGameState().checkLegalSettlement(this.playerNum, test.z, test.q)) {
                            xHitCords.add(test.x);
                            yHitCords.add(test.y);
                        }
                    }
                    break;
                case 2:
                    for (int a = 0; a < buildings.size(); a++) {
                        if (buildings.get(a).getName().equals("settlement")) {
                            xHitCords.add(buildings.get(a).getX());
                            yHitCords.add(buildings.get(a).getY());
                        }
                    }
                    break;
                default:
                    view.setHDraw(false);
                break;
            }
            view.setHitBoxVals(xHitCords, yHitCords);
    }

    //getters and setters
    public CatanGameState getGameState() {
        return gameState;
    }
    public GameMainActivity getTheActivity() {
        return theActivity;
    }
    public void setClassState (int newState) {classState = newState;}

}//end of class
