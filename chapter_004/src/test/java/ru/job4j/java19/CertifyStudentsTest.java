package ru.job4j.java19;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CertifyStudentsTest {
    CertifyStudents cs = new CertifyStudents();

    @Test
    public void whenStudentsListThenCertifiedStudentsList() {
        Student s0 = null;
        Student s1 = new Student("Ivan", 53);
        Student s2 = new Student("Olga", 12);
        Student s3 = new Student("Nikolay", 68);
        Student s4 = new Student("Roman", 37);
        Student s5 = new Student("Svetlana", 99);
        Student s6 = new Student("Mariya", 21);
        Student s7 = new Student("Sergey", 43);
        Student s8 = null;
        List<Student> result = cs.levelOf(Arrays.asList(s0, s1, s2, s3, s4, s5, s6, s7, s8), 50);
        List<Student> expect = List.of(s5, s3, s1);
        assertThat(result, is(expect));
    }
}
