package ru.job4j.diff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Diff {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        List<User> tmp = new ArrayList<>(previous);
        tmp.removeAll(current);
        info.deleted = tmp.size();
        info.added = current.size() - (previous.size() - info.deleted);
        for (User u : previous) {
            for (User c : current) {
                if (u.id == c.id && !u.name.equals(c.name)) {
                    info.changed++;
                }
            }
        }
        return info;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }


    public static class Info {
        int added;
        int changed;
        int deleted;
    }

}
