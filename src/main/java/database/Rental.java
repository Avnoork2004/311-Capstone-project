package database;
import java.sql.Timestamp;


public class Rental {
    private int rentalId;
    private int userId;
    private int movieId;
    private Timestamp rentalDate;
    private Timestamp returnDate;

    // Default constructor
    public Rental() {}

    // Constructor with parameters
    public Rental(int rentalId, int userId, int movieId, Timestamp rentalDate, Timestamp returnDate) {
        this.rentalId = rentalId;
        this.userId = userId;
        this.movieId = movieId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Timestamp getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Timestamp rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    // toString() method
    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
