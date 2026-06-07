package rvt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TodoDB {
    private static final String DB_URL = "jdbc:sqlite:todo.db";

    public TodoDB() {
        initSchema();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void initSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS todo ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "task TEXT NOT NULL"
                + ")";

        try (
                Connection connection = connect();
                Statement statement = connection.createStatement()
        ) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Schema init failed: " + e.getMessage());
        }
    }

    public void add(String task) {
        String sql = "INSERT INTO todo(task) VALUES(?)";

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, task);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Task add failed: " + e.getMessage());
        }
    }

    public ArrayList<TodoItem> findAll() {
        ArrayList<TodoItem> tasks = new ArrayList<>();

        String sql = "SELECT id, task FROM todo ORDER BY id";

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet results = statement.executeQuery()
        ) {
            while (results.next()) {
                int id = results.getInt("id");
                String task = results.getString("task");

                tasks.add(new TodoItem(id, task));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Task loading failed: " + e.getMessage());
        }

        return tasks;
    }

    public void removeById(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Task remove failed: " + e.getMessage());
        }
    }
}