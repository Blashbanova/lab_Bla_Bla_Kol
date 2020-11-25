package ru.ssau.tk.blashbanova.io;

import ru.ssau.tk.blashbanova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        File fileArray = new File("output/array function.bin");
        File fileList = new File("output/linked list function.bin");

        double[] xValue = new double[]{0.1, 0.2, 0.3, 0.4};
        double[] yValue = new double[]{1.1, 1.2, 1.3, 1.4};

        TabulatedFunction functionList = new LinkedListTabulatedFunction(xValue, yValue);
        TabulatedFunction functionArray = new ArrayTabulatedFunction(xValue, yValue);

        try (BufferedOutputStream outArray = new BufferedOutputStream(
                new FileOutputStream(fileArray));
             BufferedOutputStream outList = new BufferedOutputStream(
                     new FileOutputStream(fileList))) {

            FunctionsIO.writeTabulatedFunction(outArray, functionArray);
            FunctionsIO.writeTabulatedFunction(outList, functionList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
