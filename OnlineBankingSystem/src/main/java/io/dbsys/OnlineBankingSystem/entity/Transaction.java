package io.dbsys.OnlineBankingSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.dbsys.OnlineBankingSystem.enums.*;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonManagedReference
    private Account account; // Sender

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = true)
    @JsonManagedReference
    private Account recipient; // Receiver

    private double amount;
    private Date dateOfTransaction;

    public Transaction() {
    }

    public Transaction(TransactionType type, Account account, Account recipient, double amount) {
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
