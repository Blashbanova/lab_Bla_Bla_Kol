package ru.ssau.tk.blashbanova.functions.tabulated;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.math.MathFunction;
import ru.ssau.tk.blashbanova.functions.math.SqrFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final MathFunction sqr = new SqrFunction();
    private final static double ACCURACY = 0.0001;

    private LinkedListTabulatedFunction getListFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 9, 5);
    }

    private LinkedListTabulatedFunction getFunction() {
        return new LinkedListTabulatedFunction(sqr, -2, 2, 5);
    }

    @Test
    public void testGetCount() {
        assertEquals(getListFunction().getCount(), 5, ACCURACY);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, -1, 1, 1);
        assertNotEquals(getFunction().getCount(), 0);
        assertNotEquals(testFunction.getCount(), 0);
        assertEquals(getFunction().getCount(), 5);
        assertEquals(testFunction.getCount(), 1);
    }

    @Test
    public void testGetX() {
        assertEquals(getListFunction().getX(3), 7, ACCURACY);
        assertNotEquals(getFunction().getX(0), Double.NaN);
        assertNotEquals(getFunction().getX(4), Double.NaN);
        assertEquals(getFunction().getX(1), -1, ACCURACY);
        assertEquals(getFunction().getX(4), 2, ACCURACY);
    }


    @Test
    public void testGetY() {
        assertEquals(getListFunction().getY(3), 49, ACCURACY);
        assertNotEquals(getFunction().getY(4), Double.NaN);
        assertNotEquals(getFunction().getY(0), Double.NaN);
        assertEquals(getFunction().getY(2), 0, ACCURACY);
        assertEquals(getFunction().getY(3), 1, ACCURACY);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction function = getFunction();
        LinkedListTabulatedFunction anotherFunction = getFunction();
        LinkedListTabulatedFunction someFunction = getListFunction();
        someFunction.setY(1, 14);
        assertEquals(someFunction.getY(1), 14, ACCURACY);
        function.setY(2, 111);
        anotherFunction.setY(3, Double.NaN);
        assertEquals(function.getY(2), 111, ACCURACY);
        assertEquals(anotherFunction.getY(3), Double.NaN);
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
    }

    @Test
    public void testLeftBound() {
        assertEquals(getListFunction().leftBound(), 1, ACCURACY);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, Double.NaN, 5, 1);
        assertEquals(testFunction.leftBound(), Double.NaN, ACCURACY);
        assertEquals(getFunction().leftBound(), -2, ACCURACY);
    }

    @Test
    public void testRightBound() {
        assertEquals(getListFunction().rightBound(), 9, ACCURACY);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, Double.NaN, 5, 1);
        assertEquals(testFunction.rightBound(), Double.NaN);
        assertEquals(getFunction().rightBound(), 2, ACCURACY);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(getListFunction().floorIndexOfX(2), 0, ACCURACY);
        assertEquals(getListFunction().floorIndexOfX(11), 5, ACCURACY);
        assertEquals(getListFunction().floorIndexOfX(-10), 0, ACCURACY);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 1, 5, 10);
        assertEquals(testFunction.floorIndexOfX(1.8), 1);
        assertEquals(testFunction.floorIndexOfX(-1), 0);
        assertEquals(testFunction.floorIndexOfX(6), 10);
        assertEquals(testFunction.floorIndexOfX(4.5), 7);
        assertEquals(getFunction().floorIndexOfX(2), 3);
        assertEquals(getFunction().floorIndexOfX(-2), 0);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getListFunction().extrapolateLeft(0), -3, ACCURACY);

    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(getListFunction().extrapolateRight(20), 257, ACCURACY);
    }

    @Test
    public void testInterpolate() {
        assertEquals(getListFunction().interpolate(2, getListFunction().floorIndexOfX(2)), 5, ACCURACY);
        assertEquals(getListFunction().interpolate(0, getListFunction().floorIndexOfX(0)), -3, ACCURACY);
        assertEquals(getListFunction().interpolate(20, getListFunction().floorIndexOfX(20)), 257, ACCURACY);
    }
}