package DataClass;
// Rishabh Devgon | rishabhd

// Data Class for TeamResponse for client
import java.util.ArrayList;

public class TeamClientResponse {
    ArrayList<TeamClient> teams;

    public TeamClientResponse(ArrayList<TeamClient> teams) {
        this.teams = teams;
    }

    public ArrayList<TeamClient> getTeams() {
        return teams;
    }
}
