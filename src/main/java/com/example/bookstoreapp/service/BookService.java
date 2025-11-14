package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.Book;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BookService {

    void addBook(Book book);

    List<Book> getBooks();

    void deleteBook(int id);

    void editBook(Book book);
}
