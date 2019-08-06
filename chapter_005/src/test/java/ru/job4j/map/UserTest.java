package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenAddingTwoUsersThenSuccess() {
        Map<User, Object> m = new HashMap<>();
        m.put(new User("1", 1, Calendar.getInstance()), new Object());
        m.put(new User("1", 1, Calendar.getInstance()), new Object());
        System.out.println(m.toString());
    }

}