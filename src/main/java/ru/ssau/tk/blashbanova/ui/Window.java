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
    JTextField textField = new JTextField("2"); //пока что 2
    JButton button = new JButton("Создать");

    public Window() {
        super("Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 200);

        getContentPane().add(label);
        getContentPane().add(textField);
        getContentPane().add(button);

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
                        .addComponent(button))
                .addComponent(tableScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(button))
                .addComponent(tableScrollPane)
        );
    }

    private void addButtonListeners() {
        button.addActionListener(new AbstractAction() {
            private static final long serialVersionUID = -8821686667162924798L;

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 2; i++) {
                    xValues.add(" ");
                    yValues.add(" ");
                }
                tableModel.fireTableDataChanged();
            }
        });
    }

    public static void main(String[] args) {
        Window firstWindow = new Window();
    }
}
