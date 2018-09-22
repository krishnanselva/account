package com.abc.account.service;

import com.abc.account.data.AccountEntity;
import com.abc.account.data.AccountRepository;
import com.abc.account.domain.Account;
import com.abc.account.exception.AccountException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAllAccounts() throws AccountException {
        Iterable<AccountEntity> accountEntities = accountRepository.findAll();
        List<Account> accounts = new ArrayList<Account>();
        accountEntities.forEach(accountEntity -> accounts.add(new Account(accountEntity.getId(), accountEntity.getFirstName(), accountEntity.getSecondName(), accountEntity.getAccountNumber())));
        return accounts;
    }

    public Long createAccount(Account account) {
        return null;
    }
}
