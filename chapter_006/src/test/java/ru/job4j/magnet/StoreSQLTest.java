package ru.job4j.magnet;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ru.job4j.magnet.StoreXML.Entry;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class StoreSQLTest {
    private Config conf = new Config();
    private String conUrl = String.format("%s%s", this.conf.get("url"), "test.db");

    @Test
    public void whenGenerateThenCorrectTable() {
        try (StoreSQL sql = new StoreSQL(this.conf)) {
            sql.generate(10);
        } catch (Exception e) {
            fail();
        }
        try (Connection con = DriverManager.getConnection(this.conUrl);
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM entries;");
            List<Long> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getLong("value"));
            }
            assertThat(result, is(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L)));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void whenLoadThenCorrectList() {
        try (StoreSQL sql = new StoreSQL(this.conf)) {
            sql.generate(10);
            List<Long> result = sql.load().stream().map(Entry::getValue).collect(Collectors.toList());
            assertThat(result, is(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L)));
        } catch (Exception e) {
            fail();
        }
    }

}