package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {

        // Configure HikariCP
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/lab8database");
        config.setUsername("root");
        config.setPassword("2002");

        // Create a new HikariDataSource
        HikariDataSource dataSource = new HikariDataSource(config);

        // Get a connection from the pool
        Connection connection = dataSource.getConnection();

        GenreDAO genreDAO = new GenreDAO(connection);
        genreDAO.addGenre("TestLab");

        AlbumDAO albumDAO = new AlbumDAO(connection);
        albumDAO.addAlbum(1000, "This is a test", 3, new String[]{"TestLab"});

        // Close the connection and the pool
        connection.close();
        dataSource.close();
    }
}
