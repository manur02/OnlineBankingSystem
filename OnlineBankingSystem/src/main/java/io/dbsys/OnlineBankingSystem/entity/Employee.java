package io.dbsys.OnlineBankingSystem.entity;

import io.dbsys.OnlineBankingSystem.enums.EmployeeType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    private String firstName;
    private String lastName;
    private String address;
    private Long phoneNumber;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name="bank_id")
    private Bank employeeBranch;
    private Date joiningDate;

    public Employee(){

    }

    public Employee(EmployeeType employeeType, String firstName, String lastName, String address, Long phoneNumber, String email, String password, Bank employeeBranch) {
        this.employeeType = employeeType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.employeeBranch = employeeBranch;
        this.joiningDate=new Date();
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
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

    public Bank getEmployeeBranch() {
        return employeeBranch;
    }

    public void setEmployeeBranch(Bank employeeBranch) {
        this.employeeBranch = employeeBranch;
    }
}
