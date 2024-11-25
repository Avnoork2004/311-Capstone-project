package database;

public class Movie {
    private int movieId;
    private String title;
    private String genre;
    private String releaseDate;
    private double rating;
    private boolean availability;

    // Constructor
    public Movie(int movieId, String title, String genre, String releaseDate, double rating, boolean availability) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.availability = availability;
    }

    // Getters and Setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
