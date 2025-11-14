package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;

import java.util.List;

public class BookService  {

    private final BookRepository bookRepository = new BookRepository();


    public List<Book> getBooks()  {
        return bookRepository.getAllBooks();
    }

    public void deleteBook(int id){
        bookRepository.delete(id);
    }

    public void addBook(Book book){
        bookRepository.create(book);
    }



    public void editBook(Book book) {
        bookRepository.update(book);

    }




}
