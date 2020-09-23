package ru.ssau.tk.blashbanova.functions;

public class ModulusFunction implements MathFunction {
    @Override
    public double apply(double number) {
        if (Double.isNaN(number)) {
            throw new ArithmeticException("Can't find the remainder of NaN / 10.");
        }
        if (Double.isInfinite(number)) {
            throw new ArithmeticException("Can't find the remainder of " + number + "/ 10.");
        }
        return number % 10;
    }
}
