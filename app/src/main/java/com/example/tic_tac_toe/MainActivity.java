//Moa Wieweg, 2023

package com.example.tic_tac_toe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Tic Tac Toe game. Play player vs player or against the computer.
 * There are three difficulty levels when playing against the computer:
 * Level one: computer chooses random box
 * Level two: computer checks if player has two boxes in a row and chooses third box in that case, if not it will choose a random box
 * Level three: like level two, but if it does not have to block the player from winning it works on getting three in a row itself
 */
public class MainActivity extends AppCompatActivity {

    private int[] boxes = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private final int[][] winCombinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private int counter = 0;
    private int player = 1;
    private boolean vsComputer = false;
    private int difficultyLevel;
    private ArrayList<ImageView> possibleImageViews;

    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    TextView currentStatusTextView;
    Button endGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentStatusTextView = findViewById(R.id.currentStatusTextView);
        endGameButton = findViewById(R.id.button);
        img1 = findViewById(R.id.imageView1);
        img2 = findViewById(R.id.imageView2);
        img3 = findViewById(R.id.imageView3);
        img4 = findViewById(R.id.imageView4);
        img5 = findViewById(R.id.imageView5);
        img6 = findViewById(R.id.imageView6);
        img7 = findViewById(R.id.imageView7);
        img8 = findViewById(R.id.imageView8);
        img9 = findViewById(R.id.imageView9);

