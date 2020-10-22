package ru.ssau.tk.blashbanova.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;
    private final int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];
        xValues[0] = xFrom;
        yValues[0] = source.apply(xFrom);
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 1; i != count; i++) {
            xValues[i] = xValues[i - 1] + step;
            yValues[i] = source.apply(xValues[i]);
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i != count; i++) {
            if (x == xValues[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i != count; i++) {
            if (y == yValues[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < xValues[0]) {
            return 0;
        }
        for (int i = 0; i != count - 1; i++) {
            if (x <= xValues[i + 1]) {
                return i;
            }
        }
        return count;
    }

    @Override
    public double extrapolateLeft(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    public double extrapolateRight(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return yValues[0];
        }
        if (floorIndex == 0) {
            return extrapolateLeft(x);
        }
        if (floorIndex == count) {
            return extrapolateRight(x);
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }
}
