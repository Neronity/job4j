package ru.job4j.magnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StoreSQL implements AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(Config.class.getName());
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void generate(int size) {
        createDataBase();
        try {
            createTable();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        clearTable();
        try (PreparedStatement st = this.connect.prepareStatement("INSERT INTO entries (value) VALUES (?);")) {
            for (int i = 1; i <= size; i++) {
                st.setInt(1, i);
                st.executeUpdate();
            }
            this.connect.commit();
        } catch (SQLException e) {
            try {
                this.connect.rollback();
            } catch (SQLException ex) {
                LOG.error(e.getMessage(), e);
            }
            LOG.error(e.getMessage(), e);
        }
    }

    public List<StoreXML.Entry> load() {
        List<StoreXML.Entry> result = new ArrayList<>();
        try (Statement st = this.connect.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM entries;");
            while (rs.next()) {
                result.add(new StoreXML.Entry(rs.getInt("value")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (this.connect != null) {
            this.connect.close();
        }
    }

    private void createDataBase() {
        String conUrl = String.format("%s%s", this.config.get("url"), "test.db");
        try {
            this.connect = DriverManager.getConnection(conUrl);
            this.connect.setAutoCommit(false);
            DatabaseMetaData meta = this.connect.getMetaData();
            LOG.info("Database created");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createTable() throws SQLException {
        try (Statement st = this.connect.createStatement()) {
            ResultSet rs = st.executeQuery(
                    "SELECT name FROM sqlite_master WHERE type='table' AND name='table_name';"
            );
            if (rs.next()) {
                if (rs.getInt("name") == 0) {
                    st.execute("CREATE TABLE entries (value BIGINT);");
                    this.connect.commit();
                }
            }
        } catch (SQLException e) {
            this.connect.rollback();
            LOG.error(e.getMessage(), e, e.getSQLState(), e.getErrorCode());
        }
    }

    private void clearTable() {
        try (Statement st = this.connect.createStatement()) {
            st.execute("DELETE FROM entries;");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
