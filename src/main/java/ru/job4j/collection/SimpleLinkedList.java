package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    transient Node<E> first;
    transient Node<E> last;
    int size;
    int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        Node<E> oldLast = last;
        last = new Node<>(last, value, null);
        if (oldLast == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        Node<E> currentNode = first;
        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> currentNode = first;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
           public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E returnedItem = currentNode.item;
                currentNode = currentNode.next;
                return returnedItem;
            }
        };
    }
}