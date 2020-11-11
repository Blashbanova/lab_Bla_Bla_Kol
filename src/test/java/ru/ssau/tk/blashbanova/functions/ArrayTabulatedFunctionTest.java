package ru.ssau.tk.blashbanova.functions;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.blashbanova.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.blashbanova.exceptions.InterpolationException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final double ACCURACY = 0.0005;
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction identityFunction = new IdentityFunction();
    private final double[] xValues = {-2, -1, 0, 1, 2};
    private final double[] yValues = {4, 1, 0, 1, 4};

    private ArrayTabulatedFunction getArrayFunction() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    private ArrayTabulatedFunction getFunction() {
        return new ArrayTabulatedFunction(sqr, -2, 2, 5);
    }

    private ArrayTabulatedFunction getLeftBoundNaNFunction() {
        return new ArrayTabulatedFunction(identityFunction, Double.NaN, 5, 3);
    }

    private ArrayTabulatedFunction getRightBoundNaNFunction() {
        return new ArrayTabulatedFunction(identityFunction, 1, Double.NaN, 3);
    }

    @Test
    public void testConstructorExceptions() {
        final double[] brokenValues = {1, -1, 0};
        final double[] singleElementArray = {1};
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(singleElementArray, yValues));
        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(brokenValues, yValues));
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(brokenValues, brokenValues));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqr, 5, -5, 2));
    }

    @Test
    public void testGetCount() {
        final ArrayTabulatedFunction function = getLeftBoundNaNFunction();
        final ArrayTabulatedFunction anotherFunction = getFunction();
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertNotEquals(function.getCount(), 0);
        assertNotEquals(anotherFunction.getCount(), 0);
        assertEquals(anotherFunction.getCount(), 5);
        assertEquals(arrayTabulatedFunction.getCount(), 5);
        assertEquals(function.getCount(), 3);
    }

    @Test
    public void testGetX() {
        final ArrayTabulatedFunction function = getFunction();
        assertNotEquals(function.getX(0), Double.NaN);
        assertNotEquals(function.getX(4), Double.NaN);
        assertEquals(function.getX(1), -1, ACCURACY);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getX(-3));
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getX(-5));
        assertEquals(function.getX(4), 2, ACCURACY);
    }

    @Test
    public void testGetXArray() {
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertNotEquals(arrayTabulatedFunction.getX(0), Double.NaN);
        assertNotEquals(arrayTabulatedFunction.getX(4), Double.NaN);
        assertEquals(arrayTabulatedFunction.getX(1), -1, ACCURACY);
        assertEquals(arrayTabulatedFunction.getX(4), 2, ACCURACY);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayTabulatedFunction.getX(100));
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayTabulatedFunction.getX(-5));
    }

    @Test
    public void testGetY() {
        final ArrayTabulatedFunction testFunction = getLeftBoundNaNFunction();
        final ArrayTabulatedFunction function = getFunction();
        assertEquals(testFunction.getY(1), Double.NaN);
        assertEquals(testFunction.getY(0), Double.NaN);
        assertEquals(testFunction.getY(2), Double.NaN, ACCURACY);
        assertNotEquals(function.getY(4), Double.NaN);
        assertNotEquals(function.getY(0), Double.NaN);
        assertEquals(function.getY(2), 0, ACCURACY);
        assertEquals(function.getY(3), 1, ACCURACY);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getY(-5));
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> testFunction.getY(-1));
    }

    @Test
    public void testGetYArray() {
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertNotEquals(arrayTabulatedFunction.getY(4), Double.NaN);
        assertNotEquals(arrayTabulatedFunction.getY(0), Double.NaN);
        assertEquals(arrayTabulatedFunction.getY(2), 0, ACCURACY);
        assertEquals(arrayTabulatedFunction.getY(3), 1, ACCURACY);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayTabulatedFunction.getY(50));
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayTabulatedFunction.getY(-5));
    }

    @Test
    public void testSetY() {
        ArrayTabulatedFunction function = getFunction();
        ArrayTabulatedFunction anotherFunction = getFunction();
        function.setY(2, 111);
        anotherFunction.setY(3, Double.NaN);
        assertEquals(function.getY(2), 111, ACCURACY);
        assertEquals(anotherFunction.getY(3), Double.NaN);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.setY(-5, 100));
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> anotherFunction.setY(100, 7));
    }

    @Test
    public void testSetYArray() {
        ArrayTabulatedFunction arrayFunction = getFunction();
        ArrayTabulatedFunction anotherArrayFunction = getFunction();
        arrayFunction.setY(2, 111);
        anotherArrayFunction.setY(3, Double.NaN);
        assertEquals(arrayFunction.getY(2), 111, ACCURACY);
        assertEquals(anotherArrayFunction.getY(3), Double.NaN);
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayFunction.setY(-5, 10));
    }

    @Test
    public void testIndexOfX() {
        final ArrayTabulatedFunction function = getFunction();
        assertEquals(function.indexOfX(2), 4);
        assertEquals(function.indexOfX(0), 2);
        assertNotEquals(function.indexOfX(1000), Double.NaN);
        assertNotEquals(function.indexOfX(-1000), Double.NaN);
        assertEquals(function.indexOfX(Double.NaN), -1);
        assertEquals(function.indexOfX(5), -1);
        assertEquals(function.indexOfX(-5), -1);
    }

    @Test
    public void testIndexOfXArray() {
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertEquals(arrayTabulatedFunction.indexOfX(2), 4);
        assertEquals(arrayTabulatedFunction.indexOfX(0), 2);
        assertNotEquals(arrayTabulatedFunction.indexOfX(1000), Double.NaN);
        assertNotEquals(arrayTabulatedFunction.indexOfX(-1000), Double.NaN);
        assertEquals(arrayTabulatedFunction.indexOfX(Double.NaN), -1);
        assertEquals(arrayTabulatedFunction.indexOfX(5), -1);
        assertEquals(arrayTabulatedFunction.indexOfX(-5), -1);
    }

    @Test
    public void testIndexOfY() {
        final ArrayTabulatedFunction function = getFunction();
        assertEquals(function.indexOfY(1), 1);
        assertEquals(function.indexOfY(4), 0);
        assertNotEquals(function.indexOfY(1000), Double.NaN);
        assertNotEquals(function.indexOfY(-1000), Double.NaN);
        assertEquals(function.indexOfY(Double.NaN), -1);
        assertEquals(function.indexOfY(5), -1);
        assertEquals(function.indexOfY(-5), -1);
    }

    @Test
    public void testIndexOfYArray() {
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertEquals(arrayTabulatedFunction.indexOfY(1), 1);
        assertEquals(arrayTabulatedFunction.indexOfY(4), 0);
        assertNotEquals(arrayTabulatedFunction.indexOfY(1000), Double.NaN);
        assertNotEquals(arrayTabulatedFunction.indexOfY(-1000), Double.NaN);
        assertEquals(arrayTabulatedFunction.indexOfY(Double.NaN), -1);
        assertEquals(arrayTabulatedFunction.indexOfY(5), -1);
        assertEquals(arrayTabulatedFunction.indexOfY(-5), -1);
    }

    @Test
    public void testLeftBound() {
        final ArrayTabulatedFunction testFunction = getLeftBoundNaNFunction();
        final ArrayTabulatedFunction anotherFunction = getRightBoundNaNFunction();
        final ArrayTabulatedFunction function = getFunction();
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertEquals(arrayTabulatedFunction.leftBound(), -2, ACCURACY);
        assertEquals(testFunction.leftBound(), Double.NaN, ACCURACY);
        assertEquals(anotherFunction.leftBound(), 1, ACCURACY);
        assertEquals(function.leftBound(), -2, ACCURACY);
    }

    @Test
    public void testRightBound() {
        final ArrayTabulatedFunction testFunction = getLeftBoundNaNFunction();
        final ArrayTabulatedFunction anotherFunction = getRightBoundNaNFunction();
        final ArrayTabulatedFunction function = getFunction();
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertEquals(arrayTabulatedFunction.rightBound(), 2, ACCURACY);
        assertEquals(testFunction.rightBound(), Double.NaN);
        assertEquals(anotherFunction.rightBound(), Double.NaN);
        assertEquals(function.rightBound(), 2, ACCURACY);
    }

    @Test
    public void testFloorIndexOfX() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 1, 5, 10);
        final ArrayTabulatedFunction function = getFunction();
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertEquals(testFunction.floorIndexOfX(1.8), 1);
        Assert.assertThrows(IllegalArgumentException.class, () -> testFunction.floorIndexOfX(-1));
        assertEquals(testFunction.floorIndexOfX(6), 10);
        assertEquals(testFunction.floorIndexOfX(4.5), 7);
        assertEquals(function.floorIndexOfX(4), 5);
        Assert.assertThrows(IllegalArgumentException.class, () -> function.floorIndexOfX(-3));
        assertEquals(arrayTabulatedFunction.floorIndexOfX(2), 3);
        assertEquals(arrayTabulatedFunction.floorIndexOfX(-2), 0);
    }

    @Test
    public void testFloorIndexOfXNaN() {
        final ArrayTabulatedFunction anotherFunction = getLeftBoundNaNFunction();
        final ArrayTabulatedFunction someFunction = getRightBoundNaNFunction();
        assertEquals(anotherFunction.floorIndexOfX(3), 3);
        Assert.assertThrows(IllegalArgumentException.class, () -> someFunction.floorIndexOfX(-4));
        Assert.assertThrows(IllegalArgumentException.class, () -> someFunction.floorIndexOfX(-3));
    }

    @Test
    public void testExtrapolateLeft() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(identityFunction, -5, 5, 20);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, -5, 1, 10);
        final ArrayTabulatedFunction function = getFunction();
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertEquals(function.extrapolateLeft(-3), 7, ACCURACY);
        assertEquals(arrayTabulatedFunction.extrapolateLeft(-3), 7, ACCURACY);
        assertEquals(testFunction.extrapolateLeft(-10), -10, ACCURACY);
        assertEquals(anotherFunction.extrapolateLeft(-10), 71.6667, ACCURACY);
    }

    @Test
    public void testExtrapolateRight() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(identityFunction, -5, 5, 20);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, -7, 3, 10);
        final ArrayTabulatedFunction function = getFunction();
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertEquals(function.extrapolateRight(4), 10, ACCURACY);
        assertEquals(arrayTabulatedFunction.extrapolateRight(4), 10, ACCURACY);
        assertEquals(testFunction.extrapolateRight(10), 10, ACCURACY);
        assertEquals(anotherFunction.extrapolateRight(7), 28.5556, ACCURACY);
    }

    @Test
    public void testInterpolate() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, -5, 1, 10);
        final ArrayTabulatedFunction function = getFunction();
        final ArrayTabulatedFunction arrayTabulatedFunction = getArrayFunction();
        assertEquals(function.interpolate(-0.5, function.floorIndexOfX(-0.5)), 0.5, ACCURACY);
        assertEquals(arrayTabulatedFunction.interpolate(-0.5, arrayTabulatedFunction.floorIndexOfX(-0.5)), 0.5, ACCURACY);
        assertEquals(testFunction.interpolate(-2.5, testFunction.floorIndexOfX(-2.5)), 6.3335, ACCURACY);
    }

    @Test
    public void testInterpolateException() {
        final ArrayTabulatedFunction function = getFunction();
        assertThrows(InterpolationException.class, () -> function.interpolate(2, function.floorIndexOfX(-2)));
        assertThrows(InterpolationException.class, () -> function.interpolate(0, function.floorIndexOfX(2)));
    }

    @Test
    public void testIteratorWhile() {
        final ArrayTabulatedFunction function = getArrayFunction();
        final Iterator<Point> iterator = function.iterator();
        final ArrayTabulatedFunction scaryFunction = getRightBoundNaNFunction();
        final Iterator<Point> secondIterator = scaryFunction.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, xValues[i], ACCURACY);
            assertEquals(point.y, yValues[i++], ACCURACY);
        }
        assertEquals(i, function.getCount());
        assertThrows(NoSuchElementException.class, iterator::next);
        i = 0;
        while (secondIterator.hasNext()) {
            Point point = secondIterator.next();
            assertEquals(point.x, scaryFunction.getX(i), ACCURACY);
            assertEquals(point.y, scaryFunction.getY(i++), ACCURACY);
        }
        assertEquals(i, scaryFunction.getCount());
        assertThrows(NoSuchElementException.class, secondIterator::next);
    }

    @Test
    public void testIteratorForEach() {
        final ArrayTabulatedFunction function = getArrayFunction();
        final Iterator<Point> iterator = function.iterator();
        final ArrayTabulatedFunction scaryFunction = getRightBoundNaNFunction();
        final Iterator<Point> secondIterator = scaryFunction.iterator();
        int i = 0;
        for (Point point : function) {
            Point iteratorPoint = iterator.next();
            assertEquals(iteratorPoint.x, point.x, ACCURACY);
            assertEquals(iteratorPoint.y, point.y, ACCURACY);
            i++;
        }
        assertEquals(i, function.getCount());
        assertThrows(NoSuchElementException.class, iterator::next);
        i = 0;
        for (Point point : scaryFunction) {
            Point iteratorPoint = secondIterator.next();
            assertEquals(iteratorPoint.x, point.x, ACCURACY);
            assertEquals(iteratorPoint.y, point.y, ACCURACY);
            i++;
        }
        assertEquals(i, scaryFunction.getCount());
        assertThrows(NoSuchElementException.class, secondIterator::next);
    }
}