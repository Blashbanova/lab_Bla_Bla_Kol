package ru.ssau.tk.blashbanova.operations;

import ru.ssau.tk.blashbanova.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}
