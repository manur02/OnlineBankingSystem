package io.dbsys.OnlineBankingSystem.service;

import io.dbsys.OnlineBankingSystem.dto.*;
import io.dbsys.OnlineBankingSystem.entity.*;
import io.dbsys.OnlineBankingSystem.enums.*;
import io.dbsys.OnlineBankingSystem.repository.BankRepository;
import io.dbsys.OnlineBankingSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Bank addBank(BankDto bankDto, int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        if (employee.getEmployeeType() != EmployeeType.ADMIN) {
            throw new RuntimeException("Only Admins can add a bank");
        }

        Bank bank = new Bank();
        bank.setBankCode(bankDto.getBankCode());
        bank.setBankBranch(bankDto.getBankBranch());
        return bankRepository.save(bank);
    }



    public List<BankAccountCountDto> getAccountCountsPerBank() {
        return bankRepository.getBankAccountCounts();
    }


}
