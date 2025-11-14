package com.example.bookstoreapp.service;

import com.example.bookstoreapp.exception.BookRepositoryException;
import com.example.bookstoreapp.exception.BookServiceException;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.BookAction;
import com.example.bookstoreapp.repository.AuditRepository;
import com.example.bookstoreapp.repository.BookRepository;

import javax.ejb.*;
import java.util.List;

@Stateless
public class BookServiceImpl implements BookService {

    @EJB
    private BookRepository bookRepository;

    @EJB
    private AuditRepository auditRepository;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addBook(Book book) throws BookServiceException{
        normalizeBook(book);
        validateBook(book);
        try {
            bookRepository.create(book);
            auditRepository.createAudit(book.getId(), BookAction.INSERT);
        } catch (BookRepositoryException e) {
            throw new BookServiceException("Failed to add book", e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Book> getBooks() throws BookServiceException{
        try {
            return bookRepository.getAllBooks();
        } catch (BookRepositoryException e) {
            throw new BookServiceException("Failed to fetch books", e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteBook(int id) throws BookServiceException{
        try {
            bookRepository.delete(id);
            auditRepository.createAudit(id, BookAction.DELETE);
        } catch (BookRepositoryException e) {
            throw new BookServiceException("Failed to delete book", e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editBook(Book book)throws BookServiceException {
        normalizeBook(book);
        validateBook(book);
        try {
            bookRepository.update(book);
            auditRepository.createAudit(book.getId(), BookAction.UPDATE);
        } catch (BookRepositoryException e) {
            throw new BookServiceException("Failed to update book", e);
        }
    }

    private void normalizeBook(Book book) {
        if (book.getName() != null)
            book.setName(book.getName().trim());
        if (book.getTitle() != null)
            book.setTitle(book.getTitle().trim());
        if (book.getDescription() != null)
            book.setDescription(book.getDescription().trim());
    }



    private void validateBook(Book book)throws BookServiceException {
        if (book == null) {
            throw new BookServiceException("Book data cannot be null.");
        }
        validateField(book.getName(), "name", 50);
        validateField(book.getTitle(), "Book title", 100);
        validateField(book.getDescription(), "Book description", 100);
        if (book.getPrice() <= 0) {
            throw new BookServiceException("Price must be greater than zero.");
        }
    }

    private void validateField(String value, String fieldName, int maxLength) throws BookServiceException{
        if (value == null || value.trim().isEmpty()) {
            throw new BookServiceException(fieldName + " cannot be null or empty spaces.");
        }
        if (value.length() > maxLength) {
            throw new BookServiceException(fieldName + " must not exceed " + maxLength + " characters.");
        }
    }



}
