package ru.job4j.map;

import java.util.Calendar;

public class User {
    private String name;
    private int children;
    private Calendar birthdate;

    public User(String name, int children, Calendar birthdate) {
        this.name = name;
        this.children = children;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthdate() {
        return birthdate;
    }
}
