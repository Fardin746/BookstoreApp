package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.entity.AuditEntity;
import com.example.bookstoreapp.repository.entity.BookEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext(unitName = "BookStore_db")
    private EntityManager entityManager;

    @Override
    public void createAudit(int bookId, String auditType) {
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setBookId(bookId);
        auditEntity.setAuditType(auditType);
        auditEntity.setAuditDate(LocalDateTime.now());
        entityManager.persist(auditEntity);

    }

    @Override
    public void create(Book book) {
        BookEntity bookEntity = new BookEntity(book);
        entityManager.persist(bookEntity);
        book.setId(bookEntity.getId());
    }

    @Override
    public List<Book> getAllBooks() {
        return entityManager.createQuery("SELECT b FROM BookEntity b WHERE b.deleted = false ", BookEntity.class).getResultList()
                .stream().map(BookEntity::toBook).collect(Collectors.toList());
    }

    @Override
    public void update(Book book) {
        BookEntity bookEntity = entityManager.find(BookEntity.class, book.getId());
        if (bookEntity != null) {
            bookEntity.setName(book.getName());
            bookEntity.setTitle(book.getTitle());
            bookEntity.setDescription(book.getDescription());
            bookEntity.setPrice(book.getPrice());
            bookEntity.setType(book.getType());
            entityManager.merge(bookEntity);
        }

    }

    @Override
    public void delete(int id) {
        BookEntity bookEntity = entityManager.find(BookEntity.class, id);
        if (bookEntity != null) {
            bookEntity.setDeleted(true);
            entityManager.merge(bookEntity);
        }


    }


//    public void createAudit(EntityManager entityManager, Integer bookId, String auditType) {
//        AuditEntity auditEntity = new AuditEntity();
//        auditEntity.setBookId(bookId);
//        auditEntity.setAuditType(auditType);
//        auditEntity.setAuditDate(LocalDateTime.now());
//        entityManager.persist(auditEntity);
//    }
//
//    public List<Book> getAllBook(EntityManager entityManager){
//        return entityManager
//                .createQuery("SELECT b FROM BookEntity b WHERE b.deleted = false ",BookEntity.class)
//                .getResultList().stream().map(BookEntity::toBook).collect(Collectors.toList());
//
//    }
//
//    public void delete(EntityManager entityManager,Integer id){
//        BookEntity bookEntity = entityManager.find(BookEntity.class,id);
//        if (bookEntity != null){
//            bookEntity.setDeleted(true);
//            entityManager.merge(bookEntity);
//        }
//    }
//
//    public void create(EntityManager entityManager, Book book) {
//        BookEntity bookEntity = new BookEntity(book);
//        entityManager.persist(bookEntity);
//        book.setId(bookEntity.getId());
//    }
//
//
//    public void update(EntityManager entityManager, Book book) {
//        BookEntity bookEntity = entityManager.find(BookEntity.class, book.getId());
//        if (bookEntity != null) {
//            bookEntity.setName(book.getName());
//            bookEntity.setTitle(book.getTitle());
//            bookEntity.setDescription(book.getDescription());
//            bookEntity.setPrice(book.getPrice());
//            bookEntity.setType(book.getType());
//            entityManager.merge(bookEntity);
//        }
//    }


}
