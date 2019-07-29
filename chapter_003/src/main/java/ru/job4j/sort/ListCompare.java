package ru.job4j.sort;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int result = 0;
        for (int idx = 0; idx < left.length() && idx < right.length(); idx++) {
            result = Character.compare(left.charAt(idx), right.charAt(idx));
            if (result != 0) break;
        }
        return result != 0 ? result : Integer.compare(left.length(), right.length());
    }
}
