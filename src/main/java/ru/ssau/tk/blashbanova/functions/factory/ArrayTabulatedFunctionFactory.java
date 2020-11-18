package ru.ssau.tk.blashbanova.functions.factory;

import ru.ssau.tk.blashbanova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}
