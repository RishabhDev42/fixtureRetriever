package DataClass;
// Rishabh Devgon | rishabhd

// Data Class for Team from 3rd Party API
public class Team {
    private int id;
    private int sport_id;
    private int country_id;
    private int venue_id;
    private String gender;
    private String name;
    private String short_code;
    private String image_path;
    private int founded;
    private String type;
    private boolean placeholder;
    private String last_played_at;

    public Team(int id, int sport_id, int country_id, int venue_id, String gender, String name, String short_code, String image_path, int founded, String type, boolean placeholder, String last_played_at) {
        this.id = id;
        this.sport_id = sport_id;
        this.country_id = country_id;
        this.venue_id = venue_id;
        this.gender = gender;
        this.name = name;
        this.short_code = short_code;
        this.image_path = image_path;
        this.founded = founded;
        this.type = type;
        this.placeholder = placeholder;
        this.last_played_at = last_played_at;
    }

    public int getId() {
        return id;
    }

    public int getSport_id() {
        return sport_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public int getVenue_id() {
        return venue_id;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getShort_code() {
        return short_code;
    }

    public String getImage_path() {
        return image_path;
    }

    public int getFounded() {
        return founded;
    }

    public String getType() {
        return type;
    }

    public boolean isPlaceholder() {
        return placeholder;
    }

    public String getLast_played_at() {
        return last_played_at;
    }
}
