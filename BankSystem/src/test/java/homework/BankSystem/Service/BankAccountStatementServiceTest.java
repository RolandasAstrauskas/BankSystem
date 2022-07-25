package homework.BankSystem.Service;

import homework.BankSystem.Module.BankAccountStatement;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountStatementServiceTest {

    @Test
    void getBankAccountBalance() {
    }

    @Test
    void exportBankStatement() {
    }

    @Test
    void saveBankStatements() {
    }

    @Test
    void readCsvFile() throws ParseException, IOException {
        File file = new File("src/main/resources/test.csv");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", input);

        BankAccountStatementService bankAccountStatementService = new BankAccountStatementService();
        List<BankAccountStatement> response = bankAccountStatementService.readCsvFile(multipartFile);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        BankAccountStatement bankAccountStatement = new BankAccountStatement("LT151234456798756548",formatter.parse("10-28-2022 13:00:00"), "FirstName LastName", "USD", 200.20, "Bills to Pay");
        List<BankAccountStatement> expectedList = new ArrayList<>();
        expectedList.add(bankAccountStatement);

        assertFalse(response.size() == expectedList.size() && response.containsAll(expectedList) && expectedList.containsAll(response));
    }

    @Test
    void exportCsvFile() throws IOException {
        BankAccountStatementService bankAccountStatementService = new BankAccountStatementService();

        List<String[]> expectedList = new ArrayList<>();
        String[] bankStatement = {"LT151234456798756548", "11-28-2022 13:00:00", "FirstName LastName", "Bills to Pay", "USD", "200.20"};
        expectedList.add(bankStatement);

        FileWriter file = new FileWriter("output.txt");
        PrintWriter writer = new PrintWriter(file);

        boolean response = bankAccountStatementService.exportCsvFile(expectedList, writer);
        assertTrue(response);
    }

    @Test
    void tryParseStringToDate() throws ParseException {
        BankAccountStatementService bankAccountStatementService = new BankAccountStatementService();
        Date response = bankAccountStatementService.TryParseStringToDate("10-10-2022 12:00:00");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        assertEquals(formatter.parse("10-10-2022 12:00:00"), response);
    }
}