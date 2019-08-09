package ru.job4j.collections.lists;

public class LoopChecker {
    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public boolean hasLoop(Node first) {
        boolean result = false;
        Node firstPointer = first;
        Node secondPointer = first;
        while (firstPointer != null && secondPointer != null && secondPointer.next != null) {
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next.next;
            if (secondPointer == firstPointer) {
                result = true;
                break;
            }
        }
        return result;
    }
}
