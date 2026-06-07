package studentregistration;

public class Validator {

    public static boolean isValidName(String value) {
        return value != null && value.matches("[A-Za-zĀ-ž]{3,}");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean isValidPersonalCode(String personalCode) {
        return personalCode != null && personalCode.matches("\\d{6}-\\d{5}");
    }
}