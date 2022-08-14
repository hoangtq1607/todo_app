package com.vti.todo.controller;

import com.vti.todo.dto.request.RegisterAccountRequest;
import com.vti.todo.entity.AccountEntity;
import com.vti.todo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public AccountEntity registerNewAccount(@RequestBody @Valid RegisterAccountRequest registerAccountRequest) {
        return accountService.registerNewAccount(registerAccountRequest);
    }

}
