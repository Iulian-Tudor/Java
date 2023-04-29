package org.example;

import java.time.LocalDateTime;
import java.util.Set;

class Playlist {
    private static int playlistCount = 0;
    private int id;
    private String name;
    private LocalDateTime creationTime;
    private Set<Album> albums;

    public Playlist(String name, LocalDateTime creationTime, Set<Album> albums) {
        this.id = ++playlistCount;
        this.name = name;
        this.creationTime = creationTime;
        this.albums = albums;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist ID: ").append(id).append("\n");
        sb.append("Created at: ").append(creationTime).append("\n");
        sb.append("Albums:\n");
        for (Album album : albums) {
            sb.append("- ").append(album.getTitle()).append("\n");
        }
        return sb.toString();
    }


}

