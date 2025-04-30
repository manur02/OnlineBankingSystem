package io.dbsys.OnlineBankingSystem.service;

import io.dbsys.OnlineBankingSystem.entity.Account;
import io.dbsys.OnlineBankingSystem.entity.Customer;
import io.dbsys.OnlineBankingSystem.entity.Transaction;
import io.dbsys.OnlineBankingSystem.enums.TransactionType;
import io.dbsys.OnlineBankingSystem.repository.AccountRepository;
import io.dbsys.OnlineBankingSystem.repository.CustomerRepository;
import io.dbsys.OnlineBankingSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionService transactionService;

    public void deposit(int customerId, double amount) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            Account account = customer.getAccount();
            if (account == null) {
                throw new RuntimeException("Customer does not have an account");
            }
            double updatedBalance = account.getAccountBalance() + amount;
            account.setAccountBalance(updatedBalance);
            accountRepository.save(account);
            transactionService.recordTransaction(TransactionType.DEPOSIT, account, null, amount);

        } else {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
    }

    public void withdraw(int customerId, double amount) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            Account account = customer.getAccount();

            if (account == null) {
                throw new RuntimeException("Customer does not have an account");
            }

            double currentBalance = account.getAccountBalance();

            if (currentBalance < amount) {
                throw new RuntimeException("Insufficient balance to withdraw the requested amount");
            }

            double updatedBalance = currentBalance - amount;
            account.setAccountBalance(updatedBalance);
            accountRepository.save(account);
            transactionService.recordTransaction(TransactionType.WITHDRAW, account, null, amount);
        } else {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
    }

    public void transfer(int senderCustomerId, int recipientCustomerId, double amount) {
        // Validate the transfer amount
        if (amount <= 0) {
            throw new RuntimeException("Transfer amount must be greater than 0.");
        }

        // Retrieve sender and recipient customers
        Optional<Customer> senderOpt = customerRepository.findById(senderCustomerId);
        Optional<Customer> recipientOpt = customerRepository.findById(recipientCustomerId);

        if (senderOpt.isPresent() && recipientOpt.isPresent()) {
            Customer sender = senderOpt.get();
            Customer recipient = recipientOpt.get();

            Account senderAccount = sender.getAccount();
            Account recipientAccount = recipient.getAccount();

            // Ensure both customers have accounts
            if (senderAccount == null) {
                throw new RuntimeException("Sender does not have an account.");
            }
            if (recipientAccount == null) {
                throw new RuntimeException("Recipient does not have an account.");
            }

            double senderBalance = senderAccount.getAccountBalance();

            // Check if sender has enough balance for the transfer
            if (senderBalance < amount) {
                throw new RuntimeException("Sender does not have enough balance for the transfer.");
            }

            // Perform the transfer (withdraw from sender, deposit to recipient)
            senderAccount.setAccountBalance(senderBalance - amount);  // Withdraw from sender
            recipientAccount.setAccountBalance(recipientAccount.getAccountBalance() + amount);  // Deposit into recipient

            // Save the updated accounts
            accountRepository.save(senderAccount);
            accountRepository.save(recipientAccount);
            transactionService.recordTransaction(TransactionType.TRANSFER_OUT, senderAccount, recipientAccount, amount);

        } else {
            throw new RuntimeException("Sender or recipient not found.");
        }
    }


}
