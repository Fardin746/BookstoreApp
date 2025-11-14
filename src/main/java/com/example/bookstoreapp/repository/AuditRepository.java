package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.exception.BookRepositoryException;
import com.example.bookstoreapp.model.BookAction;

import javax.ejb.Local;

@Local
public interface AuditRepository {

    void createAudit(int bookId, BookAction auditType) throws BookRepositoryException;
}
