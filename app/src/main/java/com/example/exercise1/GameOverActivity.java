package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    TextView gameover_ETX_scoreField;
    Button gameover_BTN_backMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        findViews();
        initViews();
    }


    private void findViews() {
        gameover_BTN_backMenu= findViewById(R.id.gameover_BTN_backMenu);
        gameover_ETX_scoreField= findViewById(R.id.gameover_ETX_scoreField);

    }
    private void initViews() {

        gameover_ETX_scoreField.setText(String.valueOf(GameManager.getSumBreads()));

        gameover_BTN_backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewGameActivity();
            }
        });

        updatedRecordsArray();

    }

    private void updatedRecordsArray() {
        //Fragment_list.getRecords().add(new Record(MenuActivity.getMenu_ETX_name().getText().toString(), GameManager.getSumBreads()));
    }

    private void openNewGameActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


}