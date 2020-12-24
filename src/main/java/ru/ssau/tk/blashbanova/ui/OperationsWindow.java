package ru.ssau.tk.blashbanova.ui;

import ru.ssau.tk.blashbanova.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;
import ru.ssau.tk.blashbanova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.io.FunctionsIO;
import ru.ssau.tk.blashbanova.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperationsWindow extends JDialog {
    private static final int FIRST_FUNCTION = 0;
    private static final int SECOND_FUNCTION = 1;
    private final List<String> xValues = new ArrayList<>();
    private final List<String> yValues = new ArrayList<>();
    private final List<String> secondXValues = new ArrayList<>();
    private final List<String> secondYValues = new ArrayList<>();
    private final List<String> resultXValues = new ArrayList<>();
    private final List<String> resultYValues = new ArrayList<>();
    private final AbstractTableModel firstTableModel = new TablePartlyEditable(xValues, yValues);
    private final JTable firstTable = new JTable(firstTableModel);
    private final AbstractTableModel secondTableModel = new TablePartlyEditable(secondXValues, secondYValues);
    private final JTable secondTable = new JTable(secondTableModel);
    private final AbstractTableModel resultTableModel = new TableNotEditable(resultXValues, resultYValues);
    private final JTable resultTable = new JTable(resultTableModel);
    private final JComboBox<String> comboBox = new JComboBox<>(new String[]{"+", "-", "*", "/"});
    private final JButton saveButton = new JButton("Сохранить");
    private final JButton uploadButton = new JButton("Загрузить");
    private final JButton createButton = new JButton("Создать из...");
    private final JButton secondSaveButton = new JButton("Сохранить");
    private final JButton resultSaveButton = new JButton("Сохранить");
    private final JButton secondUploadButton = new JButton("Загрузить");
    private final JButton secondCreateButton = new JButton("Создать из...");
    private final JButton resultButton = new JButton("=");
    private JFileChooser fileChooser;
    private final TabulatedFunctionFactory factory;
    private TabulatedFunction firstFunction;
    private TabulatedFunction secondFunction;
    private TabulatedFunction resultFunction;

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
        saveButton.setEnabled(false);
        secondSaveButton.setEnabled(false);
        resultSaveButton.setEnabled(false);
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

    private void setTable(TabulatedFunction function, int flag) {
        if (function != null) {
            switch (flag) {
                case FIRST_FUNCTION:
                    firstFunction = function;
                    saveButton.setEnabled(true);
                    setValues(xValues, yValues, firstFunction);
                    firstTableModel.fireTableDataChanged();
                    break;
                case SECOND_FUNCTION:
                    secondFunction = function;
                    secondSaveButton.setEnabled(true);
                    setValues(secondXValues, secondYValues, secondFunction);
                    secondTableModel.fireTableDataChanged();
            }
        }
    }

    private void getPopupMenu(JButton button, int flag) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem table = new JMenuItem("таблицы");
        JMenuItem func = new JMenuItem("встроенной функции");
        table.addActionListener(ee -> {
            Window window = new Window(factory);
            setTable(window.getFunction(), flag);
        });
        func.addActionListener(ee -> {
            SecondWindow secondWindow = new SecondWindow(factory);
            setTable(secondWindow.getFunction(), flag);
        });
        popupMenu.add(table);
        popupMenu.addSeparator();
        popupMenu.add(func);
        popupMenu.show(button, button.getWidth() + 1, button.getHeight() / 30);
    }

    private void addButtonListeners() {
        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getPopupMenu(createButton, FIRST_FUNCTION);
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
                    getPopupMenu(secondCreateButton, SECOND_FUNCTION);
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
                TabulatedFunctionOperationService operation = new TabulatedFunctionOperationService(factory);
                switch ((String) comboBox.getSelectedItem()) {
                    case "+":
                        resultFunction = operation.sum(firstFunction, secondFunction);
                        break;
                    case "-":
                        resultFunction = operation.subtract(firstFunction, secondFunction);
                        break;
                    case "*":
                        resultFunction = operation.multiply(firstFunction, secondFunction);
                        break;
                    case "/":
                        resultFunction = operation.divide(firstFunction, secondFunction);
                }
                setValues(resultXValues, resultYValues, resultFunction);
                resultSaveButton.setEnabled(true);
                resultTableModel.fireTableDataChanged();
            } catch (InconsistentFunctionsException exp) {
                ExceptionHandler.showCorgiMessage(exp.getMessage());
            } catch (NullPointerException exp) {
                ExceptionHandler.showMessage("Введите обе функции!");
            }
        });
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        uploadButton.addActionListener(e -> readFunction(FIRST_FUNCTION));
        secondUploadButton.addActionListener(e -> readFunction(SECOND_FUNCTION));
        saveButton.addActionListener(e -> writeFunction(firstFunction));
        secondSaveButton.addActionListener(e -> writeFunction(secondFunction));
        resultSaveButton.addActionListener(e -> writeFunction(resultFunction));
    }

    private void readFunction(int flag) {
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            try (BufferedReader arrayReader = new BufferedReader(new FileReader(file))) {
                TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(arrayReader, factory);
                switch (flag) {
                    case FIRST_FUNCTION:
                        firstFunction = arrayFunction;
                        setValues(xValues, yValues, arrayFunction);
                        saveButton.setEnabled(true);
                        firstTableModel.fireTableDataChanged();
                        break;
                    case SECOND_FUNCTION:
                        secondFunction = arrayFunction;
                        setValues(secondXValues, secondYValues, arrayFunction);
                        secondSaveButton.setEnabled(true);
                        secondTableModel.fireTableDataChanged();
                }
            } catch (IOException e) {
                ExceptionHandler.showCorgiMessage(e.getMessage());
            } catch (NumberFormatException exp) {
                ExceptionHandler.showMessage("Файл пуст или данные введены некорректно!");
            }
        }
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
        new OperationsWindow(new ArrayTabulatedFunctionFactory());
    }
}
