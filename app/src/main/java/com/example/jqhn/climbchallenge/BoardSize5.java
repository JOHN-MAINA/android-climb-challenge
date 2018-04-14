package com.example.jqhn.climbchallenge;

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

public class BoardSize5 extends AppCompatActivity {

    String myLabel;
    String humanLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide the action bar
        if(getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.board_size_5);

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
        ((TextView) v).setText(humanLabel);
    }

    public void showToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
