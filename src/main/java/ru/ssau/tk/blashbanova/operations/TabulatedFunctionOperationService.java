package ru.ssau.tk.blashbanova.operations;

import ru.ssau.tk.blashbanova.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.blashbanova.functions.Point;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;
import ru.ssau.tk.blashbanova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        factory = new ArrayTabulatedFunctionFactory();
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point point : tabulatedFunction) {
            points[i] = point;
            i++;
        }
        return points;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException();
        }
        int size = a.getCount();
        Point[] pointsOfA = TabulatedFunctionOperationService.asPoints(a);
        Point[] pointsOfB = TabulatedFunctionOperationService.asPoints(b);
        double[] xValues = new double[size];
        double[] yValues = new double[size];

        for (int i = 0; i != size; i++) {
            if (pointsOfA[i].x != pointsOfB[i].x) {
                throw new InconsistentFunctionsException();
            }
            xValues[i] = pointsOfA[i].x;
            yValues[i] = operation.apply(pointsOfA[i].y, pointsOfB[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u + v);
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, new BiOperation() {
            @Override
            public double apply(double u, double v) {
                return u - v;
            }
        });
    }
}
