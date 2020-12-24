package ru.ssau.tk.blashbanova.ui;

import ru.ssau.tk.blashbanova.functions.TabulatedFunction;
import ru.ssau.tk.blashbanova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.io.FunctionsIO;
import ru.ssau.tk.blashbanova.operations.TabulatedDifferentialOperator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DiffWindow extends JDialog {
    private final List<String> xValues = new ArrayList<>();
    private final List<String> yValues = new ArrayList<>();
    private final List<String> resultXValues = new ArrayList<>();
    private final List<String> resultYValues = new ArrayList<>();
    private final AbstractTableModel firstTableModel = new TablePartlyEditable(xValues, yValues);
    private final JTable firstTable = new JTable(firstTableModel);
    private final AbstractTableModel resultTableModel = new TableNotEditable(resultXValues, resultYValues);
    private final JTable resultTable = new JTable(resultTableModel);
    private final JButton saveButton = new JButton("Сохранить");
    private final JButton uploadButton = new JButton("Загрузить");
    private final JButton createButton = new JButton("Создать из...");
    private final JButton resultSaveButton = new JButton("Сохранить");
    private final JButton resultButton = new JButton("Дифференцировать");
    private JFileChooser fileChooser;
    private final TabulatedFunctionFactory factory;
    private TabulatedFunction firstFunction;
    private TabulatedFunction resultFunction;

    public DiffWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(800, 400);
        saveButton.setFocusPainted(false);
        uploadButton.setFocusPainted(false);
        createButton.setFocusPainted(false);
        resultSaveButton.setFocusPainted(false);
        resultButton.setFocusPainted(false);
        saveButton.setEnabled(false);
        resultSaveButton.setEnabled(false);
        resultButton.setEnabled(false);
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
        JScrollPane resultTableScrollPane = new JScrollPane(resultTable);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(110)
                        .addComponent(resultButton))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(tableScrollPane)
                        .addGap(40)
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addGap(25)
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(200)
                        .addComponent(resultSaveButton)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(resultButton)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tableScrollPane)
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
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

    private void setTable(TabulatedFunction function) {
        if (function != null) {
            firstFunction = function;
            saveButton.setEnabled(true);
            resultButton.setEnabled(true);
            setValues(xValues, yValues, firstFunction);
            firstTableModel.fireTableDataChanged();
        }
    }

    private void addButtonListeners() {
        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem table = new JMenuItem("таблицы");
                    JMenuItem func = new JMenuItem("встроенной функции");
                    table.addActionListener(ee -> {
                        Window window = new Window(factory);
                        setTable(window.getFunction());
                    });
                    func.addActionListener(ee -> {
                        SecondWindow secondWindow = new SecondWindow(factory);
                        setTable(secondWindow.getFunction());
                    });
                    popupMenu.add(table);
                    popupMenu.addSeparator();
                    popupMenu.add(func);
                    popupMenu.show(createButton, createButton.getWidth() + 1, createButton.getHeight() / 30);
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
                TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
                resultFunction = operator.derive(firstFunction);
                setValues(resultXValues, resultYValues, resultFunction);
                resultSaveButton.setEnabled(true);
                resultTableModel.fireTableDataChanged();
            } catch (NullPointerException exp) {
                ExceptionHandler.showMessage("Сначала введите функцию");
            }
        });
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        uploadButton.addActionListener(e -> {
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                try (BufferedReader arrayReader = new BufferedReader(new FileReader(file))) {
                    TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(arrayReader, factory);
                    firstFunction = arrayFunction;
                    setValues(xValues, yValues, arrayFunction);
                    saveButton.setEnabled(true);
                    resultButton.setEnabled(true);
                    firstTableModel.fireTableDataChanged();
                } catch (IOException exp) {
                    ExceptionHandler.showCorgiMessage(exp.getMessage());
                } catch (NumberFormatException exp) {
                    ExceptionHandler.showMessage("Файл пуст или данные введены некорректно!");
                }
            }
        });
        saveButton.addActionListener(e -> writeFunction(firstFunction));
        resultSaveButton.addActionListener(e -> writeFunction(resultFunction));
    }

    private void writeFunction(TabulatedFunction function) {
        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            try (BufferedWriter arrayFileWriter = new BufferedWriter(new FileWriter(file))) {
                FunctionsIO.writeTabulatedFunction(arrayFileWriter, function);
            } catch (IOException e) {
                ExceptionHandler.showCorgiMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new DiffWindow(new ArrayTabulatedFunctionFactory());
    }
}

