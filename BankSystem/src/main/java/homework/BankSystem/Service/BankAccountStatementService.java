package homework.BankSystem.Service;

import homework.BankSystem.Module.BankAccountStatement;
import homework.BankSystem.Repository.IBankAccountStatementRepository;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.Record;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountStatementService implements IBankAccountStatementService {

    @Autowired
    private IBankAccountStatementRepository bankAccountStatementRepository;

    @Override
    public double getBankAccountBalance(String bankAccountNumber, String dateFrom, String DateTo) {

        //TODO
        List<BankAccountStatement> bankAccountStatementList = (List<BankAccountStatement>) bankAccountStatementRepository.findAll();

        List<BankAccountStatement> bankAccountListByNumber = bankAccountStatementList.stream().
                filter(statement -> statement.getAccountNumber().equals(bankAccountNumber)).collect(Collectors.toList());
        return 0;
    }

    @Override
    public MultipartFile exportBankStatement(String dateFrom, String DateTo) {

        List<BankAccountStatement> bankAccountStatementList = (List<BankAccountStatement>) bankAccountStatementRepository.findAll();

        //TODO
        for (BankAccountStatement bankStatement: bankAccountStatementList) {
        }
        return null;
    }

    @Override
    public void saveBankStatements(MultipartFile file) throws IOException {

        List<BankAccountStatement> accountStatementList =  new ArrayList<>();

        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        CsvParser csvParser = new CsvParser(settings);
        List<Record> parseAll = csvParser.parseAllRecords(inputStream);
        parseAll.forEach(bankStatementRecord -> {
            BankAccountStatement bankAccountStatement = new BankAccountStatement();
            bankAccountStatement.setAccountNumber(bankStatementRecord.getString("accountNumber"));
            bankAccountStatement.setOperationDate(bankStatementRecord.getString("operationDate"));
            bankAccountStatement.setBeneficiary(bankStatementRecord.getString("beneficiary"));
            bankAccountStatement.setComment(bankStatementRecord.getString("comment"));
            bankAccountStatement.setCurrency(bankStatementRecord.getString("currency"));
            bankAccountStatement.setAmount(bankStatementRecord.getDouble("amount"));
            accountStatementList.add(bankAccountStatement);
        });

        bankAccountStatementRepository.saveAll(accountStatementList);
    }
}
