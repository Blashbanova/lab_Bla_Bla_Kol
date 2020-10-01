package ru.ssau.tk.blashbanova.functions.tabulated;

import ru.ssau.tk.blashbanova.functions.math.MathFunction;

import java.util.Arrays;

import static java.lang.StrictMath.abs;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private double[] xValues;
    private double[] yValues;
    private int count;

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
        yValues[count - 1] = source.apply(xTo);
        for (int i = 0; i != count - 1; i++) {
            xValues[i + 1] = xValues[i] + (xTo - xFrom) / (count - 1);
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
            if (abs(xValues[i] - x) <= 0.0005) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i != count; i++) {
            if (abs(yValues[i] - y) <= 0.0005) {
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
        if (x - leftBound() < 0.0005) {
            return 0;
        }

        if (x - rightBound() > 0.0005) {
            return count;
        }
        for (int i = 1; i != count; i++) {
            if (x - xValues[i] <= 0.0005) {
                return i - 1;
            }
        }
        return -1;
    }

    @Override
    public double extrapolateLeft(double x) {
        if (count == 1) {
            return yValues[1];
        }
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    public double extrapolateRight(double x) {
        if (count == 1) {
            return yValues[1];
        }
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return yValues[1];
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
