package productsandcategories;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DatabaseConnection database = new DatabaseConnection();
    private static CategoryRepository categoryRepository = new CategoryRepository(database);
    private static ProductRepository productRepository = new ProductRepository(database);

    public static void main(String[] args) {
        while (true) {
            printMenu();

            System.out.print("Choose: ");
            String command = scanner.nextLine();

            if (command.equals("0")) {
                System.out.println("Program stopped.");
                break;
            } else if (command.equals("1")) {
                addCategory();
            } else if (command.equals("2")) {
                addProduct();
            } else if (command.equals("3")) {
                showCategories();
            } else if (command.equals("4")) {
                showProducts();
            } else if (command.equals("5")) {
                searchProductsByCategory();
            } else {
                System.out.println("Unknown command.");
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("1 - Add category");
        System.out.println("2 - Add product");
        System.out.println("3 - Show all categories");
        System.out.println("4 - Show all products");
        System.out.println("5 - Search products by category");
        System.out.println("0 - Exit");
    }

    private static void addCategory() {
        System.out.print("Category name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Category name cannot be empty.");
            return;
        }

        categoryRepository.addCategory(name);
    }

    private static void addProduct() {
        System.out.print("Product name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Product name cannot be empty.");
            return;
        }

        System.out.print("Price: ");
        double price;

        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Price must be a number.");
            return;
        }

        if (price <= 0) {
            System.out.println("Price must be greater than 0.");
            return;
        }

        showCategories();

        System.out.print("Category ID: ");
        int categoryId;

        try {
            categoryId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Category ID must be a number.");
            return;
        }

        if (!categoryRepository.existsById(categoryId)) {
            System.out.println("Category with this ID does not exist.");
            return;
        }

        productRepository.addProduct(name, price, categoryId);
    }

    private static void showCategories() {
        ArrayList<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }

        System.out.println("+----+----------------------+");
        System.out.println("| ID | Category             |");
        System.out.println("+----+----------------------+");

        for (Category category : categories) {
            System.out.printf("| %-2d | %-20s |%n", category.getId(), category.getName());
        }

        System.out.println("+----+----------------------+");
    }

    private static void showProducts() {
        ArrayList<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        printProducts(products);
    }

    private static void searchProductsByCategory() {
        System.out.print("Enter category ID or category name: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Search value cannot be empty.");
            return;
        }

        ArrayList<Product> products;

        try {
            int categoryId = Integer.parseInt(input);
            products = productRepository.findByCategoryId(categoryId);
        } catch (NumberFormatException e) {
            products = productRepository.findByCategoryName(input);
        }

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        printProducts(products);
    }

    private static void printProducts(ArrayList<Product> products) {
        System.out.println("+----+----------------------+----------+----------------------+");
        System.out.println("| ID | Product              | Price    | Category             |");
        System.out.println("+----+----------------------+----------+----------------------+");

        for (Product product : products) {
            System.out.printf(
                    "| %-2d | %-20s | %-8.2f | %-20s |%n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getCategoryName()
            );
        }

        System.out.println("+----+----------------------+----------+----------------------+");
    }
}