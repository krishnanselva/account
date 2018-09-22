package com.abc.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(AccountApplication.class);
/*    @Autowired
    private AccountRepository accountRepository;*/

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

/*        accountRepository.deleteAll();
        List<AccountEntity> accountEntities = new ArrayList<AccountEntity>();
        accountEntities.add(new AccountEntity("John", "Doe", "1234"));
        accountEntities.add(new AccountEntity("Jane", "Doe", "1235"));
        accountEntities.add(new AccountEntity("Jim", "Taylor", "1236"));
        logger.info("initial data loading");
        accountEntities.forEach(accountEntity -> {

            AccountEntity accountSaved = accountRepository.save(accountEntity);
            logger.info("created account id : " + accountSaved.getId());

        });
        logger.info("initial data loaded");
        logger.info("AccountApplication started");*/
    }

}
