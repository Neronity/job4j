package ru.job4j.lists;

public class SimpleStack<T> extends SimpleLinkedList<T> {

    public void push(T value) {
        add(value);
    }

    public T poll() {
        T result = get(0);
        removeFirst();
        return result;
    }
}
