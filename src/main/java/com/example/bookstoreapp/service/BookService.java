package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import java.io.Serializable;
import java.util.List;

public class BookService implements Serializable {
    private static final long serialVersionUID = 1L;

    private final BookRepository bookRepository = new BookRepository();


    public List<Book> getBooks() {
        return bookRepository.getBooks();

    }

    public void addBook(Book book) {
        book.setId(bookRepository.generateId());
        bookRepository.create(book);
        System.out.println(book);

    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
        System.out.println("Deleted book: " + book.getId());
    }

    public void editBook(Book book) {
        bookRepository.edit(book);
        System.out.println(book);
        System.out.println("Edited book: " + book.getId());
    }


}
