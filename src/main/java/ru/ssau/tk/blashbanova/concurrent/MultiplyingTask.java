package ru.ssau.tk.blashbanova.concurrent;

import ru.ssau.tk.blashbanova.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;

    public MultiplyingTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i != tabulatedFunction.getCount(); i++) {
            x = tabulatedFunction.getX(i);
            synchronized (tabulatedFunction) {
                y = tabulatedFunction.getY(i);
                System.out.printf("%s, i = %d, x = %f, old y = %f\n", Thread.currentThread().getName(), i, x, y);
                tabulatedFunction.setY(i, y * 10);
                y = tabulatedFunction.getY(i);
            }
            System.out.printf("%s, i = %d, x = %f, new y = %f\n", Thread.currentThread().getName(), i, x, y);
        }
    }
}
