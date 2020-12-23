package ru.ssau.tk.blashbanova.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

    JButton arrayButton = new JButton("Создание функции с помощью массивов");
    JButton tabulatedButton = new JButton("Создание табулировнной функции");
    JButton operationButton = new JButton("Операции над функциями");
    JButton settingsButton = new JButton("Настройки");
    JButton exitButton = new JButton("Выход");

    public MainWindow() {
        super("MainWindow");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        int width = 1280;
        int height = 720;
        setSize(width, height);

        getContentPane().add(arrayButton);
        getContentPane().add(tabulatedButton);
        getContentPane().add(operationButton);
        getContentPane().add(settingsButton);
        getContentPane().add(exitButton);

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
                .addComponent(arrayButton)
                .addComponent(tabulatedButton)
                .addComponent(operationButton)
                .addComponent(settingsButton)
                .addComponent(exitButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(arrayButton)
                .addComponent(tabulatedButton)
                .addComponent(operationButton)
                .addComponent(settingsButton)
                .addComponent(exitButton)
        );
    }

    private void addButtonListeners() {
        arrayButton.addActionListener(new AbstractAction() {
            private static final long serialVersionUID = -4459501857296985585L;

            @Override
            public void actionPerformed(ActionEvent e) {
                new Window();
            }
        });
        tabulatedButton.addActionListener(new AbstractAction() {
            private static final long serialVersionUID = 1878831017157398170L;

            @Override
            public void actionPerformed(ActionEvent e) {
                new SecondWindow();
            }
        });
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
