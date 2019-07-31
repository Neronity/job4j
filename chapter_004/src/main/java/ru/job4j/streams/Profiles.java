package ru.job4j.streams;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {

    public List<Address> collectAddresses(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }
}
