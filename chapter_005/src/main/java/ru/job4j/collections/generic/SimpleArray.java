package ru.job4j.collections.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int pointer = 0;
    private int size;

    public SimpleArray(int size) {
        this.array = new Object[size];
        this.size = size;
    }

    public void add(T model) {
        if (pointer == size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[pointer++] = model;
    }

    public void set(int index, T model) {
        if (index >= pointer) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = model;
    }

    public void remove(int index) {
        if (index >= pointer) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        pointer--;
    }

    public T get(int index) {
        if (index >= pointer) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < pointer;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[cursor++];
        }
    }
}
