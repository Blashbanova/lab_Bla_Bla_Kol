package ru.ssau.tk.blashbanova.functions.tabulated;

import ru.ssau.tk.blashbanova.functions.math.MathFunction;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    private Node head;
    private Node last;

    protected class Node {

        Node next;
        Node prev;
        double x;
        double y;
    }

    private void addNode(double x, double y) {

        Node newNode = new Node();
        if (head == null) {
            head = newNode;
            last = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {

            while (last.next != null) {
                last = last.next;
            }
            last = head.prev;
            head.prev = newNode;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = head;
            last = newNode;
        }
        count += 1;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {

        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double[] xValues = new double[count];
        xValues[0] = xFrom;
        final double step = (xTo - xFrom) / (count - 1);
        this.addNode(xValues[0], source.apply(xValues[0]));
        for (int i = 1; i <= (count - 1); i++) {
            xValues[i] = xValues[i - 1] + step;
            this.addNode(xValues[i], source.apply(xValues[i]));
        }
    }

    private Node getNode(int index) {
        //TODO: надо доделать!!! Заменить indexNode.x на что-то правильное, должен вернуть ссылку на узел номер index
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (i == index) {
                return indexNode;
            }
            indexNode = indexNode.next;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double valueY) {
        valueY = getNode(index).y;
    }

    @Override
    public int indexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x == x) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.y == y) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return last.x;
    }

    @Override
    protected int floorIndexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x < x) {
                indexNode = indexNode.next;
            } else {
                if (i == 0) {
                    return 0;
                }
                return i - 1;
            }
        }

        return getCount();
    }

    @Override
    protected double extrapolateLeft(double x) {

        return 0;
    }

    @Override
    protected double extrapolateRight(double x) {
        return 0;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return 0;
    }
}
