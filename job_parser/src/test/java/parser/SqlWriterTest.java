package parser;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Properties;

import static org.junit.Assert.*;

public class SqlWriterTest {

    @Test
    public void checkConnection() {
        Properties p = new Properties();
        Connection c = null;
        try (InputStream in = SqlWriter.class.getClassLoader().getResourceAsStream("test.properties")) {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            c = DriverManager.getConnection(p.getProperty("url"),
                    p.getProperty("username"),
                    p.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SqlWriter s = new SqlWriter(Arrays.asList(
                new Vacancy("1", "2", "3", Timestamp.valueOf(LocalDateTime.now())),
                new Vacancy("2", "3", "4", Timestamp.valueOf(LocalDateTime.now()))), c);
        s.writeData();
    }

}