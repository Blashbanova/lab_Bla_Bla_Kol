package ru.ssau.tk.blashbanova.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.blashbanova.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.blashbanova.exceptions.DifferentLengthOfArraysException;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    private double[] getSortedArray() {
        return new double[]{0.5, 1.3, 4.6};
    }

    private double[] getUnsortedArray() {
        return new double[]{0.5, 0.01, 0.5, 4.6};
    }

    @Test
    public void testCheckLengthIsTheSame() {
        final double[] firstArray = getSortedArray();
        final double[] secondArray = getSortedArray();
        final double[] thirdArray = getUnsortedArray();
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(firstArray, thirdArray));
        AbstractTabulatedFunction.checkLengthIsTheSame(firstArray, secondArray);
        AbstractTabulatedFunction.checkLengthIsTheSame(new double[0], new double[0]);
    }

    @Test
    public void testCheckSorted() {
        final double[] sortedArray = getSortedArray();
        final double[] unsortedArray = getUnsortedArray();
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(unsortedArray));
        AbstractTabulatedFunction.checkSorted(sortedArray);
    }
}