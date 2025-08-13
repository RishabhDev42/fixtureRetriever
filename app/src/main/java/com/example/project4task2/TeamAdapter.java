package com.example.project4task2;
// Rishabh Devgon | rishabhd
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.project4task2.dataClass.TeamClient;

import java.util.List;

// This code was generated with the help of GitHub Copilot
public class TeamAdapter extends ArrayAdapter<TeamClient> {

    public TeamAdapter(Context context, List<TeamClient> teams) {
        super(context, 0, teams);
    }

//    Provides a view for the dropdown menu of the spinner
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createViewFromResource(position, convertView, parent, R.layout.spinner_item);
    }

//    Provides a view for the selected item in the spinner
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createViewFromResource(position, convertView, parent, R.layout.spinner_item);
    }

//    Creates a view for the spinner item
    private View createViewFromResource(int position, @Nullable View convertView, ViewGroup parent, int resource) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        TeamClient team = getItem(position);

        ImageView teamImage = view.findViewById(R.id.teamImage);
        TextView teamName = view.findViewById(R.id.teamName);

        if (team != null) {
            teamName.setText(team.getName());
            Glide.with(getContext()).load(team.getImagePath()).into(teamImage);
        }

        return view;
    }
}