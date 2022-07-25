package homework.BankSystem.Service;

import homework.BankSystem.Module.BankAccountStatement;
import homework.BankSystem.Repository.IBankAccountStatementRepository;
import homework.BankSystem.Service.Support.FormattedDateMatcher;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.Record;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountStatementService implements IBankAccountStatementService {

    @Autowired
    private IBankAccountStatementRepository bankAccountStatementRepository;
    @Autowired
    private FormattedDateMatcher formattedDateMatcher;

    @Override
    public String getBankAccountBalance(String bankAccountNumber, String dateFrom, String dateTo) {

        double balance;
        List<BankAccountStatement> bankAccountStatementList = bankAccountStatementRepository
                .findAllByAccountNumber(bankAccountNumber);

        if(bankAccountStatementList.isEmpty()){
            return "Bank account number not found!";
        }

        if(dateFrom.isEmpty() && dateTo.isEmpty()){
            balance = bankAccountStatementList.stream()
                    .mapToDouble(BankAccountStatement::getAmount)
                    .sum();

            return Double.toString(balance);
        }

        if((!dateFrom.isEmpty() && !formattedDateMatcher.matches(dateFrom)) ||  (!dateTo.isEmpty() && !formattedDateMatcher.matches(dateTo))){
            return "Invalid date time format\n expected format: dd-MM-yyyy HH:mm:ss";
        }

        balance = bankAccountStatementList.stream()
                .filter(x -> (dateTo.isEmpty() || x.getOperationDate().before(TryParseStringToDate(dateTo))) && (dateFrom.isEmpty() || x.getOperationDate().after(TryParseStringToDate(dateFrom))))
                .mapToDouble(BankAccountStatement::getAmount)
                .sum();

        if(balance == 0) {
            return "No balance by given dates";
        }
        return Double.toString(balance);
    }

    @Override
    public String exportBankStatement(String dateFrom, String dateTo, PrintWriter csvExportService) {

        List<BankAccountStatement> bankAccountStatementList = bankAccountStatementRepository.findAll();
        if(bankAccountStatementList.isEmpty()){
           return "No data found by given dates";
        }

        if((!dateFrom.isEmpty() && !formattedDateMatcher.matches(dateFrom)) ||  (!dateTo.isEmpty() && !formattedDateMatcher.matches(dateTo))){
            return "Invalid date time format\n expected format: dd-MM-yyyy HH:mm:ss";
        }

        List<BankAccountStatement> bankAccountStatements = bankAccountStatementList.stream()
                .filter(x -> (dateTo.isEmpty() || x.getOperationDate().before(TryParseStringToDate(dateTo))) && (dateFrom.isEmpty() || x.getOperationDate().after(TryParseStringToDate(dateFrom))))
                .collect(Collectors.toList());

        if(bankAccountStatements.isEmpty())
        {
            return "No data found by given dates";
        }

        List<String[]> bankStatements = bankAccountStatements.stream()
                .map(BankAccountStatement::ToStringArray)
                .collect(Collectors.toList());

        if(!exportCsvFile(bankStatements, csvExportService)){
            return  "Error whiles parsing to CSV file";
        }
        return "File exported";
    }

    @Override
    public String saveBankStatements(MultipartFile file) {

        List<BankAccountStatement> accountStatementList =  readCsvFile(file);
        if(accountStatementList.isEmpty()){
            return "No data was collected";
        }
        bankAccountStatementRepository.saveAll(accountStatementList);
        return "Data imported successful";
    }

    public List<BankAccountStatement> readCsvFile(MultipartFile file) {

        List<BankAccountStatement> accountStatementList =  new ArrayList<>();
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return accountStatementList;
        }

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
        return accountStatementList;
    }

    public boolean exportCsvFile(List<String[]> bankStatements, PrintWriter writer) {

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("accountNumber","operationDate" , "beneficiary", "comment", "currency", "amount");
            for (String[] singleStatement : bankStatements) {
                csvPrinter.printRecord(singleStatement[0], singleStatement[1], singleStatement[2], singleStatement[3], singleStatement[4]);
            }
        } catch (IOException e)
        {
            return false;
        }
        return true;
    }

    public Date TryParseStringToDate(String dateFrom) {

        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                    .parse(dateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
