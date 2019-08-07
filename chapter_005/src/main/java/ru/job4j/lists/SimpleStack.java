package ru.job4j.lists;

public class SimpleStack<T> {

    SimpleLinkedList<T> linkedList = new SimpleLinkedList<>();

    public void push(T value) {
        linkedList.add(value);
    }

    public T poll() {
        return linkedList.removeFirst();
    }
}
