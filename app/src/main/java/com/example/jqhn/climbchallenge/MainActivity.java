package com.example.jqhn.climbchallenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup level;
    RadioGroup marker;
    RadioButton levelBtn;
    RadioButton markerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide the action bar
        if(getSupportActionBar() != null) getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus){
        }
    }

    public void startGame(View v) {
        Intent board3 = new Intent(this, BoardSize3.class);
        Intent board5 = new Intent(this, BoardSize5.class);
        level = findViewById(R.id.level_rg);
        marker = findViewById(R.id.marker_rg);

        int selectedLevelId = level.getCheckedRadioButtonId();
        int selectedMarkerId = marker.getCheckedRadioButtonId();

        if(selectedLevelId != -1 && selectedMarkerId != -1) {
            levelBtn = findViewById(selectedLevelId);
            markerBtn = findViewById(selectedMarkerId);

            String levelText = levelBtn.getText().toString();
            String markerText = markerBtn.getText().toString();

            String toastText = levelText + " : " +markerText;

            if (levelText.equals(this.getString(R.string.hard_level))){
                //start 5 * 5 board game
                startActivity(board5);
            } else {
                //Start 3 * 3 board game
                startActivity(board3);
            }

            this.showToast(toastText);


        }else {

            String text = "Ensure You have selected level and marker!";
            this.showToast(text);
        }

    }

    public void showToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
