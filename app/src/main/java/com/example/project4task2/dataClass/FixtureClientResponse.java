package com.example.project4task2.dataClass;
// Rishabh Devgon | rishabhd

import java.util.ArrayList;

// Data Class for FixtureClientResponse
public class FixtureClientResponse {
    private ArrayList<FixtureClient> fixtures;

    public FixtureClientResponse(ArrayList<FixtureClient> fixtures) {
        this.fixtures = fixtures;
    }

    public ArrayList<FixtureClient> getFixtures() {
        return fixtures;
    }
}
