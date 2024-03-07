package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button singlePLayerButton, playerVsPlayerButton, difficultyOneButton, difficultyTwoButton, difficultyThreeButton;
    TextView difficultyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        singlePLayerButton = findViewById(R.id.singlePlayerButton);
        playerVsPlayerButton = findViewById(R.id.playerVsPlayerButton);
        difficultyOneButton = findViewById(R.id.difficultyOneButton);
        difficultyTwoButton = findViewById(R.id.difficultyTwoButton);
        difficultyThreeButton = findViewById(R.id.difficultyThreeButton);
        difficultyTextView = findViewById(R.id.difficultyTextView);

        singlePLayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficultyOneButton.setVisibility(View.VISIBLE);
                difficultyTwoButton.setVisibility(View.VISIBLE);
                difficultyThreeButton.setVisibility(View.VISIBLE);
                difficultyTextView.setVisibility(View.VISIBLE);
            }
        });

        difficultyOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("againstComputer", true);
                intent.putExtra("difficulty", 1);
                startActivity(intent);
                finish();
            }
        });

        difficultyTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("againstComputer", true);
                intent.putExtra("difficulty", 2);
                startActivity(intent);
                finish();
            }
        });

        difficultyThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("againstComputer", true);
                intent.putExtra("difficulty", 3);
                startActivity(intent);
                finish();
            }
        });

        playerVsPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("againstComputer", false);
                startActivity(intent);
                finish();
            }
        });
    }
}
