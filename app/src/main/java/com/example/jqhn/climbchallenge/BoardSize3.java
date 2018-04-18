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

                        if(this.boardValues[row][col].equals("null")) {
                            ((Button) v).setText(humanPlayer.marker);

                            //Initialize the corresponding array cell with the human label
                            this.boardValues[row][col] = humanPlayer.marker;

                            //Check if the human won
                            if(this.wasThereAWin(humanPlayer.marker, this.boardValues)){
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
                                    this.showToast("Its was a draw");
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

    public void computerPlay(){

        //Check if there is move with a possible win
        Move winningMove = this.checkPossibleWinningMove(compPlayer.marker);

//        if(winningMove.col != -1 && winningMove.row != -1){
//            showToast("Before Checking if there is a possible winning move for the comp" + winningMove.col + ":" + winningMove.row);
//            //A winning move was found
//            playAMove(winningMove);
//        }else {
//            showToast("Checking if there is a possible winning move for the human");
//            //Check if on the next move the opponent has a winning move if yes block else
//            Move oponentWinningMove = this.checkPossibleWinningMove(humanPlayer.marker);
//            if(oponentWinningMove.col != -1 && oponentWinningMove.row != -1){
//                playAMove(oponentWinningMove);
//            } else {
//                //Evaluate the best possible move
//                minimax();
//            }
//
//        }
        humansTurn = true;
    }

    public void playAMove(Move move){
        Button btnToPlay = null;
        if (move.row == 0 && move.col == 0){
            btnToPlay = findViewById(R.id.button_1);
        } else if(move.row == 0 && move.col == 1) {
            btnToPlay = findViewById(R.id.button_2);
        }else if(move.row == 0 && move.col == 2) {
            btnToPlay = findViewById(R.id.button_3);
        }else if(move.row == 1 && move.col == 0) {
            btnToPlay = findViewById(R.id.button_4);
        }else if(move.row == 1 && move.col == 1) {
            btnToPlay = findViewById(R.id.button_5);
        }else if(move.row == 1 && move.col == 2) {
            btnToPlay = findViewById(R.id.button_6);
        }else if(move.row == 2 && move.col == 0) {
            btnToPlay = findViewById(R.id.button_7);
        }else if(move.row == 2 && move.col == 1) {
            btnToPlay = findViewById(R.id.button_8);
        }else if(move.row == 2 && move.col == 2) {
            btnToPlay = findViewById(R.id.button_9);
        }

        if(btnToPlay != null){
            btnToPlay.setText(compPlayer.marker);
        }
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

    public boolean wasThereAWin(String currentPlayerMarker, String [][] currBoardValues){
        boolean result = false;
        if(currBoardValues[0][0].equals(currentPlayerMarker) && currBoardValues[0][1].equals(currentPlayerMarker) && currBoardValues[0][2].equals(currentPlayerMarker) ||
                currBoardValues[1][0].equals(currentPlayerMarker) && currBoardValues[1][1].equals(currentPlayerMarker) && currBoardValues[1][2].equals(currentPlayerMarker) ||
                currBoardValues[2][0].equals(currentPlayerMarker) && currBoardValues[2][1].equals(currentPlayerMarker) && currBoardValues[2][2].equals(currentPlayerMarker) ||
                //Diagonals
                currBoardValues[0][0].equals(currentPlayerMarker) && currBoardValues[1][1].equals(currentPlayerMarker) && currBoardValues[2][2].equals(currentPlayerMarker) ||
                currBoardValues[0][2].equals(currentPlayerMarker) && currBoardValues[1][1].equals(currentPlayerMarker) && currBoardValues[2][0].equals(currentPlayerMarker) ||
                //Columns
                currBoardValues[0][0].equals(currentPlayerMarker) && currBoardValues[1][0].equals(currentPlayerMarker) && currBoardValues[2][0].equals(currentPlayerMarker) ||
                currBoardValues[0][1].equals(currentPlayerMarker) && currBoardValues[1][1].equals(currentPlayerMarker) && currBoardValues[2][1].equals(currentPlayerMarker) ||
                currBoardValues[0][2].equals(currentPlayerMarker) && currBoardValues[1][2].equals(currentPlayerMarker) && currBoardValues[2][2].equals(currentPlayerMarker)
) {
            result = true;
        }

        return  result;
    }

    public int minimax(){
        int result = -1;
        List<Cell> availableSlots = this.emptySlots();

        // checks for the terminating states such as win, lose, and tie
        if (wasThereAWin(humanPlayer.marker, boardValues)){
            result = -10;
        }
        else if (wasThereAWin(compPlayer.marker, boardValues)){
            result = 10;
        }
        else if (availableSlots.size() == 0){
            result = 0;
        } else {
            List<Move> moves = new ArrayList<> ();
            for (Cell cell: availableSlots) {
                moves.add(new Move(cell.row, cell.col));
            }
        }

        return result;
    }

    public Move checkPossibleWinningMove(String currentPLayerMaker){

        Move toPlayMove = new Move(-1, -1);
        List<Cell> emptySlots = this.emptySlots();
        String toPrint = "";
        for (Cell cell: emptySlots){
            String [][] newBoardVals = this.boardValues;
            newBoardVals[cell.row][cell.col] = currentPLayerMaker;

            for(int row = 0; row < this.boardValues.length; row++){
                for(int col = 0; col < this.boardValues[row].length; col++){
                    toPrint += boardValues[row][col];
                }
                toPrint += "\n";
            }
//            if(wasThereAWin(currentPLayerMaker, newBoardVals)){
////                toPlayMove.row = cell.row;
////                toPlayMove.col = cell.col;
//                showToast("There was a winning move");
//                break;
//            }
        }

        showToast(toPrint);


        return toPlayMove;
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
