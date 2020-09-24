package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;
import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA = 0.0001;
    private final MathFunction identify = new IdentityFunction();
    private final MathFunction anotherIdentify = new IdentityFunction();
    private final MathFunction tangent = new HyperTangentFunction();
    private final MathFunction sqr = new SqrFunction();

    @Test
    public void testApply() {
        assertEquals(new CompositeFunction(identify, anotherIdentify).apply(1), 1, DELTA);
        assertEquals(new CompositeFunction(tangent, identify).apply(100), 1, DELTA);
        assertEquals(new CompositeFunction(sqr, tangent).apply(2), 0.99932, DELTA);
    }

    @Test
    public void testApplyNaN() {
        assertEquals(new CompositeFunction(identify, anotherIdentify).apply(Double.NaN), Double.NaN, DELTA);
    }

    @Test
    public void testApplyInfinity() {
        assertEquals(new CompositeFunction(tangent, identify).apply(POSITIVE_INFINITY), 1.0, DELTA);
        assertEquals(new CompositeFunction(sqr, tangent).apply(NEGATIVE_INFINITY), 1.0, DELTA);
    }
}