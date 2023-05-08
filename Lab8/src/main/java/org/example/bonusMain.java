package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class bonusMain {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bonus", "root", "2002");
            PlaylistGenerator playlistGenerator = new PlaylistGenerator(connection);
            List<Playlist> playlists = playlistGenerator.generateMaximalPlaylists();
            for (Playlist playlist : playlists) {
                System.out.println(playlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

