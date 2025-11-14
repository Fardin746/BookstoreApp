package com.example.bookstoreapp.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BookRepositoryException extends Exception {

    public BookRepositoryException(String message, Throwable e) {
        super(message, e);
    }
}
