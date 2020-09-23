package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {

        IdentityFunction fFunction = new IdentityFunction();
        IdentityFunction gFunction = new IdentityFunction();
        CompositeFunction firstFunction = new CompositeFunction(fFunction, gFunction);
        assertEquals(firstFunction.apply(1), 1, DELTA);

    }
}