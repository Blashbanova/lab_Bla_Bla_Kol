package ru.ssau.tk.blashbanova.functions.math;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.math.*;

import static org.testng.Assert.*;

public class MathFunctionTest {
    public static final double ACCURACY = 0.0005;
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction tan = new HyperTangentFunction();
    private final MathFunction unit = new UnitFunction();
    private final MathFunction identity = new IdentityFunction();

    @Test
    public void testAndThen() {
        assertEquals(unit.andThen(sqr).andThen(tan).apply(2), 0.7615, ACCURACY); //th 1 = 0.76159..
        assertEquals(sqr.andThen(tan).apply(2), 0.99932, ACCURACY); //th 4 = 0.99932..
    }

    @Test
    public void testAndThenNan() {
        assertEquals(unit.andThen(sqr).andThen(tan).apply(Double.NaN), 0.7615, ACCURACY); //th 1 = 0.76159..
        assertThrows(ArithmeticException.class, () -> sqr.andThen(tan).apply(Double.NaN));//th 4 = 0.99932..
    }

    @Test
    public void testAndThenInfinity() {
        assertEquals(unit.andThen(sqr).andThen(tan).apply(Double.POSITIVE_INFINITY), 0.7615, ACCURACY); //th 1 = 0.76159..
        assertEquals(sqr.andThen(tan).apply(Double.POSITIVE_INFINITY), 1, ACCURACY);
        assertEquals(identity.andThen(tan).apply(Double.NEGATIVE_INFINITY), -1, ACCURACY);
    }
}