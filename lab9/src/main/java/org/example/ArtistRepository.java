package org.example;

import org.example.entities.*;
import jakarta.persistence.*;
import java.util.*;
public class ArtistRepository {

    private EntityManager entityManager;

    public ArtistRepository() {
        entityManager = EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    public void create(Artist artist) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(artist);
        transaction.commit();
    }

    public Artist findById(long id) {
        return entityManager.find(Artist.class, id);
    }

    public List<Artist> findByName(String name) {
        TypedQuery<Artist> query = entityManager.createNamedQuery("Artist.findByName", Artist.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

}
