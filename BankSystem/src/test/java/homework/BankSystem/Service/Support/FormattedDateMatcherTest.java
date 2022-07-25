package homework.BankSystem.Service.Support;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormattedDateMatcherTest {

    @Test
    void matches() {
        FormattedDateMatcher formattedDateMatcher = new FormattedDateMatcher();
        boolean response = formattedDateMatcher.matches("11-12-2022 12:00:00");
        assertTrue(response);
    }
}