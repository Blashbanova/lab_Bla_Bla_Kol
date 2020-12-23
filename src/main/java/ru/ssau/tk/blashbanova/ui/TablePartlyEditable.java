package ru.ssau.tk.blashbanova.ui;

import java.util.List;

public class TablePartlyEditable extends TableXY {
    public TablePartlyEditable(List<String> xValues, List<String> yValues) {
        super(xValues, yValues);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN_NUMBER:
                return false;
            case Y_COLUMN_NUMBER:
                return true;
        }
        return false;
    }
}
