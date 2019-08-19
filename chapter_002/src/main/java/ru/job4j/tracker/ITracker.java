package ru.job4j.tracker;

import java.util.List;

public interface ITracker {
    String add(Item item);

    boolean replace(String id, Item item);

    boolean delete(String id);

    List<Item> getAll();

    List<Item> findByName(String key);

    Item findById(String id);
}
