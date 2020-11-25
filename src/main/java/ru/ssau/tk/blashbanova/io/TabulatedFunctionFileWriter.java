package ru.ssau.tk.blashbanova.io;

import ru.ssau.tk.blashbanova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.SqrFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        try (BufferedWriter arrayFileWriter = new BufferedWriter(new FileWriter("output/array function.txt"));
             BufferedWriter listFileWriter = new BufferedWriter(new FileWriter("output/linked list function.txt"))) {
            ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new SqrFunction(), -2, 2, 5);
            LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new SqrFunction(), -4, 4, 9);
            FunctionsIO.writeTabulatedFunction(arrayFileWriter, arrayTabulatedFunction);
            FunctionsIO.writeTabulatedFunction(listFileWriter, linkedListTabulatedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