        possibleImageViews = new ArrayList<>(Arrays.asList(img1, img2, img3, img4, img5, img6, img7, img8, img9));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            vsComputer = extras.getBoolean("againstComputer");
        }

        if (vsComputer) {
            Random random = new Random();
            player = random.nextInt(2) + 1;
            if (player == 1) {
                currentStatusTextView.setText(R.string.you_start);
            } else {
                currentStatusTextView.setText(R.string.computer_starts);
                computerStarts();
            }
            difficultyLevel = extras.getInt("difficulty");
        }

        endGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxes[0] == 0) {
                    if (vsComputer) {
                        singlePlayerAddNewMarker(img1, 0);
                    } else {
                        playerVsPlayerAddNewMarker(img1, 0);
                    }
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxes[1] == 0) {
                    if (vsComputer) {
                        singlePlayerAddNewMarker(img2, 1);
                    } else {
                        playerVsPlayerAddNewMarker(img2, 1);
                    }
                }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxes[2] == 0) {
                    if (vsComputer) {
                        singlePlayerAddNewMarker(img3, 2);
                    } else {
                        playerVsPlayerAddNewMarker(img3, 2);
                    }
                }
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxes[3] == 0) {
                    if (vsComputer) {
                        singlePlayerAddNewMarker(img4, 3);
                    } else {
                        playerVsPlayerAddNewMarker(img4, 3);
                    }
                }
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxes[4] == 0) {
                    if (vsComputer) {
                        singlePlayerAddNewMarker(img5, 4);
                    } else {
                        playerVsPlayerAddNewMarker(img5, 4);
                    }
                }
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxes[5] == 0) {
                    if (vsComputer) {
                        singlePlayerAddNewMarker(img6, 5);
                    } else {
                        playerVsPlayerAddNewMarker(img6, 5);
                    }
                }
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxes[6] == 0) {
                    if (vsComputer) {
                        singlePlayerAddNewMarker(img7, 6);
                    } else {
                        playerVsPlayerAddNewMarker(img7, 6);
                    }
                }
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxes[7] == 0) {
                    if (vsComputer) {
                        singlePlayerAddNewMarker(img8, 7);
                    } else {
                        playerVsPlayerAddNewMarker(img8, 7);
                    }
                }
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxes[8] == 0) {
                    if (vsComputer) {
                        singlePlayerAddNewMarker(img9, 8);
                    } else {
                        playerVsPlayerAddNewMarker(img9, 8);
                    }
                }
            }
        });
    }

    private void computerStarts() {
        for (ImageView img : possibleImageViews) {
            img.setClickable(false);
        }
        int computerBox;
        if (difficultyLevel == 3) {
            computerBox = difficultyThree();
        } else if (difficultyLevel == 2) {
            computerBox = checkPlayerWinCombinations();
        } else {
            computerBox = randomBox();
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                singlePlayerAddNewMarker(possibleImageViews.get(computerBox), computerBox);
            }
        }, 1200);
    }

    private int randomBox() {
        Random random = new Random();
        int randValue;
        do {
            randValue = random.nextInt(9);
        } while (boxes[randValue] != 0);
        return randValue;
    }

    private int checkPlayerWinCombinations() {
        for (int[] combination : winCombinations) {
            if (boxes[combination[0]] == 0 && boxes[combination[1]] == 1 && boxes[combination[2]] == 1) {
                return combination[0];
            } else if (boxes[combination[0]] == 1 && boxes[combination[1]] == 0 && boxes[combination[2]] == 1) {
                return combination[1];
            } else if (boxes[combination[0]] == 1 && boxes[combination[1]] == 1 && boxes[combination[2]] == 0) {
                return combination[2];
            }
        }
        if (difficultyLevel == 2) {
            return randomBox();
        }
        return -1;
    }

    private int difficultyThree() {
        int stopFromWinning = checkPlayerWinCombinations();
        if (stopFromWinning != -1) {
            return stopFromWinning;
        }

        ArrayList<int[]> closeCombinations = new ArrayList<>();
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] == 2) {
                closeCombinations = findCloseCombinationsComputer(i);
                if (!closeCombinations.isEmpty()) {
                    break;
                }
            }
        }

        if (!closeCombinations.isEmpty()) {
            for (int i = 0; i < closeCombinations.size(); i++) {
                int[] combination = closeCombinations.get(i);
                for (int j : combination) {
                    if (boxes[j] == 0) {
                        return j;
                    }
                }
            }
        }
        return randomBox();
    }

    private ArrayList<int[]> findCloseCombinationsComputer(int value) {
        boolean possibleCombination;
        int possibleBoxesCount;
        ArrayList<int[]> closeCombinations = new ArrayList<>();
        for (int[] combination : winCombinations) {
            possibleBoxesCount = 0;
            possibleCombination = false;
            for (int i : combination) {
                if (i == value) {
                    possibleCombination = true;
                } else if (boxes[i] == 0 || boxes[i] == 2) {
                    possibleBoxesCount++;
                }
            }
            if (possibleCombination && possibleBoxesCount == 2) {
                closeCombinations.add(combination);
            }
        }
        return closeCombinations;
    }

    private void singlePlayerAddNewMarker(ImageView imageView, int chosenBox) {
        counter++;
        boxes[chosenBox] = player;
        if (player == 1) {
            imageView.setImageResource(R.drawable.x);
            if (hasWon()) {
                currentStatusTextView.setText(R.string.you_won);
                gameOver(R.string.you_won);
            } else if (counter == 9) {
                currentStatusTextView.setText(R.string.it_s_a_tie);
                gameOver(R.string.it_s_a_tie);
            } else {
                int computerBox;
                if (difficultyLevel == 3) {
                    computerBox = difficultyThree();
                } else if (difficultyLevel == 2) {
                    computerBox = checkPlayerWinCombinations();
                } else {
                    computerBox = randomBox();
                }
                player = 2;
                currentStatusTextView.setText(R.string.turn_computer);
                for (ImageView img : possibleImageViews) {
                    img.setClickable(false);
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        singlePlayerAddNewMarker(possibleImageViews.get(computerBox), computerBox);
                    }
                }, 700);
            }
        } else {
            imageView.setImageResource(R.drawable.o);
            for (ImageView img : possibleImageViews) {
                img.setClickable(true);
            }
            if (hasWon()) {
                currentStatusTextView.setText(R.string.the_computer_won);
                gameOver(R.string.the_computer_won);
            } else if (counter == 9) {
                currentStatusTextView.setText(R.string.it_s_a_tie);
                gameOver(R.string.it_s_a_tie);
            } else {
                player = 1;
                currentStatusTextView.setText(R.string.turn_your_turn);
            }
        }
    }

    private void playerVsPlayerAddNewMarker(ImageView imageView, int chosenBox) {
        counter++;
        boxes[chosenBox] = player;
        if (player == 1) {
            imageView.setImageResource(R.drawable.x);
            if (hasWon()) {
                currentStatusTextView.setText(R.string.player_x_won);
                gameOver(R.string.player_x_won);
            } else if (counter == 9) {
                currentStatusTextView.setText(R.string.it_s_a_tie);
                gameOver(R.string.it_s_a_tie);
            } else {
                player = 2;
                currentStatusTextView.setText(R.string.turn_player_o);
            }
        } else {
            imageView.setImageResource(R.drawable.o);
            if (hasWon()) {
                currentStatusTextView.setText(R.string.player_o_won);
                gameOver(R.string.player_o_won);
            } else if (counter == 9) {
                currentStatusTextView.setText(R.string.it_s_a_tie);
                gameOver(R.string.it_s_a_tie);
            } else {
                player = 1;
                currentStatusTextView.setText(R.string.turn_player_x);
            }
        }
    }

    private boolean hasWon() {
        for (int[] combination : winCombinations) {
            if (boxes[combination[0]] == player && boxes[combination[1]] == player && boxes[combination[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private void newGame() {
        counter = 0;
        if (vsComputer) {
            Random random = new Random();
            player = random.nextInt(2) + 1;
            if (player == 1) {
                currentStatusTextView.setText(R.string.you_start);
            } else {
                currentStatusTextView.setText(R.string.computer_starts);
                computerStarts();
            }
        } else {
            player = 1;
            currentStatusTextView.setText(R.string.player_x_starts);
        }
        Arrays.fill(boxes, 0);

        img1.setImageResource(R.drawable.blue_grid_box);
        img2.setImageResource(R.drawable.blue_grid_box);
        img3.setImageResource(R.drawable.blue_grid_box);
        img4.setImageResource(R.drawable.blue_grid_box);
        img5.setImageResource(R.drawable.blue_grid_box);
        img6.setImageResource(R.drawable.blue_grid_box);
        img7.setImageResource(R.drawable.blue_grid_box);
        img8.setImageResource(R.drawable.blue_grid_box);
        img9.setImageResource(R.drawable.blue_grid_box);
    }

    private void gameOver(int title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.dialog_game_over, null);
        Button newGameButton = alertLayout.findViewById(R.id.gameOverButton);
        Button menuButton = alertLayout.findViewById(R.id.menuButton);
        TextView gameOverTextView = alertLayout.findViewById(R.id.gameOverTextView);
        gameOverTextView.setText(title);

        builder.setCancelable(false);
        builder.setView(alertLayout);
        AlertDialog alertDialog = builder.create();

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(300);
        }

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
                alertDialog.dismiss();
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
}