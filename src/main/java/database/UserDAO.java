package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Create a new user (Sign-Up)
    public boolean createUser(Connection connection, User user) {
        String query = "INSERT INTO users (first_name, last_name, username, email, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Returns true if insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Returns false if an error occurs
        }
    }

    // Retrieve a user by username
    public User getUserByUsername(Connection connection, String username) {
        String query = "SELECT user_id, first_name, last_name, username, email, password FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no user is found
    }

    // Login user
    public boolean loginUser(Connection connection, String username, String password) {
        User user = getUserByUsername(connection, username);
        if (user != null) {
            if (user.getPassword().equals(password)) { // Direct password comparison
                System.out.println("Login successful for username: " + username);
                return true;
            } else {
                System.out.println("Invalid password for username: " + username);
            }
        } else {
            System.out.println("User not found with username: " + username);
        }
        return false; // Return false if login is unsuccessful
    }
}
