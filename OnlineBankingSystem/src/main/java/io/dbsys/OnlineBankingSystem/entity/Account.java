package io.dbsys.OnlineBankingSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @OneToOne(mappedBy = "account")
    @JsonBackReference
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "bankBranch")
    private Bank bankBranch;

    private Double accountBalance;

    @Column(unique = true)
    private Long accountNumber;

    @OneToMany(mappedBy = "account")
    @JsonBackReference
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "recipient")
    @JsonBackReference
    private List<Transaction> receivedTransactions;


    public Account(){

    }

    public Account(Customer customerId, Bank bankBranch, Double accountBalance) {
        this.customerId = customerId;
        this.bankBranch = bankBranch;
        this.accountBalance = accountBalance;
        this.accountNumber = generateAccountNumber();
    }

    private Long generateAccountNumber() {
        return 1000000000L + System.currentTimeMillis() % 1000000000;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Bank getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(Bank bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }


}
