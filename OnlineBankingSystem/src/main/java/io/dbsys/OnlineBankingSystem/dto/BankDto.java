package io.dbsys.OnlineBankingSystem.dto;

public class BankDto {
    private int bankCode;
    private int bankBranch;

    public int getBankCode() {
        return bankCode;
    }

    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    public int getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(int bankBranch) {
        this.bankBranch = bankBranch;
    }
}

