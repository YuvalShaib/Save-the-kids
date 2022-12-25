package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {
    Button records_BTN_backMenu;


    private ArrayList<Record> records;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        Fragment_list fragment_list = new Fragment_list();
        getSupportFragmentManager().beginTransaction().add(R.id.records_FRG_list, fragment_list).commit();


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.records_FRG_map, new Fragment_Map());
        fragmentTransaction.commit();



        findViews();
        initViews();

    }

    private void findViews() {
        records_BTN_backMenu= findViewById(R.id.records_BTN_backMenu);
    }

    private void initViews() {
        records_BTN_backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });
    }


    private void openNewActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

//    public void editRecord(String name, int Record) {
//        sharedPreferences.edit().putString("", MenuActivity.getEName());
//
//    }

}