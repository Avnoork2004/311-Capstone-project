package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    // Create a new movie
    public boolean createMovie(Connection connection, Movie movie) {
        String query = "INSERT INTO movies (title, genre, release_date, rating, availability) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getGenre());
            statement.setDate(3, movie.getReleaseDate());
            statement.setDouble(4, movie.getRating());
            statement.setBoolean(5, movie.isAvailability());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all movies
    public List<Movie> getAllMovies(Connection connection) {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("movie_id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getDate("release_date"),
                        resultSet.getDouble("rating"),
                        resultSet.getBoolean("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // Retrieve a single movie by ID
    public Movie getMovieById(Connection connection, int movieId) {
        String query = "SELECT * FROM movies WHERE movie_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movieId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Movie(
                            resultSet.getInt("movie_id"),
                            resultSet.getString("title"),
                            resultSet.getString("genre"),
                            resultSet.getDate("release_date"),
                            resultSet.getDouble("rating"),
                            resultSet.getBoolean("availability")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update movie availability
    public boolean updateMovieAvailability(Connection connection, int movieId, boolean availability) {
        String query = "UPDATE movies SET availability = ? WHERE movie_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, availability);
            statement.setInt(2, movieId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a movie
    public boolean deleteMovie(Connection connection, int movieId) {
        String query = "DELETE FROM movies WHERE movie_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movieId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Movie> searchMoviesByTitle(Connection connection, String query) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE LOWER(title) LIKE ?";  // Make it case-insensitive
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + query + "%");  // Using % to search for any title containing the query
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer movieId = resultSet.getInt("movie_id");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                java.sql.Date releaseDate = resultSet.getDate("release_date");
                Double rating = resultSet.getDouble("rating");
                Boolean available = resultSet.getBoolean("availability");

                Movie movie = new Movie(movieId, title, genre, releaseDate, rating, available);
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    public List<Movie> filterMoviesByGenre(Connection connection, String genre) {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies WHERE genre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, genre);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    movies.add(new Movie(
                            resultSet.getInt("movie_id"),
                            resultSet.getString("title"),
                            resultSet.getString("genre"),
                            resultSet.getDate("release_date"),
                            resultSet.getDouble("rating"),
                            resultSet.getBoolean("availability")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    public List<Movie> filterMoviesByAvailability(Connection connection) {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies WHERE availability = TRUE";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("movie_id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getDate("release_date"),
                        resultSet.getDouble("rating"),
                        resultSet.getBoolean("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    public List<Movie> filterMoviesByRating(Connection connection, double minRating) {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies WHERE rating >= ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, minRating);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    movies.add(new Movie(
                            resultSet.getInt("movie_id"),
                            resultSet.getString("title"),
                            resultSet.getString("genre"),
                            resultSet.getDate("release_date"),
                            resultSet.getDouble("rating"),
                            resultSet.getBoolean("availability")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    public boolean updateMovieDetails(Connection connection, Movie movie) {
        String query = "UPDATE movies SET title = ?, genre = ?, release_date = ?, rating = ? WHERE movie_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getGenre());
            statement.setDate(3, movie.getReleaseDate());
            statement.setDouble(4, movie.getRating());
            statement.setInt(5, movie.getMovieId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Movie> getTopRatedMovies(Connection connection, int limit) {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies ORDER BY rating DESC LIMIT ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    movies.add(new Movie(
                            resultSet.getInt("movie_id"),
                            resultSet.getString("title"),
                            resultSet.getString("genre"),
                            resultSet.getDate("release_date"),
                            resultSet.getDouble("rating"),
                            resultSet.getBoolean("availability")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }


}
