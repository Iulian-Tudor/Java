package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CsvImporter {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bonus";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "2002";
    private static final String CSV_FILE = "albumlist.csv";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Insert data into artists table
            PreparedStatement insertArtistStmt = conn.prepareStatement(
                    "INSERT INTO artists (name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS
            );

            // Insert data into genres table
            PreparedStatement insertGenreStmt = conn.prepareStatement(
                    "INSERT INTO genres (name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS
            );

            // Insert data into albums table
            PreparedStatement insertAlbumStmt = conn.prepareStatement(
                    "INSERT INTO albums (number, year, title, artist_id, genre_id) " +
                            "VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS
            );

            // Insert data into album_genres table
            PreparedStatement insertAlbumGenreStmt = conn.prepareStatement(
                    "INSERT INTO album_genres (album_id, genre_id) " +
                            "VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS
            );

            // Select genre by name
            PreparedStatement selectGenreStmt = conn.prepareStatement(
                    "SELECT id FROM genres WHERE name = ?", PreparedStatement.RETURN_GENERATED_KEYS
            );

            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE));
            String line;
            reader.readLine(); // skip header line
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                // Insert artist if not exists
                String artistName = fields[3];
                insertArtistStmt.setString(1, artistName);
                insertArtistStmt.executeUpdate();
                ResultSet artistIdResult = insertArtistStmt.getGeneratedKeys();
                int artistId = artistIdResult.next() ? artistIdResult.getInt(1) : -1;

                // Insert genre if not exists
                String genreName = fields[4];
                selectGenreStmt.setString(1, genreName);
                ResultSet genreResult = selectGenreStmt.executeQuery();
                int genreId;
                if (genreResult.next()) {
                    // Genre already exists
                    genreId = genreResult.getInt(1);
                } else {
                    // Genre does not exist, insert it
                    insertGenreStmt.setString(1, genreName);
                    insertGenreStmt.executeUpdate();
                    ResultSet genreIdResult = insertGenreStmt.getGeneratedKeys();
                    genreId = genreIdResult.next() ? genreIdResult.getInt(1) : -1;
                }

                // Insert album
                int albumNumber = Integer.parseInt(fields[0]);
                int albumYear = Integer.parseInt(fields[1]);
                String albumTitle = fields[2];
                insertAlbumStmt.setInt(1, albumNumber);
                insertAlbumStmt.setInt(2, albumYear);
                insertAlbumStmt.setString(3, albumTitle);
                insertAlbumStmt.setInt(4, artistId);
                insertAlbumStmt.setInt(5, genreId);
                insertAlbumStmt.executeUpdate();
                ResultSet albumIdResult = insertAlbumStmt.getGeneratedKeys();
                int albumId = albumIdResult.next() ? albumIdResult.getInt(1) : -1;

                // Insert album_genre
                insertAlbumGenreStmt.setInt(1, albumId);
                insertAlbumGenreStmt.setInt(2, genreId);
                insertAlbumGenreStmt.executeUpdate();
            }


            conn.commit();
            System.out.println("Data inserted successfully!");
        } catch (SQLException e) {
            // Catch any SQL exception
            e.printStackTrace();
        } catch (Exception e) {
        // Catch any other exception
            e.printStackTrace();
        } finally {
        // Close all resources
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

