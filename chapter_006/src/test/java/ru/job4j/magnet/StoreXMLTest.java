package ru.job4j.magnet;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringJoiner;

import ru.job4j.magnet.StoreXML.Entry;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class StoreXMLTest {

    @Test
    public void whenParseListThenSavedAtFile() {
        String path = "src/test/java/ru/job4j/magnet/res/store_test.xml";
        StoreXML store = new StoreXML(new File(path));
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        store.save(Arrays.asList(
                new Entry(1),
                new Entry(2),
                new Entry(3),
                new Entry(4)
        ));
        try {
            new BufferedReader(new FileReader(new File(path))).lines().forEach(sj::add);
        } catch (FileNotFoundException e) {
            fail();
        }
        assertThat(sj.toString(), is(
                new StringJoiner(System.lineSeparator())
                        .add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                        .add("<entries>")
                        .add("    <values>")
                        .add("        <value>1</value>")
                        .add("    </values>")
                        .add("    <values>")
                        .add("        <value>2</value>")
                        .add("    </values>")
                        .add("    <values>")
                        .add("        <value>3</value>")
                        .add("    </values>")
                        .add("    <values>")
                        .add("        <value>4</value>")
                        .add("    </values>")
                        .add("</entries>")
                        .toString()
        ));
    }
}