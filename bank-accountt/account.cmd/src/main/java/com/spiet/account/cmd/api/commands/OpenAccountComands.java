package com.spiet.account.cmd.api.commands;

import com.spiet.account.common.dto.AccountType;
import com.spiet.cqrs.core.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenAccountComands extends BaseCommand {
    private String accontHolder;
    private AccountType accountType;
    private Double balance;
}
