package ru.ssau.tk.blashbanova.operations;

import ru.ssau.tk.blashbanova.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.Point;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;
import ru.ssau.tk.blashbanova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        int length = points.length;
        double[] xValues = new double[length];
        double[] yValues = new double[length];

        for (int i = 0; i < length - 1; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
            xValues[i] = points[i].x;
        }

        xValues[length - 1] = points[length - 1].x;
        yValues[length - 1] = yValues[length - 2];

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        if (function instanceof SynchronizedTabulatedFunction) {
            return ((SynchronizedTabulatedFunction) function).doSynchronously(this::derive);
        }
        Object mutex = new Object();
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(function, mutex);
        return synchronizedFunction.doSynchronously(this::derive);
    }
}
