package com.spiet.account.cmd.api.commands;

import com.spiet.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class WithDrawFundsCommand extends BaseCommand {
    private Double amount;
}
