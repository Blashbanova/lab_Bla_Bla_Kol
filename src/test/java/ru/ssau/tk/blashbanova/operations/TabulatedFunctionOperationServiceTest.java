package ru.ssau.tk.blashbanova.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.*;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    private final MathFunction identityFunction = new IdentityFunction();
    private final double[] xValues = {-2, -1, 0, 1, 2};
    private final double[] yValues = {4, 1, 0, 1, 4};

    private ArrayTabulatedFunction getArrayTabulatedFunction() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction getLinkedListTabulatedFunction() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private ArrayTabulatedFunction getLeftBoundNaNFunction() {
        return new ArrayTabulatedFunction(identityFunction, Double.NaN, 5, 3);
    }

    private LinkedListTabulatedFunction getRightBoundNaNFunction() {
        return new LinkedListTabulatedFunction(identityFunction, 5, Double.NaN, 3);
    }

    @Test
    public void testAsPoints() {
        final ArrayTabulatedFunction arrayFunction = getArrayTabulatedFunction();
        final LinkedListTabulatedFunction listFunction = getLinkedListTabulatedFunction();
        final ArrayTabulatedFunction brokenArrayFunction = getLeftBoundNaNFunction();
        final LinkedListTabulatedFunction brokenListFunction = getRightBoundNaNFunction();
        final Point[] arrayPoints = TabulatedFunctionOperationService.asPoints(arrayFunction);
        final Point[] arrayBrokenPoints = TabulatedFunctionOperationService.asPoints(brokenArrayFunction);
        final Point[] listPoints = TabulatedFunctionOperationService.asPoints(listFunction);
        final Point[] listBrokenPoints = TabulatedFunctionOperationService.asPoints(brokenListFunction);
        int i = 0;
        for (Point point : arrayPoints) {
            assertEquals(arrayFunction.getX(i), point.x);
            assertEquals(arrayFunction.getY(i++), point.y);
        }
        i = 0;
        for (Point point : arrayBrokenPoints) {
            assertEquals(brokenArrayFunction.getX(i), point.x);
            assertEquals(brokenArrayFunction.getY(i++), point.y);
        }
        i = 0;
        for (Point point : listPoints) {
            assertEquals(listFunction.getX(i), point.x);
            assertEquals(listFunction.getY(i++), point.y);
        }
        i = 0;
        for (Point point : listBrokenPoints) {
            assertEquals(brokenListFunction.getX(i), point.x);
            assertEquals(brokenListFunction.getY(i++), point.y);
        }
    }
}
