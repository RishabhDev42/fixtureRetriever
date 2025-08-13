package DataClass;
// Rishabh Devgon | rishabhd

// Data Class for Team for Client
public class TeamClient {
    private int id;
    private String name;
    private String imagePath;

    public TeamClient(int id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}
