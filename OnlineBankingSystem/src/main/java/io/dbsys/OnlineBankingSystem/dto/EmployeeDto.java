package io.dbsys.OnlineBankingSystem.dto;

import io.dbsys.OnlineBankingSystem.enums.EmployeeType;


public class EmployeeDto {


    private EmployeeType employeeType;
    private String firstName;
    private String lastName;
    private String address;
    private Long phoneNumber;
    private String email;
    private String password;
    private Integer employeeBranchId;  // Instead of Bank object, we pass Bank ID

    // Constructors
    public EmployeeDto() {}

    public EmployeeDto(EmployeeType employeeType, String firstName, String lastName, String address,
                       Long phoneNumber, String email, String password, Integer employeeBranchId) {
        this.employeeType = employeeType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.employeeBranchId = employeeBranchId;
    }

    // Getters and Setters
    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

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

    public Integer getEmployeeBranchId() {
        return employeeBranchId;
    }

    public void setEmployeeBranchId(Integer employeeBranchId) {
        this.employeeBranchId = employeeBranchId;
    }
}
