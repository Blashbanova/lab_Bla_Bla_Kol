package ru.ssau.tk.blashbanova.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SecondWindow extends JFrame {
    List<String> xValues = new ArrayList<>();
    List<String> yValues = new ArrayList<>();


    JLabel label = new JLabel("Введите количество точек разбиения:");
    JTextField textField = new JTextField("");
    JLabel secondLabel = new JLabel("Интервал с");
    JTextField secondTextField = new JTextField("");
    JLabel thirdLabel = new JLabel("по");
    JTextField thirdTextField = new JTextField("");
    JComboBox<String> comboBox = new JComboBox<>(new String[] {
            "Гиперболический тангенс", "Единичная функция", "Квадратичная функция", "Константная функция", "Модуль функция", "Нулевая функция", "Тождественная функция"
    });
    JButton firstButton = new JButton("Создать");

    public SecondWindow() {
        super("SecondWindow");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 200);

        getContentPane().add(label);
        getContentPane().add(textField);
        getContentPane().add(secondLabel);
        getContentPane().add(secondTextField);
        getContentPane().add(thirdLabel);
        getContentPane().add(thirdTextField);
        getContentPane().add(comboBox);
        getContentPane().add(firstButton);

        compose();
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
                        .addComponent(label)
                        .addComponent(textField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(secondLabel)
                        .addComponent(secondTextField)
                        .addComponent(thirdLabel)
                        .addComponent(thirdTextField))
                .addComponent(comboBox)
                .addComponent(firstButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(textField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(secondLabel)
                        .addComponent(secondTextField)
                        .addComponent(thirdLabel)
                        .addComponent(thirdTextField))
                .addComponent(comboBox)
                .addComponent(firstButton)
        );
    }

    public static void main(String[] args) {
        new SecondWindow();
    }
}
