package com.example.bookstoreapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookStoreDb {
    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/BookStore_db";
        String user = "root";
        String password = "Fardeen@742";
        String driver = "com.mysql.cj.jdbc.Driver";

         return DriverManager.getConnection(url, user, password);
    }
}
