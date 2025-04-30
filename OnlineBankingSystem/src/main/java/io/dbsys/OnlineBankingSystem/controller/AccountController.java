package io.dbsys.OnlineBankingSystem.controller;

import io.dbsys.OnlineBankingSystem.repository.AccountRepository;
import io.dbsys.OnlineBankingSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/deposit/{customer_id}/{amount}")
    public ResponseEntity<String> deposit(@PathVariable int customer_id, @PathVariable double amount) {
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Deposit amount must be greater than 0.");
        }

        try {
            accountService.deposit(customer_id, amount);
            return ResponseEntity.ok("Deposit successful.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/withdraw/{customer_id}/{amount}")
    public ResponseEntity<String> withdraw(@PathVariable int customer_id, @PathVariable double amount) {
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Withdrawal amount must be greater than 0.");
        }

        try {
            accountService.withdraw(customer_id, amount);
            return ResponseEntity.ok("Withdrawal successful.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/transfer/{senderId}/{recipientId}/{amount}")
    public ResponseEntity<String> transfer(@PathVariable int senderId,
                                           @PathVariable int recipientId,
                                           @PathVariable double amount) {
        try {
            accountService.transfer(senderId, recipientId, amount);
            return ResponseEntity.ok("Transfer successful.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }


}
