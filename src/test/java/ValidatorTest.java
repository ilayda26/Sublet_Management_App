import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    void validEmailShouldReturnTrue() {
        assertTrue(
                Validator.validEmail("student@example.com")
        );
    }

    @Test
    void invalidEmailShouldReturnFalse() {
        assertFalse(
                Validator.validEmail("studentexample.com")
        );
    }

    @Test
    void emptyTextShouldReturnFalse() {
        assertFalse(
                Validator.notEmpty("")
        );
    }

    @Test
    void validPriceShouldReturnTrue() {
        assertTrue(
                Validator.validPrice(800)
        );
    }

    @Test
    void negativePriceShouldReturnFalse() {
        assertFalse(
                Validator.validPrice(-10)
        );
    }

    @Test
    void validDatesShouldReturnTrue() {
        LocalDate start =
                LocalDate.of(2026, 10, 1);

        LocalDate end =
                LocalDate.of(2026, 12, 1);

        assertTrue(
                Validator.validDates(
                        start,
                        end
                )
        );
    }
}