package com.spiet.account.query.domain;

import com.spiet.account.common.dto.AccountType;
import com.spiet.cqrs.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BankAccount extends BaseEntity {
    @Id
    private String id;

    private String accountHOlder;

    private Date creationDate;

    private AccountType accountType;

    private double balance;
}
