package ru.job4j.lists;

public class SimpleQueue<T> {
    private SimpleStack<T> firstStack = new SimpleStack<>();
    private SimpleStack<T> secondStack = new SimpleStack<>();

    public void push(T value) {
        firstStack.push(value);
    }

    public T poll() {
        if (secondStack.getSize() == 0) {
            while (firstStack.getSize() > 0) {
                secondStack.push(firstStack.poll());
            }
        }
        return secondStack.poll();
    }
}
