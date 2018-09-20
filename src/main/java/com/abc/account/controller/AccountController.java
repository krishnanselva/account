package com.abc.account.controller;

import com.abc.account.domain.Account;
import com.abc.account.exception.AccountException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-project/rest/account/json")
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @ApiOperation(value = "Get All Accounts")
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
       return null;

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(AccountException ae) {
        logger.info(ae.toString(), ae);
    }
}
