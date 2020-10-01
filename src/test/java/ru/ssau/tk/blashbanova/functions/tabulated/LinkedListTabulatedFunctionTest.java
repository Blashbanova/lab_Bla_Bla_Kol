package ru.ssau.tk.blashbanova.functions.tabulated;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.math.MathFunction;
import ru.ssau.tk.blashbanova.functions.math.SqrFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final MathFunction source = new SqrFunction();
    private final LinkedListTabulatedFunction listFunction = new LinkedListTabulatedFunction(source, 1, 9, 5);
    private final static double DELTA = 0.0001;

    @Test
    public void testGetCount() {
        assertEquals(listFunction.getCount(), 5, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(listFunction.getX(3), 7, DELTA);
    }


    @Test
    public void testGetY() {
        assertEquals(listFunction.getY(3), 49, DELTA);
    }

    @Test
    public void testSetY() {
        listFunction.setY(1, 14);
        assertEquals(listFunction.getY(1), 14, DELTA);
        listFunction.setY(1, 9);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(listFunction.indexOfX(7), 3, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(listFunction.indexOfY(49), 3, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(listFunction.leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(listFunction.rightBound(), 9, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(listFunction.floorIndexOfX(2), 0, DELTA);
        assertEquals(listFunction.floorIndexOfX(11), 5, DELTA);
        assertEquals(listFunction.floorIndexOfX(-10), 0, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(listFunction.extrapolateLeft(0), -3, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(listFunction.extrapolateRight(20), 257, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(listFunction.interpolate(2, listFunction.floorIndexOfX(2)), 5, DELTA);
        assertEquals(listFunction.interpolate(0, listFunction.floorIndexOfX(0)), -3, DELTA);
        assertEquals(listFunction.interpolate(20, listFunction.floorIndexOfX(20)), 257, DELTA);
    }
}