package ru.ssau.tk.blashbanova.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;
import ru.ssau.tk.blashbanova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {
    private final double ACCURACY = 0.0001;

    private TabulatedDifferentialOperator getArrayDifferentialOperator() {
        return new TabulatedDifferentialOperator();
    }

    private TabulatedDifferentialOperator getListDifferentialOperator() {
        return new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
    }

    @Test
    public void testDerive() {
        TabulatedFunction testList = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        TabulatedDifferentialOperator differentialListOperator = getListDifferentialOperator();
        testList = differentialListOperator.derive(testList);
        assertTrue(testList instanceof LinkedListTabulatedFunction);
        assertEquals(testList.getY(0), 3, ACCURACY);
        assertEquals(testList.getY(1), 5, ACCURACY);
        assertEquals(testList.getY(3), 7, ACCURACY);
        TabulatedFunction testArray = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
        TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        testArray = differentialArrayOperator.derive(testArray);
        assertTrue(testArray instanceof ArrayTabulatedFunction);
        assertEquals(testArray.getX(3), 4, ACCURACY);
        assertEquals(testArray.getY(2), 1, ACCURACY);
        assertEquals(testArray.getY(3), 1, ACCURACY);
    }

    @Test
    public void testGetFactory() {
        final TabulatedDifferentialOperator arrayOperator = getArrayDifferentialOperator();
        final TabulatedDifferentialOperator listOperator = getListDifferentialOperator();
        assertTrue(arrayOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(listOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        TabulatedDifferentialOperator arrayOperator = getArrayDifferentialOperator();
        TabulatedDifferentialOperator listOperator = getListDifferentialOperator();
        arrayOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        listOperator.setFactory(new ArrayTabulatedFunctionFactory());
        assertTrue(listOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(arrayOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }
}