package ru.ssau.tk.blashbanova.functions.math;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.math.ConstantFunction;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    public static final double ACCURACY = 0.0005;
    private final ConstantFunction constant = new ConstantFunction(5);

    @Test
    public void testGetConstant() {
        ConstantFunction secondConstant = new ConstantFunction(Double.NaN);
        ConstantFunction thirdConstant = new ConstantFunction(Double.POSITIVE_INFINITY);
        assertEquals(constant.getConstant(), 5, ACCURACY);
        assertTrue(Double.isNaN(secondConstant.getConstant()));
        assertEquals(thirdConstant.getConstant(), Double.POSITIVE_INFINITY);
    }

    @Test
    public void testApplyNumber() {
        assertEquals(constant.apply(0.0), 5, ACCURACY);
        assertEquals(constant.apply(Double.NaN), 5, ACCURACY);
        assertEquals(constant.apply(Double.NEGATIVE_INFINITY), 5, ACCURACY);
    }

    @Test
    public void testApplyNot() {
        assertNotEquals(constant.apply(0.0), 8, ACCURACY);
        assertNotEquals(constant.apply(Double.NaN), 8, ACCURACY);
        assertNotEquals(constant.apply(Double.NEGATIVE_INFINITY), 8, ACCURACY);
    }
}
