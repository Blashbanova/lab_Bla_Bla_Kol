package ru.ssau.tk.blashbanova.ui;

import java.util.List;

public class TableNotEditable extends TableXY {
    public TableNotEditable(List<String> xValues, List<String> yValues) {
        super(xValues, yValues);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
