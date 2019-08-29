package ru.job4j.grocery;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {

    protected List<Food> storage = new ArrayList<>();

    @Override
    public List<Food> getContainer() {
        return this.storage;
    }

    @Override
    public void addToContainer(Food food) {
        this.storage.add(food);
    }
}
