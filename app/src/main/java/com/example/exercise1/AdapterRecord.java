package com.example.exercise1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class AdapterRecord extends ArrayAdapter<Record> {


    public AdapterRecord(@NonNull Context context, ArrayList<Record> records) {
        super(context ,0, records);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Record record = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.record_row_format, parent, false);
        }
        TextView rcName = (TextView) convertView.findViewById(R.id.list_LBL_name);
        TextView rcScore = (TextView) convertView.findViewById(R.id.list_LBL_score);
        rcName.setText(record.getName());
        rcScore.setText(record.getScore());
        return convertView;
    }

}
