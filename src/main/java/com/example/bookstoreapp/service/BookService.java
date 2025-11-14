package com.example.bookstoreapp.service;

import com.example.bookstoreapp.exception.BookServiceException;
import com.example.bookstoreapp.model.Book;
import javax.ejb.Local;
import java.util.List;

@Local
public interface BookService {

    void addBook(Book book) throws BookServiceException;

    List<Book> getBooks() throws BookServiceException;

    void deleteBook(int id) throws BookServiceException;

    void editBook(Book book) throws BookServiceException;
}
