package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    TextView game_TXT_score;
    ImageButton game_IMG_arrowRight;
    ImageButton game_IMG_arrowLeft;
    private int kidsIndex = 2;
    private ImageView[] game_IMG_hearts;
    private ImageView[] game_LOT_kids;
    private ImageView[][] game_LOT_witches;
    private ImageView[][] game_IMG_breads;
    private GameManager gameManager;
    private Timer timer;
    public static boolean sensorsEnabled = true;



    public ArrayList<Record> records;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            return;
        }

        int rows = getResources().getInteger(R.integer.ROWSNUMBER);
        int cols = getResources().getInteger(R.integer.WITCHSLANES);
        int lives = getResources().getInteger(R.integer.LIVES);

        MediaPlayer mp = new MediaPlayer();
        mp = MediaPlayer.create(this, R.raw.collision_sound);



        game_LOT_witches = new ImageView[rows][cols];
        game_IMG_breads = new ImageView[rows][cols];

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        findViews();
        initViews();
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        Toast t = Toast.makeText(this, "Oops", Toast.LENGTH_SHORT);
        gameManager = new GameManager(lives, rows, cols, v, t, mp);

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

        for (int i = 0; i < game_IMG_breads.length; i++) {
            for (int j = 0; j < game_IMG_breads[0].length; j++) {
                String breadId = "game_IMG_bread" + i + j;
                int resId = getResources().getIdentifier(breadId, "id", getPackageName());
                game_IMG_breads[i][j] = findViewById(resId);
                game_IMG_breads[i][j].setVisibility(View.INVISIBLE);
            }
        }
        game_TXT_score = findViewById(R.id.game_TXT_score);
        game_IMG_arrowRight = findViewById(R.id.game_IMG_arrowRight);
        game_IMG_arrowLeft = findViewById(R.id.game_IMG_arrowLeft);
        game_LOT_kids = new ImageView[]{
                findViewById(R.id.game_IMG_kids1),
                findViewById(R.id.game_IMG_kids2),
                findViewById(R.id.game_IMG_kids3),
                findViewById(R.id.game_IMG_kids4),
                findViewById(R.id.game_IMG_kids5)

        };
        game_IMG_hearts = new ImageView[]{
                findViewById(R.id.game_IMG_heart1),
                findViewById(R.id.game_IMG_heart2),
                findViewById(R.id.game_IMG_heart3)
        };

    }

    private void initViews() {

        records = Fragment_list.getRecords();

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
        }, 0, MenuActivity.getPeriod());
    }

    private void updateUI() {

        gameManager.updateGame();
        boolean[][] witchesVisibility = gameManager.getwitchesVisibility();
        boolean[][] breadsVisibility = gameManager.getBreadsVisibility();
        for (int i = 0; i < gameManager.rows; i++) {
            for (int j = 0; j < gameManager.cols; j++) {
                game_LOT_witches[i][j].setVisibility(witchesVisibility[i][j] ? View.VISIBLE : View.INVISIBLE);
            }
        }

        for (int i = 0; i < gameManager.rows; i++) {
            for (int j = 0; j < gameManager.cols; j++) {
                game_IMG_breads[i][j].setVisibility(breadsVisibility[i][j] ? View.VISIBLE : View.INVISIBLE);
            }
        }
        updateLives();
        updateSumBreads();

    }

    private void updateSumBreads() {
            game_TXT_score.setText(" " + gameManager.getSumBreads());
    }


    private void updateLives() {
        if (gameManager.getLives() < game_IMG_hearts.length) {
            game_IMG_hearts[gameManager.getLives()].setVisibility(View.INVISIBLE);
        }

        if(gameManager.getLives() == 0) {
            timer.cancel();
            openNewActivity();
        }
    }


    public void openNewActivity(){
        Log.d("", "openNewActivity: " + records);
        Intent intent = new Intent(this, GameOverActivity.class);
        //intent.putExtra( "score", gameManager.getSumBreads());

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorsEnabled) {
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorsEnabled){
            sensorManager.unregisterListener(this);
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
        // openNewActivity();
       // stepDetector.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }


    public ImageView[] getGame_LOT_kids() {
        return game_LOT_kids;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            Log.d("sensor", "sensor location x " + x);
            // Use the x-axis acceleration to move the object left or right
            if (x > 1 && x < 2) {
                // Tilt the phone to the right, move the object to the right
                moveKids(-1);
            } else if (x < -1 && x > -2) {
                // Tilt the phone to the left, move the object to the left
                moveKids(1);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}