package ru.ssau.tk.blashbanova.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.blashbanova.functions.*;
import ru.ssau.tk.blashbanova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.LinkedListTabulatedFunctionFactory;

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

    private ArrayTabulatedFunction getFunction() {
        return new ArrayTabulatedFunction(identityFunction, -2, 2, 5);
    }

    private LinkedListTabulatedFunction getListFunction() {
        return new LinkedListTabulatedFunction(identityFunction, 8, 13, 5);
    }

    private TabulatedFunctionOperationService getArrayOperationService() {
        return new TabulatedFunctionOperationService();
    }

    private TabulatedFunctionOperationService getListOperationService() {
        return new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
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

    @Test
    public void testGetFactory() {
        final TabulatedFunctionOperationService arrayOperation = getArrayOperationService();
        final TabulatedFunctionOperationService listOperation = getListOperationService();
        assertTrue(arrayOperation.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(listOperation.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        TabulatedFunctionOperationService arrayOperation = getArrayOperationService();
        TabulatedFunctionOperationService listOperation = getListOperationService();
        arrayOperation.setFactory(new LinkedListTabulatedFunctionFactory());
        listOperation.setFactory(new ArrayTabulatedFunctionFactory());
        assertTrue(arrayOperation.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(listOperation.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {
        final TabulatedFunctionOperationService arrayOperation = getArrayOperationService();
        final TabulatedFunctionOperationService listOperation = getListOperationService();
        final ArrayTabulatedFunction arrayFunction = getArrayTabulatedFunction();
        final ArrayTabulatedFunction anotherArrayFunction = getFunction();
        final ArrayTabulatedFunction brokenFunction = getLeftBoundNaNFunction();
        final LinkedListTabulatedFunction listFunction = getLinkedListTabulatedFunction();
        final LinkedListTabulatedFunction anotherListFunction = getListFunction();

        final TabulatedFunction arraySum = arrayOperation.sum(arrayFunction, anotherArrayFunction);
        final TabulatedFunction arrayAndListSum = listOperation.sum(arrayFunction, listFunction);
        final TabulatedFunction listSum = arrayOperation.sum(anotherListFunction, anotherListFunction);
        assertThrows(InconsistentFunctionsException.class, () -> arrayOperation.sum(arrayFunction, brokenFunction));
        assertThrows(InconsistentFunctionsException.class, () -> listOperation.sum(arrayFunction, anotherListFunction));
        int i = 0;
        for (Point point : arraySum) {
            assertEquals(arrayFunction.getX(i), point.x);
            assertEquals(arrayFunction.getY(i) + anotherArrayFunction.getY(i), point.y);
            i++;
        }
        i = 0;
        for (Point point : listSum) {
            assertEquals(anotherListFunction.getX(i), point.x);
            assertEquals(2 * anotherListFunction.getY(i), point.y);
            i++;
        }
        i = 0;
        for (Point point : arrayAndListSum) {
            assertEquals(arrayFunction.getX(i), point.x);
            assertEquals(arrayFunction.getY(i) + listFunction.getY(i), point.y);
            i++;
        }
    }

    @Test
    public void testSubtract() {
        final TabulatedFunctionOperationService arrayOperation = getArrayOperationService();
        final TabulatedFunctionOperationService listOperation = getListOperationService();
        final ArrayTabulatedFunction arrayFunction = getArrayTabulatedFunction();
        final ArrayTabulatedFunction anotherArrayFunction = getFunction();
        final ArrayTabulatedFunction brokenFunction = getLeftBoundNaNFunction();
        final LinkedListTabulatedFunction listFunction = getLinkedListTabulatedFunction();
        final LinkedListTabulatedFunction anotherListFunction = getListFunction();

        final TabulatedFunction arraySubtract = arrayOperation.subtract(arrayFunction, anotherArrayFunction);
        final TabulatedFunction arrayAndListSubtract = listOperation.subtract(arrayFunction, listFunction);
        final TabulatedFunction listSubtract = listOperation.subtract(anotherListFunction, anotherListFunction);
        assertThrows(InconsistentFunctionsException.class, () -> arrayOperation.subtract(arrayFunction, brokenFunction));
        assertThrows(InconsistentFunctionsException.class, () -> listOperation.subtract(arrayFunction, anotherListFunction));
        int i = 0;
        for (Point point : arraySubtract) {
            assertEquals(arrayFunction.getX(i), point.x);
            assertEquals(arrayFunction.getY(i) - anotherArrayFunction.getY(i), point.y);
            i++;
        }
        i = 0;
        for (Point point : listSubtract) {
            assertEquals(anotherListFunction.getX(i), point.x);
            assertEquals(0.0, point.y);
            i++;
        }
        i = 0;
        for (Point point : arrayAndListSubtract) {
            assertEquals(arrayFunction.getX(i), point.x);
            assertEquals(arrayFunction.getY(i) - listFunction.getY(i), point.y);
            i++;
        }
    }

    @Test
    public void testMultiply() {
        final TabulatedFunctionOperationService arrayOperation = getArrayOperationService();
        final TabulatedFunctionOperationService listOperation = getListOperationService();
        final ArrayTabulatedFunction arrayFunction = getArrayTabulatedFunction();
        final ArrayTabulatedFunction anotherArrayFunction = getFunction();
        final ArrayTabulatedFunction brokenFunction = getLeftBoundNaNFunction();
        final LinkedListTabulatedFunction listFunction = getLinkedListTabulatedFunction();
        final LinkedListTabulatedFunction anotherListFunction = getListFunction();
        final TabulatedFunction arrayMultiply = arrayOperation.multiply(arrayFunction, anotherArrayFunction);
        final TabulatedFunction arrayAndListMultiply = listOperation.multiply(arrayFunction, listFunction);
        final TabulatedFunction listMultiply = listOperation.multiply(anotherListFunction, anotherListFunction);
        assertThrows(InconsistentFunctionsException.class, () -> arrayOperation.multiply(arrayFunction, brokenFunction));
        assertThrows(InconsistentFunctionsException.class, () -> listOperation.multiply(arrayFunction, anotherListFunction));
        int i = 0;
        for (Point point : arrayMultiply) {
            assertEquals(arrayFunction.getX(i), point.x);
            assertEquals(arrayFunction.getY(i) * anotherArrayFunction.getY(i), point.y);
            i++;
        }
        i = 0;
        for (Point point : listMultiply) {
            assertEquals(anotherListFunction.getX(i), point.x);
            assertEquals(anotherListFunction.getY(i) * anotherListFunction.getY(i), point.y);
            i++;
        }
        i = 0;
        for (Point point : arrayAndListMultiply) {
            assertEquals(arrayFunction.getX(i), point.x);
            assertEquals(arrayFunction.getY(i) * listFunction.getY(i), point.y);
            i++;
        }
    }

    @Test
    public void testDivide() {
        final TabulatedFunctionOperationService arrayOperation = getArrayOperationService();
        final TabulatedFunctionOperationService listOperation = getListOperationService();
        final ArrayTabulatedFunction arrayFunction = getArrayTabulatedFunction();
        final ArrayTabulatedFunction anotherArrayFunction = getFunction();
        final ArrayTabulatedFunction brokenFunction = getLeftBoundNaNFunction();
        final LinkedListTabulatedFunction listFunction = getLinkedListTabulatedFunction();
        final LinkedListTabulatedFunction anotherListFunction = getListFunction();
        final TabulatedFunction arrayDivide = arrayOperation.divide(arrayFunction, anotherArrayFunction);
        final TabulatedFunction arrayAndListDivide = listOperation.divide(arrayFunction, listFunction);
        final TabulatedFunction listDivide = listOperation.divide(anotherListFunction, anotherListFunction);
        assertThrows(InconsistentFunctionsException.class, () -> arrayOperation.divide(arrayFunction, brokenFunction));
        assertThrows(InconsistentFunctionsException.class, () -> listOperation.divide(arrayFunction, anotherListFunction));
        int i = 0;
        for (Point point : arrayDivide) {
            assertEquals(arrayFunction.getX(i), point.x);
            assertEquals(arrayFunction.getY(i) / anotherArrayFunction.getY(i), point.y);
            i++;
        }
        i = 0;
        for (Point point : listDivide) {
            assertEquals(anotherListFunction.getX(i), point.x);
            assertEquals(1.0, point.y);
            i++;
        }
        i = 0;
        for (Point point : arrayAndListDivide) {
            assertEquals(arrayFunction.getX(i), point.x);
            assertEquals(arrayFunction.getY(i) / listFunction.getY(i), point.y);
            i++;
        }
    }
}
