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

    @Test
    public void testDerive() {
        TabulatedFunction testList = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        TabulatedDifferentialOperator differentialListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        testList = differentialListOperator.derive(testList);
        assertEquals(testList.getY(0), 3, ACCURACY);
        assertEquals(testList.getY(1), 5, ACCURACY);
        assertEquals(testList.getY(3), 7, ACCURACY);
        TabulatedFunction testArray = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
        TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        testArray = differentialArrayOperator.derive(testArray);
        assertEquals(testArray.getX(3), 4, ACCURACY);
        assertEquals(testArray.getY(2), 1, ACCURACY);
        assertEquals(testArray.getY(3), 1, ACCURACY);
    }
}