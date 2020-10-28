package ru.ssau.tk.blashbanova.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction identityFunction = new IdentityFunction();
    private final double ACCURACY = 0.0001;
    private final double[] xValues = {1, 3, 5, 7, 9};
    private final double[] yValues = {10, 30, 50, 70, 90};
    private final double[] xValues1 = {1};
    private final double[] yValues1 = {10};

    private LinkedListTabulatedFunction getArrayListFunction() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction getListFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 9, 5);
    }

    private LinkedListTabulatedFunction getFunction() {
        return new LinkedListTabulatedFunction(sqr, -2, 2, 5);
    }

    private LinkedListTabulatedFunction getOneElementFunction() {
        return new LinkedListTabulatedFunction(sqr, 2, 4, 5);
    }

    @Test
    public void testArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(xValues1, yValues1));
        Assert.assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqr, 10, -1, 5));
    }

    @Test
    public void testGetCount() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction testFunction = getOneElementFunction();
        final LinkedListTabulatedFunction function = getFunction();
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        assertEquals(listFunction.getCount(), 5);
        assertNotEquals(function.getCount(), 0);
        assertNotEquals(testFunction.getCount(), 0);
        assertEquals(function.getCount(), 5);
        assertEquals(testFunction.getCount(), 5);
        assertEquals(arrayListFunction.getCount(), 5);
        assertNotEquals(arrayListFunction.getCount(), 0);
    }

    @Test
    public void testGetX() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        final LinkedListTabulatedFunction function = getFunction();
        assertEquals(listFunction.getX(3), 7, ACCURACY);
        assertNotEquals(function.getX(0), Double.NaN);
        assertNotEquals(function.getX(4), Double.NaN);
        assertEquals(function.getX(1), -1, ACCURACY);
        assertEquals(function.getX(4), 2, ACCURACY);
        assertEquals(arrayListFunction.getX(0), 1, ACCURACY);
        assertNotEquals(arrayListFunction.getX(2), Double.NaN);
        Assert.assertThrows(IllegalArgumentException.class, () -> listFunction.getX(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> listFunction.getX(6));
    }

    @Test
    public void testGetY() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        final LinkedListTabulatedFunction function = getFunction();
        assertEquals(listFunction.getY(3), 49, ACCURACY);
        assertNotEquals(function.getY(4), Double.NaN);
        assertNotEquals(function.getY(0), Double.NaN);
        assertEquals(function.getY(2), 0, ACCURACY);
        assertEquals(function.getY(3), 1, ACCURACY);
        assertEquals(arrayListFunction.getY(3), 70, ACCURACY);
        assertNotEquals(arrayListFunction.getY(1), Double.NaN);
        Assert.assertThrows(IllegalArgumentException.class, () -> listFunction.getY(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> listFunction.getY(6));
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
        Assert.assertThrows(IllegalArgumentException.class, () -> someFunction.setY(-6, 14));
        Assert.assertThrows(IllegalArgumentException.class, () -> otherFunction.setY(100, 125));
    }

    @Test
    public void testIndexOfX() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction function = getFunction();
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        assertEquals(listFunction.indexOfX(7), 3, ACCURACY);
        assertEquals(function.indexOfX(0), 2);
        assertNotEquals(function.indexOfX(1000), Double.NaN);
        assertNotEquals(function.indexOfX(-1000), Double.NaN);
        assertEquals(function.indexOfX(Double.NaN), -1);
        assertEquals(function.indexOfX(5), -1);
        assertEquals(function.indexOfX(-5), -1);
        assertEquals(arrayListFunction.indexOfX(7), 3);
        assertNotEquals(arrayListFunction.indexOfX(1100), Double.NaN);
        assertEquals(arrayListFunction.indexOfX(Double.NaN), -1);
    }

    @Test
    public void testIndexOfY() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        final LinkedListTabulatedFunction function = getFunction();
        assertEquals(listFunction.indexOfY(49), 3, ACCURACY);
        assertEquals(function.indexOfY(1), 1);
        assertNotEquals(function.indexOfY(1000), Double.NaN);
        assertNotEquals(function.indexOfY(-1000), Double.NaN);
        assertEquals(function.indexOfY(Double.NaN), -1);
        assertEquals(function.indexOfY(5), -1);
        assertEquals(function.indexOfY(-5), -1);
        assertEquals(arrayListFunction.indexOfX(3), 1);
        assertNotEquals(arrayListFunction.indexOfX(3), Double.NaN);
    }

    @Test
    public void testLeftBound() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        final LinkedListTabulatedFunction function = getFunction();
        assertEquals(listFunction.leftBound(), 1, ACCURACY);
        assertEquals(function.leftBound(), -2, ACCURACY);
        assertNotEquals(function.leftBound(), Double.NaN);
        assertNotEquals(function.leftBound(), Double.NEGATIVE_INFINITY);
        assertEquals(arrayListFunction.leftBound(), 1, ACCURACY);
        assertNotEquals(arrayListFunction.leftBound(), Double.NaN);
        assertNotEquals(arrayListFunction.leftBound(), Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testRightBound() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        final LinkedListTabulatedFunction function = getFunction();
        assertEquals(listFunction.rightBound(), 9, ACCURACY);
        assertEquals(function.rightBound(), 2, ACCURACY);
        assertNotEquals(function.rightBound(), Double.NaN);
        assertNotEquals(function.rightBound(), Double.POSITIVE_INFINITY);
        assertEquals(arrayListFunction.rightBound(), 9, ACCURACY);
        assertNotEquals(arrayListFunction.rightBound(), Double.NaN);
        assertNotEquals(arrayListFunction.rightBound(), Double.POSITIVE_INFINITY);
    }

    @Test
    public void testFloorIndexOfX() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction testFunction = new LinkedListTabulatedFunction(sqr, 1, 5, 10);
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        final LinkedListTabulatedFunction function = getFunction();
        assertEquals(listFunction.floorIndexOfX(2), 0, ACCURACY);
        assertEquals(listFunction.floorIndexOfX(11), 5, ACCURACY);
        assertEquals(testFunction.floorIndexOfX(1.8), 1);
        Assert.assertThrows(IllegalArgumentException.class, () -> testFunction.floorIndexOfX(-1));
        assertEquals(testFunction.floorIndexOfX(6), 10);
        assertEquals(testFunction.floorIndexOfX(4.5), 7);
        assertEquals(function.floorIndexOfX(2), 3);
        assertEquals(function.floorIndexOfX(-2), 0);
        assertEquals(arrayListFunction.floorIndexOfX(3), 0);
        assertEquals(arrayListFunction.floorIndexOfX(6), 2);
        assertEquals(arrayListFunction.floorIndexOfX(10), 5);
        Assert.assertThrows(IllegalArgumentException.class, () -> listFunction.floorIndexOfX(-15));
        Assert.assertThrows(IllegalArgumentException.class, () -> listFunction.floorIndexOfX(0));
    }

    @Test
    public void testExtrapolateLeft() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        final LinkedListTabulatedFunction testFunction = new LinkedListTabulatedFunction(identityFunction, -5, 5, 20);
        final LinkedListTabulatedFunction anotherFunction = new LinkedListTabulatedFunction(sqr, -5, 1, 10);
        final LinkedListTabulatedFunction function = getFunction();
        final LinkedListTabulatedFunction unit = getOneElementFunction();
        assertEquals(unit.extrapolateLeft(-25), -117.5, ACCURACY);
        assertEquals(function.extrapolateLeft(-3), 7, ACCURACY);
        assertEquals(testFunction.extrapolateLeft(-10), -10, ACCURACY);
        assertEquals(anotherFunction.extrapolateLeft(-10), 71.6667, ACCURACY);
        assertEquals(listFunction.extrapolateLeft(0), -3, ACCURACY);
        assertEquals(arrayListFunction.extrapolateLeft(-2), -20, ACCURACY);
    }

    @Test
    public void testExtrapolateRight() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        final LinkedListTabulatedFunction testFunction = new LinkedListTabulatedFunction(identityFunction, -5, 5, 20);
        final LinkedListTabulatedFunction anotherFunction = new LinkedListTabulatedFunction(sqr, -7, 3, 10);
        final LinkedListTabulatedFunction function = getFunction();
        final LinkedListTabulatedFunction unit = getOneElementFunction();
        assertEquals(unit.extrapolateRight(25), 173.5, ACCURACY);
        assertEquals(function.extrapolateRight(4), 10, ACCURACY);
        assertEquals(testFunction.extrapolateRight(10), 10, ACCURACY);
        assertEquals(anotherFunction.extrapolateRight(7), 28.5556, ACCURACY);
        assertEquals(listFunction.extrapolateRight(20), 257, ACCURACY);
        assertEquals(arrayListFunction.extrapolateRight(12), 120, ACCURACY);
    }

    @Test
    public void testInterpolate() {
        final LinkedListTabulatedFunction listFunction = getListFunction();
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        final LinkedListTabulatedFunction unit = getOneElementFunction();
        assertEquals(listFunction.interpolate(4, listFunction.floorIndexOfX(4)), 17, ACCURACY);
        assertEquals(listFunction.interpolate(2, listFunction.floorIndexOfX(2)), 5, ACCURACY);
        assertEquals(listFunction.interpolate(8, listFunction.floorIndexOfX(8)), 65, ACCURACY);
        assertEquals(unit.interpolate(3.25, unit.floorIndexOfX(3.25)), 10.625, ACCURACY);
        assertEquals(arrayListFunction.interpolate(6, arrayListFunction.floorIndexOfX(6)), 60, ACCURACY);

    }
}

