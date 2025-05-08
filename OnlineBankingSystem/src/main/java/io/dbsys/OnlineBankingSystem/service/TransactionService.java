package io.dbsys.OnlineBankingSystem.service;

import io.dbsys.OnlineBankingSystem.entity.Account;
import io.dbsys.OnlineBankingSystem.entity.Customer;
import io.dbsys.OnlineBankingSystem.entity.Transaction;
import io.dbsys.OnlineBankingSystem.enums.TransactionType;
import io.dbsys.OnlineBankingSystem.repository.AccountRepository;
import io.dbsys.OnlineBankingSystem.repository.CustomerRepository;
import io.dbsys.OnlineBankingSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

//    public void recordTransaction(TransactionType type, Account sender, Account recipient, double amount) {
//        try {
//            if (type == TransactionType.TRANSFER_OUT) {
//                // Transfer from sender to recipient: two entries
//                Transaction senderTxn = new Transaction(TransactionType.TRANSFER_OUT, sender, recipient, amount);
//                transactionRepository.save(senderTxn);
//
//                Transaction recipientTxn = new Transaction(TransactionType.TRANSFER_IN, recipient, sender, amount);
//                transactionRepository.save(recipientTxn);
//            } else {
//                // Standard transaction (Deposit or Withdrawal): only one account involved
//                if (sender == null) {
//                    throw new IllegalArgumentException("Sender account cannot be null for deposit or withdrawal.");
//                }
//
//                Transaction transaction = new Transaction(type, sender, null, amount);
//                transactionRepository.save(transaction);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Transaction recording failed: " + e.getMessage(), e);
//        }
//    }

    @Autowired
    private AccountRepository accountRepository;

    public void recordTransaction(TransactionType type, Account sender, Account recipient, double amount) {
        try {
            if (type == TransactionType.TRANSFER_OUT) {
                // Re-fetch both accounts
                Account freshSender = accountRepository.findById(sender.getAccountId())
                        .orElseThrow(() -> new RuntimeException("Sender account not found"));
                Account freshRecipient = accountRepository.findById(recipient.getAccountId())
                        .orElseThrow(() -> new RuntimeException("Recipient account not found"));

                Transaction senderTxn = new Transaction(TransactionType.TRANSFER_OUT, freshSender, freshRecipient, amount);
                transactionRepository.save(senderTxn);

                Transaction recipientTxn = new Transaction(TransactionType.TRANSFER_IN, freshRecipient, freshSender, amount);
                transactionRepository.save(recipientTxn);
            } else {
                Account freshSender = accountRepository.findById(sender.getAccountId())
                        .orElseThrow(() -> new RuntimeException("Sender account not found"));

                Transaction transaction = new Transaction(type, freshSender, null, amount);
                transactionRepository.save(transaction);
            }
        } catch (Exception e) {
            throw new RuntimeException("Transaction recording failed: " + e.getMessage(), e);
        }
    }

    public List<Transaction> getTransactionsByCustomerId(int customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }

        Account account = customerOpt.get().getAccount();
        if (account == null) {
            throw new RuntimeException("Customer does not have an account.");
        }

        return transactionRepository.findByAccount(account);
    }


}
