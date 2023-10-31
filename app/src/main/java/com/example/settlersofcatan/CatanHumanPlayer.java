package com.example.settlersofcatan;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;

public class CatanHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    //current android activity for GUI integration etc.
    private GameMainActivity theActivity;
    //button for testing teh toString of our humanPlayer
    private Button testButton;
    //editText for output purposes
    private EditText textEdit;
    //instances for the GameState assignment
    private CatanGameState firstInstance;
    private CatanGameState secondInstance;
    private CatanGameState thirdInstance;
    private CatanGameState fourthInstance;

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

    }//receiveInfo

    @Override
    public void setAsGui(GameMainActivity activity) {
        //pass the activity correctly
        theActivity = activity;

        //load the prefered layout file
        //TODO import the old Catan GUI we designed before
        activity.setContentView(R.layout.activity_main);

        //setup the android layout elements that we are using
        this.testButton = (Button) activity.findViewById(R.id.testButton);
        this.textEdit = (EditText) activity.findViewById(R.id.editText);

        //onClickListener needs to actually be listening
        testButton.setOnClickListener(this);
    }//setAsGui

    @Override
    public void onClick(View view) {
        //testing the toString method
        //firstInstance = new GameState();
        //textEdit.setText("");
        //textEdit.setText(firstInstance.toString());
        //TODO add the button and listener for 25% of our grade

        //Any text currently displayed in in the multi-line EditText (probably
        //from a previous test run) should be cleared.
        textEdit.setText("");

        //A new instance of the game state class is created using the default
        //constructor and assigned to a variable named firstInstance
        firstInstance = new CatanGameState();

        //Deep copy of firstInstance from the perspective of player one. Assign this copy to a variable
        //named secondInstance
        secondInstance = new CatanGameState(firstInstance);

        //Using firstInstance, call each method in the game state class at least
        //once. In each case it should be making a legal move in the game.
        //For each method call, a brief description of the action taken should
        //be printed to the multi-line EditText. (e.g., “Player 1 has moved his
        //pawn from position 10 to position 14.” or “Player 3 has rolled the
        //dice. She rolled a 9.” New messages should be appended to previous
        //ones, not overwrite them.

        //I WILL BE TESTING WITH ONLY PLAYER 1 AND USE @PARAMS OF "1" WHEN PROMPTED

        //rollDice
        firstInstance.rollDice(1);
        textEdit.append("Player 1 rolled a "+ firstInstance.getLastRoll()+"\n");

        //buildRoad
        firstInstance.buildRoad(1);
        if (firstInstance.buildRoad(1)){
            textEdit.append("Player 1 built a road"+"\n");
        }
        else{
            textEdit.append("Illegal move \n");
        }

        //buildSettlement
        firstInstance.buildSettlement(1);
        if(firstInstance.buildSettlement(1)){
            textEdit.append("Player 1 built a settlement \n");
        }
        else{
            textEdit.append("Illegal move \n");
        }

        //upgradeToCity
        firstInstance.upgradeToCity(1);
        if(firstInstance.upgradeToCity(1)){
            textEdit.append("Player 1 upgraded to a city \n");
        }
        else{
            textEdit.append("Illegal move \n");
        }

        //purchaseDC
        firstInstance.purchaseDC(1);
        if(firstInstance.purchaseDC(1)){
            textEdit.append("Player 1 purchased a development card and got" +firstInstance.getCardID());
        }
        else{
            textEdit.append("Illegal move \n");
        }

        //playDC
        firstInstance.playDC(1,1,1);
        if(firstInstance.playDC(1,1,1)){
            textEdit.append("Player 1 used DC 1 \n");
        }
        else{
            textEdit.append("Illegal move \n");
        }

        //moveRobber
        firstInstance.moveRobber(1,3);
        if(firstInstance.buildSettlement(1)){
            textEdit.append("Player 1 moved the robber to"+firstInstance.getRobberPos()+"\n");
        }
        else{
            textEdit.append("Illegal move \n");
        }


        //Create a new instance of the game state class using the default constructor.
        //Assign this value to a variable named thirdInstance.
        thirdInstance = new CatanGameState();

        //Use your deep copy constructor to make a deep copy of thirdInstance
        //from the perspective of player one. Assign this copy to a variable
        //named fourthInstance
        fourthInstance = new CatanGameState(thirdInstance);

        //Call the toString() method on secondInstance and fourthInstance.
        //The two strings should be identical. Your code should verify this.
        //Also, print both strings to the multi-line EditText for visual inspection.
        //Again, append these rather than overwrite previous messages.
        String secondString = secondInstance.toString();
        String fourthString = fourthInstance.toString();

        if (secondString.equals(fourthString)) {
            textEdit.append("The two game states are identical!\n");
        } else {
            textEdit.append("The two game states are NOT identical!\n");
        }
        textEdit.append("secondInstance:"+secondString+"\n fourthInstance:"+fourthString+'\n');

    }//onClick
}
