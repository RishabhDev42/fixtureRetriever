package DataClass;
// Rishabh Devgon | rishabhd

import java.util.ArrayList;

//Data Class for FixtureResponse for client
public class FixtureClientResponse {
    private ArrayList<FixtureClient> fixtures;

    public FixtureClientResponse(ArrayList<FixtureClient> fixtures) {
        this.fixtures = fixtures;
    }

    public ArrayList<FixtureClient> getFixtures() {
        return fixtures;
    }
}
