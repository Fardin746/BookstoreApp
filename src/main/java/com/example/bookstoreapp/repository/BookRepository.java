package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.connection.BookStoreDb;
import com.example.bookstoreapp.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookRepository {

    public List<Book> getAllBooks() {
        String query = "SELECT * FROM Books";
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = BookStoreDb.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setTitle(resultSet.getString("title"));
                book.setDescription(resultSet.getString("description"));
                book.setPrice(resultSet.getDouble("price"));
                book.setType(resultSet.getString("type"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return books;
    }

    public void delete(int id) {
        String query = "DELETE FROM Books WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BookStoreDb.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void create(Book book) {
        String query = "INSERT INTO Books (name,title,description,price,type) VALUES (?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BookStoreDb.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setString(5, book.getType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void update(Book book) {
        String query = "UPDATE Books SET name = ?,title=?,description=?,price=?,type=? WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BookStoreDb.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setString(5, book.getType());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
