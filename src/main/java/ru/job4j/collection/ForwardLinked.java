package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public boolean revert() {
        boolean result;
        if (head == null || head.next == null) {
            result = false;
        } else {
            Node<T> prevNode = head;
            Node<T> curNode = head.next;
            Node<T> nextNode = curNode.next;
            curNode.next = prevNode;

            while (nextNode.next != null) {
                prevNode = curNode;
                curNode = nextNode;
                nextNode = nextNode.next;
                curNode.next = prevNode;
            }

            nextNode.next = curNode;
            head.next = null;
            head = nextNode;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        node.next = head;
        head = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        head.value = null;
        Node<T> oldHead = head;
        head = head.next;
        oldHead.next = null;
        return value;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}