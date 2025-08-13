package DataClass;
// Rishabh Devgon | rishabhd

// Data Class for Fixture for client
public class FixtureClient {
    private String name;
    private String starting_at;

    public FixtureClient(String name, String starting_at) {
        this.name = name;
        this.starting_at = starting_at;
    }

    public String getName() {
        return name;
    }

    public String getStarting_at() {
        return starting_at;
    }
}
