package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MenuActivity extends AppCompatActivity {
    Button menu_BTN_start;
    Button menu_BTN_records;
    RadioGroup menu_RGP_mode;
    RadioGroup menu_RGP_speed;
    private static EditText menu_ETX_name;


    private static int period=1000;


    public static int getPeriod() {
        return period;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        findViews();
        initViews();
    }


    private void findViews() {
        menu_BTN_start= findViewById(R.id.menu_BTN_start);
        menu_BTN_records= findViewById(R.id.menu_BTN_records);
        menu_RGP_mode = (RadioGroup)findViewById(R.id.menu_RGP_mode);
        menu_RGP_speed = (RadioGroup)findViewById(R.id.menu_RGP_speed);
        menu_ETX_name =findViewById(R.id.menu_ETX_name);


    }

    private void initViews() {

        menu_RGP_speed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.menu_RBN_fast) {
                    period = 500;
                }
                else if(checkedId == R.id.menu_RBN_slow) {
                    period = 1000;
                }

            }
        });

        menu_RGP_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.menu_RBN_buttons) {

                }
                else if(checkedId == R.id.menu_RBN_sensors) {
                    //invisible arrows
                    //sensorsfunction
                }

            }
        });



        menu_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewGameActivity();
            }
        });

        menu_BTN_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){openNewRecordsActivity();}
        });


    }

    public void openNewGameActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void openNewRecordsActivity(){
        Intent intent = new Intent(this, RecordsActivity.class);
        startActivity(intent);
    }

    public static EditText getMenu_ETX_name() {
        return menu_ETX_name;
    }

    public static String getName() {
        return menu_ETX_name.getText().toString();
    }

//    public static String getName(EditText e) {
//        return e.getText().toString();
//    }
}