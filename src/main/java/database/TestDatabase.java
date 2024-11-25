package database;

import java.sql.Connection;

public class TestDatabase {
    public static void main(String[] args) {
        // Establish database connection
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();

        // Initialize DAO classes
        UserDAO userDAO = new UserDAO();

        // Test creating a new user
        User newUser = new User(0, "John", "Doe", "johndoe", "john@example.com", "password123");
        if (userDAO.createUser(connection, newUser)) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }

        // Close the connection (if needed)
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
