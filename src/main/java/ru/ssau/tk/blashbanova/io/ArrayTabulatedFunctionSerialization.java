package ru.ssau.tk.blashbanova.io;

import ru.ssau.tk.blashbanova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.SqrFunction;
import ru.ssau.tk.blashbanova.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new SqrFunction(), -4, 4, 9);
        ArrayTabulatedFunction firstDerivative = (ArrayTabulatedFunction) operator.derive(function);
        ArrayTabulatedFunction secondDerivative = (ArrayTabulatedFunction) operator.derive(firstDerivative);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"))) {
            FunctionsIO.serialize(out, function);
            FunctionsIO.serialize(out, firstDerivative);
            FunctionsIO.serialize(out, secondDerivative);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("output/serialized array functions.bin"))) {
            ArrayTabulatedFunction deserializedFunction = (ArrayTabulatedFunction) FunctionsIO.deserialize(in);
            ArrayTabulatedFunction deserializedFirstDerivative = (ArrayTabulatedFunction) FunctionsIO.deserialize(in);
            ArrayTabulatedFunction deserializedSecondDerivative = (ArrayTabulatedFunction) FunctionsIO.deserialize(in);
            System.out.println(deserializedFunction.toString());
            System.out.println(deserializedFirstDerivative.toString());
            System.out.println(deserializedSecondDerivative.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
