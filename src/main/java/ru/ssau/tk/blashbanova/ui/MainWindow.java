package ru.ssau.tk.blashbanova.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JButton arrayButton = new JButton("Создание функции с помощью массивов");
    private final JButton tabulatedButton = new JButton("Создание табулировнной функции");
    private final JButton operationButton = new JButton("Операции над функциями");
    private final JButton diffButton = new JButton("Производная функции");
    private final JButton settingsButton = new JButton("Настройки");
    private final JButton exitButton = new JButton("Выход");

    public MainWindow() {
        super("MainWindow");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 500);
        setResizable(false);
        getContentPane().add(arrayButton);
        getContentPane().add(tabulatedButton);
        getContentPane().add(operationButton);
        getContentPane().add(settingsButton);
        getContentPane().add(exitButton);
        getContentPane().add(diffButton);
        arrayButton.setFocusPainted(false);
        tabulatedButton.setFocusPainted(false);
        operationButton.setFocusPainted(false);
        settingsButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);
        diffButton.setFocusPainted(false);
        compose();
        addButtonListeners();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(arrayButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(tabulatedButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(operationButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(diffButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(settingsButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(exitButton)
                )
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(70)
                .addComponent(arrayButton)
                .addGap(20)
                .addComponent(tabulatedButton)
                .addGap(20)
                .addComponent(operationButton)
                .addGap(20)
                .addComponent(diffButton)
                .addGap(20)
                .addComponent(settingsButton)
                .addGap(20)
                .addComponent(exitButton)
        );
    }

    private void addButtonListeners() {
        arrayButton.addActionListener(e -> new Window(SettingsWindow.getFactory()));
        tabulatedButton.addActionListener(e -> new SecondWindow(SettingsWindow.getFactory()));
        operationButton.addActionListener(e -> new OperationsWindow(SettingsWindow.getFactory()));
        settingsButton.addActionListener(e -> new SettingsWindow());
        diffButton.addActionListener(e -> new DiffWindow(SettingsWindow.getFactory()));
        exitButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
