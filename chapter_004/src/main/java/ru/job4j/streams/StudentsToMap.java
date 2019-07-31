package ru.job4j.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentsToMap {

    public Map<String, Student> studentsToMap(List<Student> students) {
        return students.stream().collect(Collectors.toMap(
                Student::getLastName,
                student -> student
        ));
    }
}
