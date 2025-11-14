//package com.example.bookstoreapp.repository;
//
//import com.example.bookstoreapp.connection.BookStoreDb;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class TransactionManager {
//
//    public Connection startTransaction() throws SQLException {
//        Connection connection = BookStoreDb.getConnection();
//        connection.setAutoCommit(false);
//        return connection;
//    }
//
//    public void commit(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.commit();
//                System.out.println("Transaction committed");
//            } catch (SQLException e) {
//                System.out.println("Commit failed: " + e.getMessage());
//            }
//        }
//    }
//
//    public void rollback(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.rollback();
//                System.out.println("Transaction rolled back");
//            } catch (SQLException e) {
//                System.out.println("Rollback failed: " + e.getMessage());
//            }
//        }
//    }
//
//    public void close(Connection connection) throws SQLException {
//        BookStoreDb.closeConnection(connection);
//    }
//}
