package homework.BankSystem.Module;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class BankAccountStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private Date operationDate;

    @Column(nullable = false)
    private String beneficiary;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private double amount;

    private String comment;

    public BankAccountStatement() {
    }

    public BankAccountStatement(String accountNumber, Date operationDate, String beneficiary, String currency, double amount, String comment) {
        this.accountNumber = accountNumber;
        this.operationDate = operationDate;
        this.beneficiary = beneficiary;
        this.currency = currency;
        this.amount = amount;
        this.comment = comment;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {

        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
                    .parse(operationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.operationDate = date;
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

    public String[] ToStringArray() {
        return new String[]{this.accountNumber, this.operationDate.toString(), this.beneficiary, this.comment, this.currency, String.valueOf(this.amount)};
    }
}