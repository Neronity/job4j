package ru.job4j.sort;

import java.util.*;

public class SortUser {

    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }


    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, (new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        }));
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, (new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result != 0 ? result : o1.getAge() - o2.getAge();
            }
        }));
        return list;
    }
}
