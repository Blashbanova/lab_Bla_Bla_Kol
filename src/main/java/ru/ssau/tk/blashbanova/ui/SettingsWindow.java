package ru.ssau.tk.blashbanova.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class SettingsWindow extends JDialog {
    JTabbedPane tabbedPane = new JTabbedPane();

    public SettingsWindow() {
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 400);

        tabbedPane.setPreferredSize(new Dimension(300, 300));

        JPanel design = new FirstPanel();
        JPanel factorySelection = new SecondPanel();
        tabbedPane.addTab("Дизайн", design);
        tabbedPane.addTab("Выбор фабрики", factorySelection);
        tabbedPane.setBackground(Color.orange);
        tabbedPane.setForeground(Color.white);

        getContentPane().add(tabbedPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static class ColorOption extends JRadioButton {
        private final Color color;
        public ColorOption(String colorName, Color color) {
            super((colorName));
            this.color = color;
        }
        public Color getColor() {
            return color;
        }
    }

    class FirstPanel extends JPanel{
        ArrayList<ColorOption> colorOptions = new ArrayList<ColorOption>();

        public FirstPanel() {
            colorOptions.add(new ColorOption("Черный", Color.BLACK));
            colorOptions.add(new ColorOption("Белый", Color.WHITE));
            colorOptions.add(new ColorOption("Серый", Color.LIGHT_GRAY));
            colorOptions.add(new ColorOption("Оранжевый", Color.ORANGE));
            colorOptions.add(new ColorOption("Желтый", Color.YELLOW));
            ButtonGroup buttonGroup = new ButtonGroup();
            for (ColorOption colorOption : colorOptions) {
                buttonGroup.add(colorOption);
                setLayout(new FlowLayout());
                add(colorOption);
                colorOption.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            getContentPane().setBackground(colorOption.getColor());
                        }
                    }
                });
            }
        }
    }

    static class SecondPanel extends JPanel {
        ButtonGroup group = new ButtonGroup();

        public SecondPanel() {
            JRadioButton firstButton = new JRadioButton("фабрика массивов", true);
            group.add(firstButton);
            JRadioButton secondButton = new JRadioButton("фабрика списков", false);
            group.add(secondButton);
            add(firstButton);
            add(secondButton);
        }

    }

    public static void main(String[] args) {
        new SettingsWindow();
    }
}
