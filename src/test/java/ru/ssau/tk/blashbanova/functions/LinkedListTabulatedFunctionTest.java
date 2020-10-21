package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.IdentityFunction;
import ru.ssau.tk.blashbanova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.MathFunction;
import ru.ssau.tk.blashbanova.functions.SqrFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction identityFunction = new IdentityFunction();
    private final static double ACCURACY = 0.0001;
    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{10, 30, 50, 70, 90};

    private LinkedListTabulatedFunction getAnotherListFunction() {
        return new LinkedListTabulatedFunction(sqr, 10, 90, 5);
    }

    private LinkedListTabulatedFunction getArrayListFunction() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction getListFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 9, 5);
    }

    private LinkedListTabulatedFunction getFunction() {
        return new LinkedListTabulatedFunction(sqr, -2, 2, 5);
    }

    @Test
    public void testGetCount() {
        assertEquals(getListFunction().getCount(), 5, ACCURACY);
        final LinkedListTabulatedFunction testFunction = new LinkedListTabulatedFunction(sqr, -1, 1, 1);
        assertNotEquals(getFunction().getCount(), 0);
        assertNotEquals(testFunction.getCount(), 0);
        assertEquals(getFunction().getCount(), 5);
        assertEquals(testFunction.getCount(), 1);
        assertEquals(getArrayListFunction().getCount(), 5);
        assertNotEquals(getArrayListFunction().getCount(), 0);
    }

    @Test
    public void testGetX() {
        assertEquals(getListFunction().getX(3), 7, ACCURACY);
        assertNotEquals(getFunction().getX(0), Double.NaN);
        assertNotEquals(getFunction().getX(4), Double.NaN);
        assertEquals(getFunction().getX(1), -1, ACCURACY);
        assertEquals(getFunction().getX(4), 2, ACCURACY);
        assertEquals(getArrayListFunction().getX(0), 1, ACCURACY);
        assertNotEquals(getArrayListFunction().getX(2), Double.NaN);
    }


    @Test
    public void testGetY() {
        assertEquals(getListFunction().getY(3), 49, ACCURACY);
        assertNotEquals(getFunction().getY(4), Double.NaN);
        assertNotEquals(getFunction().getY(0), Double.NaN);
        assertEquals(getFunction().getY(2), 0, ACCURACY);
        assertEquals(getFunction().getY(3), 1, ACCURACY);
        assertEquals(getArrayListFunction().getY(3), 70, ACCURACY);
        assertNotEquals(getArrayListFunction().getY(1), Double.NaN);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction function = getFunction();
        LinkedListTabulatedFunction anotherFunction = getFunction();
        LinkedListTabulatedFunction someFunction = getListFunction();
        LinkedListTabulatedFunction otherFunction = getArrayListFunction();
        someFunction.setY(1, 14);
        function.setY(2, 111);
        anotherFunction.setY(3, Double.NaN);
        otherFunction.setY(1, 125);
        assertEquals(someFunction.getY(1), 14, ACCURACY);
        assertEquals(function.getY(2), 111, ACCURACY);
        assertEquals(anotherFunction.getY(3), Double.NaN);
        assertEquals(otherFunction.getY(1), 125, ACCURACY);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getListFunction().indexOfX(7), 3, ACCURACY);
        assertEquals(getFunction().indexOfX(0), 2);
        assertNotEquals(getFunction().indexOfX(1000), Double.NaN);
        assertNotEquals(getFunction().indexOfX(-1000), Double.NaN);
        assertEquals(getFunction().indexOfX(Double.NaN), -1);
        assertEquals(getFunction().indexOfX(5), -1);
        assertEquals(getFunction().indexOfX(-5), -1);
        assertEquals(getArrayListFunction().indexOfX(7), 3);
        assertNotEquals(getArrayListFunction().indexOfX(1100), Double.NaN);
        assertEquals(getArrayListFunction().indexOfX(Double.NaN), -1);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getListFunction().indexOfY(49), 3, ACCURACY);
        assertEquals(getFunction().indexOfY(1), 1);
        assertNotEquals(getFunction().indexOfY(1000), Double.NaN);
        assertNotEquals(getFunction().indexOfY(-1000), Double.NaN);
        assertEquals(getFunction().indexOfY(Double.NaN), -1);
        assertEquals(getFunction().indexOfY(5), -1);
        assertEquals(getFunction().indexOfY(-5), -1);
        assertEquals(getArrayListFunction().indexOfX(3), 1);
        assertNotEquals(getArrayListFunction().indexOfX(3), Double.NaN);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getListFunction().leftBound(), 1, ACCURACY);
        assertEquals(getFunction().leftBound(), -2, ACCURACY);
        assertNotEquals(getFunction().leftBound(), Double.NaN);
        assertNotEquals(getFunction().leftBound(), Double.NEGATIVE_INFINITY);
        assertEquals(getArrayListFunction().leftBound(), 1, ACCURACY);
        assertNotEquals(getArrayListFunction().leftBound(), Double.NaN);
        assertNotEquals(getArrayListFunction().leftBound(), Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testRightBound() {
        assertEquals(getListFunction().rightBound(), 9, ACCURACY);
        assertEquals(getFunction().rightBound(), 2, ACCURACY);
        assertNotEquals(getFunction().rightBound(), Double.NaN);
        assertNotEquals(getFunction().rightBound(), Double.POSITIVE_INFINITY);
        assertEquals(getArrayListFunction().rightBound(), 9, ACCURACY);
        assertNotEquals(getArrayListFunction().rightBound(), Double.NaN);
        assertNotEquals(getArrayListFunction().rightBound(), Double.POSITIVE_INFINITY);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction listFunction = getListFunction();
        assertEquals(listFunction.floorIndexOfX(2), 0, ACCURACY);
        assertEquals(listFunction.floorIndexOfX(11), 5, ACCURACY);
        assertEquals(listFunction.floorIndexOfX(-10), 0, ACCURACY);
        final LinkedListTabulatedFunction testFunction = new LinkedListTabulatedFunction(sqr, 1, 5, 10);
        assertEquals(testFunction.floorIndexOfX(1.8), 1);
        assertEquals(testFunction.floorIndexOfX(-1), 0);
        assertEquals(testFunction.floorIndexOfX(6), 10);
        assertEquals(testFunction.floorIndexOfX(4.5), 7);
        assertEquals(getFunction().floorIndexOfX(2), 3);
        assertEquals(getFunction().floorIndexOfX(-2), 0);
        assertEquals(getArrayListFunction().floorIndexOfX(3), 0);
        assertEquals(getArrayListFunction().floorIndexOfX(6), 2);
        assertEquals(getArrayListFunction().floorIndexOfX(10), 5);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getListFunction().extrapolateLeft(0), -3, ACCURACY);
        final LinkedListTabulatedFunction testFunction = new LinkedListTabulatedFunction(identityFunction, -5, 5, 20);
        final LinkedListTabulatedFunction anotherFunction = new LinkedListTabulatedFunction(sqr, -5, 1, 10);
        assertEquals(getFunction().extrapolateLeft(-3), 7, ACCURACY);
        assertEquals(testFunction.extrapolateLeft(-10), -10, ACCURACY);
        assertEquals(anotherFunction.extrapolateLeft(-10), 71.6667, ACCURACY);
        assertEquals(getArrayListFunction().extrapolateLeft(-2), -20, ACCURACY);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(getListFunction().extrapolateRight(20), 257, ACCURACY);
        final LinkedListTabulatedFunction testFunction = new LinkedListTabulatedFunction(identityFunction, -5, 5, 20);
        final LinkedListTabulatedFunction anotherFunction = new LinkedListTabulatedFunction(sqr, -7, 3, 10);
        assertEquals(getFunction().extrapolateRight(4), 10, ACCURACY);
        assertEquals(testFunction.extrapolateRight(10), 10, ACCURACY);
        assertEquals(anotherFunction.extrapolateRight(7), 28.5556, ACCURACY);
        assertEquals(getArrayListFunction().extrapolateRight(12), 120, ACCURACY);

    }

    @Test
    public void testInterpolate() {
        assertEquals(getListFunction().interpolate(2, getListFunction().floorIndexOfX(2)), 5, ACCURACY);
        assertEquals(getListFunction().interpolate(0, getListFunction().floorIndexOfX(0)), -3, ACCURACY);
        assertEquals(getListFunction().interpolate(20, getListFunction().floorIndexOfX(20)), 257, ACCURACY);
        assertEquals(getArrayListFunction().interpolate(6, getArrayListFunction().floorIndexOfX(6)), 60, ACCURACY);
    }

    @Test
    public void testComplexFunction() {
        assertEquals(getArrayListFunction().andThen(getAnotherListFunction()).andThen(sqr).apply(5), 6250000, ACCURACY);
        assertEquals(getArrayListFunction().andThen(getAnotherListFunction()).andThen(sqr).apply(3), 810000, ACCURACY);
        assertNotEquals(sqr.andThen(getAnotherListFunction()).andThen(getArrayListFunction()).apply(50), Double.NaN, ACCURACY);
    }
}

