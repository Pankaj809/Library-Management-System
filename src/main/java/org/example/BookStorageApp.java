package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookStorageApp extends JFrame {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField publicationDateField;

    public BookStorageApp() {
        setTitle("Book Storage App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField();
        JLabel genreLabel = new JLabel("Genre:");
        genreField = new JTextField();
        JLabel publicationDateLabel = new JLabel("Publication Date (YYYY-MM-DD):");
        publicationDateField = new JTextField();

        JButton submitButton = new JButton("Submit");

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(genreLabel);
        panel.add(genreField);
        panel.add(publicationDateLabel);
        panel.add(publicationDateField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(submitButton);

        add(panel);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String genre = genreField.getText();
                String publicationDate = publicationDateField.getText();

                // Save the book to the database
                saveBookToDatabase(title, author, genre, publicationDate);

                // Clear the input fields
                titleField.setText("");
                authorField.setText("");
                genreField.setText("");
                publicationDateField.setText("");
            }
        });
    }

    private void saveBookToDatabase(String title, String author, String genre, String publicationDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Connect to the database
            String jdbcUrl = "jdbc:mysql://localhost/library";
            String username = "myuser";
            String password = "mypassword";
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Define the SQL query to insert the book information
            String sql = "INSERT INTO books (title, author, genre, publication_date) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Set the values for the placeholders in the SQL query
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, publicationDate);

            // Execute the SQL query to insert the book information
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Book information saved to the database.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save book information to the database.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } finally {
            // Close the database connection and prepared statement
            try {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookStorageApp app = new BookStorageApp();
            app.setVisible(true);
        });
    }
}
