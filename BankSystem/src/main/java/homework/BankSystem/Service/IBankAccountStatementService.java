package homework.BankSystem.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IBankAccountStatementService {

    double getBankAccountBalance(String bankAccountNumber, String dateFrom, String DateTo);
    MultipartFile exportBankStatement(String dateFrom, String DateTo);
    void saveBankStatements(MultipartFile file) throws IOException;
}
