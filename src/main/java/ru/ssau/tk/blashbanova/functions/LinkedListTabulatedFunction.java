package ru.ssau.tk.blashbanova.functions;

import ru.ssau.tk.blashbanova.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable {
    private static final long serialVersionUID = -2533098847375363225L;
    private Node head;
    private int count = 0;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Less than minimum length");
        }
        AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);
        AbstractTabulatedFunction.checkSorted(xValues);
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            throw new IllegalArgumentException("Incorrect data");
        }
        double[] xValues = new double[count];
        xValues[0] = xFrom;
        final double step = (xTo - xFrom) / (count - 1);
        this.addNode(xValues[0], source.apply(xValues[0]));
        for (int i = 1; i <= count - 1; i++) {
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
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Incorrect index entered");
        }
        Node indexNode = head;
        if (index > count / 2) {
            for (int i = count - 1; i > count / 2; i--) {
                if (i == index) {
                    return indexNode.prev;
                }
                indexNode = indexNode.prev;
            }
        }
        for (int i = 0; i < count; i++) {
            if (i == index) {
                return indexNode;
            }
            indexNode = indexNode.next;
        }
        return head;
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
        if (x < head.x) {
            throw new IllegalArgumentException("X is less than the left border");
        }
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
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node indexNode = getNode(floorIndex);
        if (x > indexNode.next.x || x < indexNode.x) {
            throw new InterpolationException("Value is out of bounds of the interpolation interval!");
        }
        return interpolate(x, indexNode.x, indexNode.next.x, indexNode.y, indexNode.next.y);
    }

    protected static class Node implements Serializable{
        private static final long serialVersionUID = 243897275333018942L;
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Point point = new Point(node.x, node.y);
                    if (node == head.prev) {
                        node = null;
                    } else {
                        node = node.next;
                    }
                    return point;
                }
            }
        };
    }
}
