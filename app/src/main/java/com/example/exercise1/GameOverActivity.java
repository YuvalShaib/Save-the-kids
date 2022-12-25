package com.example.exercise1;

import static com.google.android.gms.location.Priority.PRIORITY_BALANCED_POWER_ACCURACY;
import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.Freezable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class GameOverActivity extends AppCompatActivity {
    TextView gameover_ETX_scoreField;
    Button gameover_BTN_backMenu;
    Record record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        if (savedInstanceState != null) {
            return;
        }

        findViews();
        initViews();


    }


    private void findViews() {
        gameover_BTN_backMenu = findViewById(R.id.gameover_BTN_backMenu);
        gameover_ETX_scoreField = findViewById(R.id.gameover_ETX_scoreField);

    }

    private void initViews() {
        final String[] locationString = {""};

        gameover_ETX_scoreField.setText(String.valueOf(GameManager.getSumBreads()));

        gameover_BTN_backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewMenuActivity();
            }
        });

        askForLocationPermission();


        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getCurrentLocation(102, null)
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.d("location", "no location: ");
                        // Got last known location. In some rare situations, this can be null.
                        if (location != null) {
                            // Convert the location to a string
                            locationString[0] = location.getLatitude() + ", " + location.getLongitude();
                            Log.d("location", "location.getLatitude() + \", \" + location.getLongitude()"+ locationString[0]);
                            record= new Record(MenuActivity.getEName(), GameManager.getSumBreads(), locationString[0]);

                            GameManager.resetGame();
                            updatedRecordsArray(record);
                        }
                    }
                });
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    private void askForLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION ,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }
    }

    private void updatedRecordsArray(Record record) {
        int resRecord;
        Log.d("tag", "updatedRecordsArray: " + record);
        resRecord= Fragment_list.insertRecord(record);


    }

    private void openNewMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

}