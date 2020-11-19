package ru.ssau.tk.blashbanova.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.MathFunction;
import ru.ssau.tk.blashbanova.functions.SqrFunction;

import static org.testng.Assert.*;

public class MiddleSteppingDifferentialOperatorTest {
    private final double ACCURACY = 0.0001;
    private final MathFunction sqr = new SqrFunction();

    @Test
    public void testDerive() {
        final MiddleSteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(1);
        assertEquals(differentialOperator.derive(sqr).apply(1), 2, ACCURACY);
        assertEquals(differentialOperator.derive(sqr).apply(2), 4, ACCURACY);
        assertEquals(differentialOperator.derive(sqr).apply(3), 6, ACCURACY);
    }
}