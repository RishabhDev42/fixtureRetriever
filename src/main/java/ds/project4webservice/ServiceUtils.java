package ds.project4webservice;
// Rishabh Devgon | rishabhd

import DataClass.Fixture;
import DataClass.FixtureClient;
import DataClass.Team;
import DataClass.TeamClient;

import java.util.ArrayList;

// Utility class to convert data classes to client classes
public class ServiceUtils {

//    Converts Team object to TeamClient object
    public static TeamClient getTeamClient(Team team) {
        return new TeamClient(team.getId(), team.getName(), team.getImage_path());
    }

//    Converts ArrayList of Team objects to ArrayList of TeamClient objects
    public static ArrayList<TeamClient> getTeamClientList(ArrayList<Team> teams) {
        ArrayList<TeamClient> teamClients = new ArrayList<>();
        for (Team team : teams) {
            teamClients.add(getTeamClient(team));
        }
        return teamClients;
    }

//    Converts Fixture object to FixtureClient object
    public static FixtureClient getFixtureClient(Fixture fixture) {
        return new FixtureClient(fixture.getName(), fixture.getStarting_at());
    }

//    Converts ArrayList of Fixture objects to ArrayList of FixtureClient objects
    public static ArrayList<FixtureClient> getFixtureClientList(ArrayList<Fixture> fixtures) {
        ArrayList<FixtureClient> fixtureClients = new ArrayList<>();
        for (Fixture fixture : fixtures) {
            fixtureClients.add(getFixtureClient(fixture));
        }
        return fixtureClients;
    }
}
