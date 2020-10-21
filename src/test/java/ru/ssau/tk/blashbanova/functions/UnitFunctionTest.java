package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.UnitFunction;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    public static final double ACCURACY = 0.0005;
    private final UnitFunction unit = new UnitFunction();

    @Test
    public void testUnitFunctionConstructor() {
        assertEquals(unit.apply(220), 1.0, ACCURACY);
        assertEquals(unit.apply(Double.NaN), 1.0, ACCURACY);
        assertEquals(unit.apply(Double.NEGATIVE_INFINITY), 1.0, ACCURACY);
    }

    @Test
    public void testGetConstant() {
        assertEquals(unit.getConstant(), 1, ACCURACY);
    }

    @Test
    public void testApplyNot() {
        assertNotEquals(unit.apply(0.0), 8, ACCURACY);
        assertNotEquals(unit.apply(Double.NaN), 8, ACCURACY);
        assertNotEquals(unit.apply(Double.NEGATIVE_INFINITY), 8, ACCURACY);
    }
}