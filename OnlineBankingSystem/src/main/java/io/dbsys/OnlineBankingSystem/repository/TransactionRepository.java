package io.dbsys.OnlineBankingSystem.repository;

import io.dbsys.OnlineBankingSystem.entity.Account;
import io.dbsys.OnlineBankingSystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByAccount(Account sender);
}
