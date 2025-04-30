package io.dbsys.OnlineBankingSystem.service;

import io.dbsys.OnlineBankingSystem.dto.AccountSummaryDto;
import io.dbsys.OnlineBankingSystem.dto.CustomerDto;
import io.dbsys.OnlineBankingSystem.entity.*;
import io.dbsys.OnlineBankingSystem.enums.*;
import io.dbsys.OnlineBankingSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
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

        if (customerRepository.existsByEmail(customerDto.getEmail())) {
            throw new RuntimeException("Customer with this email already exists");
        }

        // Check if a customer already exists with the same phone number
        if (customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber())) {
            throw new RuntimeException("Customer with this phone number already exists");
        }

        Bank bank = bankRepository.findById(customerDto.getCustomerBranchId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        Customer customer = new Customer(customerDto.getFirstName(), customerDto.getLastName(),
                customerDto.getAddress(), customerDto.getPhoneNumber(), customerDto.getEmail(),
                customerDto.getPassword(),  customerDto.getStatus(), bank);
        System.out.println(customerDto.getStatus());
        return customerRepository.save(customer);
    }


    public Optional<Customer> getCustomer(int id) {
        return customerRepository.findById(id);
    }

    public Bank getCustomerBranch(int customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            return customerOpt.get().getCustomerBranch();
        } else {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
    }

    public AccountSummaryDto getAccountSummary(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (customer.getAccount() == null) {
            throw new RuntimeException("Account not yet created for this customer.");
        }

        return new AccountSummaryDto(
                customer.getFirstName() + " " + customer.getLastName(),
                customer.getAccount().getAccountNumber(),
                customer.getAccount().getAccountBalance(),
                customer.getCustomerBranch().getBankBranch()
        );
    }



}
