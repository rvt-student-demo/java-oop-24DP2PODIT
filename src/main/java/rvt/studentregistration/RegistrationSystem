package studentregistration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RegistrationSystem {
    private ArrayList<Student> students;
    private FileHandler fileHandler;

    public RegistrationSystem(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.students = this.fileHandler.readStudents();
    }

    public boolean emailExists(String email) {
        for (Student student : this.students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    public boolean personalCodeExists(String personalCode) {
        for (Student student : this.students) {
            if (student.getPersonalCode().equals(personalCode)) {
                return true;
            }
        }

        return false;
    }

    public boolean registerStudent(String name, String surname, String email, String personalCode) {
        if (!Validator.isValidName(name)) {
            System.out.println("Kļūda: vārds drīkst saturēt tikai burtus un tam jābūt vismaz 3 simbolus garam.");
            return false;
        }

        if (!Validator.isValidName(surname)) {
            System.out.println("Kļūda: uzvārds drīkst saturēt tikai burtus un tam jābūt vismaz 3 simbolus garam.");
            return false;
        }

        if (!Validator.isValidEmail(email)) {
            System.out.println("Kļūda: nepareizs e-pasta formāts.");
            return false;
        }

        if (!Validator.isValidPersonalCode(personalCode)) {
            System.out.println("Kļūda: personas kodam jābūt formātā 123456-12345.");
            return false;
        }

        if (emailExists(email)) {
            System.out.println("Kļūda: šāds e-pasts jau eksistē.");
            return false;
        }

        if (personalCodeExists(personalCode)) {
            System.out.println("Kļūda: šāds personas kods jau eksistē.");
            return false;
        }

        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Student student = new Student(name, surname, email, personalCode, dateTime);
        this.students.add(student);
        this.fileHandler.saveStudents(this.students);

        System.out.println("Students veiksmīgi reģistrēts.");
        return true;
    }

    public void showStudents() {
        if (this.students.isEmpty()) {
            System.out.println("Nav reģistrētu studentu.");
            return;
        }

        printTable();
    }

    private void printTable() {
        System.out.println("+----------------+----------------+---------------------------+---------------+---------------------+");
        System.out.println("| Vārds          | Uzvārds        | E-pasts                   | Personas kods | Reģistrācija        |");
        System.out.println("+----------------+----------------+---------------------------+---------------+---------------------+");

        for (Student student : this.students) {
            System.out.printf(
                    "| %-14s | %-14s | %-25s | %-13s | %-19s |%n",
                    student.getName(),
                    student.getSurname(),
                    student.getEmail(),
                    student.getPersonalCode(),
                    student.getRegistrationDateTime()
            );
        }

        System.out.println("+----------------+----------------+---------------------------+---------------+---------------------+");
    }

    public boolean removeStudent(String personalCode) {
        for (int i = 0; i < this.students.size(); i++) {
            if (this.students.get(i).getPersonalCode().equals(personalCode)) {
                this.students.remove(i);
                this.fileHandler.saveStudents(this.students);
                System.out.println("Students izdzēsts.");
                return true;
            }
        }

        System.out.println("Students ar šādu personas kodu nav atrasts.");
        return false;
    }

    public boolean editStudent(String personalCode, String newName, String newSurname, String newEmail) {
        for (Student student : this.students) {
            if (student.getPersonalCode().equals(personalCode)) {
                if (!Validator.isValidName(newName)) {
                    System.out.println("Kļūda: nepareizs vārds.");
                    return false;
                }

                if (!Validator.isValidName(newSurname)) {
                    System.out.println("Kļūda: nepareizs uzvārds.");
                    return false;
                }

                if (!Validator.isValidEmail(newEmail)) {
                    System.out.println("Kļūda: nepareizs e-pasts.");
                    return false;
                }

                for (Student other : this.students) {
                    if (!other.getPersonalCode().equals(personalCode) && other.getEmail().equalsIgnoreCase(newEmail)) {
                        System.out.println("Kļūda: šāds e-pasts jau eksistē.");
                        return false;
                    }
                }

                student.setName(newName);
                student.setSurname(newSurname);
                student.setEmail(newEmail);

                this.fileHandler.saveStudents(this.students);
                System.out.println("Studenta dati atjaunoti.");
                return true;
            }
        }

        System.out.println("Students ar šādu personas kodu nav atrasts.");
        return false;
    }
}