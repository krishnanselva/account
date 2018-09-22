package com.abc.account;

import com.abc.account.data.AccountEntity;
import com.abc.account.data.AccountRepository;
import com.abc.account.domain.Account;
import com.abc.account.domain.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.abc.account.Constants.*;
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

    private String getBaseUrl() {
        return LOCAL_HOST + port + REST_API_PATH;
    }

    private void createAccounts() {
        accountRepository.deleteAll();
        List<AccountEntity> accountEntities = new ArrayList<AccountEntity>();
        accountEntities.add(new AccountEntity("John", "Doe", "1234"));
        accountEntities.add(new AccountEntity("Jane", "Doe", "1235"));
        accountEntities.add(new AccountEntity("Jim", "Taylor", "1236"));
        accountEntities.forEach(accountEntity -> accountRepository.save(accountEntity));
    }

    @Test
    public void shouldListAccounts() {
        createAccounts();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Account>> response = restTemplate.exchange(
                getBaseUrl(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Account>>() {
                });
        List<Account> accounts = response.getBody();
        assertEquals(3, accounts.size());
    }


    @Test
    public void shouldCreateAccount() {

        Account account = new Account("Will", "Jones", "456");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(account, headers);

        ResponseEntity<Message> response = template.exchange(
                getBaseUrl(),
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Message>() {
                });

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ACCOUNT_CREATED, response.getBody().getMessage());

    }

    @Test
    public void shouldDeleteAccount() {

        AccountEntity accountEntity = accountRepository.save(new AccountEntity("Will", "Jones", "456"));

        ResponseEntity<Message> response = template.exchange(
                getBaseUrl() + "/" + accountEntity.getId(),
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Message>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ACCOUNT_DELETED, response.getBody().getMessage());

    }
}
