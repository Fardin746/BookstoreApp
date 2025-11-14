package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;

import java.util.*;


public class BookRepository {


    private final Map<Integer, Book> books = new HashMap<>();
    private int nextId = 1;

    public BookRepository() {
        books.put(nextId, new Book(nextId++, "Java Basics", "Learn Java", "A beginner Java book", 499.0, "Programming"));
        books.put(nextId, new Book(nextId++, "Spring Boot", "Master Spring", "Advanced Spring Boot topics", 799.0, "Framework"));
        books.put(nextId, new Book(nextId++, "Yuval Noah Harari", "Sapiens", "A brief history of humankind", 599.0, "Non-Fiction"));
        books.put(nextId, new Book(nextId++, "Robert Kiyoshi", "Rich Dad Poor Dad", "Lessons on wealth and financial freedom", 299.0, "Self-Help"));
        books.put(nextId, new Book(nextId++, "Dan Brown", "The Da Vinci Code", "A mystery thriller involving secret societies", 450.0, "Thriller"));
    }

    public void create(Book book) {
        book.setId(nextId);
        books.put(nextId++, book);
    }

    public Map<Integer, Book> ReadBook() {
        return books;
    }


    public void update(Book book) {
        books.put(book.getId(), book);
    }

    public void delete(int id) {
        books.remove(id);
    }


}
