package com.abc.account.service;

import com.abc.account.data.AccountRepository;
import com.abc.account.domain.Account;
import com.abc.account.exception.AccountException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public List<Account> findAllAccounts() throws AccountException {
        return null;
    }
}
