package ru.ssau.tk.blashbanova.functions.math;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.math.ModulusFunction;

import static org.testng.Assert.*;

public class ModulusFunctionTest {
    public static final double ACCURACY = 0.0005;
    private final ModulusFunction mod10 = new ModulusFunction();

    @Test
    public void testApplyNumber() {
        assertEquals(mod10.apply(-10), 0, ACCURACY);
        assertEquals(mod10.apply(2.5), 2.5, ACCURACY);
    }

    @Test
    public void testApplyInfinity() {
        assertThrows(ArithmeticException.class, () -> mod10.apply(Double.POSITIVE_INFINITY));
        assertThrows(ArithmeticException.class, () -> mod10.apply(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testApplyNaN() {
        assertThrows(ArithmeticException.class, () -> mod10.apply(Double.NaN));
    }
}
