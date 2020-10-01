package ru.ssau.tk.blashbanova.functions.tabulated;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.math.MathFunction;
import ru.ssau.tk.blashbanova.functions.math.SqrFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final MathFunction source = new SqrFunction();
    private final LinkedListTabulatedFunction listFunction = new LinkedListTabulatedFunction(source,1,9,5);
    private final static double DELTA = 0.0001;

    @Test
    public void testGetCount() {
        assertEquals(listFunction.getCount(),5,DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(listFunction.getX(4),7,DELTA);
    }

    @Test
    public void testGetY() {
    }

    @Test
    public void testSetY() {
    }

    @Test
    public void testIndexOfX() {
    }

    @Test
    public void testIndexOfY() {
    }

    @Test
    public void testLeftBound() {
    }

    @Test
    public void testRightBound() {
    }

    @Test
    public void testFloorIndexOfX() {
    }

    @Test
    public void testExtrapolateLeft() {
    }

    @Test
    public void testExtrapolateRight() {
    }

    @Test
    public void testInterpolate() {
    }
}