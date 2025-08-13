package DataClass;
// Rishabh Devgon | rishabhd

// DataClass for Fixture fetched from 3rd party API
public class Fixture {
    private int id;
    private int sport_id;
    private int league_id;
    private int season_id;
    private int stage_id;
    private Integer group_id;
    private Integer aggregate_id;
    private int round_id;
    private int state_id;
    private int venue_id;
    private String name;
    private String starting_at;
    private String result_info;
    private String leg;
    private String details;
    private int length;
    private boolean placeholder;
    private boolean has_odds;
    private boolean has_premium_odds;
    private long starting_at_timestamp;

    public Fixture(int id, int sport_id, int league_id, int season_id, int stage_id, Integer group_id, Integer aggregate_id, int round_id, int state_id, int venue_id, String name, String starting_at, String result_info, String leg, String details, int length, boolean placeholder, boolean has_odds, boolean has_premium_odds, long starting_at_timestamp) {
        this.id = id;
        this.sport_id = sport_id;
        this.league_id = league_id;
        this.season_id = season_id;
        this.stage_id = stage_id;
        this.group_id = group_id;
        this.aggregate_id = aggregate_id;
        this.round_id = round_id;
        this.state_id = state_id;
        this.venue_id = venue_id;
        this.name = name;
        this.starting_at = starting_at;
        this.result_info = result_info;
        this.leg = leg;
        this.details = details;
        this.length = length;
        this.placeholder = placeholder;
        this.has_odds = has_odds;
        this.has_premium_odds = has_premium_odds;
        this.starting_at_timestamp = starting_at_timestamp;
    }

    public int getId() {
        return id;
    }

    public int getSport_id() {
        return sport_id;
    }

    public int getLeague_id() {
        return league_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public int getStage_id() {
        return stage_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public Integer getAggregate_id() {
        return aggregate_id;
    }

    public int getRound_id() {
        return round_id;
    }

    public int getState_id() {
        return state_id;
    }

    public int getVenue_id() {
        return venue_id;
    }

    public String getName() {
        return name;
    }

    public String getStarting_at() {
        return starting_at;
    }

    public String getResult_info() {
        return result_info;
    }

    public String getLeg() {
        return leg;
    }

    public String getDetails() {
        return details;
    }

    public int getLength() {
        return length;
    }

    public boolean isPlaceholder() {
        return placeholder;
    }

    public boolean isHas_odds() {
        return has_odds;
    }

    public boolean isHas_premium_odds() {
        return has_premium_odds;
    }

    public long getStarting_at_timestamp() {
        return starting_at_timestamp;
    }
}
