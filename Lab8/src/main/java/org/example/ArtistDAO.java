package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistDAO {
    private final Connection connection;

    public ArtistDAO(Connection connection) {
        this.connection = connection;
    }

    public Optional<Artist> getArtistById(int id) throws SQLException {
        String query = "SELECT * FROM artists WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Artist(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            } else {
                return Optional.empty();
            }
        }
    }

    public List<Artist> getAllArtists() throws SQLException {
        List<Artist> artists = new ArrayList<>();
        String query = "SELECT * FROM artists";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Artist artist = new Artist(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                artists.add(artist);
            }
        }
        return artists;
    }

    public int addArtist(Artist artist) throws SQLException {
        String query = "INSERT INTO artists (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, artist.getName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                artist.setId(generatedKeys.getInt(1));
                return artist.getId();
            } else {
                throw new SQLException("Failed to add artist: " + artist);
            }
        }
    }

    public int updateArtist(Artist artist) throws SQLException {
        String query = "UPDATE artists SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, artist.getName());
            statement.setInt(2, artist.getId());
            return statement.executeUpdate();
        }
    }

    public int deleteArtist(int id) throws SQLException {
        String query = "DELETE FROM artists WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }
}

