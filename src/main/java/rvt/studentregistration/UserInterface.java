package studentregistration;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private RegistrationSystem system;

    public UserInterface(Scanner scanner, RegistrationSystem system) {
        this.scanner = scanner;
        this.system = system;
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("Izvēlies darbību:");
            System.out.println("register - reģistrēt jaunu studentu");
            System.out.println("show - rādīt visus studentus");
            System.out.println("remove - dzēst studentu pēc personas koda");
            System.out.println("edit - rediģēt studentu pēc personas koda");
            System.out.println("exit - apturēt programmu");
            System.out.print("Darbība: ");

            String command = scanner.nextLine();

            if (command.equals("exit")) {
                System.out.println("Programma apturēta.");
                break;
            } else if (command.equals("register")) {
                register();
            } else if (command.equals("show")) {
                system.showStudents();
            } else if (command.equals("remove")) {
                remove();
            } else if (command.equals("edit")) {
                edit();
            } else {
                System.out.println("Nezināma komanda.");
            }
        }
    }

    private void register() {
        System.out.print("Vārds: ");
        String name = scanner.nextLine();

        System.out.print("Uzvārds: ");
        String surname = scanner.nextLine();

        System.out.print("E-pasts: ");
        String email = scanner.nextLine();

        System.out.print("Personas kods: ");
        String personalCode = scanner.nextLine();

        system.registerStudent(name, surname, email, personalCode);
    }

    private void remove() {
        System.out.print("Ievadi personas kodu: ");
        String personalCode = scanner.nextLine();

        system.removeStudent(personalCode);
    }

    private void edit() {
        System.out.print("Ievadi personas kodu: ");
        String personalCode = scanner.nextLine();

        System.out.print("Jaunais vārds: ");
        String name = scanner.nextLine();

        System.out.print("Jaunais uzvārds: ");
        String surname = scanner.nextLine();

        System.out.print("Jaunais e-pasts: ");
        String email = scanner.nextLine();

        system.editStudent(personalCode, name, surname, email);
    }
}