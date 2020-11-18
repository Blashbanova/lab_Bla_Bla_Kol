package ru.ssau.tk.blashbanova.functions.factory;

import ru.ssau.tk.blashbanova.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
