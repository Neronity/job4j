package ru.job4j.io.search;

import java.io.File;
import java.util.*;

public class Search {

    public List<File> files(String parent, List<String> exts) {
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
                String[] name = file.getName().split("\\.");
                if (exts.contains(name[name.length - 1])) {
                    result.add(file);
                }
            }
        }
        return result;
    }
}
