package com.example.settlersofcatan;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;
import com.example.util.Building;
import com.example.util.DoNotTouch;
import com.example.util.Hex;

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
    private Button tradeButton;
    private Button rollButton;
    private Button endTurnButton;
    private Button roadButton;
    private Button settlementButton;
    private Button cityButton;
    private ImageButton wheatButton;
    private ImageButton woodButton;
    private ImageButton sheepButton;
    private ImageButton brickButton;
    private ImageButton oreButton;
    private CatanGameState gameState;
    CatanBoardView view;
    DoNotTouch c = new DoNotTouch();
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
    }//receiveInfo

    @Override
    public void setAsGui(GameMainActivity activity) {
        //pass the activity correctly
        theActivity = activity;
        //load the prefered layout file
        theActivity.setContentView(R.layout.activity_main);
        view = theActivity.findViewById(R.id.boardView);
        view.setHexVals(hexes);
        tradeButton = theActivity.findViewById(R.id.tradeButton);
        rollButton = theActivity.findViewById(R.id.rollButton);
        endTurnButton = theActivity.findViewById(R.id.endButton);
        roadButton = theActivity.findViewById(R.id.roadButton);
        settlementButton = theActivity.findViewById(R.id.settlementButton);
        cityButton = theActivity.findViewById(R.id.cityButton);
        wheatButton = theActivity.findViewById(R.id.wheatButton);
        oreButton = theActivity.findViewById(R.id.oreButton);
        sheepButton = theActivity.findViewById(R.id.sheepButton4);
        brickButton = theActivity.findViewById(R.id.brickButton3);
        woodButton = theActivity.findViewById(R.id.woodButton5);
        tradeButton.setOnClickListener(this);
        rollButton.setOnClickListener(this);
        endTurnButton.setOnClickListener(this);
        roadButton.setOnClickListener(this);
        settlementButton.setOnClickListener(this);
        cityButton.setOnClickListener(this);
        wheatButton.setOnClickListener(this);
        oreButton.setOnClickListener(this);
        sheepButton.setOnClickListener(this);
        brickButton.setOnClickListener(this);
        woodButton.setOnClickListener(this);
        view.invalidate();//double check the GUI is gonna innit correctly
    }//setAsGui

    @Override
    public void onClick(View view) {
        if (view.equals(tradeButton)) { //trade
            Log.e("HIIIIII :3", "trade");
        } else if (view.equals(rollButton) ) { //roll
            Log.e("HIIIIII :3", "roll");
        } else if (view.equals(endTurnButton)) { //end turn
            Log.e("HIIIIII :3", "end");
        } else if (view.equals(roadButton)) { //road
            Log.e("HIIIIII :3", "road");
        } else if (view.equals(settlementButton)) { //settlement

        } else if (view.equals(cityButton)) { //city
            Log.e("HIIIIII :3", "city");
        } else if (view.equals(oreButton)) { //ore
            Log.e("HIIIIII :3", "ore");
        } else if (view.equals(wheatButton)) { //wheat
            Log.e("HIIIIII :3", "wheat");
        } else if (view.equals(sheepButton)) { //sheep
            Log.e("HIIIIII :3", "sheep");
        } else if (view.equals(woodButton)) { //wood
            Log.e("HIIIIII :3", "wood");
        } else if (view.equals(brickButton)) { //brick
            Log.e("HIIIIII :3", "brick");
        }
    }//onclick

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //TODO onTouch
        return false;
    }//onTouch

    public void updateUI(CatanGameState gs) {
        if (gs.playerWood[playerNum] < 1 || gs.playerBrick[playerNum] < 1) {
            roadButton.setEnabled(false);
        }
    }
    public CatanGameState getGameState() {
        return gameState;
    }

    public GameMainActivity getTheActivity() {
        return theActivity;
    }


}//end of class
