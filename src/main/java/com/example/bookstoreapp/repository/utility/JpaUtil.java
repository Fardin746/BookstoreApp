package com.example.bookstoreapp.repository.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUtil {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStore_db");
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }


    public static void closeEntityManager(EntityManager entityManager) {
        entityManager.close();
    }


    public EntityManager begin() {
        entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        return entityManager;

    }


    public void commit() {
        try {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            if (entityTransaction.isActive()) {
                entityTransaction.commit();
            }
        } catch (Exception e) {
            rollback();
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null) {
                closeEntityManager(entityManager);
            }
        }
    }

    public void rollback() {
        try {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeEntityManager(entityManager);
        }
    }


}
