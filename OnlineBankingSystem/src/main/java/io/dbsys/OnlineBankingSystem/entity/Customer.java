package io.dbsys.OnlineBankingSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.dbsys.OnlineBankingSystem.enums.AccountStatus;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Entity
@Table
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int customerId;


    private String firstName;
    private String lastName;
    private String address;
    private Long phoneNumber;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name="bank_id")
    private Bank customerBranch;
    private Date createdOn;

    public Customer(    ){

    }

    public Customer(String firstName, String lastName, String address, Long phoneNumber, String email, String password, AccountStatus status, Bank customerBranch) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.status = status;
        this.customerBranch = customerBranch;
        this.createdOn = new Date();
    }


    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Bank getCustomerBranch() {
        return customerBranch;
    }

    public void setCustomerBranch(Bank customerBranch) {
        this.customerBranch = customerBranch;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @Nullable Account getAccount() {
        return account;
    }

    public void setAccount(@Nullable Account account) {
        this.account = account;
    }
}
