package com.abc.account.service;

import com.abc.account.data.AccountEntity;
import com.abc.account.data.AccountRepository;
import com.abc.account.domain.Account;
import com.abc.account.exception.AccountException;
import com.abc.account.exception.AccountNotFoundException;
import com.abc.account.exception.DuplicateAccountException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            Iterable<AccountEntity> accountEntities = accountRepository.findAll();
            List<Account> accounts = new ArrayList<Account>();
            accountEntities.forEach(accountEntity -> accounts.add(new Account(accountEntity.getId(), accountEntity.getFirstName(), accountEntity.getSecondName(), accountEntity.getAccountNumber())));
            return accounts;
        } catch (Exception e) {
            throw new AccountException(e);
        }
    }

    public Long createAccount(Account account) throws AccountException {
        try {
            AccountEntity accountEntity = new AccountEntity(account.getFirstName(), account.getSecondName(), account.getAccountNumber());
            AccountEntity accountEntitySaved = accountRepository.save(accountEntity);
            return accountEntitySaved.getId();
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateAccountException(e);
        } catch (Exception e) {
            throw new AccountException(e);
        }
    }

    public void deleteAccount(Long accountId) throws AccountException {
        try {
            accountRepository.deleteById(accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new AccountNotFoundException(e);
        } catch (Exception e) {
            throw new AccountException(e);
        }
    }
}
