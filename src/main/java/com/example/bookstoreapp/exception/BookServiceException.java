package com.example.bookstoreapp.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class BookServiceException extends Exception {

    public BookServiceException(String message) {
        super(message);
    }

    public BookServiceException(String message, Throwable e) {
        super(message, e);
    }
}
