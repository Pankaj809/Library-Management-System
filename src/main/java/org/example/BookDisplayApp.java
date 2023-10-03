package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDisplayApp extends JFrame {
    private JPanel bookPanel;

    public BookDisplayApp() {
        setTitle("Book Display App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bookPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane scrollPane = new JScrollPane(bookPanel);

        add(scrollPane);

        displayBooks();
    }

    private void displayBooks() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            String jdbcUrl = "jdbc:mysql://localhost/library";
            String username = "myuser";
            String password = "mypassword";
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Define the SQL query to retrieve book information
            String sql = "SELECT title, author, publication_date FROM books";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publicationDate = resultSet.getString("publication_date");

                // Create a panel for each book
                JPanel bookFrame = createBookFrame(title, author, publicationDate);
                bookPanel.add(bookFrame);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close the database connection, prepared statement, and result set
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private JPanel createBookFrame(String title, String author, String publicationDate) {
        JPanel bookFrame = new JPanel();
        bookFrame.setLayout(new BoxLayout(bookFrame, BoxLayout.Y_AXIS));
        bookFrame.setPreferredSize(new Dimension(150, 200));
        bookFrame.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel titleLabel = new JLabel("Title: " + title);
        JLabel authorLabel = new JLabel("Author: " + author);
        JLabel dateLabel = new JLabel("Date Published: " + publicationDate);

        bookFrame.add(Box.createVerticalStrut(10));
        bookFrame.add(titleLabel);
        bookFrame.add(authorLabel);
        bookFrame.add(dateLabel);

        return bookFrame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookDisplayApp app = new BookDisplayApp();
            app.setVisible(true);
        });
    }
}
