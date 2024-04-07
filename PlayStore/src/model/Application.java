package model;

public class Application {
    // Member variables
    private int app_id;
    private String name;
    private String description;
    private String releaseDate;
    private String version;
    private double ratings;
    private String genre;

    // Constructor
    public Application(int app_id, String name, String description, String releaseDate, String version, double ratings, String genre) {
        this.app_id = app_id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.version = version;
        this.ratings = ratings;
        this.genre = genre;
    }

    // Getters and setters
    public int getAppId() {
        return app_id;
    }

    public void setAppId(int app_id) {
        this.app_id = app_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "Application{" +
                "app_id=" + app_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", version='" + version + '\'' +
                ", ratings=" + ratings +
                ", genre='" + genre + '\'' +
                '}';
    }
}