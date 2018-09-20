package com.abc.account.service;

import com.abc.account.domain.Account;
import com.abc.account.exception.AccountException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountService {
    public List<Account> findAllAccounts() throws AccountException {
        return null;
    }
}
