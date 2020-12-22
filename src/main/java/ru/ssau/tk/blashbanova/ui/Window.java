package ru.ssau.tk.blashbanova.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {
    List<String> xValues = new ArrayList<>();
    List<String> yValues = new ArrayList<>();
    AbstractTableModel tableModel = new TableXY(xValues, yValues);
    JTable table = new JTable(tableModel);
    JLabel label = new JLabel("Введите количество точек:");
    JTextField textField = new JTextField("");
    JButton firstButton = new JButton("Добавить");
    JButton secondButton = new JButton("Создать");

    public Window() {
        super("Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(800, 400);

        getContentPane().add(label);
        getContentPane().add(textField);
        getContentPane().add(firstButton);
        getContentPane().add(secondButton);

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
                        .addComponent(firstButton)
                        .addComponent(secondButton))
                .addComponent(tableScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(firstButton)
                        .addComponent(secondButton))
                .addComponent(tableScrollPane)
        );
    }

    private void addButtonListeners() {
        firstButton.addActionListener(new AbstractAction() {
            private static final long serialVersionUID = -8821686667162924798L;

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Integer.parseInt(textField.getText()); i++) {
                    xValues.add(" ");
                    yValues.add(" ");
                }
                tableModel.fireTableDataChanged();
            }
        });

        secondButton.addActionListener(new AbstractAction() {
            private static final long serialVersionUID = 3602367915716170408L;

            @Override
            public void actionPerformed(ActionEvent e) {
                //что-то нужно сделать
            }
        });
    }

    public static void main(String[] args) {
        new Window();
    }
}
