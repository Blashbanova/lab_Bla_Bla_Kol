package ru.ssau.tk.blashbanova.functions;

public class HyperTangentFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.tanh(x);
    }
}

