package io.dbsys.OnlineBankingSystem.dto;

import io.dbsys.OnlineBankingSystem.enums.AccountStatus;

public class CustomerDto {

    private String firstName;
    private String lastName;
    private String address;
    private Long phoneNumber;
    private String email;
    private String password;
    private AccountStatus status;
    private int customerBranchId;  // Only the Bank ID, not the full Bank object

    // ====== Constructors ======

    public CustomerDto() {
        // No-args constructor (needed for Spring to deserialize JSON)
    }

    public CustomerDto(String firstName, String lastName, String address, Long phoneNumber,
                       String email, String password, AccountStatus status, int customerBranchId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.status = status;
        this.customerBranchId = customerBranchId;
    }

    // ====== Getters and Setters ======

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public int getCustomerBranchId() {
        return customerBranchId;
    }

    public void setCustomerBranchId(int customerBranchId) {
        this.customerBranchId = customerBranchId;
    }
}
