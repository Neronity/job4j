package ru.job4j.tree;

import ru.job4j.lists.SimpleLinkedList;

import java.util.*;

public class SimpleTreeClass<E extends Comparable<E>> implements SimpleTree<E>, Iterable<E> {

    private Node<E> root;
    private int modCount;


    public SimpleTreeClass(E value) {
        this.root = new Node<>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> n = findBy(parent);
        if (n.isPresent()) {
            n.get().add(new Node<>(child));
            result = true;
        }
        modCount++;
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(root, modCount);
    }

    private class Itr implements Iterator<E> {
        Queue<Node<E>> queue;
        int expectedMod;


        public Itr(Node<E> root, int modCount) {
            queue = new LinkedList<>();
            if (root != null) {
                queue.add(root);
            }
            expectedMod = modCount;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

        public E next() {
            if (modCount != expectedMod) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> node = queue.poll();
            E result = node.getValue();
            if (!node.leaves().isEmpty()) {
                queue.addAll(node.leaves());
            }
            return result;
        }
    }
}
