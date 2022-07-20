package homework.BankSystem.Module;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccountStatement {

    @Id
    private String accountNumber;

    private String operationDate;
    private String beneficiary;
    private String comment;
    private String currency;
    private double amount;

    public BankAccountStatement() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    //TODO
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}