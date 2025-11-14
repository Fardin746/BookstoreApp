package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.exception.BookRepositoryException;
import com.example.bookstoreapp.model.Book;
import javax.ejb.Local;
import java.util.List;

@Local
public interface BookRepository {

    void create(Book book) throws BookRepositoryException;

    List<Book> getAllBooks() throws BookRepositoryException;

    void update(Book book) throws BookRepositoryException;

    void delete(int id)throws BookRepositoryException;


}
