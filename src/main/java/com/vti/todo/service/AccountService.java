package com.vti.todo.service;

import com.vti.todo.dto.request.RegisterAccountRequest;
import com.vti.todo.entity.AccountEntity;
import com.vti.todo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity registerNewAccount(RegisterAccountRequest registerAccountRequest) {
        AccountEntity account = new AccountEntity();
        account.setEmail(registerAccountRequest.getEmail());
        account.setFullName(registerAccountRequest.getFullName());
        account.setPassword(registerAccountRequest.getPassword());
        accountRepository.save(account);
        return account;
    }

}
