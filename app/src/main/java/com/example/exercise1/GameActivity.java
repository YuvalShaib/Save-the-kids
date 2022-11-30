package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    ImageButton game_IMG_arrowRight;
    ImageButton game_IMG_arrowLeft;
    private int kidsIndex = 1;
    private ImageView[] game_IMG_hearts;
    private ImageView[] game_LOT_kids;
    private ImageView[][] game_LOT_witches;
    private GameManager gameManager;
    private Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int rows = getResources().getInteger(R.integer.ROWSNUMBER);
        int cols = getResources().getInteger(R.integer.WITCHSLANES);
        int lives = getResources().getInteger(R.integer.LIVES);

        game_LOT_witches = new ImageView[rows][cols];

        findViews();
        initViews();
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        Toast t = Toast.makeText(this, "Oops", Toast.LENGTH_SHORT);
        gameManager = new GameManager(lives, rows, cols, v, t);

        startTimer();
    }

    private void findViews() {

        for (int i = 0; i < game_LOT_witches.length; i++) {
            for (int j = 0; j < game_LOT_witches[0].length; j++) {
                String witchId = "game_IMG_witch" + i + j;
                int resId = getResources().getIdentifier(witchId, "id", getPackageName());
                game_LOT_witches[i][j] = findViewById(resId);
                game_LOT_witches[i][j].setVisibility(View.INVISIBLE);
            }
        }

        game_IMG_arrowRight = findViewById(R.id.game_IMG_arrowRight);
        game_IMG_arrowLeft = findViewById(R.id.game_IMG_arrowLeft);
        game_LOT_kids = new ImageView[]{
                findViewById(R.id.game_IMG_kids1),
                findViewById(R.id.game_IMG_kids2),
                findViewById(R.id.game_IMG_kids3)
        };
        game_IMG_hearts = new ImageView[]{
                findViewById(R.id.game_IMG_heart1),
                findViewById(R.id.game_IMG_heart2),
                findViewById(R.id.game_IMG_heart3)
        };

    }

    private void initViews() {

        game_IMG_arrowLeft.setOnClickListener(view -> moveKids(-1));

        game_IMG_arrowRight.setOnClickListener(view -> moveKids(1));

    }

    private void moveKids(int direction) {
        game_LOT_kids[gameManager.getKidsLocation()].setVisibility(View.INVISIBLE);
        gameManager.moveKids(direction);
        game_LOT_kids[gameManager.getKidsLocation()].setVisibility(View.VISIBLE);

    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> updateUI());
            }
        }, 0, 1000);
    }

    private void updateUI() {
        gameManager.updateGame();
        boolean[][] witchesVisibility = gameManager.getwitchesVisibility();
        for (int i = 0; i < game_LOT_witches.length; i++) {
            for (int j = 0; j < game_LOT_witches[0].length; j++) {
                game_LOT_witches[i][j].setVisibility(witchesVisibility[i][j] ? View.VISIBLE : View.INVISIBLE);
            }
        }
        updateLives();
    }


    private void updateLives() {
        if (gameManager.getLives() < game_IMG_hearts.length) {
            game_IMG_hearts[gameManager.getLives()].setVisibility(View.INVISIBLE);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        startTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }


}