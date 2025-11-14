package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BookRepository {

    void createAudit(int bookId, String auditType);

    void create(Book book);

    List<Book> getAllBooks();

    void update(Book book);

    void delete(int id);


}
