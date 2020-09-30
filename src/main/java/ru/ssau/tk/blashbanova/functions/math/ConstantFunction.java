package ru.ssau.tk.blashbanova.functions.math;

public class ConstantFunction implements MathFunction {
    private final double constant;

    public ConstantFunction(double number) {
        constant = number;
    }

    @Override
    public double apply(double number) {
        return constant;
    }

    public double getConstant() {
        return constant;
    }
}
