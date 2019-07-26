package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class UserConvertTest {

    @Test
    public void whenUserListThenUserMap() {
        UserConvert uc = new UserConvert();
        User u1 = new User(1, "a", "b");
        User u2 = new User(2, "c", "d");
        User u3 = new User(3, "e", "f");
        User u4 = new User(4, "g", "h");
        User u5 = new User(5, "i", "j");
        HashMap<Integer, User> result = uc.process(
                Arrays.asList(u1, u2, u3, u4, u5));
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(u1.getId(), u1);
        expect.put(u2.getId(), u2);
        expect.put(u3.getId(), u3);
        expect.put(u4.getId(), u4);
        expect.put(u5.getId(), u5);

        assertThat(result, is(expect));
    }
}
