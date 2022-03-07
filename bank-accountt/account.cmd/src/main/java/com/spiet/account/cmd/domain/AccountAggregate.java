package com.spiet.account.cmd.domain;

import com.spiet.account.cmd.api.commands.OpenAccountComands;
import com.spiet.account.common.events.AccountClosedEvent;
import com.spiet.account.common.events.FundsDepositEvent;
import com.spiet.account.common.events.FundsWithDrawEvents;
import com.spiet.account.common.events.OpenAccountEvent;
import com.spiet.cqrs.core.domain.AggregatorRoot;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@NoArgsConstructor
public class AccountAggregate extends AggregatorRoot {
    private Boolean active;
    private double balance;

    public AccountAggregate(OpenAccountComands comands) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        raiseEvent(OpenAccountEvent.builder().accountType(comands.getAccountType())
                .id(comands.getId())
                .accountHolder(comands.getAccontHolder())
                .createdDate(new Date())
                .openingBalance(comands.getBalance()).build());
    }

    public void apply(OpenAccountEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFounds(double amount) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (!this.active) {
            throw new IllegalAccessException("Founds cannot be deposited into a closed account");
        }

        if (amount <= 0) {
            throw new IllegalStateException("should be greater than 0");
        }

        raiseEvent(FundsDepositEvent.builder()
                .id(this.id)
                .amount(amount)
                .build()
        );
    }

    public void apply(FundsDepositEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance += event.getAmount();
    }

    public void withDrawFunds(double amount) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (!this.active) {
            throw new IllegalAccessException("Founds cannot be deposited into a closed account");
        }

        if (amount <= 0) {
            throw new IllegalStateException("should be greater than 0");
        }

        raiseEvent(FundsWithDrawEvents.builder()
                .id(this.id)
                .amount(amount).build());
    }

    public void apply(FundsWithDrawEvents event) {
        this.id = event.getId();
        this.active = true;
        this.balance -= event.getAmount();
    }

    public  void  closeAccount() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (!this.active) {
            throw new IllegalAccessException("Bank account already be closed");
        }

        raiseEvent(AccountClosedEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
