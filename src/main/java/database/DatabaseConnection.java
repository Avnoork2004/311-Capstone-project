package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    // Create or return a valid connection
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) { // Check if the connection is closed
                connection = DriverManager.getConnection(
                        DatabaseConfig.URL + DatabaseConfig.SSL,
                        DatabaseConfig.USERNAME,
                        DatabaseConfig.PASSWORD

                );
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    // Close the connection and reset the instance
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Reset the connection
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
