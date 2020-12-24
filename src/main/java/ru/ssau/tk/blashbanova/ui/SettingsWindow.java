package ru.ssau.tk.blashbanova.ui;


import ru.ssau.tk.blashbanova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class SettingsWindow extends JDialog {
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    private static boolean checked = true;

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

    public static TabulatedFunctionFactory getFactory() {
        return factory;
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

    class FirstPanel extends JPanel {
        ArrayList<ColorOption> colorOptions = new ArrayList<>();

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
                colorOption.addItemListener(e -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        getContentPane().setBackground(colorOption.getColor());
                    }
                });
            }
        }
    }

    static class SecondPanel extends JPanel {
        ButtonGroup group = new ButtonGroup();

        public SecondPanel() {
            JRadioButton firstButton = new JRadioButton("фабрика массивов", checked);
            group.add(firstButton);
            JRadioButton secondButton = new JRadioButton("фабрика списков", !checked);
            group.add(secondButton);
            add(firstButton);
            add(secondButton);
            firstButton.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    factory = new ArrayTabulatedFunctionFactory();
                    checked = true;
                }
            });
            secondButton.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    factory = new LinkedListTabulatedFunctionFactory();
                    checked = false;
                }
            });
        }
    }

    public static void main(String[] args) {
        new SettingsWindow();
    }
}
