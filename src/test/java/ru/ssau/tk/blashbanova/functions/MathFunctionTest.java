package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    public static final double ACCURACY = 0.0005;
    IdentityFunction identity = new IdentityFunction();
    SqrFunction sqr = new SqrFunction();
    TangentFunction tan = new TangentFunction();
    UnitFunction unit = new UnitFunction();

    @Test
    public void testAndThen() {
        assertEquals(unit.andThen(sqr).andThen(tan).apply(2), 0.7615, ACCURACY); //th 1 = 0.76159..
        assertEquals(sqr.andThen(tan).apply(2), 0.99932, ACCURACY); //th 4 = 0.99932..
    }
}