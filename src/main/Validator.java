import java.time.LocalDate;

public class Validator {

    // Checks that a field is not empty
    public static boolean notEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    // Basic email check
    public static boolean validEmail(String email) {
        return email != null
                && email.contains("@")
                && email.contains(".");
    }

    // Password must contain at least 6 characters
    public static boolean validPassword(String password) {
        return password != null && password.length() >= 6;
    }

    // Price must be greater than zero
    public static boolean validPrice(double price) {
        return price > 0;
    }

    // End date must be after the start date
    public static boolean validDates(LocalDate startDate, LocalDate endDate) {
        return startDate != null
                && endDate != null
                && endDate.isAfter(startDate);
    }
}