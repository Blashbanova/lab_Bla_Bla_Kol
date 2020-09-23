package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        IdentityFunction firstFunction = new IdentityFunction();
        assertEquals(firstFunction.apply(53), 53, DELTA);
        assertEquals(firstFunction.apply(100), 100, DELTA);

    }
}