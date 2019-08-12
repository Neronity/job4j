package ru.job4j.io.unavailable;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class AnalizeTest {
    private Analize a = new Analize();
    private String target = "src/test/java/ru/job4j/io/unavailable/unavailable.csv";
    private String source = "src/test/java/ru/job4j/io/unavailable/logs.log";

    @Test
    public void whenAnalizeLogsThenCorrectOutput() {
        a.unavailable(source, target);
        List<String> result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            result = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(result, is(Arrays.asList("10:58:01;10:59:01", "11:01:02;11:02:02")));
    }
}