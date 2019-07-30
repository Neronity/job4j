package ru.job4j.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DepartmentGroup {
    private Queue<String> deps;

    public DepartmentGroup(Queue<String> subDeps) {
        this.deps = subDeps;
    }

    public Queue<String> getDeps() {
        return deps;
    }

}
