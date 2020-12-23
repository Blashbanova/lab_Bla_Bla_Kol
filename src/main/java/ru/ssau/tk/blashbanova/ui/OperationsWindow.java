package ru.ssau.tk.blashbanova.ui;

import ru.ssau.tk.blashbanova.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;
import ru.ssau.tk.blashbanova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


public class OperationsWindow extends JDialog {
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
    AbstractTableModel resultTableModel = new TableNotEditable(resultXValues, resultYValues);
    JTable resultTable = new JTable(resultTableModel);
    JComboBox<String> comboBox = new JComboBox<>(new String[]{"+", "-", "*", "/",});
    JButton saveButton = new JButton("Сохранить");
    JButton uploadButton = new JButton("Загрузить");
    JButton createButton = new JButton("Создать из...");
    JButton secondSaveButton = new JButton("Сохранить");
    JButton resultSaveButton = new JButton("Сохранить");
    JButton secondUploadButton = new JButton("Загрузить");
    JButton secondCreateButton = new JButton("Создать из...");
    JButton resultButton = new JButton("=");
    private final TabulatedFunctionFactory factory;
    private TabulatedFunction firstFunction;
    private TabulatedFunction secondFunction;

    public OperationsWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(1000, 400);
        saveButton.setFocusPainted(false);
        uploadButton.setFocusPainted(false);
        createButton.setFocusPainted(false);
        secondSaveButton.setFocusPainted(false);
        secondCreateButton.setFocusPainted(false);
        secondUploadButton.setFocusPainted(false);
        resultSaveButton.setFocusPainted(false);
        resultButton.setFocusPainted(false);
        comboBox.setPreferredSize(new Dimension(2, 2));
        comboBox.setFont(new Font("Consolas", Font.BOLD, 18));
        resultButton.setFont(new Font("Consolas", Font.BOLD, 24));
        getContentPane().add(resultButton);
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
                        .addGap(8)
                        .addComponent(tableScrollPane)
                        .addComponent(comboBox)
                        .addComponent(secondTableScrollPane)
                        .addComponent(resultButton)
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(33)
                        .addComponent(secondCreateButton)
                        .addComponent(secondUploadButton)
                        .addComponent(secondSaveButton)
                        .addGap(125)
                        .addComponent(resultSaveButton)));
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
                                .addComponent(resultButton)
                        )
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(60)
                        .addComponent(secondCreateButton)
                        .addComponent(secondUploadButton)
                        .addComponent(secondSaveButton)
                        .addGap(60)
                        .addComponent(resultSaveButton)));
    }

    private void setValues(List<String> xValues, List<String> yValues, TabulatedFunction function) {
        xValues.clear();
        yValues.clear();
        for (int i = 0; i < function.getCount(); i++) {
            xValues.add(Double.toString(function.getX(i)));
            yValues.add(Double.toString(function.getY(i)));
        }
    }

    private void getPopupMenu(JButton button, List<String> xValues, List<String> yValues, AbstractTableModel tableModel) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem table = new JMenuItem("таблицы");
        JMenuItem func = new JMenuItem("встроенной функции");
        table.addActionListener(ee -> {
            Window window = new Window(factory);
            if (button == createButton) {
                firstFunction = window.getFunction();
            } else if (button == secondCreateButton) {
                secondFunction = window.getFunction();
            }
            setValues(xValues, yValues, window.getFunction());
            tableModel.fireTableDataChanged();
        });
        func.addActionListener(ee -> {
            SecondWindow secondWindow = new SecondWindow(factory);
            if (button == createButton) {
                firstFunction = secondWindow.getFunction();
            } else if (button == secondCreateButton) {
                secondFunction = secondWindow.getFunction();
            }
            setValues(xValues, yValues, secondWindow.getFunction());
            tableModel.fireTableDataChanged();
        });
        popupMenu.add(table);
        popupMenu.addSeparator();
        popupMenu.add(func);
        popupMenu.show(button, button.getWidth() + 1, button.getHeight() / 30);
    }

    private TabulatedFunction getOperation(TabulatedFunction a, TabulatedFunction b) {
        TabulatedFunctionOperationService operation = new TabulatedFunctionOperationService(factory);
        switch ((String) comboBox.getSelectedItem()) {
            case "+":
                return operation.sum(a, b);
            case "-":
                return operation.subtract(a, b);
            case "*":
                return operation.multiply(a, b);
            case "/":
                return operation.divide(a, b);
        }
        return a;
    }

    private void addButtonListeners() {
        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getPopupMenu(createButton, xValues, yValues, firstTableModel);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        secondCreateButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getPopupMenu(secondCreateButton, secondXValues, secondYValues, secondTableModel);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        resultButton.addActionListener(e -> {
            try {
                TabulatedFunction resultFunction = getOperation(firstFunction, secondFunction);
                setValues(resultXValues, resultYValues, resultFunction);
                resultTableModel.fireTableDataChanged();
            } catch (NullPointerException exp) {
                ExceptionHandler.showMessage("Введите обе функции!");
            } catch (InconsistentFunctionsException exp) {
                ExceptionHandler.showCorgiMessage(exp.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        new OperationsWindow(new ArrayTabulatedFunctionFactory());
    }
}
