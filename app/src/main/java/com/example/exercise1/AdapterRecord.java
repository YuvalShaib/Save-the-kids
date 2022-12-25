package com.example.exercise1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

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
        //this rows print in the list the name and score
        TextView rcName = (TextView) convertView.findViewById(R.id.list_LBL_name);
        TextView rcScore = (TextView) convertView.findViewById(R.id.list_LBL_score);
        Button rcLocation = (Button) convertView.findViewById(R.id.list_BTN_location);
        rcName.setText(record.getName());
        rcScore.setText(record.getScore());
        rcLocation.setText(record.getLocation());

        initViews(rcLocation);
        return convertView;
    }
    private LatLng getLocationFromString(String location) {
        // Split the location string into latitude and longitude
        String[] latLng = location.split(",");
        double latitude = Double.parseDouble(latLng[0]);
        double longitude = Double.parseDouble(latLng[1]);

        // Return the LatLng object
        return new LatLng(latitude, longitude);
    }
    private void initViews(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                String buttonText = b.getText().toString();

                Log.d("maps", "locationRec"+ buttonText);
                if (!buttonText.isEmpty()) {
                    Fragment_Map.mMap.moveCamera(CameraUpdateFactory.newLatLng(getLocationFromString(buttonText)));
                }
            }
        });
    }

}
