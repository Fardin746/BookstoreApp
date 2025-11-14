package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.utility.JpaUtil;
import javax.persistence.EntityManager;
import java.util.List;

public class BookService {

    private final BookRepository bookRepository = new BookRepository();
    private final JpaUtil jpaUtil = new JpaUtil();


//    public List<Book> getBooks()  {
//        return bookRepository.getAllBooks();
//    }

//    public void deleteBook(int id){
//        bookRepository.delete(id);
//    }

//    public void addBook(Book book){
//        bookRepository.create(book);
//    }

//    public void addBook(Book book) {
//        try {
//            EntityManager entityManager = jpaUtil.begin();
//            bookRepository.create(entityManager,book);
//            AuditEntity auditEntity = new AuditEntity();
//            auditEntity.setAuditType("INSERT_BOOK");
//            auditEntity.setBookId(book.getId());
//            auditEntity.setAuditDate(LocalDateTime.now());
//            bookRepository.createAudit(entityManager, auditEntity);
//            jpaUtil.commit();
//        } catch (Exception e) {
//            jpaUtil.rollback();
//        }
//    }

    public void addBook(Book book) {
        try {
            EntityManager entityManager = jpaUtil.begin();
            System.out.println("before id :"+book.getId());
            bookRepository.create(entityManager, book);
            System.out.println("after id :"+book.getId());
            bookRepository.createAudit(entityManager, book.getId(), "INSERT_BOOK");
            jpaUtil.commit();
        } catch (Exception e) {
            jpaUtil.rollback();
        }
    }

    public List<Book> getBooks() {
        EntityManager entityManager = jpaUtil.getEntityManager();
        return bookRepository.getAllBook(entityManager);
    }


    public void deleteBook(int id) {
        try {
            EntityManager entityManager = jpaUtil.begin();
            bookRepository.delete(entityManager, id);
            bookRepository.createAudit(entityManager, id, "DELETE_BOOK");
            jpaUtil.commit();
        } catch (Exception e) {
            jpaUtil.rollback();
        }
    }

    public void editBook(Book book) {
        try {
            EntityManager entityManager = jpaUtil.begin();
            bookRepository.update(entityManager, book);
            bookRepository.createAudit(entityManager, book.getId(), "UPDATE_BOOK");
            jpaUtil.commit();
        } catch (Exception e) {
            jpaUtil.rollback();
        }
    }




//public void addBook(Book book) {
//    try {
//        EntityManager entityManager = jpaUtil.begin();
//        BookEntity bookEntity = bookRepository.create(entityManager, book);
//        System.out.println("after  id : "+bookEntity.getId());
//        AuditEntity auditEntity = new AuditEntity();
//        auditEntity.setAuditType("INSERT_BOOK");
//        auditEntity.setBookId(bookEntity.getId());
//        auditEntity.setAuditDate(LocalDateTime.now());
//        bookRepository.createAudit(entityManager, auditEntity);
//        jpaUtil.commit();
//    } catch (Exception e) {
//        jpaUtil.rollback();
//    }
//}



}
