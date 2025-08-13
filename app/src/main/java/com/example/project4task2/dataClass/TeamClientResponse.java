package com.example.project4task2.dataClass;
// Rishabh Devgon | rishabhd
import java.util.ArrayList;

// Data Class for TeamClientResponse
public class TeamClientResponse {
    ArrayList<TeamClient> teams;

    public TeamClientResponse(ArrayList<TeamClient> teams) {
        this.teams = teams;
    }

    public ArrayList<TeamClient> getTeams() {
        return teams;
    }
}
