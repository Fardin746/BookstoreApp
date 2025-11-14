package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.repository.entity.BookEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJpa {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStore_db");
    public static void main(String[] args) {




        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        BookEntity book = new BookEntity();
        book.setName("Book1");
        book.setTitle("Title1");
        book.setDescription("Description1");
        book.setPrice(100.0);
        book.setType("Type1");
        entityManager.persist(book);

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
