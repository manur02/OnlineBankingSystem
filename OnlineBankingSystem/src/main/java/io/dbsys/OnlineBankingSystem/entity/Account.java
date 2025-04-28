package io.dbsys.OnlineBankingSystem.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    private Customer customerId;
    private Bank bankBranch;
    private Double accountBalance;
    private Long accountNumber;

    public Account(Customer customerId, Bank bankBranch, Double accountBalance, Long accountNumber) {

        this.customerId = customerId;
        this.bankBranch = bankBranch;
        this.accountBalance = accountBalance;
        this.accountNumber = accountNumber;
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
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
}
