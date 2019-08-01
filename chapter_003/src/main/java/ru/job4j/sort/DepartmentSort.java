package ru.job4j.sort;

import java.util.*;
import java.util.stream.Collectors;

public class DepartmentSort {
    private Set<String> departments = new TreeSet<>();

    private static List<String> getAllMainDepartments(String[] e) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < e.length; i++) {
            StringJoiner sj = new StringJoiner("\\");
            for (int j = 0; j <= i; j++) {
                sj.add(e[j]);
            }
            result.add(sj.toString());
        }
        return result;
    }

    public Set<String> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<String> departments) {
        this.departments = departments;
        departments.addAll(departments
                .stream()
                .map(e -> e.split("\\\\"))
                .distinct()
                .map(DepartmentSort::getAllMainDepartments)
                .flatMap(List::stream)
                .collect(Collectors.toList()));

    }

    public void ascSort() {
        Set<String> s = new TreeSet<>(Comparator.comparing((String o) -> o.split("\\\\")[0]).thenComparing(o -> o));
        s.addAll(departments);
        departments = s;
    }

    public void descSort() {
        Set<String> s = new TreeSet<>((o1, o2) -> {
            int mainGroupResult = o2.split("\\\\")[0].compareTo(o1.split("\\\\")[0]);
            return mainGroupResult != 0 ? mainGroupResult : o1.compareTo(o2);
        });
        s.addAll(departments);
        departments = s;
    }

}
