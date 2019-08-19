package ru.job4j.magnet;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConvertXSQTTest {

    @Test
    public void whenParseXmlThenCorrectXsl() {
        String path = "src/test/java/ru/job4j/magnet/res/";
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        ConvertXSQT conv = new ConvertXSQT(
                new File(path + "convert_source.xml"),
                new File(path + "convert_dest.xml"),
                new File("src/main/java/ru/job4j/magnet/default_scheme.xsl")
        );
        conv.convert();
        try {
            new BufferedReader(new FileReader(path + "convert_dest.xml")).lines().forEach(sj::add);
        } catch (FileNotFoundException e) {
            fail();
        }
        assertThat(sj.toString(), is(new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<entries>")
                .append("<entry field=\"1\"/>")
                .append("<entry field=\"2\"/>")
                .append("<entry field=\"3\"/>")
                .append("<entry field=\"4\"/>")
                .append("</entries>")
                .toString()
        ));
    }
}