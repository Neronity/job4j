package ru.job4j.streams;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SchoolTest {
    School school = new School();

    @Test
    public void whenClassAThenSuccess() {
        Student s1 = new Student(98);
        Student s2 = new Student(50);
        Student s3 = new Student(23);
        Student s4 = new Student(12);
        Student s5 = new Student(14);
        Student s6 = new Student(71);
        List<Student> students = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5, s6));
        List<Student> result = school.collect(students,
                student -> student.getScore() >= 70 && student.getScore() <= 100);
        assertThat(result, is(new ArrayList<>(Arrays.asList(s1, s6))));
    }

    @Test
    public void whenClassBThenSuccess() {
        Student s1 = new Student(98);
        Student s2 = new Student(50);
        Student s3 = new Student(65);
        Student s4 = new Student(49);
        Student s5 = new Student(53);
        Student s6 = new Student(71);
        List<Student> students = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5, s6));
        List<Student> result = school.collect(students,
                student -> student.getScore() >= 50 && student.getScore() < 70);
        assertThat(result, is(new ArrayList<>(Arrays.asList(s2, s3, s5))));
    }

    @Test
    public void whenClassVThenSuccess() {
        Student s1 = new Student(98);
        Student s2 = new Student(50);
        Student s3 = new Student(65);
        Student s4 = new Student(49);
        Student s5 = new Student(53);
        Student s6 = new Student(1);
        List<Student> students = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5, s6));
        List<Student> result = school.collect(students,
                student -> student.getScore() > 0 && student.getScore() < 50);
        assertThat(result, is(new ArrayList<>(Arrays.asList(s4, s6))));
    }
}
