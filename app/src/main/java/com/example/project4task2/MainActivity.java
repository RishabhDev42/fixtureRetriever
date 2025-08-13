package com.example.project4task2;
//Rishabh Devgon | rishabhd
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project4task2.dataClass.FixtureClient;
import com.example.project4task2.dataClass.FixtureClientResponse;
import com.example.project4task2.dataClass.TeamClient;
import com.example.project4task2.dataClass.TeamClientResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Spinner teamSpinner;
    private DatePickerDialog startDatePickerDialog;
    private DatePickerDialog endDatePickerDialog;
    private Button dateStartButton;
    private Button dateEndButton;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        Initialise the UI components
        dateStartButton = findViewById(R.id.dateStartPickerButton);
        dateStartButton.setText(getTodaysDate());
        dateEndButton = findViewById(R.id.dateEndPickerButton);
        dateEndButton.setText(getTodaysDate());

        teamSpinner = findViewById(R.id.teamSpinner);
//        Run the network operations on a separate thread
        executorService.execute(this::fetchTeams);

//        Set up the date pickers
        initDatePickers();

        Button searchButton = findViewById(R.id.searchButton);
//        On click listener for the search button
        searchButton.setOnClickListener(v -> {
            String startDate = dateStartButton.getText().toString();
            String endDate = dateEndButton.getText().toString();
            TeamClient selectedTeam = (TeamClient) teamSpinner.getSelectedItem();
            int teamId = selectedTeam.getId();
//            Collects start date, end date and team id from user input and passes them to the fetchFixtures method
//            fetch fixtures method is run on a separate thread
            executorService.execute(() -> fetchFixtures(teamId, startDate, endDate));
        });
    }

    private void fetchFixtures(int teamId, String startDate, String endDate) {
        try {
            URL url = new URL(String.format(Locale.getDefault(),"https://obscure-waffle-j77jxxqrwwrcqvr-8080.app.github.dev/fixtures?teamId=%d&startDate=%s&endDate=%s", teamId, startDate, endDate));
//            HTTP connection to the server
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("Trace", "Connecting to URL: " + url);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//            get response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
//                Parse the response using Gson
                Gson gson = new Gson();
                FixtureClientResponse fixturesResponse = gson.fromJson(response.toString(), FixtureClientResponse.class);
                ArrayList<String> fixturesList = new ArrayList<>();
//                Create a list of fixtures
                for (FixtureClient fixture : fixturesResponse.getFixtures()) {
                    fixturesList.add(fixture.getName() + " - " + fixture.getStarting_at());
                }
//                Pass the fixtures list to the FixturesActivity
//                The FixturesActivity is started with the fixtures list as an extra
                runOnUiThread(() -> {
                    Intent intent = new Intent(MainActivity.this, FixturesActivity.class);
                    intent.putStringArrayListExtra("fixtures", fixturesList);
                    startActivity(intent);
                });
                Log.d("Trace", "Response: " + response.toString());
            } else {
                Log.e("HTTP request error", "GET request failed. Response Code: " + responseCode);
            }
            conn.disconnect();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }

    private void fetchTeams() {
        try{
//            HTTP connection to the server
            URL url = new URL("https://obscure-waffle-j77jxxqrwwrcqvr-8080.app.github.dev/teams");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("Trace", "Connecting to URL: " + url);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//            Get response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
//                Parse the response using Gson
                Gson gson = new Gson();
                TeamClientResponse teamClientResponse = gson.fromJson(response.toString(), TeamClientResponse.class);
//                Create a list of teams
                ArrayList<TeamClient> teamList = teamClientResponse.getTeams();
//                Populate the spinner with the list of teams
                runOnUiThread(() -> {
                    TeamAdapter adapter = new TeamAdapter(MainActivity.this, teamList);
                    teamSpinner.setAdapter(adapter);
                });

                // Print the response
                Log.d("Trace", "Response: " + response.toString());
            } else {
                Log.e("HTTP request error", "GET request failed. Response Code: " + responseCode);
            }
            conn.disconnect();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }


//    Reference for DatePickerDialog: https://github.com/codeWithCal/DatePickerTutorial/blob/master/app/src/main/java/codewithcal/au/datepickertutorial/MainActivity.java
    private void initDatePickers() {
        DatePickerDialog.OnDateSetListener startDateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateStartButton.setText(date);
        };

        DatePickerDialog.OnDateSetListener endDateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateEndButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        startDatePickerDialog = new DatePickerDialog(this, style, startDateSetListener, year, month, day);
        endDatePickerDialog = new DatePickerDialog(this, style, endDateSetListener, year, month, day);
    }

//    Makes date strings to pass on to server later
    private String makeDateString(int day, int month, int year)
    {
        return String.format(Locale.getDefault(),"%04d-%02d-%02d", year, month, day);
    }


    public void openStartDatePicker(View view) {
        startDatePickerDialog.show();
    }

    public void openEndDatePicker(View view) {
        endDatePickerDialog.show();
    }
}