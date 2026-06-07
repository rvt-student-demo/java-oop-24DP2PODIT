package productsandcategories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryRepository {
    private DatabaseConnection database;

    public CategoryRepository(DatabaseConnection database) {
        this.database = database;
    }

    public void addCategory(String name) {
        String sql = "INSERT INTO categories(name) VALUES(?)";

        try (
                Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, name);
            statement.executeUpdate();

            System.out.println("Category added.");
        } catch (SQLException e) {
            System.out.println("Could not add category: " + e.getMessage());
        }
    }

    public ArrayList<Category> findAll() {
        ArrayList<Category> categories = new ArrayList<>();

        String sql = "SELECT id, name FROM categories ORDER BY id";

        try (
                Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet results = statement.executeQuery()
        ) {
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");

                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            System.out.println("Could not load categories: " + e.getMessage());
        }

        return categories;
    }

    public boolean existsById(int id) {
        String sql = "SELECT id FROM categories WHERE id = ?";

        try (
                Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);

            try (ResultSet results = statement.executeQuery()) {
                return results.next();
            }
        } catch (SQLException e) {
            System.out.println("Could not check category: " + e.getMessage());
        }

        return false;
    }
}