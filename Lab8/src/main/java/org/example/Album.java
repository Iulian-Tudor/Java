package org.example;

import java.util.List;

public class Album {
    private int id;
    private int releaseYear;
    private String title;
    private Artist artist;
    private List<Genre> genres;

    public Album(int id, int releaseYear, String title, Artist artist, List<Genre> genres) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
