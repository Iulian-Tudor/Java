package org.example;


import com.opencsv.CSVReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CsvImporter {

    private static final String CSV_FILE_PATH = "sample.csv";

    private Connection getConnection() throws SQLException {
        // Get a connection from the connection pool using Hikari
        return DatabaseConnection.getConnection();
    }

    public void importCsv() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] line;
            // Skip header line
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String artist = line[0];
                String album = line[1];
                int year = Integer.parseInt(line[2]);
                String genre = line[3];
                int artist_id = Integer.parseInt(line[4]);


                // Insert the album into the database
                try (Connection connection = getConnection()) {
                    String sql = "INSERT INTO albums (id, title, release_year, artist_id) VALUES (?, ?, ?, " +
                            "(SELECT id FROM artists WHERE name = ?))";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, Integer.parseInt(line[4]));
                    statement.setString(2, line[1]);
                    statement.setInt(3, Integer.parseInt(line[2]));
                    statement.setString(4, line[0]);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Insert the genre into the database
                try (Connection connection = getConnection()) {
                    String sql = "INSERT INTO genres (name) VALUES (?) " +
                            "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, genre);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Associate the album with the genre
                try (Connection connection = getConnection()) {
                    String sql = "INSERT INTO album_genres (album_id, genre_id) VALUES " +
                            "((SELECT id FROM albums WHERE title = ?), " +
                            "(SELECT id FROM genres WHERE name = ?))";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, album);
                    statement.setString(2, genre);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CsvImporter importer = new CsvImporter();
        importer.importCsv();
    }
}
