package ru.ssau.tk.blashbanova.functions.math;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.math.HyperTangentFunction;
import ru.ssau.tk.blashbanova.functions.math.MathFunction;

import static org.testng.Assert.*;

public class HyperTangentFunctionTest {
    private final static double DELTA = 0.0001;
    private final MathFunction firstFunction = new HyperTangentFunction();

    @Test
    public void testApply() {
        assertEquals(firstFunction.apply(5), 0.99991, DELTA);
        assertEquals(firstFunction.apply(100), 1, DELTA);
    }

    @Test
    public void testApplyNaN() {
        assertEquals(firstFunction.apply(Double.NaN), Double.NaN, DELTA);
    }

    @Test
    public void testApplyInfinity() {
        assertEquals(firstFunction.apply(Double.POSITIVE_INFINITY), 1.0, DELTA);
        assertEquals(firstFunction.apply(Double.NEGATIVE_INFINITY), -1.0, DELTA);
    }
}