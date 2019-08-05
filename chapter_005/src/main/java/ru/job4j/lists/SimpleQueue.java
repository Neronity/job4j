package ru.job4j.lists;

public class SimpleQueue<T> {
    private SimpleStack<T> firstStack = new SimpleStack<>();
    private SimpleStack<T> secondStack = new SimpleStack<>();

    public void push(T value) {
        while (firstStack.getSize() > 0) {
            secondStack.push(firstStack.poll());
        }

        firstStack.push(value);

        while (secondStack.getSize() > 0) {
            firstStack.push(secondStack.poll());
        }
    }

    public T poll() {
        return firstStack.poll();
    }
}
