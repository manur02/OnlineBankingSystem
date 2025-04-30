package io.dbsys.OnlineBankingSystem.dto;

public class AccountSummaryDto {
    private String customerName;
    private Long accountNumber;
    private Double balance;
    private int bankBranch;

    public AccountSummaryDto() {}

    public AccountSummaryDto(String customerName, Long accountNumber, Double balance, int bankBranch) {
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankBranch = bankBranch;
    }

    // Getters and setters

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getBankBranch() {
        return bankBranch;
    }

    public void setBankName(int bankBranch) {
        this.bankBranch = bankBranch;
    }
}
