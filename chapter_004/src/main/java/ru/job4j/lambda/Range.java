package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Range {

    public List<Double> diapason(int start, int end, Function<Double, Double> f) {
        List<Double> result  = new ArrayList<>();
        for (double i = start; i < end; i++) {
            result.add(f.apply(i));
        }
        return result;
    }
}
