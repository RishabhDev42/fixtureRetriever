package DataClass;
// Rishabh Devgon | rishabhd

import java.util.ArrayList;
// Data Class for FixtureResponse from 3rd party API
public class FixtureResponse {
    private ArrayList<Fixture> data;
    private Object pagination;
    private Object subscription;
    private Object rate_limit;
    private String timezone;

    public FixtureResponse(ArrayList<Fixture> data, Object pagination, Object subscription, Object rate_limit, String timezone) {
        this.data = data;
        this.pagination = pagination;
        this.subscription = subscription;
        this.rate_limit = rate_limit;
        this.timezone = timezone;
    }

    public ArrayList<Fixture> getData() {
        return data;
    }
}
