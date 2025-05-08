package io.dbsys.OnlineBankingSystem.dto;

public class BankAccountCountDto {
    private int bankId;
    private int bankBranch;
    private long accountCount;

    public BankAccountCountDto(int bankId, int bankBranch, long accountCount) {
        this.bankId = bankId;
        this.bankBranch = bankBranch;
        this.accountCount = accountCount;
    }

    // Getters
    public int getBankId() {
        return bankId;
    }

    public int getBankBranch() {
        return bankBranch;
    }

    public long getAccountCount() {
        return accountCount;
    }
}
