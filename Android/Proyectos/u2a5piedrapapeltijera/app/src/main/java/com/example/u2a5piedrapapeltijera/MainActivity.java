package com.example.u2a5piedrapapeltijera;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageButton rockButton;
    private ImageButton paperButton;
    private ImageButton scissorsButton;
    private TextView resultTextView;
    private Button playButton;
    private int playerScore = 0;
    private int computerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorsButton = findViewById(R.id.scissorsButton);
        resultTextView = findViewById(R.id.resultTextView);
        playButton = findViewById(R.id.playButton);

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionSelected("Rock");
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionSelected("Paper");
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionSelected("Scissors");
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void onOptionSelected(String playerChoice) {
        String computerChoice = getRandomChoice();
        updateResult(playerChoice, computerChoice);
        updateScores();
        checkForWinner();
    }

    private String getRandomChoice() {
        Random random = new Random();
        int choice = random.nextInt(3);
        switch (choice) {
            case 0:
                return "Rock";
            case 1:
                return "Paper";
            default:
                return "Scissors";
        }
    }

    private void updateResult(String playerChoice, String computerChoice) {
        String result = "";
        if (playerChoice.equals(computerChoice)) {
            result = "It's a tie!";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            result = "You win!";
            playerScore++;
        } else {
            result = "You lose!";
            computerScore++;
        }
        resultTextView.setText("Result: " + result);
    }

    private void updateScores() {
        resultTextView.append("\nScores - You: " + playerScore + ", Computer: " + computerScore);
    }

    private void checkForWinner() {
        if (playerScore == 3 || computerScore == 3) {
            String winner = (playerScore == 3) ? "You" : "Computer";
            resultTextView.append("\n" + winner + " wins the game!");
            disableButtons();
        }
    }

    private void disableButtons() {
        rockButton.setEnabled(false);
        paperButton.setEnabled(false);
        scissorsButton.setEnabled(false);
        playButton.setVisibility(View.VISIBLE);
    }

    private void resetGame() {
        playerScore = 0;
        computerScore = 0;
        resultTextView.setText("Result: ");
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorsButton.setEnabled(true);
        playButton.setVisibility(View.GONE);
    }
}
