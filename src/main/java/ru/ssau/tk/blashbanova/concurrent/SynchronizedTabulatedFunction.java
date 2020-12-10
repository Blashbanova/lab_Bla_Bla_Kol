package ru.ssau.tk.blashbanova.concurrent;

import ru.ssau.tk.blashbanova.functions.Point;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;
import ru.ssau.tk.blashbanova.operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    TabulatedFunction tabulatedFunction;
    final Object mutex;

    public SynchronizedTabulatedFunction(TabulatedFunction tabulatedFunction, Object mutex) {
        this.tabulatedFunction = tabulatedFunction;
        this.mutex = Objects.requireNonNull(mutex);
    }

    @Override
    public int getCount() {
        synchronized (mutex) {
            return tabulatedFunction.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (mutex) {
            return tabulatedFunction.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (mutex) {
            return tabulatedFunction.getY(index);
        }
    }

    @Override
    public void setY(int index, double valueY) {
        synchronized (mutex) {
            tabulatedFunction.setY(index, valueY);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (mutex) {
            return tabulatedFunction.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (mutex) {
            return tabulatedFunction.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (mutex) {
            return tabulatedFunction.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (mutex) {
            return tabulatedFunction.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (mutex) {
            Point[] points = TabulatedFunctionOperationService.asPoints(tabulatedFunction);
            return new Iterator<Point>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    return i < points.length;
                }

                @Override
                public Point next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return points[i++];
                }
            };
        }
    }

    @Override
    public double apply(double x) {
        synchronized (mutex) {
            return tabulatedFunction.apply(x);
        }
    }

    public interface Operation<T> {
        T apply(SynchronizedTabulatedFunction function);
    }

    public <T> T doSynchronously(Operation<? extends T> o) {
        synchronized (mutex) {
            return o.apply(this);
        }
    }
}
