package parser;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SqlRuReaderTest {
    private SqlRuReader reader = new SqlRuReader();

    @Test
    public void asd() {
        for (int i = 0; i < 15; i++) {
            try {
                reader.readPage();
            } catch (IOException e) {
                fail();
            }
        }
        for (Vacancy v : reader.getVacancies()) {
            System.out.println(v.getName() + " " + v.getDate());
        }
    }
}