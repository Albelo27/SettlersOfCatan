package com.example.settlersofcatan;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;

public class HumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    //current android activity for GUI integration etc.
    private GameMainActivity theActivity;
    //button for testing teh toString of our humanPlayer
    private Button testButton;
    //editText for output purposes
    private EditText textEdit;
    //instances for the GameState assignment
    private GameState firstInstance;
    private GameState secondInstance;
    private GameState thirdInstance;
    private GameState fourthInstance;

    /**
     * Constructor for the HumanPlayer class, creates a new HumanPlayer which extends GameHumanPlayer
     *
     * @param name The name of the human player to be created
     */
    public HumanPlayer(String name) {
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
        firstInstance = new GameState();
        textEdit.setText("");
        textEdit.setText(firstInstance.toString());
        //TODO add the button and listener for 25% of our grade
    }//onClick
}
