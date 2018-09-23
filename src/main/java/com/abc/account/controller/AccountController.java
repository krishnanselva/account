package com.abc.account.controller;

import com.abc.account.Constants;
import com.abc.account.domain.Account;
import com.abc.account.domain.Message;
import com.abc.account.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-project/rest/account/json")
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Get All Accounts")
    @GetMapping
    public ResponseEntity<List<Account>> findAllAccounts() {
        logger.info("findAllAccounts");
        return new ResponseEntity<List<Account>>(accountService.findAllAccounts(), HttpStatus.OK);

    }

    @ApiOperation(value = "Create Account")
    @PostMapping
    public ResponseEntity<Message> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<Message>(new Message(Constants.ACCOUNT_CREATED), null, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete Account")
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Message> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return new ResponseEntity<Message>(new Message(Constants.ACCOUNT_DELETED), null, HttpStatus.OK);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) {
        logger.error(e.toString());
        logger.debug(e.toString(), e);
    }
}
