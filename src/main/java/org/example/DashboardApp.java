package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardApp extends JFrame {
    public DashboardApp() {
        setTitle("Library Dashboard");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 0, 20)); // Added vertical spacing

        JButton bookDisplayButton = new JButton("View Books");
        JButton bookEntryButton = new JButton("Add a Book");

        // Style buttons for better visibility
        styleButton(bookDisplayButton);
        styleButton(bookEntryButton);

        panel.add(bookDisplayButton);
        panel.add(bookEntryButton);

        add(panel);

        bookDisplayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBookDisplayApp();
            }
        });

        bookEntryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBookStorageApp();
            }
        });
    }

    private void openBookDisplayApp() {
        EventQueue.invokeLater(() -> {
            BookDisplayApp bookDisplayApp = new BookDisplayApp();
            bookDisplayApp.setVisible(true);
        });
    }

    private void openBookStorageApp() {
        EventQueue.invokeLater(() -> {
            BookStorageApp bookStorageApp = new BookStorageApp();
            bookStorageApp.setVisible(true);
        });
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(59, 89, 182));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set the look and feel to improve UI
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            DashboardApp dashboardApp = new DashboardApp();
            dashboardApp.setVisible(true);
        });
    }
}
