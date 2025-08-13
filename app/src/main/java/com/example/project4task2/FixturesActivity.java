package com.example.project4task2;
//Rishabh Devgon | rishabhd
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FixturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);

//        Initialize the ListView
        ListView fixturesListView = findViewById(R.id.fixturesListView);
//        Get the fixtures data passed from the previous activity
        ArrayList<String> fixturesList = getIntent().getStringArrayListExtra("fixtures");

//        Set up the ArrayAdapter to display the fixtures
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fixturesList);
        fixturesListView.setAdapter(adapter);
    }
}