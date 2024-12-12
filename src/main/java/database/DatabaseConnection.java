package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    // Create or return a valid connection
    public synchronized static Connection getConnection() {
        if (connection == null || isClosed(connection)) { // Check if the connection is closed
            try {
                connection = DriverManager.getConnection(
                        DatabaseConfig.URL + DatabaseConfig.SSL,
                        DatabaseConfig.USERNAME,
                        DatabaseConfig.PASSWORD
                );
                System.out.println("Connected to the database successfully!");
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database.");
                e.printStackTrace();
              //  throw new RuntimeException("Database connection failed.", e); // Throw an exception to stop execution if the connection fails
            }
        }
        return connection; // Return the connection
    }

    private static boolean isClosed(Connection connection) {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true; // Assume closed in case of an error
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Reset the connection
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No active connection to close.");
        }
    }
}