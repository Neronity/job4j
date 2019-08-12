package ru.job4j.io.config;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    private Config conf = new Config("/Users/clutterfunk/Documents/GitHub"
            + "/job4j/chapter_005/src/test/java/ru/job4j/io/config/app.properties");

    @Test
    public void whenLoadFileThenMapFilled() {
        conf.load();
        assertThat(conf.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(conf.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(conf.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(conf.value("hibernate.connection.username"), is("postgres"));
        assertThat(conf.value("hibernate.connection.password"), is("password"));
        assertThat(conf.valuesSize(), is(5));
    }
}