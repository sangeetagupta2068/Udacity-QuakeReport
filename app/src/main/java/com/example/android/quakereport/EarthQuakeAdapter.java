package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sangeetagupta1998 on 7/4/18.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {

    public EarthQuakeAdapter(@NonNull Context context, ArrayList<EarthQuake> places) {
        super(context, 0, places);

    }

    public int getMagnitudeColor(String magnitude){

        int magnitudeColorResourceId = 0;
        int magnitudeValue = (int)Float.parseFloat(magnitude);
        switch (magnitudeValue){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null){

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list, parent, false);

        }

        EarthQuake earthQuake = (EarthQuake) getItem(position);

        TextView magnitudeTextView = listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(earthQuake.getMagnitude());

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        TextView locationTextView = listItemView.findViewById(R.id.location);
        locationTextView.setText(earthQuake.getLocation());

        TextView dateTextView = listItemView.findViewById(R.id.date);
        dateTextView.setText(earthQuake.getDate());

        TextView distanceTextView = listItemView.findViewById(R.id.distance);
        distanceTextView.setText(earthQuake.getDistance());

        TextView timeTextView = listItemView.findViewById(R.id.time);
        timeTextView.setText(earthQuake.getTime());

        return listItemView;

    }

}
