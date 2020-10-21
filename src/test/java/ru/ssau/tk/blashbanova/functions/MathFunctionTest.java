package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    public static final double ACCURACY = 0.0005;
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction tan = new HyperTangentFunction();
    private final MathFunction unit = new UnitFunction();
    private final MathFunction identity = new IdentityFunction();
    private final double[] xValues = {1, 3, 5, 7, 9};
    private final double[] yValues = {10, 30, 50, 70, 90};

    private LinkedListTabulatedFunction getAnotherListFunction() {
        return new LinkedListTabulatedFunction(sqr, 10, 90, 5);
    }

    private LinkedListTabulatedFunction getArrayListFunction() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private ArrayTabulatedFunction getFunction() {
        return new ArrayTabulatedFunction(sqr, 10, 90, 5);
    }

    private ArrayTabulatedFunction getArrayFunction() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

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

    @Test
    public void testCompositeFunctionList() {
        final LinkedListTabulatedFunction arrayListFunction = getArrayListFunction();
        final LinkedListTabulatedFunction anotherListFunction = getAnotherListFunction();
        assertEquals(arrayListFunction.andThen(anotherListFunction).andThen(sqr).apply(5), 6250000, ACCURACY);
        assertEquals(arrayListFunction.andThen(anotherListFunction).andThen(sqr).apply(3), 810000, ACCURACY);
        assertNotEquals(sqr.andThen(anotherListFunction).andThen(arrayListFunction).apply(50), Double.NaN, ACCURACY);
    }

    @Test
    public void testCompositeFunctionArray() {
        final ArrayTabulatedFunction function = getFunction();
        final ArrayTabulatedFunction arrayFunction = getArrayFunction();
        assertEquals(arrayFunction.andThen(function).andThen(sqr).apply(5), 6250000, ACCURACY);
        assertEquals(arrayFunction.andThen(function).andThen(sqr).apply(3), 810000, ACCURACY);
        assertNotEquals(sqr.andThen(function).andThen(arrayFunction).apply(50), Double.NaN, ACCURACY);
    }
}