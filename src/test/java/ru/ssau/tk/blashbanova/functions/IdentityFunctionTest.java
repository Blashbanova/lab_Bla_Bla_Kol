package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {
    private final static double DELTA = 0.0001;
    private final MathFunction firstFunction = new IdentityFunction();

    @Test
    public void testApply() {
        assertEquals(firstFunction.apply(53), 53, DELTA);
        assertEquals(firstFunction.apply(100), 100, DELTA);
    }

    @Test
    public void testApplyInfinity() {
        assertEquals(firstFunction.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        assertEquals(firstFunction.apply(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testApplyNaN() {
        assertEquals(firstFunction.apply(Double.NaN), Double.NaN);
    }
}