package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        boolean hasNext = true;
        while (hasNext) {
            try {
                out.push(in.pop());
            } catch (NoSuchElementException e) {
                hasNext = false;
            }
        }
        T value = out.pop();
        hasNext = true;
        while (hasNext) {
            try {
                in.push(out.pop());
            } catch (NoSuchElementException e) {
                hasNext = false;
            }
        }
        return value;
    }

    public void push(T value) {
        in.push(value);
    }
}