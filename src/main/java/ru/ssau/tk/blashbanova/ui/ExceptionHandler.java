package ru.ssau.tk.blashbanova.ui;

import javax.swing.*;

public class ExceptionHandler {
    private ExceptionHandler() {
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE, new ImageIcon("images/eror.jpg"));
    }

    public static void showCorgiMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE, new ImageIcon("images/error2.jpg"));
    }
}
