package DataClass;
// Rishabh Devgon | rishabhd

import java.util.ArrayList;

//Data Class for TeamResponse from 3rd party API
public class TeamResponse {
    private ArrayList<Team> data;
    private Object pagination;
    private Object subscription;
    private Object rate_limit;
    private String timezone;

    public TeamResponse(ArrayList<Team> data, Object pagination, Object subscription, Object rate_limit, String timezone) {
        this.data = data;
        this.pagination = pagination;
        this.subscription = subscription;
        this.rate_limit = rate_limit;
        this.timezone = timezone;
    }

    public ArrayList<Team> getData() {
        return data;
    }
}
