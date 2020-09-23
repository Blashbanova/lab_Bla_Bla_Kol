package ru.ssau.tk.blashbanova.functions;

public class ConstantFunction implements MathFunction {
    private final double CONSTANT;

    public ConstantFunction(double number) {
        CONSTANT = number;
    }

    @Override
    public double apply(double number) {
        return CONSTANT;
    }

    public double getConstant() {
        return CONSTANT;
    }
}
