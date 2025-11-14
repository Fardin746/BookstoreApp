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
        String deleteAudit = "INSERT INTO books_audit(audit_type,book_id) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BookStoreDb.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(deleteAudit);
            preparedStatement.setString(1, "DELETE_BOOK");
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
//            System.out.println("audit deleted successfully" + i1);



//            if (i == 0) {
//                throw new RuntimeException("Book not found");
//            }

            connection.commit();
            System.out.println("Book and audit deleted successfully");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("transaction rollback due to error");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
//                if (connection != null) {
//                    connection.close();
//                }
                BookStoreDb.closeConnection(connection);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void update(Book book) {
        String query = "UPDATE Books SET name = ?,title=?,description=?,price=?,type=? WHERE id = ?";
        String updateAudit = "INSERT INTO books_audit(audit_type,book_id) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = BookStoreDb.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setString(5, book.getType());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(updateAudit);
            preparedStatement.setString(1, "UPDATE_BOOK");
            preparedStatement.setInt(2, book.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Book and audit updated successfully");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("transaction rollback due to error");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
//                if (connection != null) {
//                    connection.close();
//                }
                BookStoreDb.closeConnection(connection);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void create(Book book) {
        String query = "INSERT INTO Books (name,title,description,price,type) VALUES (?,?,?,?,?)";
        String insertAudit = "INSERT INTO books_audit(audit_type,book_id) VALUES (?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int bookId = 0;
        try {
            connection = BookStoreDb.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setString(5, book.getType());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                bookId = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(insertAudit);
            preparedStatement.setString(1, "INSERT_BOOK");
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();
//            throw new SQLException("Book not created");

            connection.commit();

            System.out.println("Book and audit create successfully");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("transaction rollback due to error");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                BookStoreDb.closeConnection(connection);
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
