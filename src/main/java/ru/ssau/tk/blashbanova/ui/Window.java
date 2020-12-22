package ru.ssau.tk.blashbanova.ui;

import ru.ssau.tk.blashbanova.exceptions.ArrayIsNotSortedException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {
    List<String> xValues = new ArrayList<>();
    List<String> yValues = new ArrayList<>();
    AbstractTableModel tableModel = new TableXY(xValues, yValues);
    JTable table = new JTable(tableModel);
    JLabel label = new JLabel("Число точек:");
    JTextField textField = new JTextField("");
    JButton addingButton = new JButton("Добавить");
    JButton cancelButton = new JButton("Отмена");
    JButton refreshButton = new JButton("Очистить");
    JButton createButton = new JButton("Создать");

    public Window() {
        super("Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 400);
        addingButton.setFocusPainted(false);
        cancelButton.setFocusPainted(false);
        refreshButton.setFocusPainted(false);
        createButton.setFocusPainted(false);
        getContentPane().add(label);
        getContentPane().add(textField);
        getContentPane().add(addingButton);
        getContentPane().add(cancelButton);
        getContentPane().add(refreshButton);
        getContentPane().add(createButton);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(addingButton)
                        .addComponent(cancelButton))
                .addComponent(tableScrollPane)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addComponent(refreshButton)
                )
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(addingButton)
                        .addComponent(cancelButton))
                .addComponent(tableScrollPane)
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(refreshButton)
                )
        );
    }

    private void addButtonListeners() {
        class AddingAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = Integer.parseInt(textField.getText());
                    if (size < 0) {
                        ExceptionHandler.showMessage("Введите положительное число.");
                    }
                    for (int i = 0; i < size; i++) {
                        xValues.add(" ");
                        yValues.add(" ");
                    }
                    tableModel.fireTableDataChanged();
                } catch (NumberFormatException exp) {
                    ExceptionHandler.showMessage("Введите целое число.");
                }
            }
        }
        textField.addActionListener(new AddingAction());
        addingButton.addActionListener(new AddingAction());
        createButton.addActionListener(e -> {
            try {
                int size = xValues.size();
                double[] x = new double[size];
                double[] y = new double[size];
            } catch (NumberFormatException exp) {
                ExceptionHandler.showMessage("Введите число в виде десятичной дроби через точку.");
            } catch (ArrayIsNotSortedException exp) {
                ExceptionHandler.showMessage("Некорректные данные: значения X должны располагаться по возрастанию.");
            } catch (IllegalArgumentException exp) {
                ExceptionHandler.showMessage("Невозможно создать функцию менее чем из двух точек.");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new Window();
    }
}
