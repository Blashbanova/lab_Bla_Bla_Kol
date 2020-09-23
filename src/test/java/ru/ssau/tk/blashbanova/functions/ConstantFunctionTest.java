package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    public static final double ACCURACY = 0.0005;
    ConstantFunction constant = new ConstantFunction(5);

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
}
