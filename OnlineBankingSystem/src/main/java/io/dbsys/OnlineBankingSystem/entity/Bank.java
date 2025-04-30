package io.dbsys.OnlineBankingSystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int bankId;
    private  int bankCode;
    private  int bankBranch;
    @OneToMany(mappedBy = "customerBranch")
    private List<Customer> CustomersOfBranch;
    @OneToMany(mappedBy = "employeeBranch")
    private List<Employee> EmployeesOfBranch;

    @OneToMany(mappedBy = "bankBranch")
    private List<Account> BankAccounts;

    public Bank(){

    }

    public Bank(int bankCode, int bankBranch) {
        this.bankCode = bankCode;
        this.bankBranch = bankBranch;
    }

    public int getBankId() {
        return bankId;
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
