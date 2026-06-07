package studentregistration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FileHandler fileHandler = new FileHandler("data/students.csv");
        RegistrationSystem registrationSystem = new RegistrationSystem(fileHandler);

        UserInterface ui = new UserInterface(scanner, registrationSystem);
        ui.start();
    }
}