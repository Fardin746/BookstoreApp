package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.BookAction;
import com.example.bookstoreapp.repository.BookRepository;

import javax.ejb.*;
import java.util.List;

@Stateless
public  class BookServiceImpl implements BookService{

    @EJB
    private BookRepository bookRepository;


    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addBook(Book book) {
        bookRepository.create(book);
        bookRepository.createAudit(book.getId(), BookAction.INSERT_BOOK.name());
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Book> getBooks() {
        return bookRepository.getAllBooks();
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteBook(int id) {
            bookRepository.delete(id);
            bookRepository.createAudit(id, BookAction.DELETE_BOOK.name());


    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editBook(Book book) {
        bookRepository.update(book);
        bookRepository.createAudit(book.getId(), BookAction.UPDATE_BOOK.name());
    }



//    private final BookRepositoryImpl bookRepository= new BookRepositoryImpl();

//
//    @PersistenceContext(unitName = "BookStore_db")
//    private EntityManager entityManager;
//
//    @Transactional
//    public void addBook(Book book) {
//        try {
////            EntityManager entityManager = jpaUtil.begin();
//            EntityManager entityManager = jpaUtil.getEntityManager();
//            bookRepository.create(entityManager, book);
//            bookRepository.createAudit(entityManager, book.getId(), "INSERT_BOOK");
////            jpaUtil.commit();
//        } catch (Exception e) {
//             jpaUtil.rollback();
//        }
//    }
//
//
//    public List<Book> getBooks() {
//        EntityManager entityManager = jpaUtil.getEntityManager();
//        return bookRepository.getAllBook(entityManager);
//    }
//
//
//    @Transactional
//    public void deleteBook(int id) {
//        try {
////            EntityManager entityManager = jpaUtil.begin();
//            EntityManager entityManager = jpaUtil.getEntityManager();
//            bookRepository.delete(entityManager, id);
//            bookRepository.createAudit(entityManager, id, "DELETE_BOOK");
////            jpaUtil.commit();
//        } catch (Exception e) {
//            jpaUtil.rollback();
//        }
//    }
//
//
//    @Transactional
//    public void editBook(Book book) {
//        try {
////            EntityManager entityManager = jpaUtil.begin();
//            EntityManager entityManager = jpaUtil.getEntityManager();
//            bookRepository.update(entityManager, book);
//            bookRepository.createAudit(entityManager, book.getId(), "UPDATE_BOOK");
////            jpaUtil.commit();
//        } catch (Exception e) {
//            jpaUtil.rollback();
//        }
//    }



}
