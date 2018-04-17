package com.example.jqhn.climbchallenge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqhn on 4/10/18.
 */

public class BoardSize3 extends AppCompatActivity {

    Player humanPlayer;
    Player compPlayer;

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

        //Initialize Board values
        this.initializeBoardValues();

        context = getApplicationContext();


        Intent intent = getIntent();

        String humanLabel =  intent.getStringExtra("userLabel");

        if (humanLabel.equals("X")){
            humanPlayer = new Player("X", 0);
            compPlayer = new Player("O", 0);
        } else {
            humanPlayer = new Player("O", 0);
            compPlayer = new Player("X", 0);
        }

    }

    public void computerPlay(){

        //Check if there is move with a possible win


        //Check if on the next move the opponent has a winning move if yes block else

        //Evaluate the best possible move
        humansTurn = true;
    }

    public void initializeBoardValues(){
        for (int row = 0; row < boardValues.length; row++){
            for (int col = 0; col < boardValues[row].length; col++){
                boardValues[row][col] = "null";
            }
        }
    }

    public void showScoreBoard(){
        TextView humanScoreLabel = findViewById(R.id.human_score);
        TextView aiScoreLabel = findViewById(R.id.ai_score);
        String humanLabel = "Your Score " + humanPlayer.score;
        String aiLabel = "Computer Score " + compPlayer.score;

        humanScoreLabel.setText(humanLabel);
        aiScoreLabel.setText(aiLabel);

    }

    public void onTextViewClicked(View v) {

        if(humansTurn){
            //Human playing
            String id = getResources().getResourceEntryName(v.getId());

            for (int row = 0; row < 3; row++){
                for (int col = 0; col < btnIds[row].length; col++){

                    if(id.equals(btnIds[row][col])){

                        if(boardValues[row][col].equals("null")) {
                            ((Button) v).setText(humanPlayer.marker);

                            //Initialize the corresponding array cell with the human label
                            boardValues[row][col] = humanPlayer.marker;

                            //Check if the human won
                            if(this.wasThereAWin(humanPlayer.marker)){
                                //Add human score and reset the board
                                this.showToast("You Won!");
                                this.resetBoard();
                                humanPlayer.score += 1;
                                showScoreBoard();

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
                        break;
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

    public List<Cell> emptySlots(){
        List<Cell> availableSlots = new ArrayList<>();
        for (int i = 0; i < boardValues.length; i++){
            for (int j = 0; j < boardValues[i].length; j++){
                if (boardValues[i][j].equals("null")){
                    availableSlots.add(new Cell(i, j));
                }
            }
        }

        return availableSlots;
    }

    public boolean wasThereAWin(String currentPlayerMarker){
        boolean result = false;
        if(boardValues[0][0].equals(currentPlayerMarker) && boardValues[0][1].equals(currentPlayerMarker) && boardValues[0][2].equals(currentPlayerMarker) ||
                boardValues[1][0].equals(currentPlayerMarker) && boardValues[1][1].equals(currentPlayerMarker) && boardValues[1][2].equals(currentPlayerMarker) ||
                boardValues[2][0].equals(currentPlayerMarker) && boardValues[2][1].equals(currentPlayerMarker) && boardValues[2][2].equals(currentPlayerMarker) ||
                //Diagonals
                boardValues[0][0].equals(currentPlayerMarker) && boardValues[1][1].equals(currentPlayerMarker) && boardValues[2][2].equals(currentPlayerMarker) ||
                boardValues[0][2].equals(currentPlayerMarker) && boardValues[1][1].equals(currentPlayerMarker) && boardValues[2][0].equals(currentPlayerMarker) ||
                //Columns
                boardValues[0][0].equals(currentPlayerMarker) && boardValues[1][0].equals(currentPlayerMarker) && boardValues[2][0].equals(currentPlayerMarker) ||
                boardValues[0][1].equals(currentPlayerMarker) && boardValues[1][1].equals(currentPlayerMarker) && boardValues[2][1].equals(currentPlayerMarker) ||
                boardValues[0][2].equals(currentPlayerMarker) && boardValues[1][2].equals(currentPlayerMarker) && boardValues[2][2].equals(currentPlayerMarker)
) {
            result = true;
        }

        return  result;
    }

    public void minimax(){
        int result = -1;
        List<Cell> availableSlots = this.emptySlots();

        // checks for the terminating states such as win, lose, and tie
        if (wasThereAWin(humanPlayer.marker)){
            result = -10;
        }
        else if (wasThereAWin(compPlayer.marker)){
            result = 10;
        }
        else if (availableSlots.size() == 0){
            result = 0;
        }
    }

    public void resetBoard() {

        for (int row = 0; row < btnIds.length; row++){
            for (int col = 0; col < btnIds.length; col++){
                String currBtnId = btnIds[row][col];
                int id = getResources().getIdentifier(currBtnId, "id", context.getPackageName());
                Button currBtn = findViewById(id);
                currBtn.setText("");

            }
        }

        initializeBoardValues();
        humansTurn = true;
    }

    public boolean isBoardFilled(){
        boolean result = true;
        List<Cell> emptySlots = this.emptySlots();

        if(emptySlots.size() != 0) {
            result = false;
        }

        return result;
    }
}
