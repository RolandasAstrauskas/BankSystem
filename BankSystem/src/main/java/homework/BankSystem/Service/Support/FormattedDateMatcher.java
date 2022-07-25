package homework.BankSystem.Service.Support;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class FormattedDateMatcher implements DateMatcher {

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^\\d{2}-\\d{2}-\\d{4}\\s*\\d{2}:\\d{2}:\\d{2}$");

    @Override
    public boolean matches(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }
}