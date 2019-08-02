package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    UserStore us = new UserStore();
    User u1 = new User("1");
    User u2 = new User("2");
    User u3 = new User("3");
    User u4 = new User("4");
    User u5 = new User("5");
    User u6 = new User("6");
    User u7 = new User("7");

    @Test
    public void whenFindingUsersThenSuccess() {
        us.add(u1);
        assertThat(us.findById("1"), is(u1));
    }

    @Test
    public void whenRemoveUserThenSuccess() {
        us.add(u1);
        us.add(u2);
        us.add(u3);
        us.delete("2");
        assertThat(us.indexOf("3"), is(1));
    }

    @Test
    public void whenReplacingUsersThenSuccess() {
        us.add(u1);
        us.add(u2);
        us.add(u3);
        us.replace("3", u4);
        assertThat(us.indexOf("4"), is(2));
    }
}