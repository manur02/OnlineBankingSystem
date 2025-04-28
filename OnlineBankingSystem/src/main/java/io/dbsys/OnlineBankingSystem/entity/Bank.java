package io.dbsys.OnlineBankingSystem.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Bank {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int bankId;
    private  int bankCode;
    private  int bankBranch;

    public Bank(int bankId, int bankCode, int bankBranch) {
        this.bankCode = bankCode;
        this.bankBranch = bankBranch;
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

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
