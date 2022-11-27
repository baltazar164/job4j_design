package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    int size;

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        for (int i = 1; i <= size; i++) {
            out.push(in.pop());
        }
        T value = out.pop();
        for (int i = 1; i < size; i++) {
            in.push(out.pop());
        }
        size--;
        return value;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}