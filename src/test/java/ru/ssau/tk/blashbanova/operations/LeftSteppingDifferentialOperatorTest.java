package ru.ssau.tk.blashbanova.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.MathFunction;
import ru.ssau.tk.blashbanova.functions.SqrFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {
    private final double ACCURACY = 0.001;
    private final MathFunction sqr = new SqrFunction();

    private LeftSteppingDifferentialOperator getLeftSteppingDifferentialOperator() {
        return new LeftSteppingDifferentialOperator(0.0009);
    }

    @Test
    public void testGetStep() {
        final LeftSteppingDifferentialOperator differentialOperator = getLeftSteppingDifferentialOperator();
        assertEquals(differentialOperator.getStep(), 0.0009);
    }

    @Test
    public void testSetStep() {
        LeftSteppingDifferentialOperator differentialOperator = getLeftSteppingDifferentialOperator();
        differentialOperator.setStep(0.02);
        assertEquals(differentialOperator.getStep(), 0.02);
    }

    @Test
    public void testDerive() {
        final LeftSteppingDifferentialOperator differentialOperator = getLeftSteppingDifferentialOperator();
        assertEquals(differentialOperator.derive(sqr).apply(1), 2, ACCURACY);
        assertEquals(differentialOperator.derive(sqr).apply(2), 4, ACCURACY);
        assertEquals(differentialOperator.derive(sqr).apply(3), 6, ACCURACY);
    }
}