package com.example.bookstoreapp.utils;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.entity.BookEntity;

public  class  Mapper {

    public static  BookEntity toBookEntity(Book book){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(book.getName());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setDescription(book.getDescription());
        bookEntity.setPrice(book.getPrice());
        bookEntity.setType(book.getType());
        return bookEntity;
    }

    public static Book toBook(BookEntity bookEntity){
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setName(bookEntity.getName());
        book.setTitle(bookEntity.getTitle());
        book.setDescription(bookEntity.getDescription());
        book.setPrice(bookEntity.getPrice());
        book.setType(bookEntity.getType());
        return book;
    }
}
