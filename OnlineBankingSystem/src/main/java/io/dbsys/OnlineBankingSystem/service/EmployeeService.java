package io.dbsys.OnlineBankingSystem.service;

import io.dbsys.OnlineBankingSystem.dto.EmployeeDto;
import io.dbsys.OnlineBankingSystem.entity.Account;
import io.dbsys.OnlineBankingSystem.entity.Bank;
import io.dbsys.OnlineBankingSystem.entity.Customer;
import io.dbsys.OnlineBankingSystem.entity.Employee;
import io.dbsys.OnlineBankingSystem.enums.AccountStatus;
import io.dbsys.OnlineBankingSystem.enums.EmployeeType;
import io.dbsys.OnlineBankingSystem.repository.AccountRepository;
import io.dbsys.OnlineBankingSystem.repository.BankRepository;
import io.dbsys.OnlineBankingSystem.repository.CustomerRepository;
import io.dbsys.OnlineBankingSystem.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Employee addEmployee(EmployeeDto employeeDto, int currentEmployeeId) {

        Employee currentEmployee = employeeRepository.findById(currentEmployeeId)
                .orElseThrow(() -> new RuntimeException("Current employee not found with id: " + currentEmployeeId));

        EmployeeType currentEmployeeType = currentEmployee.getEmployeeType();

        if (currentEmployeeType == EmployeeType.CLERK) {
            throw new RuntimeException("Clerk is not allowed to add any employees");
        }

        if (currentEmployeeType == EmployeeType.MANAGER) {
            if (employeeDto.getEmployeeType() != EmployeeType.CLERK) {
                throw new RuntimeException("Manager can only add Clerk employees");
            }

            if (currentEmployee.getEmployeeBranch().getBankId() != employeeDto.getEmployeeBranchId()) {
                throw new RuntimeException("Manager can only add Clerks to their own branch");
            }
        }

        Bank bank = bankRepository.findById(employeeDto.getEmployeeBranchId())
                .orElseThrow(() -> new RuntimeException("Bank not found with id: " + employeeDto.getEmployeeBranchId()));

        Employee employee = new Employee(
                employeeDto.getEmployeeType(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getAddress(),
                employeeDto.getPhoneNumber(),
                employeeDto.getEmail(),
                employeeDto.getPassword(),
                bank
        );

        return employeeRepository.save(employee);
    }


    public List<Customer> getCustomers(Bank bank, AccountStatus status) {
        return customerRepository.findByCustomerBranchAndStatus(bank, status);
    }

    public Bank getEmployeeBranch(int employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            Bank bank = employee.getEmployeeBranch();
            return bank;
        } else {
            throw new EntityNotFoundException("Employee not found with ID: " + employeeId);
        }
    }

    public Bank getCustomerBranch(int customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            return customerOpt.get().getCustomerBranch();
        } else {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
    }

    public Customer approveCustomer(int customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (customer.getStatus() == AccountStatus.PENDING) {
                customer.setStatus(AccountStatus.ACTIVE);
                Customer savedCustomer = customerRepository.save(customer);
                Account openAccount = new Account(savedCustomer, customer.getCustomerBranch(), 0.0);
                accountRepository.save(openAccount);
                savedCustomer.setAccount(openAccount);
                customerRepository.save(savedCustomer);
                return savedCustomer;
            }
            else{
                throw new RuntimeException("Account already approved or closed.");
            }

        } else {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
    }


}
