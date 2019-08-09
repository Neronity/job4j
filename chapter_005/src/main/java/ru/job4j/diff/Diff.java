package ru.job4j.diff;

import javax.naming.Name;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Diff {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        List<User> tmp = new ArrayList<>(previous);
        tmp.removeAll(current);
        info.deleted = tmp.size();
        info.added = current.size() - (previous.size() - info.deleted);
        Map<Integer, String> currentMap = current
                .stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User u : previous) {
            String currentName = currentMap.get(u.id);
            if (currentName != null && !u.name.equals(currentName)) {
                    info.changed++;
            }
        }
        return info;
    }

    public static class User {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

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
