package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class SqlWriter implements AutoCloseable {

    private final List<Vacancy> vacancies;
    private final Logger LOG = LogManager.getLogger();
    private Connection connection;

    public SqlWriter(List<Vacancy> vacancies, Connection connection) {
        this.vacancies = vacancies;
        this.connection = connection;
    }

    public boolean writeData() {
        boolean result = false;
        try (PreparedStatement st = this.connection.prepareStatement(
                     "INSERT INTO vacancies (name, descr, link) VALUES (?, ?, ?) ON CONFLICT DO NOTHING;")) {
            for (Vacancy v : vacancies) {
                st.setString(1, v.getName());
                st.setString(2, v.getDesc());
                st.setString(3, v.getUrl());
                st.addBatch();
            }
            st.executeBatch();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
