package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TangentFunctionTest {
    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        TangentFunction firstFunction = new TangentFunction();
        assertEquals(firstFunction.apply(5), 0.99991, DELTA);
        assertEquals(firstFunction.apply(100), 1, DELTA);
    }
}