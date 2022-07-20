package homework.BankSystem;

import homework.BankSystem.Service.BankAccountStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/bankSystem")
public class BankSystemController {

    @Autowired
    private BankAccountStatementService bankAccountStatementService;

    @PostMapping("/uploadFile")
    public String uploadBankStatements(@RequestParam("file") MultipartFile file) throws IOException {

        bankAccountStatementService.saveBankStatements(file);
        return "Hello world!";
    }

    @PostMapping("/addBasicBook")
    public MultipartFile exportBankStatements(@RequestParam String dateFrom, @RequestParam String dateTo){

        return bankAccountStatementService.exportBankStatement(dateFrom, dateTo);
    }

    @PostMapping("/getBalance")
    public double getBankAccountBalance(@RequestParam  String bankAccountNumber, @RequestParam String dateFrom, @RequestParam String dateTo){

        return bankAccountStatementService.getBankAccountBalance(bankAccountNumber, dateFrom, dateTo);
    }
}
