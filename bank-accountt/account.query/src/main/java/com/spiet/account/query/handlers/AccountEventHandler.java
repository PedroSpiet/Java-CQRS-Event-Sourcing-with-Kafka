package com.spiet.account.query.handlers;

import com.spiet.account.common.events.AccountClosedEvent;
import com.spiet.account.common.events.FundsDepositEvent;
import com.spiet.account.common.events.FundsWithDrawEvents;
import com.spiet.account.common.events.OpenAccountEvent;
import com.spiet.account.query.domain.AccountRepository;
import com.spiet.account.query.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountEventHandler implements EventHandler{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void on(OpenAccountEvent event) {
        var bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHOlder(event.getAccountHolder())
                .creationDate(event.getCreatedDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance()).build();

        accountRepository.save(bankAccount);
    }

    @Override
    public void on(AccountClosedEvent event) {

    }

    @Override
    public void on(FundsDepositEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        var curretBalance = bankAccount.get().getBalance();

        var latestBalance = curretBalance + event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(FundsWithDrawEvents event) {
        var bankAccount = accountRepository.findById(event.getId());
        var curretBalance = bankAccount.get().getBalance();

        var latestBalance = curretBalance - event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        accountRepository.save(bankAccount.get());
    }
}
