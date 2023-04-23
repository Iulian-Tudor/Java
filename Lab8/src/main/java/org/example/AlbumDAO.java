package org.example;

import java.sql.*;

public class AlbumDAO {
    private final Connection connection;

    public AlbumDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAlbum(int releaseYear, String title, int artist_id, String[] genres) {
        String query = "INSERT INTO albums (release_year, title, artist_id) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, releaseYear);
            statement.setString(2, title);
            statement.setInt(3, artist_id);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int albumId = generatedKeys.getInt(1);

                for (String genre : genres) {
                    addAlbumGenre(albumId, genre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addAlbumGenre(int albumId, String genreName) {
        String query = "INSERT INTO album_genres (album_id, genre_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int genreId = getGenreIdByName(genreName);
            statement.setInt(1, albumId);
            statement.setInt(2, genreId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getGenreIdByName(String name) throws SQLException {
        String query = "SELECT id FROM genres WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Genre not found: " + name);
            }
        }
    }


}
