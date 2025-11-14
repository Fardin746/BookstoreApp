package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookService  {

    private final BookRepository bookRepository = new BookRepository();

   public List<Book> getBooks() {
        return new ArrayList<>(bookRepository.ReadBook().values());
   }

    public void addBook(Book book) {
        bookRepository.create(book);  //id assign inside
    }

//    public void addBookOrEditBook(Book book) {
//        if (book.getId() == 0) {
//            bookRepository.create(book); // assigns ID inside
//        } else {
//            bookRepository.update(book.getId(), book);
//        }
//    }

    public void deleteBook(int id) {
        bookRepository.delete(id);

    }

    public void editBook(Book book) {
        bookRepository.update(book);

    }


}
