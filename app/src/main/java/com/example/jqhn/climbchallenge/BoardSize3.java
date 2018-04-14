package com.example.jqhn.climbchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jqhn on 4/10/18.
 */

public class BoardSize3 extends AppCompatActivity {

    String myLabel;
    String humanLabel;

    int[][] boardValues = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide the action bar
        if(getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.board_size_3);


        Intent intent = getIntent();

        String userLabel = intent.getStringExtra("userLabel");
        this.humanLabel =  userLabel;

        if (humanLabel.equals("X")){
            myLabel = "O";
        } else {
            myLabel = "X";
        }

        this.showToast("Your label is: " + userLabel);
    }

    public void onTextViewClicked(View v) {
        //Human playing
        ((TextView) v).setText(humanLabel);
    }

    public void showToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public int possibleWinningMove() {
        return 1;
    }

    public void counterPossibleForOponent(){}

    public void evaluateBestMove(){}

    public void wasThereAWin() {}

    public void isBoardFilled(){}

}
