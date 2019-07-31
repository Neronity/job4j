package ru.job4j.streams;

public class Student {
    private int score;
    private String lastName;

    public Student(int score) {
        this.score = score;
    }

    public Student(int score, String lastName) {
        this.score = score;
        this.lastName = lastName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLastName() {
        return lastName;
    }
}
