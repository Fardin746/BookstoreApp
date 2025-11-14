package com.example.bookstoreapp.repository.impl;

import com.example.bookstoreapp.exception.BookRepositoryException;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.entity.BookEntity;
import com.example.bookstoreapp.utils.Mapper;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext(unitName = "BookStore_db")
    private EntityManager entityManager;


    @Override
    public void create(Book book)throws BookRepositoryException {
        try {
            BookEntity bookEntity = Mapper.toBookEntity(book);
            entityManager.persist(bookEntity);
            book.setId(bookEntity.getId());
        } catch (PersistenceException e) {
            throw new BookRepositoryException("Error while saving book", e);
        }
    }

    @Override
    public List<Book> getAllBooks()throws BookRepositoryException {
        try {
            return entityManager.createQuery("SELECT b FROM BookEntity b WHERE b.deleted = false ", BookEntity.class).getResultList()
                    .stream().map(Mapper::toBook).collect(Collectors.toList());
        } catch (PersistenceException e) {
            throw new BookRepositoryException("Error while fetching books from database", e);
        }
    }


    @Override
    public void update(Book book) throws BookRepositoryException{
        try {
            BookEntity bookEntity = entityManager.find(BookEntity.class, book.getId());
            if (bookEntity != null) {
                bookEntity.setName(book.getName());
                bookEntity.setTitle(book.getTitle());
                bookEntity.setDescription(book.getDescription());
                bookEntity.setPrice(book.getPrice());
                bookEntity.setType(book.getType());
                entityManager.merge(bookEntity);
            }
        } catch (PersistenceException e) {
            throw new BookRepositoryException("Error while updating book", e);
        }
    }

    @Override
    public void delete(int id) throws BookRepositoryException{
        try {
            BookEntity bookEntity = entityManager.find(BookEntity.class, id);
            if (bookEntity != null) {
                bookEntity.setDeleted(true);
                entityManager.merge(bookEntity);
            }
        } catch (PersistenceException e) {
            throw new BookRepositoryException("Error while deleting book", e);
        }
    }

}
