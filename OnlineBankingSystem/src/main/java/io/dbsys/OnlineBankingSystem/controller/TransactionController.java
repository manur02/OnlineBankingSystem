package io.dbsys.OnlineBankingSystem.controller;

import io.dbsys.OnlineBankingSystem.entity.Transaction;
import io.dbsys.OnlineBankingSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Get all transactions by customerId
    @GetMapping("/{customerId}")
    public List<Transaction> getTransactionsByCustomerId(@PathVariable int customerId) {
        return transactionService.getTransactionsByCustomerId(customerId);
    }

}
