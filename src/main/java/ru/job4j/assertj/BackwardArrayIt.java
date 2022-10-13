package ru.job4j.assertj;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        point = data.length;
    }

    @Override
    public boolean hasNext() {
        return point - 1 >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int currentPoint = point;
        int nextPoint = currentPoint - 1;
        point = nextPoint;
        return data[nextPoint];
    }
}