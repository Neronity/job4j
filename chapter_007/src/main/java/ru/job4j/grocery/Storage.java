package ru.job4j.grocery;

import java.util.ArrayList;
import java.util.List;

public interface Storage {

    List<Food> getContainer();

    void addToContainer(Food food);
}
