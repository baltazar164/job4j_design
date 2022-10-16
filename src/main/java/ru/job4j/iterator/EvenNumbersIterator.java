package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    private boolean isEven(int n) {
        return n % 2 == 0;
    }

    @Override
    public boolean hasNext() {
        while (index < data.length && !isEven(data[index])) {
            index++;
        }
        return index < data.length;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}