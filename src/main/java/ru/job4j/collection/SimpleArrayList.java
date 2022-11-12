package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) throws IndexOutOfBoundsException {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        T oldValue = get(index);
        if ((--size) > index) {
            System.arraycopy(container, index + 1, container, index, size - index);
        }
        container[size] = null;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
       return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != size;
            }

            @Override
            public T next() throws ConcurrentModificationException, NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }

    private void grow() {
        if (size != 0) {
            container = Arrays.copyOf(container, size * 2);
        }
    }
}
