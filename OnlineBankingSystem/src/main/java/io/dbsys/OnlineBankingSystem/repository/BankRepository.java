package io.dbsys.OnlineBankingSystem.repository;

import io.dbsys.OnlineBankingSystem.dto.BankAccountCountDto;
import io.dbsys.OnlineBankingSystem.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

    public Optional<Bank> findById(Integer id);

    @Query("SELECT new io.dbsys.OnlineBankingSystem.dto.BankAccountCountDto(b.bankId, b.bankBranch, COUNT(a)) " +
            "FROM Bank b LEFT JOIN b.BankAccounts a GROUP BY b.bankId, b.bankBranch")
    List<BankAccountCountDto> getBankAccountCounts();


}
