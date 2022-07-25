package homework.BankSystem;

import homework.BankSystem.Service.BankAccountStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/bankSystem")
public class BankSystemController {

    @Autowired
    private BankAccountStatementService bankAccountStatementService;

    @PostMapping("/uploadFile")
    public String uploadBankStatements(@RequestParam("file") MultipartFile file) {

        return bankAccountStatementService.saveBankStatements(file);
    }

    @PostMapping("/exportFile")
    public String exportBankStatements(@RequestParam(required = false)  String dateFrom, @RequestParam(required = false)  String dateTo, HttpServletResponse servletResponse) throws IOException {

        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"Bank Statements.csv\"");
        return bankAccountStatementService.exportBankStatement(dateFrom, dateTo, servletResponse.getWriter());
    }

    @PostMapping("/getBalance")
    public String getBankAccountBalance(@RequestParam String bankAccountNumber, @RequestParam(required = false)  String dateFrom, @RequestParam(required = false)  String dateTo) {
        return bankAccountStatementService.getBankAccountBalance(bankAccountNumber, dateFrom, dateTo);
    }
}
