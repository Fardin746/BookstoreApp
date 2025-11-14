package com.example.bookstoreapp.repository.impl;

import com.example.bookstoreapp.exception.BookRepositoryException;
import com.example.bookstoreapp.model.BookAction;
import com.example.bookstoreapp.repository.AuditRepository;
import com.example.bookstoreapp.repository.entity.AuditEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Stateless
public  class AuditRepositoryImpl implements AuditRepository {


    @PersistenceContext(unitName = "BookStore_db")
    EntityManager entityManager;


    @Override
    public void createAudit(int bookId, BookAction auditType) throws BookRepositoryException {
        try {
            AuditEntity auditEntity = new AuditEntity();
            auditEntity.setBookId(bookId);
            auditEntity.setAuditType(auditType);
            auditEntity.setAuditDate(java.time.LocalDateTime.now());
            entityManager.persist(auditEntity);
        }catch (PersistenceException e){
            throw new BookRepositoryException("Error while saving audit",e);
        }

    }
}
