package io.dbsys.OnlineBankingSystem.service;

import io.dbsys.OnlineBankingSystem.dto.CustomerDto;
import io.dbsys.OnlineBankingSystem.entity.*;
import io.dbsys.OnlineBankingSystem.enums.*;
import io.dbsys.OnlineBankingSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private final BankRepository bankRepository;

    // Constructor injection for both repositories
    @Autowired
    public CustomerService(CustomerRepository customerRepository, BankRepository bankRepository) {
        this.customerRepository = customerRepository;
        this.bankRepository = bankRepository;
    }

    public Customer createCustomer(CustomerDto customerDto){
        Bank bank = bankRepository.findById(customerDto.getCustomerBranchId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        Customer customer = new Customer(customerDto.getFirstName(), customerDto.getLastName(),
                customerDto.getAddress(), customerDto.getPhoneNumber(), customerDto.getEmail(),
                customerDto.getPassword(),  customerDto.getStatus(), bank);
        return customerRepository.save(customer);
    }


}
