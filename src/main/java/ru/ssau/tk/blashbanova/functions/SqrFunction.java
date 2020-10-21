package ru.ssau.tk.blashbanova.functions;

import ru.ssau.tk.blashbanova.functions.MathFunction;

public class SqrFunction implements MathFunction {
    @Override
    public double apply(double number) {
        if (Double.isNaN(number)) {
            throw new ArithmeticException("Can't square the number: it's NaN.");
        }
        return Math.pow(number, 2);
    }
}

