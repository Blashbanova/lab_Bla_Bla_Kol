package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ModulusFunctionTest {
    public static final double ACCURACY = 0.0005;

    @Test
    public void testApplyNumber() {
        ModulusFunction mod10 = new ModulusFunction();
        assertEquals(mod10.apply(-10), 0, ACCURACY);
        assertEquals(mod10.apply(2.5), 2.5, ACCURACY);
    }

    @Test
    public void testApplyInfinity() {
        ModulusFunction mod10 = new ModulusFunction();
        assertThrows(ArithmeticException.class, () -> mod10.apply(Double.POSITIVE_INFINITY));
        assertThrows(ArithmeticException.class, () -> mod10.apply(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testApplyNaN() {
        ModulusFunction mod10 = new ModulusFunction();
        assertThrows(ArithmeticException.class, () -> mod10.apply(Double.NaN));
    }
}
