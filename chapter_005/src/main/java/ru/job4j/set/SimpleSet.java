package ru.job4j.set;

import ru.job4j.lists.SimpleDynamicList;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {

    private SimpleDynamicList<T> list;

    public SimpleSet() {
        this.list = new SimpleDynamicList<>();
    }

    public void add(T value) {
        if (!this.contains(value)) {
            list.add(value);
        }
    }

    private boolean contains(T value) {
        boolean result = false;
        for (T elem : list) {
            if (elem.equals(value)) {
                result = true;
            }
        }
        return result;
    }


    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}