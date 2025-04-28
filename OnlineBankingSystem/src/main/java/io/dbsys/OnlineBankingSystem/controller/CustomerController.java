package io.dbsys.OnlineBankingSystem.controller;

import io.dbsys.OnlineBankingSystem.dto.CustomerDto;
import io.dbsys.OnlineBankingSystem.entity.Customer;
import io.dbsys.OnlineBankingSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    private final CustomerService customerService;

    // Constructor injection of CustomerService
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.POST, value= "/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            // Call service method to create a customer
            Customer customer = customerService.createCustomer(customerDto);

            // Return success message
            return new ResponseEntity<>("Customer created successfully with ID: " + customer.getCustomerId(), HttpStatus.CREATED);
        } catch (Exception e) {
            // Return error message if something goes wrong (e.g., bank not found)
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
