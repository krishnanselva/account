package com.abc.account.service;

import com.abc.account.data.AccountEntity;
import com.abc.account.data.AccountRepository;
import com.abc.account.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;


    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void findAllAccounts_returns3Accounts() throws Exception {
        final List<AccountEntity> accountEntities = new ArrayList<AccountEntity>();
        accountEntities.add(new AccountEntity("John", "Doe", "1234"));
        accountEntities.add(new AccountEntity("Jane", "Doe", "1235"));
        accountEntities.add(new AccountEntity("Jim", "Taylor", "1236"));
        given(accountRepository.findAll()).willReturn(accountEntities);
        List<Account> accounts = accountService.findAllAccounts();
        assertThat(accounts.size()).isEqualTo(3);
        assertThat(accounts.get(0).getFirstName()).isEqualTo(accountEntities.get(0).getFirstName());
        assertThat(accounts.get(0).getSecondName()).isEqualTo(accountEntities.get(0).getSecondName());
        assertThat(accounts.get(0).getAccountNumber()).isEqualTo(accountEntities.get(0).getAccountNumber());
    }
}
