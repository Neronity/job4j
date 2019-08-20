package ru.job4j.tracker;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.principle_003.ExJob;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {

    private final Connection connection;
    private static final Logger LOG = LogManager.getLogger(ExJob.class.getName());

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    public boolean init() {
        return this.connection != null;
    }


    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public String add(Item item) {
        String sqlInsert = "INSERT INTO items (i_name, i_desc) VALUES (?, ?);";
        try (PreparedStatement st = this.connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                item.setId(String.valueOf(rs.getInt(1)));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item.getId();
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        String sqlReplace = "UPDATE items SET i_name = ?, i_desc = ? WHERE id = ?;";
        try (PreparedStatement st = this.connection.prepareStatement(sqlReplace)) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setInt(3, Integer.parseInt(id));
            result = st.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        if (result) item.setId(id);
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (id.matches("^(\\d+)$")) {
            String sqlDelete = "DELETE FROM items WHERE id = ?";
            try (PreparedStatement st = this.connection.prepareStatement(sqlDelete)) {
                st.setInt(1, Integer.parseInt(id));
                result = st.executeUpdate() != 0;
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    @Override
    public List<Item> getAll() {
        String sqlGetAll = "SELECT * FROM items;";
        List<Item> result = new ArrayList<>();
        try (Statement st = this.connection.createStatement()) {
            ResultSet rs = st.executeQuery(sqlGetAll);
            while (rs.next()) {
                result.add(new Item(
                        rs.getString("i_name"),
                        rs.getString("i_desc"),
                        String.valueOf(rs.getInt("id")
                        )));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        String sqlGet = "SELECT * FROM items WHERE i_name = ?;";
        List<Item> result = new ArrayList<>();
        try (PreparedStatement st = this.connection.prepareStatement(sqlGet)) {
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Item(
                        rs.getString("i_name"),
                        rs.getString("i_desc"),
                        String.valueOf(rs.getInt("id")
                        )));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    @Override
    public Item findById(String id) {
        String sqlGet = "SELECT * FROM items WHERE id = ?;";
        Item result = null;
        if (id.matches("^(\\d+)$")) {
            try (PreparedStatement st = this.connection.prepareStatement(sqlGet)) {
                st.setInt(1, Integer.parseInt(id));
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    result = new Item(
                            rs.getString("i_name"),
                            rs.getString("i_desc"),
                            String.valueOf(rs.getInt("id")
                            ));
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    boolean resetDb() {
        boolean result = false;
        try (Statement st = this.connection.createStatement()) {
            st.execute("TRUNCATE TABLE items;");
            st.execute("ALTER SEQUENCE items_id_seq RESTART WITH 1;");
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

}
