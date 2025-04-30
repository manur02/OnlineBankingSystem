package io.dbsys.OnlineBankingSystem.controller;

import io.dbsys.OnlineBankingSystem.dto.EmployeeDto;
import io.dbsys.OnlineBankingSystem.entity.*;

import io.dbsys.OnlineBankingSystem.enums.*;
import io.dbsys.OnlineBankingSystem.enums.EmployeeType;
import io.dbsys.OnlineBankingSystem.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //    http://localhost:8081/api/employees/add?currentEmployeeType=MANAGER
    @PostMapping("/add/{currentEmployeeId}")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto,
                                         @PathVariable int currentEmployeeId) {
        try {
            Employee createdEmployee = employeeService.addEmployee(employeeDto, currentEmployeeId);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            // If there is an exception, create a JSON-style response
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("timestamp", LocalDateTime.now());
            errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
            errorDetails.put("error", "Bad Request");
            errorDetails.put("message", ex.getMessage());
            errorDetails.put("path", "/api/employees/add/" + currentEmployeeId); // optional, to show path

            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/getCustomers/{bankId}/{empId}/{customerStatus}")
    public ResponseEntity<?> getCustomers(@PathVariable int bankId, @PathVariable int empId, @PathVariable AccountStatus customerStatus ) {
        Bank employeeBranch = employeeService.getEmployeeBranch(empId);

        if (employeeBranch.getBankId() == bankId) {
            List<Customer> customers = employeeService.getCustomers(employeeBranch, customerStatus);
            return ResponseEntity.ok(customers);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "You are not authorized to view this branch's information.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        }
    }

    @RequestMapping("/approve/{bankId}/{empId}/{customerId}")
    public ResponseEntity<?> approveCustomer(@PathVariable int bankId,
                                             @PathVariable int empId,
                                             @PathVariable int customerId) {
        Bank employeeBranch = employeeService.getEmployeeBranch(empId);
        Bank customerBranch = employeeService.getCustomerBranch(customerId);

        if (employeeBranch != null && customerBranch != null && employeeBranch.getBankId() == customerBranch.getBankId()) {
            try {
                Customer approvedCustomer = employeeService.approveCustomer(customerId);
                return ResponseEntity.ok(approvedCustomer);
            } catch (RuntimeException e) {
                Map<String, Object> errorDetails = new HashMap<>();
                errorDetails.put("timestamp", LocalDateTime.now());
                errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
                errorDetails.put("error", "Bad Request");
                errorDetails.put("message", e.getMessage());
                errorDetails.put("path", "/api/employees/approve/" + bankId + "/" + empId + "/" + customerId);
                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
            }
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "You are not authorized to approve customers for a different branch.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        }
    }


}
