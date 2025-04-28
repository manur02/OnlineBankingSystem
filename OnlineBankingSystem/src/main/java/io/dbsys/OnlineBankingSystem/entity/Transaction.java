package io.dbsys.OnlineBankingSystem.entity;

import io.dbsys.OnlineBankingSystem.enums.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private TransactionType type;
    private Account account;
    private Account recipient;
    private double amount;
    private Date dateOfTransaction;


    public Transaction(int transactionId, TransactionType type, Account account, Account recipient, double amount) {
        this.transactionId = transactionId;
        this.type = type;
        this.account = account;
        this.recipient = recipient;
        this.amount = amount;
        this.dateOfTransaction = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getRecipient() {
        return recipient;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
