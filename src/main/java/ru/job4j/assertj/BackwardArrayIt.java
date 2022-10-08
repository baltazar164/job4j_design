package ru.job4j.assertj;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public BackwardArrayIt(int[] data) {
        this.data = data;
        setPoint(data.length);
    }

    @Override
    public boolean hasNext() {
        return getPoint() - 1 >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int currentPoint = getPoint();
        int nextPoint = currentPoint - 1;
        setPoint(nextPoint);
        return data[nextPoint];
    }
}