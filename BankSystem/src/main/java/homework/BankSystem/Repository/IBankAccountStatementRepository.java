package homework.BankSystem.Repository;

import homework.BankSystem.Module.BankAccountStatement;
import org.springframework.data.repository.CrudRepository;

public interface IBankAccountStatementRepository extends CrudRepository<BankAccountStatement, String> {

    BankAccountStatement findByAccountNumber(String accountNumber);
}
