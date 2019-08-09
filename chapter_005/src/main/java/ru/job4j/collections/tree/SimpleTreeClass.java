package ru.job4j.collections.tree;

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
        if (findBy(child).isEmpty()) {
            Optional<Node<E>> n = findBy(parent);
            if (n.isPresent()) {
                n.get().add(new Node<>(child));
                result = true;
                modCount++;
            }
        }
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

    public boolean isBinary() {
        Iterator<Node<E>> itr = new NodeItr();
        boolean result = true;
        while(itr.hasNext()) {
            if (itr.next().leaves().size() > 2) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new ValueItr();
    }

    private abstract class Itr {
        Queue<Node<E>> queue;
        int expectedMod;


        public Itr() {
            this.queue = new LinkedList<>();
            if (root != null) {
                this.queue.add(root);
            }
            expectedMod = modCount;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

        public Node<E> nextNode() {
            if (modCount != expectedMod) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> node = queue.poll();
            if (!node.leaves().isEmpty()) {
                queue.addAll(node.leaves());
            }
            return node;
        }
    }

    private class ValueItr extends Itr implements Iterator<E> {
        public E next() {
            return nextNode().getValue();
        }
    }

    private class NodeItr extends Itr implements Iterator<Node<E>> {
        public Node<E> next() {
            return nextNode();
        }
    }
}
