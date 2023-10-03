package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardApp extends JFrame {
    public DashboardApp() {
        setTitle("Library Dashboard");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton bookDisplayButton = new JButton("Book Display");
        JButton bookEntryButton = new JButton("Book Entry");

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardApp dashboardApp = new DashboardApp();
            dashboardApp.setVisible(true);
        });
    }
}
