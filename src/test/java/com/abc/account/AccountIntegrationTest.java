package com.abc.account;

import com.abc.account.data.AccountEntity;
import com.abc.account.data.AccountRepository;
import com.abc.account.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountIntegrationTest {

    private static final String LOCAL_HOST =
            "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private AccountRepository accountRepository;

    private TestRestTemplate template = new TestRestTemplate();
    private List<AccountEntity> accountEntities = new ArrayList<AccountEntity>();

    private String getBaseUrl(String uri) {
        return LOCAL_HOST + port + uri;
    }

    @Before
    public void setup() throws IOException {
        createAccounts();
    }

    private void createAccounts() {
        accountEntities.add(new AccountEntity("John", "Doe", "1234"));
        accountEntities.add(new AccountEntity("Jane", "Doe", "1235"));
        accountEntities.add(new AccountEntity("Jim", "Taylor", "1236"));
        accountEntities.forEach(accountEntity -> accountRepository.save(accountEntity));
    }

    @Test
    public void shouldListAccounts() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Account>> response = restTemplate.exchange(
                getBaseUrl("/account-project/rest/account/json"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Account>>() {
                });
        List<Account> accounts = response.getBody();
        assertEquals(3, accounts.size());
    }
}
