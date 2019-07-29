package ru.job4j.sort;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SortUserTest {

    @Test
    public void WhenUserListThenSortedUserSet() {
        User u1 = new User(4, "ivan");
        User u2 = new User(2, "viktor");
        User u3 = new User(3, "artem");
        User u4 = new User(1, "nikolay");
        List<User> list = new ArrayList<>(Arrays.asList(
                u1,
                u2,
                u3,
                u4
        ));

        List<User> result = new ArrayList<>(new SortUser().sort(list));
        List<User> expect = new ArrayList<>(
                Arrays.asList(
                        u4,
                        u2,
                        u3,
                        u1
        ));
        assertThat(result, is(expect));
    }

}
