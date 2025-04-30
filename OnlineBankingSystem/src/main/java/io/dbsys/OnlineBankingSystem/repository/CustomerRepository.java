package io.dbsys.OnlineBankingSystem.repository;

import io.dbsys.OnlineBankingSystem.dto.CustomerDto;
import io.dbsys.OnlineBankingSystem.entity.Bank;
import io.dbsys.OnlineBankingSystem.entity.Customer;
import io.dbsys.OnlineBankingSystem.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
       Boolean existsByEmail(String Email);
       Boolean existsByPhoneNumber(Long phoneNumber);

    Optional<Customer> findById(int id);

    List<Customer> findByCustomerBranchAndStatus(Bank customerBranch, AccountStatus status);


}
