package com.example.bookstoreapp.controller;


import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.service.BookService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "bookBean")
@SessionScoped
public class BookBean {


    private final BookService service = new BookService();
    private Book newBook = new Book();


    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }


    public List<Book> getBooks() {
        return new ArrayList<>(service.getBooks());
    }

//    public String addBook() {
//        service.addBook(newBook);
//        newBook = new Book();
//        return "BookList.xhtml?faces-redirect=true";
//    }

    public String addBook() {
        if (newBook.getId() == 0) {
            service.addBook(newBook); // add new
        } else {
            service.editBook(newBook); // edit existing
        }
        newBook = new Book(); // reset
        return "BookList.xhtml?faces-redirect=true";
    }


    public String editBook(Book book) {
        // Fill a newBook with selected book values

        newBook = new Book(
                book.getId(),
                book.getName(),
                book.getTitle(),
                book.getDescription(),
                book.getPrice(),
                book.getType()
        );
        return "AddBook.xhtml?faces-redirect=true";
    }


    public String  deleteBook(int id) {
        service.deleteBook(id);
//        return "BookList.xhtml?faces-redirect=true";
        return null;
    }




    // for go to add a book page
    public String addBookPage() {
        return "AddBook?faces-redirect=true";
    }


    // for go to the book list page
    public String BookListPage() {
        return "BookList?faces-redirect=true";
    }

}
