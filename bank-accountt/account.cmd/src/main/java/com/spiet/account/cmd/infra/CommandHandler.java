package com.spiet.account.cmd.infra;

import com.spiet.account.cmd.api.commands.CloseAccountCommand;
import com.spiet.account.cmd.api.commands.DepositFundsAccountCommand;
import com.spiet.account.cmd.api.commands.OpenAccountComands;
import com.spiet.account.cmd.api.commands.WithDrawFundsCommand;
import com.spiet.account.common.events.FundsWithDrawEvents;

import java.lang.reflect.InvocationTargetException;

public interface CommandHandler {
    void handle(OpenAccountComands comands) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
    void handle(DepositFundsAccountCommand command) throws Exception;
    void handle(WithDrawFundsCommand command);
    void handle(CloseAccountCommand command);
}
