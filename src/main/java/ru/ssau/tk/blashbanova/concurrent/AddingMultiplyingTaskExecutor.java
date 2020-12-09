package ru.ssau.tk.blashbanova.concurrent;

import ru.ssau.tk.blashbanova.functions.ConstantFunction;
import ru.ssau.tk.blashbanova.functions.LinkedListTabulatedFunction;

public class AddingMultiplyingTaskExecutor {
    public static void main(String[] args) {
        LinkedListTabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 100);
        Thread firstThread = new Thread(new MultiplyingTask(tabulatedFunction));
        firstThread.start();
        Thread secondThread = new Thread(new MultiplyingTask(tabulatedFunction));
        secondThread.start();
        Thread thirdThread = new Thread(new AddingTask(tabulatedFunction));
        thirdThread.start();
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tabulatedFunction);
    }
}
