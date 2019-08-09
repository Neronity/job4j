package ru.job4j.java19;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CertifyStudents {

    public List<Student> levelOf(List<Student> students, int bound) {
        return students
                .stream()
                .sorted((o1, o2) -> {
                    if (o1 == null) {
                        return (o2 == null) ? 0 : -1;
                    }
                    if (o2 == null) {
                        return 1;
                    }
                    return o2.getScope() - o1.getScope();
                })
                .flatMap(Stream::ofNullable)
                .takeWhile(e -> e.getScope() > bound)
                .collect(Collectors.toList());
    }
}
