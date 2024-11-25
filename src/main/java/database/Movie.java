package database;

public class Movie {
    private Integer movieId;
    private String title;
    private String genre;
    private Integer releaseDate;
    private Double rating;
    private Boolean availability;

    // Constructor
    public Movie(Integer movieId, String title, String genre, Integer releaseDate, Double rating, Boolean availability) {
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

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
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
