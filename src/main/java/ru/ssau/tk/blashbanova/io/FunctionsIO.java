package ru.ssau.tk.blashbanova.io;

import ru.ssau.tk.blashbanova.functions.Point;
import ru.ssau.tk.blashbanova.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.PrintWriter;

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
}
