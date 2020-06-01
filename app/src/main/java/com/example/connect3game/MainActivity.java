package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //yellow = 0, red = 1, empty = 2

    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};  //keep track of which is empty

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;

    boolean gameActive = true;  //if no player has won

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        Log.i("counter", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());  //get which counter is being occupied

        if(gameState[tappedCounter] == 2 && gameActive) { // if counter space is not empty i.e check using counter tags
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500); //y to top, x to left

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().rotation(360).translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //won
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    gameActive = false;

                    Button playAgainButton = (Button) findViewById(R.id.playAgain);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText("Winner is: " + winner);

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playGame(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgain);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null); //set imageView to empty
        }

        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        activePlayer = 0;

        gameActive = true;  //if no player has won

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
