package ru.ssau.tk.blashbanova.functions;

import ru.ssau.tk.blashbanova.functions.AbstractTabulatedFunction;
import ru.ssau.tk.blashbanova.functions.MathFunction;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head;
    private int count = 0;

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

    private void addNode(double x, double y) {
        Node newNode = new Node();
        if (head == null) {
            head = newNode;
            newNode.x = x;
            newNode.y = y;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            Node last = head.prev;
            head.prev = newNode;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = head;
            newNode.x = x;
            newNode.y = y;
        }
        count += 1;
    }

    private Node getNode(int index) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (i == index) {
                return indexNode;
            }
            indexNode = indexNode.next;
        }
        return null;
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
    public void setY(int index, double value) {
        getNode(index).y = value;
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
        return head.prev.x;
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
        Node indexNode = head;
        return interpolate(x, leftBound(), indexNode.next.x, indexNode.y, indexNode.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        Node indexNode = head.prev;
        return interpolate(x, indexNode.prev.x, indexNode.x, indexNode.prev.y, indexNode.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node indexNode = head;
        if (floorIndex == 0) {
            return extrapolateLeft(x);
        }
        if (floorIndex == count) {
            return extrapolateRight(x);
        }
        for (int i = 0; i < count; i++) {
            if (i == floorIndex) {
                return interpolate(x, indexNode.x, indexNode.next.x, indexNode.y, indexNode.next.y);
            }
            indexNode = indexNode.next;
        }
        return 0;
    }

    protected class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }
}
