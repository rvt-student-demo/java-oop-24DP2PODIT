package productsandcategories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:products_categories.db";

    public DatabaseConnection() {
        initDatabase();
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void initDatabase() {
        String createCategoriesTable = "CREATE TABLE IF NOT EXISTS categories ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL UNIQUE"
                + ")";

        String createProductsTable = "CREATE TABLE IF NOT EXISTS products ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "price REAL NOT NULL, "
                + "category_id INTEGER NOT NULL, "
                + "FOREIGN KEY(category_id) REFERENCES categories(id)"
                + ")";

        try (
                Connection connection = connect();
                Statement statement = connection.createStatement()
        ) {
            statement.execute("PRAGMA foreign_keys = ON");
            statement.execute(createCategoriesTable);
            statement.execute(createProductsTable);
        } catch (SQLException e) {
            System.out.println("Database initialization failed: " + e.getMessage());
        }
    }
}