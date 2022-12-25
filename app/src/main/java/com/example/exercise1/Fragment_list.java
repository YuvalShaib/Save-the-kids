package com.example.exercise1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Fragment_list extends Fragment {

    private AdapterRecord arrayAdapter;
    private static ArrayList<Record> records = new ArrayList<Record>();
    private ListView records_LAY_list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        records_LAY_list = view.findViewById(R.id.records_LAY_list);

        addRecords();

        Log.d("records", "onCreateView: "+ records);

        arrayAdapter = new AdapterRecord(getContext() ,records);
        Log.d("arrayAdapter", "onCreateView: "+ records);
        records_LAY_list.setAdapter(arrayAdapter);

        return view;

    }

    private void saveTopRecords(ArrayList<Record> topRecords) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("top_Record", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        for (int i = 0; i < topRecords.size(); i++) {
            Record record = topRecords.get(i);
            editor.putInt("Record_" + i, record.getIntScore());
            editor.putString("name_" + i, record.getName());
            editor.putString("location_" + i, record.getLocation());
        }
        editor.commit();
    }


    private void loadTopRecords() {
       // ArrayList<Record> topRecords = new ArrayList<>();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("top_Records", Context.MODE_PRIVATE);
        for (int i = 0; i < 10; i++) {
            int record = sharedPreferences.getInt("Record_" + i, 0);
            String name = sharedPreferences.getString("name_" + i, "");
            String location = sharedPreferences.getString("location_" + i, "");
            if (record > 0) {
                records.add(new Record(name, record, location));
            }
        }
    }

    public static int insertRecord(Record record) {
        Log.d("insetRecord", "insertRecord: " + records);
        if(records.isEmpty()){
            records.add(record);
            return 1;
        }

        if(records.size() == 10) {
            if(records.get(records.size()-1).getIntScore() < record.getIntScore()) {
                records.set((records.size() - 1), record);
            }
            else {
                return 0;
            }
        }
        else {
            records.add(record);
        }
        //sortArray(record);
        // Sort the ArrayList in ascending order by Score
        Collections.sort(records, new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                return r1.getIntScore() - r2.getIntScore();
            }
        });
        Collections.reverse(records);
        return 1;
    }

    //if sort doesnt work
    public static void sortArray(Record r) {
        for (int i = 0 ; i > records.size()-1 ; i++) {
            if(r.getIntScore() > records.get(i).getIntScore()) {
                for (int j = records.size()-2 ; j > i; j--) {
                    records.set(j+1, records.get(j));
                }
                records.set(i, r);
            }

        }
    }


    @Override
    public void onDestroy() {
        saveTopRecords(records);
        super.onDestroy();
    }

    public Fragment_list setRecords(ArrayList<Record> records) {
        this.records = records;
        return this;
    }

    public static ArrayList<Record> getRecords() {
        return records;
    }

    public void addRecords() {
        ArrayList<String> locations = new ArrayList<>();
        locations.add("33.0686836, 34.98217");
       ArrayList<String> names= new ArrayList<>();
       names.add("yuval");
       names.add("or");
       names.add("ron");
       names.add("noy");
       names.add("noam");
       names.add("tal");
       names.add("shany");
       names.add("shir");
       names.add("daniel");
        names.add("ariel");
        if (records.isEmpty() || !Objects.equals(records.get(0).getName(), "yuval")){
            for (int i = 0; i < 10; i++)  {
                int rec = 11 - i;
                String location = "";
                insertRecord(new Record(names.get(i), rec , location));
            }
        }

    }
}