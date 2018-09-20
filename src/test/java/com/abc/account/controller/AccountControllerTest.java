package com.abc.account.controller;

import com.abc.account.domain.Account;
import com.abc.account.exception.AccountException;
import com.abc.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.abc.account.Constants.REST_API_PATH;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void findAllAccounts_throwsException() throws Exception {
        given(accountService.findAllAccounts()).willThrow(new AccountException());
        mockMvc.perform(get(REST_API_PATH)).andExpect(status().is5xxServerError());
    }

    @Test
    public void findAllAccounts_returns3Accounts() throws Exception {
        final List<Account> accounts = new ArrayList<Account>();
        accounts.add(new Account("John", "Doe", "1234"));
        accounts.add(new Account("Jane", "Doe", "1235"));
        accounts.add(new Account("Jim", "Taylor", "1236"));
        given(accountService.findAllAccounts()).willReturn(accounts);
        mockMvc.perform(get(REST_API_PATH)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(accounts.get(0).getFirstName()))
                .andExpect(jsonPath("$[0].secondName").value(accounts.get(0).getSecondName()))
                .andExpect(jsonPath("$[0].accountNumber").value(accounts.get(0).getAccountNumber()));
    }

}
