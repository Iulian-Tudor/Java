package org.example;

import jakarta.persistence.*;

public class EntityManagerFactorySingleton {

    private static final EntityManagerFactorySingleton instance = new EntityManagerFactorySingleton();

    private final EntityManagerFactory entityManagerFactory;

    private EntityManagerFactorySingleton() {
        entityManagerFactory = Persistence.createEntityManagerFactory("lab9");
    }

    public static EntityManagerFactorySingleton getInstance() {
        return instance;
    }

    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManagerFactory.close();
    }

}
