package com.spiet.account.query.handlers;

import com.spiet.account.common.events.AccountClosedEvent;
import com.spiet.account.common.events.FundsDepositEvent;
import com.spiet.account.common.events.FundsWithDrawEvents;
import com.spiet.account.common.events.OpenAccountEvent;

public interface EventHandler {
    void on(OpenAccountEvent event);
    void on(AccountClosedEvent event);
    void on(FundsDepositEvent event);
    void on(FundsWithDrawEvents event);
}
