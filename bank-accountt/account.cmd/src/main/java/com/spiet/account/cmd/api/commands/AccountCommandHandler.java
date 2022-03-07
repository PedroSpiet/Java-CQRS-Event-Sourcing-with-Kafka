package com.spiet.account.cmd.api.commands;

import com.spiet.account.cmd.domain.AccountAggregate;
import com.spiet.account.cmd.infra.CommandHandler;
import com.spiet.cqrs.core.handlers.EventSourceHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;

public class AccountCommandHandler implements CommandHandler {

    @Autowired
    private EventSourceHandler<AccountAggregate> eventSourcing;

    @Override
    public void handle(OpenAccountComands comands) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        var aggregate = new AccountAggregate(comands);
        eventSourcing.save(aggregate);
    }

    @Override
    public void handle(DepositFundsAccountCommand command) throws Exception {
        var aggregate = eventSourcing.getById(command.getId());
        aggregate.depositFounds(command.getAmount());
        eventSourcing.save(aggregate);

    }

    @Override
    public void handle(WithDrawFundsCommand command) {

    }

    @Override
    public void handle(CloseAccountCommand command) {

    }
}
