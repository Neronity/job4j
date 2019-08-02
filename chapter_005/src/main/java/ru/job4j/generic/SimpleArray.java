package ru.job4j.generic;

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
        if (index >= size || array[index] == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = model;
    }

    public void remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        pointer--;
    }

    public T get(int index) {
        if (index >= size || array[index] == null) {
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
            return cursor < size && array[cursor] != null;
        }

        @Override
        public T next() {
            if (cursor >= size || array[cursor] == null) {
                throw new NoSuchElementException();
            }
            return (T) array[cursor++];
        }
    }
}
