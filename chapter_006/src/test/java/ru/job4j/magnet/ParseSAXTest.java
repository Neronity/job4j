package ru.job4j.magnet;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ParseSAXTest {

    @Test
    public void whenParseThenCorrectResult() {
        ParseSAX p = new ParseSAX(new File("src/test/java/ru/job4j/magnet/res/parseSAX_source.xml"));
        assertThat(p.parseXML(), is(10L));
    }

}