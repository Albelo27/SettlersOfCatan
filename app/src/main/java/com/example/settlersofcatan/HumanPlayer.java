package com.example.settlersofcatan;

import android.view.View;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;

public class HumanPlayer extends GameHumanPlayer implements View.OnClickListener {
    /**
     * constructor
     *
     * @param name the name of the player
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

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }
    @Override
    public void onClick(View view) {
        //TODO add the button and listener for 25% of our grade
    }
}
