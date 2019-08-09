package ru.job4j.collections.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumberIterator implements Iterator {

    private int[] array;
    private int lastEvenNumberIndex;
    private int index;

    public EvenNumberIterator(int[] array) {
        this.array = array;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] % 2 == 0) {
                lastEvenNumberIndex = i;
                break;
            }
        }
        for (int i = 0; i <= lastEvenNumberIndex; i++) {
            if (array[i] % 2 == 0) {
                index = i;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return index <= lastEvenNumberIndex && array[index] % 2 == 0;
    }

    @Override
    public Object next() {
        if (index > lastEvenNumberIndex) {
            throw new NoSuchElementException();
        }
        int result = array[index++];
        while (index <= lastEvenNumberIndex && array[index] % 2 != 0) {
            index++;
        }
        return result;
    }

}
