package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    public static final double ACCURACY = 0.0005;
    UnitFunction unit = new UnitFunction();

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
}