package io.dbsys.OnlineBankingSystem.repository;

import io.dbsys.OnlineBankingSystem.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

    public Optional<Bank> findById(Integer id);

}
