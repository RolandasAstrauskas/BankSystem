package homework.BankSystem.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.PrintWriter;

public interface IBankAccountStatementService {
    String getBankAccountBalance(String bankAccountNumber, String dateFrom, String DateTo);
    String exportBankStatement(String dateFrom, String DateTo, PrintWriter csvExportService);
    String saveBankStatements(MultipartFile file);
}
