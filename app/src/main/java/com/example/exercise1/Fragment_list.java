package com.example.exercise1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment_list extends Fragment {

    private AdapterRecord arrayAdapter;
    private static ArrayList<Record> records;
    private ListView records_LAY_list;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        records_LAY_list = view.findViewById(R.id.records_LAY_list);
        records = new ArrayList<Record>();

        records.add(new Record(""+MenuActivity.getName(), GameManager.getSumBreads()));
        records.add(new Record("ron", 23));
        records.add(new Record("or", 69));

        arrayAdapter = new AdapterRecord(getContext() ,records);
        records_LAY_list.setAdapter(arrayAdapter);

        return view;
    }


    public Fragment_list setRecords(ArrayList<Record> records) {
        this.records = records;
        return this;
    }

    public static ArrayList<Record> getRecords() {
        return records;
    }
}