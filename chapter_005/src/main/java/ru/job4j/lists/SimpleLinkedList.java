package ru.job4j.lists;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private int size = 0;
    private int modCounter = 0;

    public int getSize() {
        return size;
    }

    public void add(T data) {
        if (first != null) {
            this.first = new Node<>(data, this.first);
        } else {
            first = new Node<>(data, null);
        }
        size++;
        modCounter++;
    }

    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    public void removeFirst() {
        if (size > 0) {
            this.first = this.first.next;
            size--;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private int modExpected = modCounter;
        private int counter = 0;
        private Node<T> next = first;

        @Override
        public boolean hasNext() {
            return this.counter < size;
        }

        @Override
        public T next() {
            if (this.modExpected != modCounter) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> result = next;
            next = next.next;
            counter++;
            return result.data;
        }
    }

    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
