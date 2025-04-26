package io.dbsys.OnlineBankingSystem.entity;

import io.dbsys.OnlineBankingSystem.enums.AccountStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Customer {

    @Id
    private final int customerId;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final Long phoneNumber;
    private final String email;
    private final String password;
    private AccountStatus status;
    private Bank customerBranch;
    private Date createdOn;


}
