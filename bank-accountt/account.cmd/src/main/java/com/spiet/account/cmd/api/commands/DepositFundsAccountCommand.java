package com.spiet.account.cmd.api.commands;

import com.spiet.cqrs.core.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositFundsAccountCommand extends BaseCommand {
    private Double amount;

    public DepositFundsAccountCommand(String id) {
        super(id);
    }
}
