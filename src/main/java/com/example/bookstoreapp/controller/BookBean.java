package com.example.bookstoreapp.controller;


import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.service.BookService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;


@ManagedBean(name = "bookBean")
@SessionScoped
public class BookBean {

    private String header = "";
    private final BookService service = new BookService();
    private Book newBook = new Book();
    private boolean editMode;

    public boolean getEditMode(){
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

    public List<Book> getBooks() {
        return service.getBooks();
    }

    public String addBook() {
        header = "Add Book";
        service.addBook(newBook);
        newBook = new Book();
        editMode = false;
        return "bookList.xhtml?faces-redirect=true";
    }

    public String editBook(Book book) {
        newBook = book;
        editMode = true;
        header = "Edit Book";
        return "addBook.xhtml?faces-redirect=true";
    }

    public String updateBook(){
        header = "Edit Book";
        service.editBook(newBook);
        editMode = false;
        return  "bookList.xhtml?faces-redirect=true";
    }

    public String deleteBook(int id){
        service.deleteBook(id);
        return null;
    }


    // for go to add a book page
    public String addBookPage() {
        header = "Add Book";
        newBook = new Book();
        editMode = false;
        return "addBook.xhtml?faces-redirect=true";
    }


    // for go to the book list page
    public String BookListPage() {
        return "bookList.xhtml?faces-redirect=true";
    }


}
