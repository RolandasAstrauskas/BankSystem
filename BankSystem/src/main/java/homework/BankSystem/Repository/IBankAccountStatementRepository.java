package homework.BankSystem.Repository;

import homework.BankSystem.Module.BankAccountStatement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBankAccountStatementRepository extends CrudRepository<BankAccountStatement, String> {

    List<BankAccountStatement> findAllByAccountNumber(String accountNumber);
    List<BankAccountStatement> findAll();
}
