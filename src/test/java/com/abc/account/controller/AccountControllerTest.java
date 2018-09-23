package com.abc.account.controller;

import com.abc.account.Constants;
import com.abc.account.domain.Account;
import com.abc.account.exception.AccountException;
import com.abc.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.abc.account.Constants.REST_API_PATH;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

    @Test
    public void createAccount_returnMessage() throws Exception {
        given(accountService.createAccount(new Account("Steven", "Doe", "1238"))).willReturn(1L);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post(REST_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Steven\",\"secondName\": \"Doe\",\"accountNumber\": \"1238\"}");
        mockMvc.perform(builder).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.message").value(Constants.ACCOUNT_CREATED));

    }


    @Test
    public void deleteAccount_returnMessage() throws Exception {
        mockMvc.perform(delete(REST_API_PATH + "/1"))
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.message").value(Constants.ACCOUNT_DELETED));
    }


}
