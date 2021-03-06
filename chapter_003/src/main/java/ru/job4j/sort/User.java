package ru.job4j.sort;

public class User implements Comparable {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        User u = (User) o;
        return Integer.compare(this.age, u.getAge());
    }
}
