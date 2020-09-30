package ru.ssau.tk.blashbanova.functions.math;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.math.ZeroFunction;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    public static final double ACCURACY = 0.0005;
    private final ZeroFunction zero = new ZeroFunction();

    @Test
    public void testZeroFunctionConstructor() {
        assertEquals(zero.apply(-78), 0.0, ACCURACY);
        assertEquals(zero.apply(Double.NaN), 0.0, ACCURACY);
        assertEquals(zero.apply(Double.NEGATIVE_INFINITY), 0, ACCURACY);
    }

    @Test
    public void testGetConstant() {
        assertEquals(zero.getConstant(), 0, ACCURACY);
    }

    @Test
    public void testApplyNot() {
        assertNotEquals(zero.apply(0.0), 8, ACCURACY);
        assertNotEquals(zero.apply(Double.NaN), 8, ACCURACY);
        assertNotEquals(zero.apply(Double.NEGATIVE_INFINITY), 8, ACCURACY);
    }
}

