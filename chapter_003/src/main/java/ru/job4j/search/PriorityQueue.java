package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    public LinkedList<Task> getTasks() {
        return tasks;
    }

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     *
     * @param task задача
     */
    public void put(Task task) {
        int size = tasks.size();
        for (int index = 0; index < size; index++) {
            if (task.getPriority() <= tasks.get(index).getPriority() || index == size - 1) {
                tasks.add(index, task);
                break;
            }
        }
        if (size == 0) {
            tasks.add(0, task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}