package ru.job4j.collections.map;

import java.util.Arrays;

class People {
    String name;
    long id;
    int age;
    float weight;
    boolean isMember;
    double credits;
    String[] interests;

    public People(String name, long id, int age, float weight, boolean isMember, double credits, String[] interests) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.isMember = isMember;
        this.credits = credits;
        this.interests = interests;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + name.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + age;
        result = 31 * result + Float.floatToIntBits(weight);
        result = 31 * result + (isMember ? 1 : 0);
        long creditsLong = Double.doubleToLongBits(credits);
        result = 31 * result + (int) (creditsLong ^ (creditsLong >>> 32));
        result = 31 * result + Arrays.hashCode(interests);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof People)) {
            return false;
        }

        People p = (People) obj;
        return this.id == p.id
                && this.name.equals(p.name)
                && this.age == p.age
                && this.weight == p.weight
                && this.isMember == p.isMember
                && this.credits == p.credits
                && Arrays.equals(this.interests, p.interests);
    }
}