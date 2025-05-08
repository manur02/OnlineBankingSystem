package io.dbsys.OnlineBankingSystem.controller;


import io.dbsys.OnlineBankingSystem.dto.BankAccountCountDto;
import io.dbsys.OnlineBankingSystem.dto.BankDto;
import io.dbsys.OnlineBankingSystem.entity.Bank;
import io.dbsys.OnlineBankingSystem.enums.EmployeeType;
import io.dbsys.OnlineBankingSystem.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @PostMapping("/add/{employeeId}")
    public Bank addBank(@RequestBody BankDto bankDto,
                        @PathVariable int employeeId) {
        return bankService.addBank(bankDto, employeeId);
    }

    @GetMapping("/account-counts")
    public ResponseEntity<List<BankAccountCountDto>> getAccountCounts() {
        List<BankAccountCountDto> counts = bankService.getAccountCountsPerBank();
        return ResponseEntity.ok(counts);
    }




}