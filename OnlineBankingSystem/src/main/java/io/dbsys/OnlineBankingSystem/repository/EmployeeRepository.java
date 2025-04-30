package io.dbsys.OnlineBankingSystem.repository;

import io.dbsys.OnlineBankingSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}
