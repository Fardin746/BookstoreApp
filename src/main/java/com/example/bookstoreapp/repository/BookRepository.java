package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;


import java.util.ArrayList;
import java.util.List;


public class BookRepository {


    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        books.add(new Book(1, "Java Basics", "Learn Java", "A beginner Java book", 499.0, "Programming"));
        books.add(new Book(2, "Spring Boot", "Master Spring", "Advanced Spring Boot topics", 799.0, "Framework"));
        books.add(new Book(3, "Yuval Noah Harari", "Sapiens", "A brief history of humankind", 599.0, "Non-Fiction"));
        books.add(new Book(4, "Robert Kiyosaki", "Rich Dad Poor Dad", "Lessons on wealth and financial freedom", 299.0, "Self-Help"));
        books.add(new Book(5, "Dan Brown", "The Da Vinci Code", "A mystery thriller involving secret societies", 450.0, "Thriller"));
    }

    public void create(Book book){
        books.add(book);
    }

    public void delete(Book book){
        books.remove(book);
    }


    public void edit(Book book){
        for (Book b : books){
            if (b.getId() == book.getId()){
                b.setName(book.getName());
                b.setTitle(book.getTitle());
                b.setDescription(book.getDescription());
                b.setPrice(book.getPrice());
                b.setType(book.getType());
                break;
            }
        }
    }

    public int generateId(){
        return books.size() + 1;
    }

    public List<Book> getBooks() {
        return books;
    }



}
