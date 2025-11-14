package com.example.bookstoreapp.controller;


import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.service.BookService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;


@ManagedBean(name = "bookBean")
@SessionScoped
public class BookBean {

    private String header ="Add Book";
    private final BookService service = new BookService();
    private Book newBook = new Book();
  

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    public List<Book> getBooks() {
        return service.getBooks();
    }


    public String addBook() {
//        header = "Add Book";
        service.addBook(newBook);
        newBook = new Book();
        return "bookList?faces-redirect=true";
    }

    public String editBook(Book book) {
        newBook = book;
        header = "Edit Book";
        return "addBook?faces-redirect=true";
    }


    public String deleteBook(int id) {
        service.deleteBook(id);
//        return "bookList.xhtml?faces-redirect=true";
        return null;
    }

    // for go to add a book page
    public String addBookPage() {
        return "addBook?faces-redirect=true";
    }


    // for go to the book list page
    public String BookListPage() {
        newBook = new Book();
        return "bookList.xhtml?faces-redirect=true";
    }


}
