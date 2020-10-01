package ru.ssau.tk.blashbanova.functions.tabulated;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.math.IdentityFunction;
import ru.ssau.tk.blashbanova.functions.math.MathFunction;
import ru.ssau.tk.blashbanova.functions.math.SqrFunction;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final double ACCURACY = 0.0005;
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction identityFunction = new IdentityFunction();

    private ArrayTabulatedFunction getFunction() {
        return new ArrayTabulatedFunction(sqr, -2, 2, 5);
    }

    @Test
    public void testGetCount() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, -1, 1, 1);
        assertNotEquals(getFunction().getCount(), 0);
        assertNotEquals(testFunction.getCount(), 0);
        assertEquals(getFunction().getCount(), 5);
        assertEquals(testFunction.getCount(), 1);
    }

    @Test
    public void testGetX() {
        assertNotEquals(getFunction().getX(0), Double.NaN);
        assertNotEquals(getFunction().getX(4), Double.NaN);
        assertEquals(getFunction().getX(1), -1, ACCURACY);
        assertEquals(getFunction().getX(4), 2, ACCURACY);
    }

    @Test
    public void testGetY() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(identityFunction, Double.NaN, 3, 5);
        assertEquals(testFunction.getY(1), Double.NaN);
        assertEquals(testFunction.getY(3), Double.NaN);
        assertEquals(testFunction.getY(4), 3, ACCURACY);
        assertNotEquals(getFunction().getY(4), Double.NaN);
        assertNotEquals(getFunction().getY(0), Double.NaN);
        assertEquals(getFunction().getY(2), 0, ACCURACY);
        assertEquals(getFunction().getY(3), 1, ACCURACY);
    }

    @Test
    public void testSetY() {
        ArrayTabulatedFunction function = getFunction();
        ArrayTabulatedFunction anotherFunction = getFunction();
        function.setY(2, 111);
        anotherFunction.setY(3, Double.NaN);
        assertEquals(function.getY(2), 111, ACCURACY);
        assertEquals(anotherFunction.getY(3), Double.NaN);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getFunction().indexOfX(2), 4);
        assertEquals(getFunction().indexOfX(0), 2);
        assertNotEquals(getFunction().indexOfX(1000), Double.NaN);
        assertNotEquals(getFunction().indexOfX(-1000), Double.NaN);
        assertEquals(getFunction().indexOfX(Double.NaN), -1);
        assertEquals(getFunction().indexOfX(5), -1);
        assertEquals(getFunction().indexOfX(-5), -1);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getFunction().indexOfY(1), 1);
        assertEquals(getFunction().indexOfY(4), 0);
        assertNotEquals(getFunction().indexOfY(1000), Double.NaN);
        assertNotEquals(getFunction().indexOfY(-1000), Double.NaN);
        assertEquals(getFunction().indexOfY(Double.NaN), -1);
        assertEquals(getFunction().indexOfY(5), -1);
        assertEquals(getFunction().indexOfY(-5), -1);
    }

    @Test
    public void testLeftBound() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, Double.NaN, 5, 1);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(identityFunction, 1, Double.NaN, 1);
        assertEquals(testFunction.leftBound(), Double.NaN, ACCURACY);
        assertEquals(anotherFunction.leftBound(), 1, ACCURACY);
        assertEquals(getFunction().leftBound(), -2, ACCURACY);
    }

    @Test
    public void testRightBound() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, Double.NaN, 5, 1);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(identityFunction, 1, Double.NaN, 1);
        assertEquals(testFunction.rightBound(), Double.NaN);
        assertEquals(anotherFunction.rightBound(), 1, ACCURACY);
        assertEquals(getFunction().rightBound(), 2, ACCURACY);
    }

    @Test
    public void testFloorIndexOfX() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 1, 5, 10);
        assertEquals(testFunction.floorIndexOfX(1.8), 1);
        assertEquals(testFunction.floorIndexOfX(-1), 0);
        assertEquals(testFunction.floorIndexOfX(6), 10);
        assertEquals(testFunction.floorIndexOfX(4.5), 7);
        assertEquals(getFunction().floorIndexOfX(2), 3);
        assertEquals(getFunction().floorIndexOfX(-2), 0);
    }

    @Test
    public void testFloorIndexOfXNaN() {
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(identityFunction, Double.NaN, 5, 3);
        final ArrayTabulatedFunction someFunction = new ArrayTabulatedFunction(identityFunction, -3, Double.NaN, 5);
        assertEquals(anotherFunction.floorIndexOfX(3), -1);
        assertEquals(someFunction.floorIndexOfX(-4), 0);
        assertEquals(someFunction.floorIndexOfX(-3), 0);
    }

    @Test
    public void testExtrapolateLeft() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(identityFunction, -5, 5, 20);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, -5, 1, 10);
        assertEquals(getFunction().extrapolateLeft(-3), 7, ACCURACY);
        assertEquals(testFunction.extrapolateLeft(-10), -10, ACCURACY);
        assertEquals(anotherFunction.extrapolateLeft(-10), 71.6667, ACCURACY);
    }

    @Test
    public void testExtrapolateRight() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(identityFunction, -5, 5, 20);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, -7, 3, 10);
        assertEquals(getFunction().extrapolateRight(4), 10, ACCURACY);
        assertEquals(testFunction.extrapolateRight(10), 10, ACCURACY);
        assertEquals(anotherFunction.extrapolateRight(7), 28.5556, ACCURACY);
    }

    @Test
    public void testInterpolate() {
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, -7, 3, 10);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, -5, 1, 10);
        assertEquals(anotherFunction.interpolate(7, anotherFunction.floorIndexOfX(7)), 28.5556, ACCURACY);
        assertEquals(testFunction.interpolate(-10, testFunction.floorIndexOfX(-10)), 71.6667, ACCURACY);
        assertEquals(getFunction().interpolate(-0.5, getFunction().floorIndexOfX(-0.5)), 0.5, ACCURACY);
        assertEquals(testFunction.interpolate(-2.5, testFunction.floorIndexOfX(-2.5)), 6.3335, ACCURACY);
    }
}