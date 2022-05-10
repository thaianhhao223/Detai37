package com.example.detai37.controller;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.entity.Account;
import com.example.detai37.entity.Customer;
import com.example.detai37.request.account.CreateAccountRequest;
import com.example.detai37.request.account.LoginAccountRequest;
import com.example.detai37.request.account.UpdateAccountRequest;
import com.example.detai37.request.customer.CreateCustomerRequest;
import com.example.detai37.request.customer.UpdateCustomerRequest;
import com.example.detai37.request.customer.UpdateStatusCustomerRequest;
import com.example.detai37.service.AccountService;
import com.example.detai37.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/get-by-username/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> findById(@PathVariable("id") String userName) {
        return ResponseEntity.ok(accountService.findUserByUserName(userName));
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<Boolean> loginAccount(@RequestBody LoginAccountRequest loginAccountRequest) {
        return ResponseEntity.ok(accountService.loginAccount(loginAccountRequest));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Account> saveAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return ResponseEntity.ok(accountService.saveAccount(createAccountRequest));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Account> updateAccount(@RequestBody UpdateAccountRequest updateAccountRequest) {
        return ResponseEntity.ok(accountService.updateAccount(updateAccountRequest));
    }
}
