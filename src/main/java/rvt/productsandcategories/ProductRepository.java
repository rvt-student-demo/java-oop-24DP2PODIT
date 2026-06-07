package productsandcategories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepository {
    private DatabaseConnection database;

    public ProductRepository(DatabaseConnection database) {
        this.database = database;
    }

    public void addProduct(String name, double price, int categoryId) {
        String sql = "INSERT INTO products(name, price, category_id) VALUES(?, ?, ?)";

        try (
                Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setInt(3, categoryId);
            statement.executeUpdate();

            System.out.println("Product added.");
        } catch (SQLException e) {
            System.out.println("Could not add product: " + e.getMessage());
        }
    }

    public ArrayList<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT products.id, products.name, products.price, "
                + "products.category_id, categories.name AS category_name "
                + "FROM products "
                + "JOIN categories ON products.category_id = categories.id "
                + "ORDER BY products.id";

        try (
                Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet results = statement.executeQuery()
        ) {
            while (results.next()) {
                products.add(new Product(
                        results.getInt("id"),
                        results.getString("name"),
                        results.getDouble("price"),
                        results.getInt("category_id"),
                        results.getString("category_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Could not load products: " + e.getMessage());
        }

        return products;
    }

    public ArrayList<Product> findByCategoryId(int categoryId) {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT products.id, products.name, products.price, "
                + "products.category_id, categories.name AS category_name "
                + "FROM products "
                + "JOIN categories ON products.category_id = categories.id "
                + "WHERE categories.id = ? "
                + "ORDER BY products.id";

        try (
                Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, categoryId);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    products.add(new Product(
                            results.getInt("id"),
                            results.getString("name"),
                            results.getDouble("price"),
                            results.getInt("category_id"),
                            results.getString("category_name")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Could not search products: " + e.getMessage());
        }

        return products;
    }

    public ArrayList<Product> findByCategoryName(String categoryName) {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT products.id, products.name, products.price, "
                + "products.category_id, categories.name AS category_name "
                + "FROM products "
                + "JOIN categories ON products.category_id = categories.id "
                + "WHERE categories.name LIKE ? "
                + "ORDER BY products.id";

        try (
                Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, "%" + categoryName + "%");

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    products.add(new Product(
                            results.getInt("id"),
                            results.getString("name"),
                            results.getDouble("price"),
                            results.getInt("category_id"),
                            results.getString("category_name")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Could not search products: " + e.getMessage());
        }

        return products;
    }
}