package com.example.exercise1;

import android.media.MediaPlayer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class GameManager {

    int kidsLocation = 2;
    Random random = new Random();
    private boolean[][] witchesVisibility;
    private boolean[][] breadsVisibility;
    MediaPlayer mp;

    private static int sumBreads = 0;



    int rows;
    int cols;
    int lives;
    //int sumBreads =0;
    int ticks =0;
    int randWitch=0;
    private Vibrator vibrator;
    private Toast toast;

    public GameManager(int lives, int rows, int cols ,  Vibrator vibrator, Toast toast, MediaPlayer mp) {
        this.lives= lives;
        this.rows = rows;
        this.cols = cols;
        this.mp= mp;

        witchesVisibility = new boolean[rows][cols];
        breadsVisibility = new boolean[rows][cols];

        for (boolean[] rowColumn : witchesVisibility) {
            Arrays.fill(rowColumn, false);
       }

        for (boolean[] rowColumn : breadsVisibility) {
            Arrays.fill(rowColumn, false);
        }


        this.vibrator=vibrator;
        this.toast=toast;
    }

    public static void resetGame(){
        sumBreads = 0;
    }

    public void moveKids(int direction) {
        if(direction >= 0 && kidsLocation < 4) //move kids right
            kidsLocation++;
        if(direction < 0 && kidsLocation > 0) //move kids left
            kidsLocation--;
    }

    public void updateWitches() {
        for(int i = rows -1; i > 0; i--) {
            for (int j = 0; j < cols; j++) {
                witchesVisibility[i][j] = witchesVisibility[i-1][j];
            }
        }
        Arrays.fill(witchesVisibility[0], Boolean.FALSE);
    }

    public void updatebreads() {
        for (int i = rows-1 ; i > 0; i--) {
            for (int j = 0; j < cols; j++) {
                breadsVisibility[i][j] = breadsVisibility[i-1][j];
            }
        }
        Arrays.fill(breadsVisibility[0], Boolean.FALSE);
    }


    public void newRow() {
        randWitch= random.nextInt(cols);
        witchesVisibility[0][randWitch]=true;

    }

    public void newBread() {
        int randBread = random.nextInt(cols);

        while (randBread == randWitch) {
            randBread = random.nextInt(cols);
        }

        breadsVisibility[0][randBread]=true;


    }
    public void updateGame() {
        ticks++;
        checkCollision();
        updateWitches();
        checkPickBreads();
        updatebreads();
        if(ticks%2==0) {
            newRow();
        }
        if (ticks%3 ==0) {
            newBread();
        }


    }

    public int getKidsLocation() {
        return kidsLocation;
    }

    public int getLives() {
        return lives;
    }

    private void checkCollision() {

        if(witchesVisibility[rows-1][kidsLocation])
        {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            toast.show();

            mp.start();

            if(lives > 0) {
                lives--;
            }
            else {

            }
        }
    }


    private void checkPickBreads() {
        if(breadsVisibility[rows-1][kidsLocation])
        {
            sumBreads++;
        }
    }

    public static int getSumBreads() {
        return sumBreads;
    }

    public boolean[][] getwitchesVisibility(){
        return witchesVisibility;
    }

    public boolean[][] getBreadsVisibility(){
        return breadsVisibility;
    }





}
