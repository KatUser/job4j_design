package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;
    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(child).isEmpty()) {
            Queue<Node<E>> data = new LinkedList<>();
            data.offer(this.root);
            Node<E> el = data.poll();
            el.children.add(new Node<>(parent));
            el.children.set(0, new Node<>(child));
            rsl = true;
            }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public String toString() {
        return "SimpleTree{"
                + "root=" + root
                + "children= " + root.children
                + '}';
    }
}
