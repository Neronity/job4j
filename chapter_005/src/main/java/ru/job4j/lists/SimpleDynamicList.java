package ru.job4j.lists;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleDynamicList<T> implements Iterable<T> {
    private Object[] array;
    private int pointer = 0;
    private int size;
    private int modCounter = 0;

    public SimpleDynamicList() {
        this.array = new Object[10];
        this.size = 10;
    }

    public void add(T model) {
        if (this.pointer == this.size) {
            grow();
        }
        this.array[pointer++] = model;
    }

    private void grow() {
        this.modCounter++;
        int newSize = this.size + this.size / 2;
        this.array = Arrays.copyOf(this.array, newSize);
        this.size = newSize;
    }

    public T get(int index) {
        if (index >= this.pointer) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr(modCounter);
    }

    private class Itr implements Iterator<T> {
        int cursor = 0;
        int modExpected;

        public Itr(int modExpected) {
            this.modExpected = modExpected;
        }

        @Override
        public boolean hasNext() {
            return this.cursor < pointer && array[this.cursor] != null;
        }

        @Override
        public T next() {
            if (this.modExpected != modCounter) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[cursor++];
        }
    }
}
