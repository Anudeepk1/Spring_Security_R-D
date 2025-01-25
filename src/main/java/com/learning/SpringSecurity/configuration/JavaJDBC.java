package com.learning.SpringSecurity.configuration;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.*;
import java.util.UUID;

public class JavaJDBC {
    public static void main(String[] args) {
        // JDBC URL, username, and password for MySQL
        String url = "jdbc:mysql://localhost:3306/anudeep_db";  // Replace with your database URL
        String username = "root";  // Replace with your database username
        String password = "root";  // Replace with your database password

        String random = String.valueOf(UUID.randomUUID());

        SecureRandom random1 = new SecureRandom();
        String runId = new BigInteger(256, random1).toString(32);
        // SQL query to retrieve all records from 'book' table
        String query = "SELECT * FROM book";

        // Establishing the connection and executing the query
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database!");

            // Create a Statement object to send the SQL query to the database
            Statement statement = connection.createStatement();

            // Execute the query
            ResultSet resultSet = statement.executeQuery(query);

            // Loop through the result set and print the data
            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                String bookName = resultSet.getString("name");
                String author = resultSet.getString("author");
                String price = resultSet.getString("price");

                // Print the retrieved data
                System.out.println("Book ID: " + bookId + ", Name: " + bookName + ", Author: " + author + ", Price: " + price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
