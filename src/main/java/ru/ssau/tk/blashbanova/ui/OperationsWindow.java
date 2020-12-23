package ru.ssau.tk.blashbanova.ui;

import ru.ssau.tk.blashbanova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OperationsWindow extends JFrame {
    List<String> xValues = new ArrayList<>();
    List<String> yValues = new ArrayList<>();
    List<String> secondXValues = new ArrayList<>();
    List<String> secondYValues = new ArrayList<>();
    List<String> resultXValues = new ArrayList<>();
    List<String> resultYValues = new ArrayList<>();
    AbstractTableModel firstTableModel = new TablePartlyEditable(xValues, yValues);
    JTable firstTable = new JTable(firstTableModel);
    AbstractTableModel secondTableModel = new TablePartlyEditable(secondXValues, secondYValues);
    JTable secondTable = new JTable(secondTableModel);
    AbstractTableModel resultTableModel = new TablePartlyEditable(resultXValues, resultYValues);
    JTable resultTable = new JTable(resultTableModel);
    JLabel label = new JLabel("=");
    JComboBox<String> comboBox = new JComboBox<>(new String[]{"+", "-", "*", "/",});
    JButton saveButton = new JButton("Сохранить");
    JButton uploadButton = new JButton("Загрузить");
    JButton createButton = new JButton("Создать");
    JButton secondSaveButton = new JButton("Сохранить");
    JButton secondUploadButton = new JButton("Загрузить");
    JButton secondCreateButton = new JButton("Создать");


    public OperationsWindow() {
        super("Operation Service");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(1000, 400);
        saveButton.setFocusPainted(false);
        uploadButton.setFocusPainted(false);
        createButton.setFocusPainted(false);
        comboBox.setPreferredSize(new Dimension(2, 2));
        label.setFont(new Font("Consolas", Font.BOLD, 24));
        getContentPane().add(label);
        getContentPane().add(comboBox);
        getContentPane().add(saveButton);
        getContentPane().add(uploadButton);
        getContentPane().add(createButton);
        firstTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        JScrollPane tableScrollPane = new JScrollPane(firstTable);
        JScrollPane secondTableScrollPane = new JScrollPane(secondTable);
        JScrollPane resultTableScrollPane = new JScrollPane(resultTable);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(tableScrollPane)
                        .addComponent(comboBox)
                        .addComponent(secondTableScrollPane)
                        .addComponent(label)
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(60)
                        .addComponent(secondCreateButton)
                        .addComponent(secondUploadButton)
                        .addComponent(secondSaveButton)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tableScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120)
                                .addComponent(comboBox)
                                .addGap(110)
                        )
                        .addComponent(secondTableScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(130)
                                .addComponent(label)
                        )
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(60)
                        .addComponent(secondCreateButton)
                        .addComponent(secondUploadButton)
                        .addComponent(secondSaveButton)));
    }

    private void addButtonListeners() {
        createButton.addActionListener(e -> {
            Window secondWindow = new Window();
            TabulatedFunction function = secondWindow.getFunction();
            for (int i = 0; i < function.getCount(); i++) {
                xValues.add(Double.toString(function.getX(i)));
                yValues.add(Double.toString(function.getY(i)));
            }
            firstTableModel.fireTableDataChanged();
        });
    }

    public static void main(String[] args) {
        new OperationsWindow();
    }
}
