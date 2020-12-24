package ru.ssau.tk.blashbanova.ui;

import ru.ssau.tk.blashbanova.functions.*;
import ru.ssau.tk.blashbanova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SecondWindow extends JDialog {
    private final JLabel label = new JLabel("Введите количество точек разбиения:");
    private final JTextField textField = new JTextField("");
    private final JLabel secondLabel = new JLabel("Интервал с");
    private final JTextField secondTextField = new JTextField("");
    private final JLabel thirdLabel = new JLabel("по");
    private final JTextField thirdTextField = new JTextField("");
    private final JComboBox<String> comboBox = new JComboBox<>(new String[]{
            "Гиперболический тангенс", "Единичная функция", "Квадратичная функция", "Константная функция", "Модуль функция", "Нулевая функция", "Тождественная функция"
    });
    private final JButton firstButton = new JButton("Создать");
    private TabulatedFunction function;
    private final TabulatedFunctionFactory factory;

    public SecondWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 200);

        firstButton.setFocusPainted(false);
        getContentPane().add(label);
        getContentPane().add(textField);
        getContentPane().add(secondLabel);
        getContentPane().add(secondTextField);
        getContentPane().add(thirdLabel);
        getContentPane().add(thirdTextField);
        getContentPane().add(comboBox);
        getContentPane().add(firstButton);

        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public TabulatedFunction getFunction() {
        return function;
    }

    private void createFunction() {
        Map<String, MathFunction> mapFunction = new HashMap<>();
        mapFunction.put("Гиперболический тангенс", new HyperTangentFunction());
        mapFunction.put("Единичная функция", new UnitFunction());
        mapFunction.put("Квадратичная функция", new SqrFunction());
        mapFunction.put("Константная функция", new ConstantFunction(3));
        mapFunction.put("Модуль функция", new ModulusFunction());
        mapFunction.put("Нулевая функция", new ZeroFunction());
        mapFunction.put("Тождественная функция", new IdentityFunction());

        String func = (String) comboBox.getSelectedItem();
        MathFunction selectedFunction = mapFunction.get(func);
        double from = Double.parseDouble(secondTextField.getText());
        double to = Double.parseDouble(thirdTextField.getText());
        int count = Integer.parseInt(textField.getText());
        function = factory.createOther(selectedFunction, from, to, count);
        System.out.println(function);
    }

    private void addButtonListeners() {
        firstButton.addActionListener(e -> {
            try {
                int size = Integer.parseInt(textField.getText());
                if (size <= 0) {
                    ExceptionHandler.showCorgiMessage("Введите положительное число.");
                }
                createFunction();
                dispose();
            } catch (NumberFormatException exp) {
                ExceptionHandler.showMessage("Введите целое число.");
            } catch (IllegalArgumentException exp) {
                ExceptionHandler.showMessage(exp.getMessage());
            }
        });
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
        new SecondWindow(new ArrayTabulatedFunctionFactory());
    }
}
