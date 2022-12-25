package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    private static String strName;
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
                    GameActivity.sensorsEnabled = false;

                }
                else if(checkedId == R.id.menu_RBN_sensors) {
                    GameActivity.sensorsEnabled = true;
                }
            }
        });


        menu_BTN_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                strName= menu_ETX_name.getText().toString();
                if(strName.isEmpty()) {
                    Toast t = Toast.makeText(view.getContext(), "Please enter name", Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }

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


    public static String getEName(){
        return strName;
    }

    public static String getName() {
        return menu_ETX_name.getText().toString();
    }

}