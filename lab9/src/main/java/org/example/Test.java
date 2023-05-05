package org.example;

import jakarta.persistence.*;
import org.example.entities.*;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        ArtistRepository artistRepository = new ArtistRepository();
        Artist artist = new Artist("The Beatles");
        artistRepository.create(artist);

        Artist retrievedArtist = artistRepository.findById(artist.getId());
        System.out.println(retrievedArtist.getName());

        List<Artist> artists = artistRepository.findByName("Beatles");
        for (Artist a : artists) {
            System.out.println(a.getName());
        }

        EntityManagerFactorySingleton.getInstance().close();
    }

}
