package com.vti.todo.controller;

import com.vti.todo.dto.request.LoginRequest;
import com.vti.todo.dto.request.RegisterAccountRequest;
import com.vti.todo.dto.request.ResetPasswordRequest;
import com.vti.todo.dto.response.JwtResponse;
import com.vti.todo.entity.AccountEntity;
import com.vti.todo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    private Page<AccountEntity> searchAccount(Integer departmentId, String role, String search, Pageable pageable) {
        return accountService.searchAccount(departmentId, role, search, pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping
    public ResponseEntity<?> registerNewAccount(@RequestBody @Valid RegisterAccountRequest registerAccountRequest) {
        return accountService.registerNewAccount(registerAccountRequest);
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return accountService.login(loginRequest);
    }

    @GetMapping("/principal")
    public UserDetails principal(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @DeleteMapping
    public void getCurrentWorkspace(@RequestParam Set<Integer> id) {
        //TODO call delete account repository - hay bị lỗi constrain sql phải xoá account khồng có constrain đến bảng khác
        System.out.println(id); // -> thay bằng cách gọi đến repository để delete
    }

    @PostMapping("/forgot/{email}")
    public void forgotPassword(@PathVariable String email) {
        accountService.forgotPassword(email);
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        return accountService.resetPassword(request);
    }
}
