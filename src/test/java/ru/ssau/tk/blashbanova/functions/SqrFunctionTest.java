package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {
    public static final double ACCURACY = 0.0005;
    private final MathFunction sqr = new SqrFunction();

    @Test
    public void testApplyNumbers() {
        assertEquals(sqr.apply(-2.5), 6.25, ACCURACY);
        assertEquals(sqr.apply(0), 0, ACCURACY);
    }

    @Test
    public void testApplyInfinity() {
        assertEquals(sqr.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        assertEquals(sqr.apply(Double.NEGATIVE_INFINITY), Double.POSITIVE_INFINITY);
    }

    @Test
    public void testApplyNaN() {
        assertThrows(ArithmeticException.class, () -> sqr.apply(Double.NaN));
    }
}
