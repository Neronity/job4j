package ru.job4j.io.files;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class SearchType {

    List<File> applyFilter(List<File> list, Predicate<File> p) {
        return list
                .stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    abstract boolean filter(File f);

}
