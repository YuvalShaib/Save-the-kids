package com.example.exercise1;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class GameManager {

    int kidsLocation = 1;
    Random random = new Random();
    private boolean[][] witchesVisibility;
    int rows;
    int cols;
    int lives;
    int ticks =0;
    int ticksToNewWitch = 2;
    private Vibrator vibrator;
    private Toast toast;

    public GameManager(int lives, int rows, int cols ,  Vibrator vibrator, Toast toast) {
        this.lives= lives;
        this.rows = rows;
        this.cols = cols;
        witchesVisibility = new boolean[rows][cols];

        for (boolean[] rowColumn : witchesVisibility) {
            Arrays.fill(rowColumn, false);
       }
        this.vibrator=vibrator;
        this.toast=toast;
    }

    public void moveKids(int direction) {
        if(direction >= 0 && kidsLocation < 2) //move kids right
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

    public void newRow() {
        int rand= random.nextInt(cols);
        witchesVisibility[0][rand]=true;
    }

    public void updateGame() {
        ticks++;
        checkCollision();
        updateWitches();
        if(ticks==ticksToNewWitch) {
            newRow();
            ticks=0;
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

            if(lives > 0) {
                lives--;
            }
        }
    }

    public boolean[][] getwitchesVisibility(){
        return witchesVisibility;
    }





}
