package rvt;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TodoList {
    private ArrayList<String> tasks;
    private final String filePath = "data/todo.csv";

    public TodoList() {
        this.tasks = new ArrayList<>();
        this.loadFromFile();
    }

    private void loadFromFile() {
        try (Scanner fileReader = new Scanner(Paths.get(filePath))) {
            if (fileReader.hasNextLine()) {
                fileReader.nextLine(); // izlaižam pirmo rindu: id,task
            }

            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                String[] parts = row.split(",", 2);

                if (parts.length == 2) {
                    this.tasks.add(parts[1]);
                }
            }
        } catch (Exception e) {
            System.out.println("Could not read file.");
        }
    }

    private int getLastId() {
        return this.tasks.size();
    }

    private boolean updateFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("id,task");

            for (int i = 0; i < this.tasks.size(); i++) {
                writer.println((i + 1) + "," + this.tasks.get(i));
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkEventString(String value) {
        if (value.length() < 3) {
            return false;
        }

        return value.matches("[a-zA-Z0-9 ]+");
    }

    public void add(String task) {
        if (!checkEventString(task)) {
            System.out.println("Task must contain only letters, numbers and spaces.");
            System.out.println("Task length must be at least 3 characters.");
            return;
        }

        this.tasks.add(task);
        this.updateFile();
    }

    public void print() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ": " + this.tasks.get(i));
        }
    }

    public void remove(int number) {
        this.tasks.remove(number - 1);
        this.updateFile();
    }
}