package io.dbsys.OnlineBankingSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Bank {

    @Id
    private final int bankId;
    private final int bankCode;
    private final int bankBranch;
}
