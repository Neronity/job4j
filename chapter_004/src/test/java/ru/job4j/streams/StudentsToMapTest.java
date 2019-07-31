package ru.job4j.streams;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StudentsToMapTest {
    private StudentsToMap st = new StudentsToMap();

    @Test
    public void whenStudentListThenStudentMap() {
        Student s1 = new Student(98, "Ivanov");
        Student s2 = new Student(50, "Petrov");
        Student s3 = new Student(23, "Tokareva");
        Student s4 = new Student(12, "Vorontsov");
        Student s5 = new Student(14, "Borisov");
        Student s6 = new Student(71, "Teryaeva");
        Map<String, Student> result = st.studentsToMap(new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5, s6)));
        Map<String, Student> expect = new HashMap<>();
        expect.put("Ivanov", s1);
        expect.put("Petrov", s2);
        expect.put("Tokareva", s3);
        expect.put("Vorontsov", s4);
        expect.put("Borisov", s5);
        expect.put("Teryaeva", s6);
        assertThat(result, is(expect));
    }
}
