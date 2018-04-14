package com.example.jqhn.climbchallenge;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jqhn on 4/10/18.
 */

public class BoardSize3 extends AppCompatActivity {

    String myLabel;
    String humanLabel;

    int humanScore = 0; //Human score
    int myScore = 0; //Computer score

    Context context;

    boolean humansTurn = true;

    String[][] boardValues = new String[3][3];

    String [][] btnIds = {
                            {"button_1", "button_2", "button_3"},
                            {"button_4", "button_5", "button_6"},
                            {"button_7", "button_8", "button_9"}
                        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide the action bar
        if(getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.board_size_3);

        context = getApplicationContext();


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

    public void computerPlay(){

        //Check if there is move with a possible win


        //Check if on the next move the opponent has a winning move if yes block else

        //Evaluate the best possible move
        humansTurn = true;
    }

    public void onTextViewClicked(View v) {

        if(humansTurn){
            //Human playing
            String id = getResources().getResourceEntryName(v.getId());

            for (int row = 0; row < 3; row++){
                for (int col = 0; col < 3; col++){
                    if(id.equals(btnIds[row][col])){
                        if(btnIds[row][col] != null) {
                            ((Button) v).setText(humanLabel);

                            //Initialize the corresponding array cell with the human label
                            boardValues[row][col] = humanLabel;

                            //Check if the human won
                            if(this.wasThereAWin(humanLabel)){
                                //Add human score and reset the board

                            } else {
                                //Prevent human from playing until the computer has played
                                humansTurn = false;

                                // Only let the computer play if the board is not filled
                                if (!this.isBoardFilled()){
                                    this.computerPlay();
                                } else {
                                    // Its was a draw and reset the board
                                    this.showToast("Its was a draw, resetting the board");
                                    this.resetBoard();
                                }
                            }
                        }
                    }
                }
            }

        }

    }

    public void showToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public boolean possibleWinningMove(String currPlayerLabel) {


        return false;
    }

    public void counterPossibleForOponent(){}

    public void evaluateBestMove(){}

    public void resetBoard() {

        for (int row = 0; row < btnIds.length; row++){
            for (int col = 0; col < btnIds.length; col++){
                String currBtnId = btnIds[row][col];
                int id = getResources().getIdentifier(currBtnId, "id", context.getPackageName());
                Button currBtn = findViewById(id);
                currBtn.setText("");

            }
        }

        //Reset board values
        boardValues = new String[3][3];
        humansTurn = true;
    }

    public boolean wasThereAWin(String currentPlayerLabel) {

        return false;
    }

    public boolean isBoardFilled(){

        boolean result = true;

        for (int row = 0; row < boardValues.length; row++){
            for (int col = 0; col < boardValues.length; col++){
                if(boardValues[row][col] == null){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
