package io.dbsys.OnlineBankingSystem.repository;

import io.dbsys.OnlineBankingSystem.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {


}
