package com.abc.account.data;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAllAccounts_returns3Accounts() throws Exception {
        accountRepository.deleteAll();
        entityManager.flush();
        List<AccountEntity> accountEntities = new ArrayList<AccountEntity>();
        accountEntities.add(new AccountEntity("John", "Doe", "1234"));
        accountEntities.add(new AccountEntity("Jane", "Doe", "1235"));
        accountEntities.add(new AccountEntity("Jim", "Taylor", "1236"));
        accountEntities.forEach(accountEntity -> entityManager.persistAndFlush(accountEntity));
        Iterable<AccountEntity> accountEntitiesIterable = accountRepository.findAll();
        List<AccountEntity> accountEntitiesSaved = new ArrayList<AccountEntity>();
        accountEntitiesIterable.forEach(accountEntity -> {
            accountEntitiesSaved.add(accountEntity);
            Assertions.assertThat(accountEntity.getId()).isNotNull();
            Assertions.assertThat(accountEntity.getId()).isNotZero();

        });
        assertEquals(accountEntitiesSaved.size(), 3);

    }


    @Test
    public void createAccount_returnsAccountId() throws Exception {
        AccountEntity accountEntity = new AccountEntity("Steven", "Doe", "12381");
        AccountEntity accountEntitySaved = entityManager.persistAndFlush(accountEntity);
        Assertions.assertThat(accountEntitySaved.getId()).isNotNull().isNotZero();
    }

}
