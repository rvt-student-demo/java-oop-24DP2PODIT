package studentregistration;

public class Student {
    private String name;
    private String surname;
    private String email;
    private String personalCode;
    private String registrationDateTime;

    public Student(String name, String surname, String email, String personalCode, String registrationDateTime) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.personalCode = personalCode;
        this.registrationDateTime = registrationDateTime;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPersonalCode() {
        return this.personalCode;
    }

    public String getRegistrationDateTime() {
        return this.registrationDateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toCsvLine() {
        return this.name + "," + this.surname + "," + this.email + "," + this.personalCode + "," + this.registrationDateTime;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + " | " + this.email + " | " + this.personalCode + " | " + this.registrationDateTime;
    }
}