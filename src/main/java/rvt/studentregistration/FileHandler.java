package studentregistration;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
        createFileIfNeeded();
    }

    private void createFileIfNeeded() {
        try {
            File file = new File(this.filePath);

            File parentFolder = file.getParentFile();
            if (parentFolder != null) {
                parentFolder.mkdirs();
            }

            if (!file.exists()) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                    writer.println("name,surname,email,personalCode,registrationDateTime");
                }
            }
        } catch (Exception e) {
            System.out.println("Kļūda veidojot CSV failu.");
        }
    }

    public ArrayList<Student> readStudents() {
        ArrayList<Student> students = new ArrayList<>();

        try (Scanner reader = new Scanner(Paths.get(this.filePath))) {
            if (reader.hasNextLine()) {
                reader.nextLine();
            }

            while (reader.hasNextLine()) {
                String row = reader.nextLine();
                String[] parts = row.split(",", -1);

                if (parts.length == 5) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    students.add(student);
                }
            }
        } catch (Exception e) {
            System.out.println("Kļūda lasot CSV failu.");
        }

        return students;
    }

    public void saveStudents(ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.filePath))) {
            writer.println("name,surname,email,personalCode,registrationDateTime");

            for (Student student : students) {
                writer.println(student.toCsvLine());
            }
        } catch (Exception e) {
            System.out.println("Kļūda saglabājot CSV failu.");
        }
    }
}