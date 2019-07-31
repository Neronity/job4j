package ru.job4j.streams;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfilesTest {
    private Profiles pr = new Profiles();

    @Test
    public void whenProfileListThenAddressList() {
        Address a1 = new Address("Zob", "Arg", 1, 1);
        Address a2 = new Address("Van", "Port", 2, 1);
        Address a3 = new Address("Arb", "Req", 3, 3);
        Address a4 = new Address("Arb", "Req", 3, 3);
        Address a5 = new Address("Flok", "Far", 1, 2);
        Profile p1 = new Profile(a1);
        Profile p2 = new Profile(a2);
        Profile p3 = new Profile(a3);
        Profile p4 = new Profile(a4);
        Profile p5 = new Profile(a5);
        List<Profile> profiles = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5));
        List<Address> result = pr.collectAddresses(profiles);
        assertThat(result, is(new ArrayList<>(Arrays.asList(a3, a5, a2, a1))));
    }
}
