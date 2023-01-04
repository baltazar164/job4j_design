package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean isBinary() {
        Optional<Node<E>> foundNode = findByPredicate(t -> t.children.size() > 2);
        return foundNode.isEmpty();
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> foundOptionalChild = findBy(child);
        if (foundOptionalChild.isEmpty()) {
            Optional<Node<E>> foundOptionalParent = findBy(parent);
            if (foundOptionalParent.isPresent()) {
                Node<E> currentParentNode = foundOptionalParent.get();
                currentParentNode.children.add(new Node(child));
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(t -> Objects.equals(t.value, value));
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Tree.Node<E>> rsl = Optional.empty();
        Queue<Tree.Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Tree.Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}