package ru.job4j.io.search;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Search {

    public List<File> files(String parent, List<String> exts) {
        return filterExtensions(searchAll(parent), exts::contains);
    }

    public List<File> searchAll(String parent) {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.add(new File(parent));
        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory()) {
                File[] a = file.listFiles();
                if (a != null) {
                    queue.addAll(Arrays.asList(a));
                }
            } else {
                result.add(file);
            }
        }
        return result;
    }

    public List<File> filterExtensions(List<File> list, Predicate<String> p) {
        return list
                .stream()
                .filter(e -> {
                    String[] s = e.getName().split("\\.");
                    return p.test(s[s.length - 1]);
                })
                .collect(Collectors.toList());
    }
}
