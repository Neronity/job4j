package ru.job4j.collections.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void whenAddingTwoUsersThenSuccess() {
        Map<User, Object> m = new HashMap<>();
        Calendar c = Calendar.getInstance();
        User u1 = new User("1", 1, c);
        User u2 = new User("1", 1, c);
        m.put(u1, new Object());
        m.put(u2, new Object());
        System.out.println(m.toString());
        System.out.println(u1.equals(u2));
    }

    @Test
    public void asdk() {
        int a = 876543;
        System.out.println(a >>> 16);
        System.out.println(a ^ (a >>> 16));
    }

}