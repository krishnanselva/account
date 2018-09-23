package com.abc.account;

import com.abc.account.controller.AccountController;
import com.abc.account.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(AccountApplication.class);

    @Autowired
    AccountController accountController;

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        accountController.createAccount((new Account("John", "Doe", "1234")));
        accountController.createAccount((new Account("Jane", "Doe", "1235")));
        accountController.createAccount((new Account("Jim", "Taylor", "1236")));
        logger.info("***Greetings from SARA***");
    }

}
