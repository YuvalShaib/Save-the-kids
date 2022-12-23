package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        Fragment_list fragment_list = new Fragment_list();
        getSupportFragmentManager().beginTransaction().add(R.id.records_FRG_list, fragment_list).commit();
        ///SharedPreferences.Editor editor = sharedPreferences.edit();
    }



    public void editScore(String name, int score) {
        sharedPreferences.edit().putString("", MenuActivity.getMenu_ETX_name().getText().toString());

    }



}