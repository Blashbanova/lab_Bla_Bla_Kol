package ru.ssau.tk.blashbanova.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;

import static org.testng.Assert.*;

public class TabulatedFunctionFactoryTest {

    @Test
    public void testCreate() {
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {10, 20, 30, 40, 50};
        TabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction otherListFactory = listFactory.create(x, y);
        assertTrue(otherListFactory instanceof LinkedListTabulatedFunction);
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction otherArrayFactory = arrayFactory.create(x, y);
        assertTrue(otherArrayFactory instanceof ArrayTabulatedFunction);
    }
}