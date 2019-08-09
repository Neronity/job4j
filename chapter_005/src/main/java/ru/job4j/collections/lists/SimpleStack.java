package ru.job4j.collections.lists;

public class SimpleStack<T> {

    private SimpleLinkedList<T> linkedList = new SimpleLinkedList<>();

    public void push(T value) {
        linkedList.add(value);
    }

    public T poll() {
        return linkedList.removeFirst();
    }

    public int getSize() {
        return linkedList.getSize();
    }
}
