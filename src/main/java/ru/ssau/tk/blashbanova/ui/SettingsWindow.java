package ru.ssau.tk.blashbanova.ui;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JDialog {
    JTabbedPane tabbedPane = new JTabbedPane();

    public SettingsWindow() {
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 400);

        tabbedPane.setPreferredSize(new Dimension(200, 300));
    }

    private static JPanel createPanel(String name) {
        JPanel panel = new JPanel();
        panel.add(new Label(name));
        return panel;
    }
}
