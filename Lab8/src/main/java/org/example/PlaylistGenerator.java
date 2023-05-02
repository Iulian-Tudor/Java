package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class PlaylistGenerator {

    private final Connection connection;

    public PlaylistGenerator(Connection connection){
        this.connection = connection;}

    private List<Genre> getAlbumGenres(int albumId) throws SQLException {
        List<Genre> genres = new ArrayList<>();

        String sql = "SELECT * FROM genres " +
                "WHERE id IN " +
                "(SELECT genre_id FROM album_genres WHERE album_id = ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, albumId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Genre genre = new Genre(id, name);
                genres.add(genre);
            }
        }
        return genres;
    }


    public List<Album> getRelatedAlbums(Album album) throws SQLException {
        List<Album> relatedAlbums = new ArrayList<>();

        String sql = "SELECT * FROM albums " +
                "WHERE (artist_id = ? OR year = ? OR id IN " +
                "(SELECT album_id FROM album_genres WHERE genre_id IN " +
                "(SELECT genre_id FROM album_genres WHERE album_id = ?))) " +
                "AND id != ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, album.getArtist().getId());
            statement.setInt(2, album.getReleaseYear());
            statement.setInt(3, album.getId());
            statement.setInt(4, album.getId());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                int artistId = rs.getInt("artist_id");
                Artist artist = getArtist(artistId);
                List<Genre> genres = getAlbumGenres(id);

                Album relatedAlbum = new Album(id, year, title, artist, genres);
                relatedAlbums.add(relatedAlbum);
            }
        }
        return relatedAlbums;
    }


    public List<Playlist> generateMaximalPlaylists() throws SQLException {
        List<Album> allAlbums = getAllAlbums();
        Map<Album, Set<Album>> relatedAlbumsMap = new HashMap<>();

        // Create a map of related albums for each album
        for (Album album : allAlbums) {
            List<Album> relatedAlbums = getRelatedAlbums(album);
            Set<Album> unrelatedAlbums = new HashSet<>(allAlbums);
            relatedAlbums.forEach(unrelatedAlbums::remove);
            unrelatedAlbums.remove(album);
            relatedAlbumsMap.put(album, unrelatedAlbums);
        }

        List<Playlist> playlists = new ArrayList<>();

        while (!relatedAlbumsMap.isEmpty()) {
            Set<Album> playlistAlbums = new HashSet<>();
            Album bestAlbum = null;
            int mostUnrelated = Integer.MIN_VALUE;

            // Find an album with the most unrelated albums
            for (Map.Entry<Album, Set<Album>> entry : relatedAlbumsMap.entrySet()) {
                int unrelatedCount = entry.getValue().size();
                if (unrelatedCount > mostUnrelated) {
                    bestAlbum = entry.getKey();
                    mostUnrelated = unrelatedCount;
                }
            }

            // Add the best album and all unrelated albums to a playlist
            playlistAlbums.add(bestAlbum);
            playlistAlbums.addAll(relatedAlbumsMap.get(bestAlbum));
            Playlist playlist = new Playlist(UUID.randomUUID().toString(), LocalDateTime.now(), playlistAlbums);
            playlists.add(playlist);

            // Remove the best album and all related albums from the map
            relatedAlbumsMap.remove(bestAlbum);
            for (Set<Album> related : relatedAlbumsMap.values()) {
                related.remove(bestAlbum);
            }
        }

        return playlists;
    }




    private List<Album> getAllAlbums() throws SQLException {
        List<Album> albums = new ArrayList<>();

        String sql = "SELECT * FROM albums";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                int artistId = rs.getInt("artist_id");
                Artist artist = getArtist(artistId);

                Album album = new Album(id, year, title, artist, new ArrayList<>());
                albums.add(album);
            }
        }
        return albums;
    }

    private Artist getArtist(int id) throws SQLException {
        String sql = "SELECT * FROM artists WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                return new Artist(id, name);
            }
            return null;
        }
    }

}