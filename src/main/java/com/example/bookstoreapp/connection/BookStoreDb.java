package com.example.bookstoreapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookStoreDb {

    private static final String URL = "jdbc:mysql://localhost:3306/BookStore_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Fardeen@742";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) throws SQLException{
        if(connection != null){
            connection.close();
        }
    }

}
