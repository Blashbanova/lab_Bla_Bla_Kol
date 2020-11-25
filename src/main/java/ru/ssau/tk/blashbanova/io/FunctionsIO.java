package ru.ssau.tk.blashbanova.io;

import ru.ssau.tk.blashbanova.functions.Point;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;
import ru.ssau.tk.blashbanova.functions.factory.TabulatedFunctionFactory;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter write = new PrintWriter(writer);
        write.println(function.getCount());
        for (Point point : function) {
            write.printf("%f %f\n", point.x, point.y);
        }
        write.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        try {
            int size = Integer.parseInt(reader.readLine());
            double[] xValues = new double[size];
            double[] yValues = new double[size];
            NumberFormat formatter = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
            for (int i = 0; i != size; i++) {
                String[] values = reader.readLine().split(" ");
                xValues[i] = formatter.parse(values[0]).doubleValue();
                yValues[i] = formatter.parse(values[1]).doubleValue();
            }
            return factory.create(xValues, yValues);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IOException("Unexpected exception", e);
        }
        return null;
    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(function);
        out.flush();
    }
}
