package ru.ssau.tk.blashbanova.functions.factory;

import ru.ssau.tk.blashbanova.functions.MathFunction;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
    TabulatedFunction createOther(MathFunction source, double xFrom, double xTo, int count);
}
