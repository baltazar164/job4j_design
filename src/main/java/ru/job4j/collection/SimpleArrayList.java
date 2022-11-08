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
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size = size + 1;
    }

    @Override
    public T set(int index, T newValue) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        modCount++;
        T oldValue = container[index];
        if ((--size) > index) {
            System.arraycopy(container, index + 1, container, index, size - index);
        }
        container[size] = null;
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
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public T next() throws ConcurrentModificationException, NoSuchElementException {
                checkForComodification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }

            void checkForComodification() throws ConcurrentModificationException {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    private T[] grow() {
        return Arrays.copyOf(container, size * 2);
    }

}
