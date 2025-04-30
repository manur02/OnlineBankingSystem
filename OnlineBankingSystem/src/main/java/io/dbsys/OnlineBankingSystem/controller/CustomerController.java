package io.dbsys.OnlineBankingSystem.controller;

import io.dbsys.OnlineBankingSystem.dto.AccountSummaryDto;
import io.dbsys.OnlineBankingSystem.dto.CustomerDto;
import io.dbsys.OnlineBankingSystem.entity.Customer;
import io.dbsys.OnlineBankingSystem.service.AccountService;
import io.dbsys.OnlineBankingSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    private final CustomerService customerService;



    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }

    @RequestMapping(method = RequestMethod.POST, value= "/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDto customerDto) {
        try {

            Customer customer = customerService.createCustomer(customerDto);

            return new ResponseEntity<>("Customer created successfully with ID: " + customer.getCustomerId(), HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        return customerService.getCustomer(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAccountSummary/{id}")
    public ResponseEntity<AccountSummaryDto> getAccountSummary(@PathVariable int id) {
        try {
            AccountSummaryDto accountSummary = customerService.getAccountSummary(id);
            return new ResponseEntity<>(accountSummary, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }






}
