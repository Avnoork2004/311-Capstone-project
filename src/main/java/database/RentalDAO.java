package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {

    // Create a new rental
    public boolean createRental(Connection connection, Rental rental) {
        String query = "INSERT INTO rentals (user_id, movie_id, rental_date, return_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rental.getUserId());
            statement.setInt(2, rental.getMovieId());
            statement.setString(3, rental.getRentalDate());
            statement.setString(4, rental.getReturnDate());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all rentals
    public List<Rental> getAllRentals(Connection connection) {
        List<Rental> rentals = new ArrayList<>();
        String query = "SELECT * FROM rentals";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                rentals.add(new Rental(
                        resultSet.getInt("rental_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("movie_id"),
                        resultSet.getString("rental_date"),
                        resultSet.getString("return_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    // Retrieve rentals by user ID
    public List<Rental> getRentalsByUserId(Connection connection, int userId) {
        List<Rental> rentals = new ArrayList<>();
        String query = "SELECT * FROM rentals WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rentals.add(new Rental(
                            resultSet.getInt("rental_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("movie_id"),
                            resultSet.getString("rental_date"),
                            resultSet.getString("return_date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    // Update return date
    public boolean updateReturnDate(Connection connection, int rentalId, String returnDate) {
        String query = "UPDATE rentals SET return_date = ? WHERE rental_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, returnDate);
            statement.setInt(2, rentalId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a rental
    public boolean deleteRental(Connection connection, int rentalId) {
        String query = "DELETE FROM rentals WHERE rental_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rentalId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
