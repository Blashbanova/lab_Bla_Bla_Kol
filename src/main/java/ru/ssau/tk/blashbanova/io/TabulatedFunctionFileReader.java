package ru.ssau.tk.blashbanova.io;

import ru.ssau.tk.blashbanova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.blashbanova.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        try (BufferedReader arrayReader = new BufferedReader(new FileReader("input/function.txt"));
             BufferedReader listReader = new BufferedReader(new FileReader("input/function.txt"))) {
            ArrayTabulatedFunction arrayFunction = (ArrayTabulatedFunction) FunctionsIO.readTabulatedFunction(arrayReader, new ArrayTabulatedFunctionFactory());
            LinkedListTabulatedFunction linkedListFunction = (LinkedListTabulatedFunction) FunctionsIO.readTabulatedFunction(listReader, new LinkedListTabulatedFunctionFactory());
            System.out.println(arrayFunction.toString());
            System.out.println(linkedListFunction.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
