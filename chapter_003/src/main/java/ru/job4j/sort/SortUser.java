package ru.job4j.sort;

import java.util.*;

public class SortUser {

    public  Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.age - o2.age;
            }
        });
        result.addAll(list);
        return result;
    }

}
