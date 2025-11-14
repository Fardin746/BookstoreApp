package com.example.bookstoreapp.controller;


import com.example.bookstoreapp.exception.BookServiceException;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.service.BookService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;


@ManagedBean(name = "bookBean")
@SessionScoped
public class BookBean {

    private String header = "";
    private boolean editMode;
    private Book newBook;
    private String message;

    @EJB
    private BookService service;

    public boolean getEditMode() {
        return editMode;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<Book> getBooks() {
        try {
            return service.getBooks();
        }catch (BookServiceException e){
            message = e.getMessage();
           return null;
        }
    }

    public String addBook() {
        try {
            header = "Add Book";
            service.addBook(newBook);
            newBook = new Book();
            editMode = false;
            message = null; // clear old error
            return "bookList.xhtml?faces-redirect=true";
        } catch (BookServiceException e) {
            message = e.getMessage();
            return null;
        }
    }

    public String editBook(Book book) {
        newBook = book;
        editMode = true;
        message = null;
        header = "Edit Book";
        return "addBook.xhtml?faces-redirect=true";
    }

    public String updateBook() {
        try {
            service.editBook(newBook);
            message = null;
            return "bookList.xhtml?faces-redirect=true";
        } catch (BookServiceException e) {
            message = e.getMessage();
            editMode = true;
            return null;
        }
    }

    public String deleteBook(int id) {
        try {
            service.deleteBook(id);
            return null;
        } catch (BookServiceException e) {
            message = e.getMessage();
            return null;
        }
    }


    // for go to add a book page
    public String addBookPage() {
        header = "Add Book";
        editMode = false;
        newBook = new Book();
        message = null;
        return "addBook.xhtml?faces-redirect=true";
    }


    // for go to the book list page
    public String BookListPage() {
        message = null;
        return "bookList.xhtml?faces-redirect=true";
    }


}

